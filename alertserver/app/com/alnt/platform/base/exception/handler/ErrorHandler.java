package com.alnt.platform.base.exception.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Singleton;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.alnt.platform.base.exception.BaseBusinessException;
import com.alnt.platform.base.exception.type.ErrorType;
import com.alnt.platform.base.response.ApiMessage;
import com.alnt.platform.base.response.ApiResponse;

import play.http.HttpErrorHandler;
import play.libs.Json;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import play.mvc.Results;

@Singleton
public class ErrorHandler implements HttpErrorHandler {
  public CompletionStage<Result> onClientError(
      RequestHeader request, int statusCode, String message) {
    return CompletableFuture.completedFuture(
        Results.status(statusCode, "A client error occurred: " + message));
  }

  public CompletionStage<Result> onServerError(RequestHeader request, Throwable exception) {
	  exception.printStackTrace();
	  String stacktrace = ExceptionUtils.getStackTrace(exception);
	  List<ApiMessage> messages = new ArrayList<ApiMessage>();
	  ApiMessage message = null;
	  if(exception.getCause() instanceof BaseBusinessException) {
		  BaseBusinessException be = (BaseBusinessException)exception.getCause();
		  message = ErrorType.toApiMessage(be.getErrorType());
	  } else {
		  message = ErrorType.toApiMessage(ErrorType.GENERIC_SERVER_ERROR, stacktrace);
	  }
	  messages.add(message);
	  return CompletableFuture.completedFuture(
        Results.internalServerError(Json.toJson(new ApiResponse(Boolean.FALSE, null, messages))));
  }
}
