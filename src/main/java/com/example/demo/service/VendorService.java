package com.example.demo.service;

import com.example.demo.dto.;

import java.util.List;

public interface VendorService {
    VendorDto createVendor(VendorDto vendorDto);
    List<VendorDto> getAllVendors();
}
