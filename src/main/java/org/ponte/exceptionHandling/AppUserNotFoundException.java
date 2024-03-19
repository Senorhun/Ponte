package org.ponte.exceptionHandling;

public class AppUserNotFoundException extends RuntimeException {
    private final long appUserId;
    public AppUserNotFoundException(Long appUserId) {
        this.appUserId = appUserId;
    }

    public long getAppUserId() {
        return appUserId;
    }
}
