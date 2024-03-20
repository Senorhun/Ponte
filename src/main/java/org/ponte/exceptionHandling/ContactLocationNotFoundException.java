package org.ponte.exceptionHandling;

public class ContactLocationNotFoundException extends RuntimeException {
    private final Long ContactLocationId;

    public ContactLocationNotFoundException(Long contactLocationId) {
        ContactLocationId = contactLocationId;
    }

    public Long getContactLocationId() {
        return ContactLocationId;
    }
}
