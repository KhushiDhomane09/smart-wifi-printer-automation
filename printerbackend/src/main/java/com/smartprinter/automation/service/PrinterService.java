package com.smartprinter.automation.service;

import com.smartprinter.automation.dto.PrinterRequest;
import com.smartprinter.automation.dto.PrinterResponse;
import com.smartprinter.automation.entity.Printer;
import com.smartprinter.automation.exception.ResourceNotFoundException;
import com.smartprinter.automation.repository.PrinterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrinterService {

    private final PrinterRepository printerRepository;

    public PrinterResponse addPrinter(PrinterRequest req) {
        int port = (req.getPort() == null) ? 9100 : req.getPort();
        String protocol = (req.getProtocol() == null || req.getProtocol().isBlank()) ? "RAW" : req.getProtocol();

        Printer p = Printer.builder()
                .printerName(req.getPrinterName())
                .ipAddress(req.getIpAddress())
                .port(port)
                .protocol(protocol)
                .build();

        Printer saved = printerRepository.save(p);
        return toResponse(saved);
    }

    public List<PrinterResponse> getAll() {
        return printerRepository.findAll().stream().map(this::toResponse).toList();
    }

    public PrinterResponse update(Long id, PrinterRequest req) {
        Printer p = printerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Printer not found: " + id));

        if (req.getPrinterName() != null) p.setPrinterName(req.getPrinterName());
        if (req.getIpAddress() != null) p.setIpAddress(req.getIpAddress());
        if (req.getPort() != null) p.setPort(req.getPort());
        if (req.getProtocol() != null) p.setProtocol(req.getProtocol());

        return toResponse(printerRepository.save(p));
    }

    public void delete(Long id) {
        if (!printerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Printer not found: " + id);
        }
        printerRepository.deleteById(id);
    }

    public String checkStatus(Long id) {
        Printer p = printerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Printer not found: " + id));

        // Socket connect try
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(p.getIpAddress(), p.getPort()), 1500);
            return "ONLINE";
        } catch (Exception e) {
            return "OFFLINE";
        }
    }

    private PrinterResponse toResponse(Printer p) {
        return PrinterResponse.builder()
                .id(p.getId())
                .printerName(p.getPrinterName())
                .ipAddress(p.getIpAddress())
                .port(p.getPort())
                .protocol(p.getProtocol())
                .build();
    }
}