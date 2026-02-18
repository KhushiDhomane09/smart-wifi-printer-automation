package com.smartprinter.automation.repository;

import com.smartprinter.automation.entity.Printer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrinterRepository extends JpaRepository<Printer, Long> {
}