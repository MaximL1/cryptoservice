package com.crypto.cryptoproject.business.dto;

import java.math.BigDecimal;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CryptoCsvDto {

    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "timestamp")
    private BigDecimal date;

    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "symbol")
    private String symbol;

    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "price")
    private Double price;
}
