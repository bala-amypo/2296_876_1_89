package com.example.demo.service.impl;

import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {
 
    private final VendorRepository repo;

    public VendorServiceImpl(VendorRepository repo) {
        this.repo = repo;
    }

    public Vendor createVendor(Vendor vendor) {
        return repo.save(vendor);
    }

    public Vendor getVendor(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public List<Vendor> getAllVendors() {
        return repo.findAll();
    }
}