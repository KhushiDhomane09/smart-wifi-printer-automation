package com.smartprinter.automation.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PrinterResponse {
    private Long id;
    private String printerName;
    private String ipAddress;
    private Integer port;
    private String protocol;
}