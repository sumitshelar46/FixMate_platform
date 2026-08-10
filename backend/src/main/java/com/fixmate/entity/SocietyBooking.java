package com.fixmate.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "society_booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocietyBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "society_booking_id")
    private Long societyBookingId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceEntity service;

    @Column(name = "members_count")
    private Integer membersCount = 1;

    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    @Column(length = 20)
    private String status = "ACTIVE"; // ACTIVE, CONFIRMED, COMPLETED

    @Column(name = "society_name", nullable = false, length = 100)
    private String societyName;

    @Column(name = "discount_percentage")
    private Integer discountPercentage = 15;

    public SocietyBooking() {}

    public SocietyBooking(Long societyBookingId, User customer, ServiceEntity service, Integer membersCount, LocalDate bookingDate, String status, String societyName, Integer discountPercentage) {
        this.societyBookingId = societyBookingId;
        this.customer = customer;
        this.service = service;
        this.membersCount = membersCount;
        this.bookingDate = bookingDate;
        this.status = status;
        this.societyName = societyName;
        this.discountPercentage = discountPercentage;
    }

    public Long getSocietyBookingId() { return societyBookingId; }
    public void setSocietyBookingId(Long societyBookingId) { this.societyBookingId = societyBookingId; }

    public User getCustomer() { return customer; }
    public void setCustomer(User customer) { this.customer = customer; }

    public ServiceEntity getService() { return service; }
    public void setService(ServiceEntity service) { this.service = service; }

    public Integer getMembersCount() { return membersCount; }
    public void setMembersCount(Integer membersCount) { this.membersCount = membersCount; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getSocietyName() { return societyName; }
    public void setSocietyName(String societyName) { this.societyName = societyName; }

    public Integer getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(Integer discountPercentage) { this.discountPercentage = discountPercentage; }
}
