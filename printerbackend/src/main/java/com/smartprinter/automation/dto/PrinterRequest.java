package com.smartprinter.automation.dto;

import lombok.Data;

@Data
public class PrinterRequest {
    private String printerName;
    private String ipAddress;
    private Integer port;     // if null -> 9100
    private String protocol;
}