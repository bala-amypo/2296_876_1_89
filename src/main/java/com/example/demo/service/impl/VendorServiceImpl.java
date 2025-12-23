package com.example.demo.service.impl;

import com.example.demo.dto.VendorDto;
import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    private VendorDto mapToDto(Vendor vendor) {
        VendorDto dto = new VendorDto();
        dto.setId(vendor.getId());
        dto.setVendorName(vendor.getVendorName());
        dto.setContactEmail(vendor.getContactEmail());
        dto.setAddress(vendor.getAddress());
        dto.setCreatedAt(vendor.getCreatedAt());
        return dto;
    }

    private Vendor mapToEntity(VendorDto dto) {
        Vendor vendor = new Vendor();
        vendor.setVendorName(dto.getVendorName());
        vendor.setContactEmail(dto.getContactEmail());
        vendor.setAddress(dto.getAddress());
        return vendor;
    }

    @Override
    public VendorDto createVendor(VendorDto vendorDto) {
        Vendor vendor = mapToEntity(vendorDto);
        Vendor saved = vendorRepository.save(vendor);
        return mapToDto(saved);
    }

    @Override
    public List<VendorDto> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
