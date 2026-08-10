package com.fixmate.service.impl;

import com.fixmate.dto.SocietyBookingRequestDTO;
import com.fixmate.dto.SocietyBookingResponseDTO;
import com.fixmate.entity.ServiceEntity;
import com.fixmate.entity.SocietyBooking;
import com.fixmate.entity.User;
import com.fixmate.exception.ResourceNotFoundException;
import com.fixmate.repository.ServiceRepository;
import com.fixmate.repository.SocietyBookingRepository;
import com.fixmate.repository.UserRepository;
import com.fixmate.service.SocietyBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SocietyBookingServiceImpl implements SocietyBookingService {

    @Autowired
    private SocietyBookingRepository societyBookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SocietyBookingResponseDTO> getAllSocietyBookings() {
        return societyBookingRepository.findAll()
                .stream()
                .map(SocietyBookingResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SocietyBookingResponseDTO> getCustomerSocietyBookings(Long customerId) {
        return societyBookingRepository.findByCustomerUserId(customerId)
                .stream()
                .map(SocietyBookingResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public SocietyBookingResponseDTO getSocietyBookingById(Long id) {
        SocietyBooking sb = societyBookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Society Booking", "id", id));
        return SocietyBookingResponseDTO.fromEntity(sb);
    }

    @Override
    public SocietyBookingResponseDTO createSocietyBooking(SocietyBookingRequestDTO requestDTO) {
        User customer = userRepository.findById(requestDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer (User)", "id", requestDTO.getCustomerId()));

        ServiceEntity service = serviceRepository.findById(requestDTO.getServiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Service", "id", requestDTO.getServiceId()));

        SocietyBooking sb = new SocietyBooking();
        sb.setCustomer(customer);
        sb.setService(service);
        sb.setSocietyName(requestDTO.getSocietyName());
        sb.setBookingDate(requestDTO.getBookingDate() != null ? requestDTO.getBookingDate() : LocalDate.now().plusDays(7));
        sb.setMembersCount(requestDTO.getMembersCount() != null ? requestDTO.getMembersCount() : 1);
        sb.setStatus("ACTIVE");
        sb.setDiscountPercentage(requestDTO.getDiscountPercentage() != null ? requestDTO.getDiscountPercentage() : 15);

        SocietyBooking saved = societyBookingRepository.save(sb);
        return SocietyBookingResponseDTO.fromEntity(saved);
    }

    @Override
    public SocietyBookingResponseDTO joinSocietyBooking(Long id, Long customerId) {
        SocietyBooking sb = societyBookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Society Booking", "id", id));

        // Increment joined residents count
        int updatedCount = (sb.getMembersCount() != null ? sb.getMembersCount() : 0) + 1;
        sb.setMembersCount(updatedCount);

        // Dynamic Group Discount Engine: 1-5 members = 15%, 6-12 members = 20%, 13+ members = 25%
        if (updatedCount >= 13) {
            sb.setDiscountPercentage(25);
        } else if (updatedCount >= 6) {
            sb.setDiscountPercentage(20);
        } else {
            sb.setDiscountPercentage(15);
        }

        SocietyBooking updated = societyBookingRepository.save(sb);
        return SocietyBookingResponseDTO.fromEntity(updated);
    }
}
