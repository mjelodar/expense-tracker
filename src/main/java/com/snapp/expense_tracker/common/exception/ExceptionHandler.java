package com.snapp.expense_tracker.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class ExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(value = RestApiException.class)
    public ResponseEntity<ExceptionResponse> handleMyRestTemplateException(RestApiException ex, HttpServletRequest request) {
        logger.error("Exception", ex);
        ExceptionResponse response = makeRestExceptionResponse(ex, request);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    private ExceptionResponse makeRestExceptionResponse(RestApiException exception,
                                                        HttpServletRequest request) {
        ExceptionResponse response = new ExceptionResponse();
        response.setMethod(request.getMethod());
        response.setLocale(LocaleContextHolder.getLocale().toString());
        response.setPath(request.getRequestURI());
        response.setStatusCode(exception.getStatusCode());
        String errorClassName = getErrorClassName(exception);
        response.setError(errorClassName);

        return response;
    }

    private String getErrorClassName(Throwable ex) {
        try {
            String[] exceptionClassNameArray = ex.getClass().getName().split("\\.");
            return exceptionClassNameArray[exceptionClassNameArray.length - 1];
        } catch (Exception e) {
            logger.error("Error in getErrorClassName()", e);
        }
        return null;
    }

    public static class ExceptionResponse {
        private Integer statusCode;
        private String error;
        private String path;
        private String method;
        private String locale;

        public Integer getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(Integer statusCode) {
            this.statusCode = statusCode;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getLocale() {
            return locale;
        }

        public void setLocale(String locale) {
            this.locale = locale;
        }
    }

}
