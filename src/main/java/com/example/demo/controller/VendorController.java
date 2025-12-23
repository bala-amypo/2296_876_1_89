package com.example.demo.controller;

import com.example.demo.dto.VendorDto;
import com.example.demo.service.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping
    public ResponseEntity<VendorDto> createVendor(@RequestBody VendorDto vendorDto) {
        return ResponseEntity.ok(vendorService.createVendor(vendorDto));
    }

    @GetMapping
    public ResponseEntity<List<VendorDto>> getAllVendors() {
        return ResponseEntity.ok(vendorService.getAllVendors());
    }
}
