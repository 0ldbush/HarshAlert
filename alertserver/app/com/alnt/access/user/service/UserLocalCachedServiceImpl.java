package com.alnt.access.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.inject.Named;
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
import com.alnt.platform.base.service.BaseLocalCachedServiceImpl;
import com.alnt.platform.modules.PlatformModule;

import play.cache.AsyncCacheApi;
import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class UserLocalCachedServiceImpl extends BaseLocalCachedServiceImpl<User, UserDTO> implements UserService {

	@Inject
	@Named(PlatformModule.LOCAL_CACHE)
	UserService userService;
	
	@Inject
	public UserLocalCachedServiceImpl(AsyncCacheApi caceApi, HttpExecutionContext ec, UserRepository repository) {
		super(caceApi, "User", ec, repository, UserMapper.INSTANCE);
	}

	@Override
	public CompletionStage<Optional<UserDTO>> getUserByName(RequestDetails requestDetails, String username) {
		return cache.getOrElseUpdate(cacheKeyPrefix+"_getUserByName_"+username,() -> {
			return ((UserRepository) this.getDaoRepository()).getUserByName(requestDetails, username)
				.thenApplyAsync(optionalData -> {
					return Optional.of(getMapper().entityToDTO(optionalData.get()));
				}, ec.current());
		});
	}

	@Override
	public CompletionStage<ApiResponse> changePassword(RequestDetails requestDetails,
			PasswordChangeDTO passwordChangeDTO) {
		return userService.changePassword(requestDetails, passwordChangeDTO);
	}

	@Override
	public CompletionStage<ApiResponse> resetPassword(RequestDetails requestDetails) {
		return userService.resetPassword(requestDetails);
	}

	@Override
	public CompletionStage<ApiResponse> approveResetPasswordRequest(RequestDetails requestDetails,
			List<UserDTO> userDTOs) {
		return userService.approveResetPasswordRequest(requestDetails, userDTOs);
				
	}

}
