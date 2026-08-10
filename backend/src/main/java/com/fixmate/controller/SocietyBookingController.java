package com.fixmate.controller;
import com.fixmate.dto.SocietyBookingRequestDTO;
import com.fixmate.dto.SocietyBookingResponseDTO;
import com.fixmate.service.SocietyBookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/society-bookings")
@CrossOrigin(origins = "*")
public class SocietyBookingController {
    @Autowired
    private SocietyBookingService societyBookingService;
    @GetMapping
    public ResponseEntity<List<SocietyBookingResponseDTO>> getAllSocietyBookings() {
        return ResponseEntity.ok(societyBookingService.getAllSocietyBookings());
    }
    @GetMapping("/{id}")
    public ResponseEntity<SocietyBookingResponseDTO> getSocietyBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(societyBookingService.getSocietyBookingById(id));
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<SocietyBookingResponseDTO>> getCustomerSocietyBookings(@PathVariable Long customerId) {
        return ResponseEntity.ok(societyBookingService.getCustomerSocietyBookings(customerId));
    }
    @PostMapping
    public ResponseEntity<SocietyBookingResponseDTO> createSocietyBooking(@Valid @RequestBody SocietyBookingRequestDTO requestDTO) {
        SocietyBookingResponseDTO created = societyBookingService.createSocietyBooking(requestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @PostMapping("/{id}/join")
    public ResponseEntity<SocietyBookingResponseDTO> joinSocietyBooking(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "1") Long customerId) {
        SocietyBookingResponseDTO updated = societyBookingService.joinSocietyBooking(id, customerId);
        return ResponseEntity.ok(updated);
    }
}

