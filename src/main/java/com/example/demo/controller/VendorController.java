 
package com.example.demo.controller;

import com.example.demo.entity.Vendor;
import com.example.demo.service.VendorService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService service;

    public VendorController(VendorService service) {
        this.service = service;
    }

    @PostMapping
    public Vendor create(@RequestBody Vendor vendor) {
        return service.createVendor(vendor);
    }

    @GetMapping
    public List<Vendor> list() {
        return service.getAllVendors();
    }
}
