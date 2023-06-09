package com.crypto.cryptoproject.business.mapper;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.crypto.cryptoproject.business.dto.CryptoCsvDto;
import com.crypto.cryptoproject.business.dto.CryptoCurrencyDto;
import com.crypto.cryptoproject.business.dto.CryptoDto;
import com.crypto.cryptoproject.data.entity.Crypto;
import com.crypto.cryptoproject.data.entity.CryptoCurrency;

@Mapper(imports = ZoneOffset.class)
public interface CryptoCurrencyMapper {

    @Mapping(target = "date", expression = "java(formatDate(csvRow.getDate()))")
    CryptoCurrency toCryptoCurrency(CryptoCsvDto csvRow);

    @Mapping(target = "date", expression = "java(LocalDateTime.ofInstant(cryptoCurrency.getDate(), ZoneOffset.UTC))")
    @Mapping(target = "currencyName", source = "symbol")
    CryptoCurrencyDto toCryptoCurrencyDto(CryptoCurrency cryptoCurrency);

    List<CryptoCurrencyDto> toCryptoCurrencyDtos(List<CryptoCurrency> allCurrency);

    default Instant formatDate(BigDecimal bigDecimal) {
        return Instant.ofEpochMilli(bigDecimal.toBigInteger().longValue());
    }

    CryptoDto toCryptoDto(Crypto crypto);
}
