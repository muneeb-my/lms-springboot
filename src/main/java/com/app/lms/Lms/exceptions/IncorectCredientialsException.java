package com.app.lms.Lms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Incorrect username or password")
public class IncorectCredientialsException extends RuntimeException {
}
