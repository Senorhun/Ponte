package org.ponte.exceptionHandling;

public class ContactNotFoundException extends RuntimeException {
    private final Long contactId;

    public ContactNotFoundException(Long contactId) {
        this.contactId = contactId;
    }

    public Long getContactId() {
        return contactId;
    }
}
