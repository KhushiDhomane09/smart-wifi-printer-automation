package com.smartprinter.automation.service;

import com.smartprinter.automation.entity.PrintJob;
import com.smartprinter.automation.entity.Printer;
import com.smartprinter.automation.repository.PrintJobRepository;
import com.smartprinter.automation.repository.PrinterRepository;
import com.smartprinter.automation.service.printer.Raw9100PrinterClient;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrintQueueProcessor {

    private final PrintJobRepository jobRepo;
    private final PrinterRepository printerRepo;
    private final Raw9100PrinterClient rawClient;

    @Scheduled(fixedDelay = 5000)
    public void processQueue() {

        List<PrintJob> pendingJobs = jobRepo.findByStatus("PENDING");

        for (PrintJob job : pendingJobs) {
            try {
                job.setStatus("PRINTING");
                job.setErrorMessage(null);
                jobRepo.save(job);

                Printer printer = printerRepo.findById(job.getPrinterId())
                        .orElseThrow(() -> new RuntimeException("Printer not found"));

                if (printer.getActive() != null && !printer.getActive()) {
                    throw new RuntimeException("Printer inactive");
                }

                // REAL WiFi RAW print
                rawClient.print(printer, job.getFileData());

                job.setStatus("COMPLETED");
                jobRepo.save(job);

            } catch (Exception e) {
                job.setStatus("FAILED");
                job.setErrorMessage(e.getMessage());
                jobRepo.save(job);
            }
        }
    }
}
