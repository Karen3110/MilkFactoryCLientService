package ge.alpin.javakhet.milkfactory.commons.handlers;


import ge.alpin.javakhet.milkfactory.commons.model.ErrorResponse;
import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Component
@ControllerAdvice
public class ResponseExceptionHandler {



    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<ErrorResponse> handleException(ResponseException exception, HttpServletRequest webRequest) {
        ErrorResponse responseDto = new ErrorResponse();
        responseDto.setTimestamp(new Date());
        responseDto.setPath(webRequest.getServletPath());
        responseDto.setError(exception.getStatus().getReasonPhrase());
        responseDto.setStatus(exception.getStatus().value());
        responseDto.setMessage(exception.getMessage());
        return new ResponseEntity<>(responseDto, exception.getStatus());

    }


}
