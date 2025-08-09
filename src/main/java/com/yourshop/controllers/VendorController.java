package com.yourshop.controllers;

import com.yourshop.dto.vendor.VendorApplyRequest;
import com.yourshop.dto.vendor.VendorResponse;
import com.yourshop.services.VendorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping("/apply")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<VendorResponse> apply(@Valid @RequestBody VendorApplyRequest request) {
        return ResponseEntity.ok(vendorService.apply(request));
    }

    @PostMapping("/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VendorResponse> approve(@PathVariable Long id) {
        return ResponseEntity.ok(vendorService.approve(id));
    }
}