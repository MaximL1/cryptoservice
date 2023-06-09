package com.crypto.cryptoproject.business.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CryptoReportDto {
    private LocalDateTime oldest;
    private LocalDateTime newest;
    private Double min;
    private Double max;
}

