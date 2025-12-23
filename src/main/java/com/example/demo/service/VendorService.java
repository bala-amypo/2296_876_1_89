package com.example.demo.service;

import com.example.demo.dto.VendorDto;

import java.util.List;

public interface VendorService {
    VendorDto createVendor(VendorDto vendorDto);
    List<VendorDto> getAllVendors();
}
