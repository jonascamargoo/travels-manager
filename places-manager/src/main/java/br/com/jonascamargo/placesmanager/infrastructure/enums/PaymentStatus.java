package br.com.jonascamargo.placesmanager.infrastructure.enums;

public enum PaymentStatus {
    PENDING,
    COMPLETED,
    CANCELED,
    FAILED;

    // private String paymentStatus;


    // private PaymentStatus (String status) {
    //     this.paymentStatus = status;

    // }

    // private PaymentStatus() {}

    // public static PaymentStatus fromString(String statusString) {
    //     for (PaymentStatus status : PaymentStatus.values()) {
    //         if (status.toString().equalsIgnoreCase(statusString)) {
    //             return status;
    //         }
    //     }
    //     throw new IllegalArgumentException("Invalid PaymentStatus: " + statusString);
    // }

    // @Override
    // public String toString() {
    //     return name().toLowerCase();
    // }
}