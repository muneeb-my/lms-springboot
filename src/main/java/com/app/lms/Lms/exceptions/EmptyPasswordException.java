package com.app.lms.Lms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "You must include a password")
public class EmptyPasswordException extends RuntimeException {
}
