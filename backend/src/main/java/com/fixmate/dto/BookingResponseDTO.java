package com.fixmate.dto;

import com.fixmate.entity.Booking;
import java.time.LocalDateTime;

public class BookingResponseDTO {

    private Long bookingId;
    private UserDTO customer;
    private ProviderDTO provider;
    private ServiceDTO service;
    private LocalDateTime bookingDate;
    private String status;
    private Boolean emergencyFlag;
    private String address;

    public BookingResponseDTO() {}

    public BookingResponseDTO(Long bookingId, UserDTO customer, ProviderDTO provider, ServiceDTO service, LocalDateTime bookingDate, String status, Boolean emergencyFlag, String address) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.provider = provider;
        this.service = service;
        this.bookingDate = bookingDate;
        this.status = status;
        this.emergencyFlag = emergencyFlag;
        this.address = address;
    }

    public static BookingResponseDTO fromEntity(Booking booking) {
        if (booking == null) return null;
        return new BookingResponseDTO(
                booking.getBookingId(),
                UserDTO.fromEntity(booking.getCustomer()),
                ProviderDTO.fromEntity(booking.getProvider()),
                ServiceDTO.fromEntity(booking.getService()),
                booking.getBookingDate(),
                booking.getStatus(),
                booking.getEmergencyFlag(),
                booking.getAddress()
        );
    }

    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }

    public UserDTO getCustomer() { return customer; }
    public void setCustomer(UserDTO customer) { this.customer = customer; }

    public ProviderDTO getProvider() { return provider; }
    public void setProvider(ProviderDTO provider) { this.provider = provider; }

    public ServiceDTO getService() { return service; }
    public void setService(ServiceDTO service) { this.service = service; }

    public LocalDateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Boolean getEmergencyFlag() { return emergencyFlag; }
    public void setEmergencyFlag(Boolean emergencyFlag) { this.emergencyFlag = emergencyFlag; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
