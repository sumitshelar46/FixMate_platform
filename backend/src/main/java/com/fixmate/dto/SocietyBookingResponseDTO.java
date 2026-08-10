package com.fixmate.dto;

import com.fixmate.entity.SocietyBooking;
import java.time.LocalDate;

public class SocietyBookingResponseDTO {

    private Long societyBookingId;
    private UserDTO customer;
    private ServiceDTO service;
    private String societyName;
    private Integer membersCount;
    private LocalDate bookingDate;
    private String status;
    private Integer discountPercentage;

    public SocietyBookingResponseDTO() {}

    public SocietyBookingResponseDTO(Long societyBookingId, UserDTO customer, ServiceDTO service, String societyName, Integer membersCount, LocalDate bookingDate, String status, Integer discountPercentage) {
        this.societyBookingId = societyBookingId;
        this.customer = customer;
        this.service = service;
        this.societyName = societyName;
        this.membersCount = membersCount;
        this.bookingDate = bookingDate;
        this.status = status;
        this.discountPercentage = discountPercentage;
    }

    public static SocietyBookingResponseDTO fromEntity(SocietyBooking societyBooking) {
        if (societyBooking == null) return null;
        return new SocietyBookingResponseDTO(
                societyBooking.getSocietyBookingId(),
                UserDTO.fromEntity(societyBooking.getCustomer()),
                ServiceDTO.fromEntity(societyBooking.getService()),
                societyBooking.getSocietyName(),
                societyBooking.getMembersCount(),
                societyBooking.getBookingDate(),
                societyBooking.getStatus(),
                societyBooking.getDiscountPercentage()
        );
    }

    public Long getSocietyBookingId() { return societyBookingId; }
    public void setSocietyBookingId(Long societyBookingId) { this.societyBookingId = societyBookingId; }

    public UserDTO getCustomer() { return customer; }
    public void setCustomer(UserDTO customer) { this.customer = customer; }

    public ServiceDTO getService() { return service; }
    public void setService(ServiceDTO service) { this.service = service; }

    public String getSocietyName() { return societyName; }
    public void setSocietyName(String societyName) { this.societyName = societyName; }

    public Integer getMembersCount() { return membersCount; }
    public void setMembersCount(Integer membersCount) { this.membersCount = membersCount; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(Integer discountPercentage) { this.discountPercentage = discountPercentage; }
}
