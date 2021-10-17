package com.app.lms.Lms.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ExceptionHandlers {

    private Logger log = LoggerFactory.getLogger(ExceptionHandlers.class);

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleUserNotFoundException(final UserNotFoundException ex) {
        log.error("Contact not found thrown");
        return new ErrorResponse("CONTACT_NOT_FOUND", "The contact was not found");
    }

    @ExceptionHandler(EmptyPasswordException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorResponse handleEmptyPasswordException(final EmptyPasswordException ex) {
        log.error("username missing information thrown");
        return new ErrorResponse("MISSING_INFORMATION", "You must include a password");
    }
    
    @ExceptionHandler(UserMissingInformationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorResponse handleUsernameMissingInformationException(final UserMissingInformationException ex) {
        log.error("username missing information thrown");
        return new ErrorResponse("MISSING_INFORMATION", "You must include a username");
    }
    
    @ExceptionHandler(UsernameExistsException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    @ResponseBody
    public ErrorResponse handleUsernameExistsException(final UsernameExistsException ex) {
        log.error("username missing information thrown");
        return new ErrorResponse("NOT_UNIQUE", "Username already exists");
    }
    @ExceptionHandler(IncorectCredientialsException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ErrorResponse handleIncorectCredientialsException(final IncorectCredientialsException ex) {
        log.error("incorrect login credientials");
        return new ErrorResponse("INCORRECT_CREDIENTIALS", "Incorrect Username or Password");
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ErrorResponse handleThrowable(final Throwable ex) {
        log.error("Unexpected Error", ex);
        return new ErrorResponse("INTERNAL_SERVER_ERROR", "An unexpected internal server error occurred");
    }



}
