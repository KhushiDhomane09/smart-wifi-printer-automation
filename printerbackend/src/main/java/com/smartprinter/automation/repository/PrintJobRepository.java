package com.smartprinter.automation.repository;

import com.smartprinter.automation.entity.PrintJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrintJobRepository extends JpaRepository<PrintJob, Long> {
    List<PrintJob> findByStatus(String status);
}
