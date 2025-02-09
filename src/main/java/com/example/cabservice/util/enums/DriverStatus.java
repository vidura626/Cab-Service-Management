package com.example.cabservice.util.enums;

public enum DriverStatus {
    AVAILABLE("Available"),
    BUSY("Busy");

    private final String status;

    DriverStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static DriverStatus fromString(String status) {
        for (DriverStatus statusEnum : DriverStatus.values()) {
            if (statusEnum.getStatus().equalsIgnoreCase(status)) {
                return statusEnum;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + status);
    }
}
