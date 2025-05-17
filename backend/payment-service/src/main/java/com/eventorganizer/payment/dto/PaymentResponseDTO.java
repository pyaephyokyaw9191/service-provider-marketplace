package com.eventorganizer.payment.dto;

public class PaymentResponseDTO {
    private Long paymentId;
    private String status;
    private String receipt;

    public PaymentResponseDTO(Long paymentId, String status, String receipt) {
        this.paymentId = paymentId;
        this.status = status;
        this.receipt = receipt;
    }

    // Getters and setters

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }
}
