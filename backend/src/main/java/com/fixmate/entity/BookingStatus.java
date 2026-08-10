package com.fixmate.entity;

public enum BookingStatus {
    REQUESTED,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED,
    REJECTED;

    public static boolean isValid(String statusStr) {
        if (statusStr == null) return false;
        for (BookingStatus status : values()) {
            if (status.name().equalsIgnoreCase(statusStr.trim())) {
                return true;
            }
        }
        return false;
    }

    public static BookingStatus fromString(String statusStr) {
        for (BookingStatus status : values()) {
            if (status.name().equalsIgnoreCase(statusStr.trim())) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid booking status: " + statusStr + ". Allowed values: REQUESTED, ACCEPTED, IN_PROGRESS, COMPLETED, CANCELLED, REJECTED");
    }
}
