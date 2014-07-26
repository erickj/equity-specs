package io.vos.equity.webapp.api;

import com.google.common.base.Preconditions;

import io.vos.equity.model.Model;

final class ApiResponseWrapper<T> {

  private static final String ANONYMOUS_TYPE = "_anonymous_";
  private static final String ERROR_TYPE = "error";

  private final T result;
  private final String type;
  private final String errorMessage;

  ApiResponseWrapper(T result, String type) {
    this(result, type, null /* errorMessage */);
  }

  ApiResponseWrapper(T result, String type, String errorMessage) {
    this.result = result;
    this.type = type.toLowerCase();
    this.errorMessage = errorMessage;
  }

  static <T> ApiResponseWrapper<T> create(T result) {
    Preconditions.checkNotNull(result);
    String type = result.getClass().getSimpleName();
    if (type.isEmpty()) {
      type = ANONYMOUS_TYPE;
    }
    return new ApiResponseWrapper<T>(result, type);
  }


  static <T extends Exception> ApiResponseWrapper<T> createError(T error) {
    Preconditions.checkNotNull(error);
    return new ApiResponseWrapper<T>(null /* result */, ERROR_TYPE, error.getMessage());
  }
}
