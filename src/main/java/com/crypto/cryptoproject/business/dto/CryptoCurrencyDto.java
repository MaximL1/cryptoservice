package com.crypto.cryptoproject.business.dto;

import java.time.LocalDateTime;

public record CryptoCurrencyDto(LocalDateTime date,
                                String currencyName,
                                Double price) {
}
