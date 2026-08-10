package com.fixmate.service.impl;

import com.fixmate.dto.BookingRequestDTO;
import com.fixmate.dto.BookingResponseDTO;
import com.fixmate.entity.Booking;
import com.fixmate.entity.BookingStatus;
import com.fixmate.entity.Provider;
import com.fixmate.entity.ServiceEntity;
import com.fixmate.entity.User;
import com.fixmate.exception.BadRequestException;
import com.fixmate.exception.ResourceNotFoundException;
import com.fixmate.repository.BookingRepository;
import com.fixmate.repository.ProviderRepository;
import com.fixmate.repository.ServiceRepository;
import com.fixmate.repository.UserRepository;
import com.fixmate.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponseDTO> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(BookingResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponseDTO> getCustomerBookings(Long customerId) {
        return bookingRepository.findByCustomerUserId(customerId)
                .stream()
                .map(BookingResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponseDTO> getProviderBookings(Long providerId) {
        return bookingRepository.findByProviderProviderId(providerId)
                .stream()
                .map(BookingResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BookingResponseDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "id", id));
        return BookingResponseDTO.fromEntity(booking);
    }

    @Override
    public BookingResponseDTO createBooking(BookingRequestDTO requestDTO) {
        // 1. Validate Customer
        User customer = userRepository.findById(requestDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer (User)", "id", requestDTO.getCustomerId()));

        // 2. Validate Service
        ServiceEntity service = serviceRepository.findById(requestDTO.getServiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Service", "id", requestDTO.getServiceId()));

        // 3. Provider Resolution & Emergency Logic
        Provider provider = null;
        boolean isEmergency = Boolean.TRUE.equals(requestDTO.getEmergencyFlag());

        if (requestDTO.getProviderId() != null) {
            provider = providerRepository.findById(requestDTO.getProviderId())
                    .orElseThrow(() -> new ResourceNotFoundException("Provider", "id", requestDTO.getProviderId()));
        } else if (isEmergency) {
            // Auto-assign available and verified provider for Emergency Booking
            provider = providerRepository.findByVerificationStatus("VERIFIED")
                    .stream()
                    .filter(p -> Boolean.TRUE.equals(p.getIsAvailable()))
                    .findFirst()
                    .orElseThrow(() -> new BadRequestException("No available verified provider found for emergency dispatch. Please try again shortly."));
        } else {
            throw new BadRequestException("Provider ID is required for standard non-emergency bookings");
        }

        // 4. Validate Booking Status
        String statusStr = requestDTO.getStatus();
        if (statusStr == null || statusStr.trim().isEmpty()) {
            statusStr = "REQUESTED";
        }
        if (!BookingStatus.isValid(statusStr)) {
            throw new BadRequestException("Invalid booking status: " + statusStr + ". Allowed values: REQUESTED, ACCEPTED, IN_PROGRESS, COMPLETED, CANCELLED, REJECTED");
        }

        // 5. Create Entity
        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setProvider(provider);
        booking.setService(service);
        booking.setBookingDate(requestDTO.getBookingDate() != null ? requestDTO.getBookingDate() : LocalDateTime.now());
        booking.setStatus(BookingStatus.fromString(statusStr).name());
        booking.setEmergencyFlag(isEmergency);
        booking.setAddress(requestDTO.getAddress());

        Booking savedBooking = bookingRepository.save(booking);
        return BookingResponseDTO.fromEntity(savedBooking);
    }

    @Override
    public BookingResponseDTO updateBookingStatus(Long id, String statusStr) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "id", id));

        if (!BookingStatus.isValid(statusStr)) {
            throw new BadRequestException("Invalid booking status: " + statusStr + ". Allowed values: REQUESTED, ACCEPTED, IN_PROGRESS, COMPLETED, CANCELLED, REJECTED");
        }

        booking.setStatus(BookingStatus.fromString(statusStr).name());
        Booking updatedBooking = bookingRepository.save(booking);
        return BookingResponseDTO.fromEntity(updatedBooking);
    }
}
