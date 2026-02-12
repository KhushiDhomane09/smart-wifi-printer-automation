package com.smartprinter.automation.controller;

import com.smartprinter.automation.dto.PrinterRequest;
import com.smartprinter.automation.dto.PrinterResponse;
import com.smartprinter.automation.dto.StatusResponse;
import com.smartprinter.automation.service.PrinterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/printers")
@RequiredArgsConstructor
public class PrinterController {

    private final PrinterService printerService;

    @PostMapping
    public ResponseEntity<PrinterResponse> add(@RequestBody PrinterRequest req) {
        return ResponseEntity.ok(printerService.addPrinter(req));
    }

    @GetMapping
    public ResponseEntity<List<PrinterResponse>> all() {
        return ResponseEntity.ok(printerService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrinterResponse> update(@PathVariable Long id, @RequestBody PrinterRequest req) {
        return ResponseEntity.ok(printerService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        printerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<StatusResponse> status(@PathVariable Long id) {
        String st = printerService.checkStatus(id);
        return ResponseEntity.ok(StatusResponse.builder().status(st).build());
    }
}