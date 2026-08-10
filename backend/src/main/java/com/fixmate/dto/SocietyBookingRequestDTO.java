package com.fixmate.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class SocietyBookingRequestDTO {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Service ID is required")
    private Long serviceId;

    @NotBlank(message = "Society name is required")
    private String societyName;

    private LocalDate bookingDate;

    private Integer membersCount;

    private Integer discountPercentage;

    public SocietyBookingRequestDTO() {}

    public SocietyBookingRequestDTO(Long customerId, Long serviceId, String societyName, LocalDate bookingDate, Integer membersCount, Integer discountPercentage) {
        this.customerId = customerId;
        this.serviceId = serviceId;
        this.societyName = societyName;
        this.bookingDate = bookingDate;
        this.membersCount = membersCount;
        this.discountPercentage = discountPercentage;
    }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public Long getServiceId() { return serviceId; }
    public void setServiceId(Long serviceId) { this.serviceId = serviceId; }

    public String getSocietyName() { return societyName; }
    public void setSocietyName(String societyName) { this.societyName = societyName; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }

    public Integer getMembersCount() { return membersCount; }
    public void setMembersCount(Integer membersCount) { this.membersCount = membersCount; }

    public Integer getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(Integer discountPercentage) { this.discountPercentage = discountPercentage; }
}
