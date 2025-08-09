package com.yourshop.dto.vendor;

import lombok.Data;

@Data
public class VendorResponse {
    private Long id;
    private String companyName;
    private String contact;
    private String email;
    private Long userId;
    private boolean approved;
}