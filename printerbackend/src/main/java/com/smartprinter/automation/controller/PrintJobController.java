package com.smartprinter.automation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.smartprinter.automation.entity.PrintJob;
import com.smartprinter.automation.service.PrintJobService;

import java.util.List;

@RestController
@RequestMapping("/api/printjobs")
@RequiredArgsConstructor
public class PrintJobController {

    private final PrintJobService service;

    @PostMapping
    public PrintJob uploadFile(
            @RequestParam Long printerId,
            @RequestParam MultipartFile file) throws Exception {

        PrintJob job = new PrintJob();
        job.setPrinterId(printerId);
        job.setFileName(file.getOriginalFilename());
        job.setContentType(file.getContentType());
        job.setFileData(file.getBytes());

        return service.createJob(job);
    }

    @GetMapping
    public List<PrintJob> getAll() {
        return service.getAllJobs();
    }

    @GetMapping("/{id}")
    public PrintJob getJob(@PathVariable Long id) {
        return service.getJob(id);
    }
}
