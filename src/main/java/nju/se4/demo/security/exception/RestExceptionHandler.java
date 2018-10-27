package nju.se4.demo.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * 异常处理器
 *
 * @author xuxiangzhe
 */
@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public RestErrorInformation nfHandler(NotFoundException e) {
        return new RestErrorInformation(e.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidInputException.class)
    public RestErrorInformation invalidInputHandler(InvalidInputException e) {
        return new RestErrorInformation(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public RestErrorInformation messageErrorHandler(HttpMessageNotReadableException e) {
        return new RestErrorInformation(e.getMessage());
    }


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(TokenErrorException.class)
    public RestErrorInformation tokenErrorHandler(TokenErrorException e) {
        return new RestErrorInformation(e.getMessage());
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler(InvalidOperationException.class)
    public RestErrorInformation invalidOperationHandler(InvalidOperationException e) {
        return new RestErrorInformation(e.getMessage());
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler(SQLException.class)
    public RestErrorInformation sqlErrorHandler(SQLException e) {
        e.printStackTrace();
        return new RestErrorInformation(e.getMessage());
    }

}