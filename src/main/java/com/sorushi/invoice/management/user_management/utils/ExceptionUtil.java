package com.sorushi.invoice.management.user_management.utils;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

@Slf4j
public class ExceptionUtil {

  private ExceptionUtil() {}

  public static ProblemDetail buildProblemDetail(
      HttpStatus httpStatus,
      String errorCode,
      Object[] errorMessageArgument,
      MessageSource messageSource) {

    if (Objects.isNull(httpStatus)) {
      log.debug("HttpStatus is null");
      httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    if (Objects.isNull(messageSource)) {
      log.debug("MessageSource is null");
      return ProblemDetail.forStatus(httpStatus);
    } else if (Objects.isNull(errorCode)) {
      log.debug("ErrorCode is null");
      return ProblemDetail.forStatus(httpStatus);
    } else {
      String errorMessage =
          messageSource.getMessage(
              errorCode, errorMessageArgument, LocaleContextHolder.getLocale());
      ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, errorCode);
      problemDetail.setProperty("ERROR_DETAILS", errorCode + " : " + errorMessage);
      return problemDetail;
    }
  }
}
