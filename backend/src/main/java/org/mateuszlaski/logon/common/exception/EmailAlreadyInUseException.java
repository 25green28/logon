package org.mateuszlaski.logon.common.exception;

public class EmailAlreadyInUseException extends RuntimeException {
    public EmailAlreadyInUseException(String email) {
        super("Email '%s' is already in use".formatted(email));
    }
}
