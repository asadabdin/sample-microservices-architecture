package org.asad.customer.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
@Component("ServiceExceptionHandlerAdvice")
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFound(NotFoundException e) {
        log.warn("Encountered not found exception while processing request, responding with [{}]: [{}]",
                NOT_FOUND, e.getMessage());
        return e.getMessage();
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e) {
        log.error("Internal server error occurred: [{}]", e.getMessage(), e);
        return e.getMessage();
    }

    @ResponseBody
    @ResponseStatus(CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolationException(HttpServletResponse response, DataIntegrityViolationException e) {
        log.error("Data integrity violation exception, responding with [{}]: {}", CONFLICT, e.getMostSpecificCause().getMessage());
        return e.getMostSpecificCause().getMessage();
    }

}
