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
    private String printerName;   // e.g. "Brother DCP-T520W"

    @Column(nullable=false)
    private String ipAddress;     // e.g. "192.168.1.50"

    @Column(nullable=false)
    private Integer port;         // usually 9100

    @Column(nullable=false)
    private String protocol;      // "RAW9100"

    @Column(nullable=false)
    private Boolean active;       // true
}