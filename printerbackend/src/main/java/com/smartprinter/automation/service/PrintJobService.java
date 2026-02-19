package com.smartprinter.automation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.smartprinter.automation.entity.PrintJob;
import com.smartprinter.automation.repository.PrintJobRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrintJobService {

    private final PrintJobRepository repository;

    public PrintJob createJob(PrintJob job) {
        job.setStatus("PENDING");
        return repository.save(job);
    }

    public List<PrintJob> getAllJobs() {
        return repository.findAll();
    }

    public PrintJob getJob(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }
}
