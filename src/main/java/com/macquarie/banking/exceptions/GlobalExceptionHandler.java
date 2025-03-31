package com.macquarie.banking.exceptions;

import com.macquarie.banking.account.exceptions.NoAccountFoundException;
import com.macquarie.banking.transaction.exceptions.NoTransactionFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler({NoAccountFoundException.class, NoTransactionFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCustom4xxRuntimeException(final RuntimeException ex) {
        return new ErrorResponse(
                Integer.toString(HttpStatus.BAD_REQUEST.value()),
                "Runtime exception",
                ex instanceof NoAccountFoundException ? "Account not found" : "Transaction not found"
        );
    }
}
