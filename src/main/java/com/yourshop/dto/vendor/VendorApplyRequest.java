package com.yourshop.dto.vendor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VendorApplyRequest {
    @NotBlank
    private String companyName;
    private String contact;
    @Email
    @NotBlank
    private String email;
}