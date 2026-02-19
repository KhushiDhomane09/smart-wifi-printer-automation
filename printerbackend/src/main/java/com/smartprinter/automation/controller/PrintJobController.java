package com.smartprinter.automation.controller;

import com.smartprinter.automation.entity.PrintJob;
import com.smartprinter.automation.repository.PrintJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/printjobs")
@RequiredArgsConstructor
public class PrintJobController {

    private final PrintJobRepository repo;

    @PostMapping
    public PrintJob upload(@RequestParam Long printerId,
                           @RequestParam MultipartFile file) throws Exception {

        PrintJob job = new PrintJob();
        job.setPrinterId(printerId);
        job.setFileName(file.getOriginalFilename());
        job.setContentType(file.getContentType());
        job.setFileData(file.getBytes());
        job.setStatus("PENDING");

        return repo.save(job);
    }

    @GetMapping
    public List<PrintJob> all() {
        return repo.findAll();
    }
}
