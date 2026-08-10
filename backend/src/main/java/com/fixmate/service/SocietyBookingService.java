package com.fixmate.service;

import com.fixmate.dto.SocietyBookingRequestDTO;
import com.fixmate.dto.SocietyBookingResponseDTO;
import java.util.List;

public interface SocietyBookingService {
    List<SocietyBookingResponseDTO> getAllSocietyBookings();
    List<SocietyBookingResponseDTO> getCustomerSocietyBookings(Long customerId);
    SocietyBookingResponseDTO getSocietyBookingById(Long id);
    SocietyBookingResponseDTO createSocietyBooking(SocietyBookingRequestDTO requestDTO);
    SocietyBookingResponseDTO joinSocietyBooking(Long id, Long customerId);
}
