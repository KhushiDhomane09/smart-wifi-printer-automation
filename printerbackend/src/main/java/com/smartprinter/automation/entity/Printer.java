package com.smartprinter.automation.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "printers")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Printer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String printerName;

    @Column(nullable=false)
    private String ipAddress;

    @Column(nullable=false)
    private Integer port;   // default 9100

    @Column(nullable=false)
    private String protocol; // "RAW" (start with this)
}