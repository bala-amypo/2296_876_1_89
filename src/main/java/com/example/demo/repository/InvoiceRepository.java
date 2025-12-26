package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.*;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByUploadedBy(User user);

    @Query("SELECT i FROM Invoice i WHERE i.amount > :amount")
    List<Invoice> findByAmountGreaterThanHql(Double amount);
}
