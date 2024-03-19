package org.ponte.exceptionHandling;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DuplicateEmailException extends RuntimeException {
    private final String email;
    public DuplicateEmailException(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
}
