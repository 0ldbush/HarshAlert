package com.alnt.access.user.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import com.alnt.access.user.domain.User;
import com.alnt.access.user.domain.dto.PasswordChangeDTO;
import com.alnt.access.user.domain.dto.UserDTO;
import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.response.ApiResponse;
import com.alnt.platform.base.service.BaseService;
import com.google.inject.ImplementedBy;

@ImplementedBy(UserServiceImpl.class)
public interface UserService extends BaseService<User, UserDTO> {
	
	public CompletionStage<Optional<UserDTO>> getUserByName(RequestDetails requestDetails, String username);
	
	public CompletionStage<ApiResponse> changePassword(RequestDetails requestDetails,PasswordChangeDTO passwordChangeDTO);
	
	public CompletionStage<ApiResponse> resetPassword(RequestDetails requestDetails);
	
	public CompletionStage<ApiResponse> approveResetPasswordRequest(RequestDetails requestDetails,List<UserDTO> userDTOs);

}
