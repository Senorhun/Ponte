package org.ponte.exceptionHandling;

public class UserEmailNotFoundException extends RuntimeException {
    private final String email;
    public UserEmailNotFoundException(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
}
