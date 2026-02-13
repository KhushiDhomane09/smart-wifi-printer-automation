package com.smartprinter.automation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.smartprinter.automation.entity.PrintJob;
import com.smartprinter.automation.repository.PrintJobRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrintQueueProcessor {

    private final PrintJobRepository repository;

    @Scheduled(fixedDelay = 5000) // every 5 seconds
    public void processQueue() {

        List<PrintJob> pendingJobs = repository.findByStatus("PENDING");

        for (PrintJob job : pendingJobs) {
            try {
                job.setStatus("PRINTING");
                repository.save(job);

                // Simulate printing (Module 6 me real printing hoga)
                Thread.sleep(2000);

                job.setStatus("COMPLETED");
                repository.save(job);

            } catch (Exception e) {
                job.setStatus("FAILED");
                repository.save(job);
            }
        }
    }
}
