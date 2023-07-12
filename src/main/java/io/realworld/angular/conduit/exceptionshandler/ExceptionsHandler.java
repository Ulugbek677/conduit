package io.realworld.angular.conduit.exceptionshandler;

import io.realworld.angular.conduit.customexseptions.EmailAlreadyRegisteredException;
import io.realworld.angular.conduit.customexseptions.NoResourceFoundException;
import io.realworld.angular.conduit.customexseptions.UsernameAlreadyRegisteredException;
import io.realworld.angular.conduit.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto notFoundHandler(NoResourceFoundException ex){
        ex.printStackTrace();
        return ErrorDto.builder().errors(Map.of("error ",List.of(ex.getMessage()))).build();
    }
    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDto emailAlreadyRegisteredHandler(EmailAlreadyRegisteredException ex){
        ex.printStackTrace();
        return ErrorDto.builder().errors(Map.of("email ",List.of(ex.getMessage()))).build();
    }

    @ExceptionHandler(UsernameAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDto usernameAlreadyRegisteredHandler(UsernameAlreadyRegisteredException ex){
        ex.printStackTrace();
        return ErrorDto.builder().errors(Map.of("username ",List.of(ex.getMessage()))).build();
    }
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto notFoundHandler(Throwable ex){
        ex.printStackTrace();
        return ErrorDto.builder().errors(Map.of("error ",List.of(ex.getMessage()))).build();
    }
}
