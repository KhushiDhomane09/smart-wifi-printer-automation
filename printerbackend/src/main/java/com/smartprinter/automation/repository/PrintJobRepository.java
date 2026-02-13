package com.smartprinter.automation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartprinter.automation.entity.PrintJob;
import java.util.List;

public interface PrintJobRepository extends JpaRepository<PrintJob, Long> {

    List<PrintJob> findByStatus(String status);
}
