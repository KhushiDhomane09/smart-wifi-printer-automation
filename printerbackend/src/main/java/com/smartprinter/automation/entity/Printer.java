package com.smartprinter.automation.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "printers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Printer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String printerName;

    private String ipAddress;

    private int port;

    private String protocol;  // RAW / IPP
}
