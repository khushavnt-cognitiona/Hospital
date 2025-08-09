package com.yourshop.services.impl;

import com.yourshop.dto.vendor.VendorApplyRequest;
import com.yourshop.dto.vendor.VendorResponse;
import com.yourshop.entities.Role;
import com.yourshop.entities.User;
import com.yourshop.entities.Vendor;
import com.yourshop.exceptions.NotFoundException;
import com.yourshop.mappers.VendorMapper;
import com.yourshop.repositories.RoleRepository;
import com.yourshop.repositories.UserRepository;
import com.yourshop.repositories.VendorRepository;
import com.yourshop.services.VendorService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, UserRepository userRepository, RoleRepository roleRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    @Transactional
    public VendorResponse apply(VendorApplyRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
        Vendor vendor = Vendor.builder()
                .companyName(request.getCompanyName())
                .contact(request.getContact())
                .email(request.getEmail())
                .user(user)
                .approved(false)
                .build();
        return vendorMapper.toResponse(vendorRepository.save(vendor));
    }

    @Override
    @Transactional
    public VendorResponse approve(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow(() -> new NotFoundException("Vendor not found"));
        vendor.setApproved(true);
        // grant ROLE_VENDOR
        Role vendorRole = roleRepository.findByName("ROLE_VENDOR").orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_VENDOR").build()));
        User user = vendor.getUser();
        user.getRoles().add(vendorRole);
        userRepository.save(user);
        return vendorMapper.toResponse(vendorRepository.save(vendor));
    }
}