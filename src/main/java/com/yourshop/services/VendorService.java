package com.yourshop.services;

import com.yourshop.dto.vendor.VendorApplyRequest;
import com.yourshop.dto.vendor.VendorResponse;

public interface VendorService {
    VendorResponse apply(VendorApplyRequest request);
    VendorResponse approve(Long vendorId);
}