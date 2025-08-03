package com.sorushi.invoice.management.user_management.exception;

import com.sorushi.invoice.management.user_management.utils.ExceptionUtil;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

public class UserManagementException extends ErrorResponseException {

  public UserManagementException(
      HttpStatus status,
      String messageDetailCode,
      Object[] messageDetailArguments,
      MessageSource messageSource,
      Throwable cause) {
    super(
        status,
        ExceptionUtil.buildProblemDetail(
            status, messageDetailCode, messageDetailArguments, messageSource),
        cause,
        messageDetailCode,
        messageDetailArguments);
  }
}
