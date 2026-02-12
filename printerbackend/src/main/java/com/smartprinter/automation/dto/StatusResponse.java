package com.smartprinter.automation.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class StatusResponse {
    private String status; // ONLINE / OFFLINE
}