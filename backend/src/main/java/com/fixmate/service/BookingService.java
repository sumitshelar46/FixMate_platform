package com.fixmate.service;

import com.fixmate.dto.BookingRequestDTO;
import com.fixmate.dto.BookingResponseDTO;
import java.util.List;

public interface BookingService {
    List<BookingResponseDTO> getAllBookings();
    List<BookingResponseDTO> getCustomerBookings(Long customerId);
    List<BookingResponseDTO> getProviderBookings(Long providerId);
    BookingResponseDTO getBookingById(Long id);
    BookingResponseDTO createBooking(BookingRequestDTO requestDTO);
    BookingResponseDTO updateBookingStatus(Long id, String status);
}
