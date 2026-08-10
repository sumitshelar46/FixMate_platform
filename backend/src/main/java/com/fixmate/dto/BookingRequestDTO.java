package com.fixmate.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
public class BookingRequestDTO {
    @NotNull(message = "Customer ID is required")
    private Long customerId;
    private Long providerId;
    @NotNull(message = "Service ID is required")
    private Long serviceId;
    private LocalDateTime bookingDate;
    private String status;
    private Boolean emergencyFlag;
    @NotBlank(message = "Address is required")
    private String address;
    public BookingRequestDTO() {}
    public BookingRequestDTO(Long customerId, Long providerId, Long serviceId, LocalDateTime bookingDate, String status, Boolean emergencyFlag, String address) {
        this.customerId = customerId;
        this.providerId = providerId;
        this.serviceId = serviceId;
        this.bookingDate = bookingDate;
        this.status = status;
        this.emergencyFlag = emergencyFlag;
        this.address = address;
    }
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public Long getProviderId() { return providerId; }
    public void setProviderId(Long providerId) { this.providerId = providerId; }
    public Long getServiceId() { return serviceId; }
    public void setServiceId(Long serviceId) { this.serviceId = serviceId; }
    public LocalDateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Boolean getEmergencyFlag() { return emergencyFlag; }
    public void setEmergencyFlag(Boolean emergencyFlag) { this.emergencyFlag = emergencyFlag; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}