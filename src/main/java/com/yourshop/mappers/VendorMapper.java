package com.yourshop.mappers;

import com.yourshop.dto.vendor.VendorResponse;
import com.yourshop.entities.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VendorMapper {
    @Mapping(target = "userId", source = "user.id")
    VendorResponse toResponse(Vendor vendor);
}