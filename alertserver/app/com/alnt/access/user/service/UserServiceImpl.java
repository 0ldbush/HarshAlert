package com.alnt.access.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.access.user.domain.User;
import com.alnt.access.user.domain.dto.PasswordChangeDTO;
import com.alnt.access.user.domain.dto.UserDTO;
import com.alnt.access.user.mapper.UserMapper;
import com.alnt.access.user.repository.UserRepository;
import com.alnt.platform.application.security.hashing.HashUtils;
import com.alnt.platform.base.exception.BaseBusinessException;
import com.alnt.platform.base.exception.type.ErrorType;
import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.response.ApiResponse;
import com.alnt.platform.base.service.BaseServiceImpl;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class UserServiceImpl extends BaseServiceImpl<User, UserDTO> implements UserService {

	@Inject
	public UserServiceImpl(HttpExecutionContext ec, UserRepository repository) {
		super(ec, repository, UserMapper.INSTANCE);
	}

	@Override
	public CompletionStage<Optional<UserDTO>> getUserByName(RequestDetails requestDetails, String username) {
		return ((UserRepository) this.getDaoRepository()).getUserByName(requestDetails, username)
				.thenApplyAsync(optionalData -> {
					return Optional.of(getMapper().entityToDTO(optionalData.get()));
				}, ec.current());
	}

	@Override
	public CompletionStage<ApiResponse> changePassword(RequestDetails requestDetails,
			PasswordChangeDTO passwordChangeDTO) {
		boolean validPassword = HashUtils.verify(passwordChangeDTO.getPassword(),
				requestDetails.getUser().getPassword());
		if (!validPassword) {
			throw new BaseBusinessException(ErrorType.INVALID_PASSWORD);
		}
		requestDetails.getUser().setPassword(HashUtils.hash(passwordChangeDTO.getNewPassword()));
		return this.save(requestDetails, requestDetails.getUser()).thenApplyAsync(optional1 -> {
			return new ApiResponse(Boolean.TRUE, null, null);
		}, ec.current());
	}

	@Override
	public CompletionStage<ApiResponse> resetPassword(RequestDetails requestDetails) {
		requestDetails.getUser().setResetPassword(true);
		return this.save(requestDetails, requestDetails.getUser()).thenApplyAsync(optional1 -> {
			return new ApiResponse(Boolean.TRUE, null, null);
		}, ec.current());
	}

	@Override
	public CompletionStage<ApiResponse> approveResetPasswordRequest(RequestDetails requestDetails,
			List<UserDTO> userDTOs) {
		List<User> usersToBeReset = resetPasswordUsers(requestDetails, userDTOs);
		return this.getDaoRepository().saveAll(requestDetails, usersToBeReset).thenApplyAsync(optional1 -> {
			return new ApiResponse(Boolean.TRUE, null, null);
		}, ec.current());
	}

	private List<User> resetPasswordUsers(RequestDetails requestDetails, List<UserDTO> userDTOs) {

		List<User> entityList = new ArrayList<>();

		userDTOs.forEach(userDTO -> {
			CompletionStage<Optional<User>> completionStage =getDaoRepository().get(requestDetails, userDTO.getId());
			try {
				Optional<User> user = completionStage.toCompletableFuture().get();
				if (user.isPresent()) {
					user.get().setPassword(getPasswordToReset(user.get()));
					user.get().setResetPassword(false);
					entityList.add(user.get());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return entityList;
	}
	private String getPasswordToReset(User user) {
		return HashUtils.hash(user.getUserName());
	}

}
