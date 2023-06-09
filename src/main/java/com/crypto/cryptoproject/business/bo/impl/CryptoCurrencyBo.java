package com.crypto.cryptoproject.business.bo.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.crypto.cryptoproject.business.bo.CryptoCurrencyBusinessObject;
import com.crypto.cryptoproject.business.dto.CryptoReportDto;
import com.crypto.cryptoproject.business.mapper.ReportMapper;
import com.crypto.cryptoproject.data.entity.CryptoCurrency;
import com.crypto.cryptoproject.data.repository.CryptoCurrencyRepository;
import com.crypto.cryptoproject.data.repository.CryptoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CryptoCurrencyBo implements CryptoCurrencyBusinessObject {

    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final CryptoRepository cryptoRepository;

    @Override
    @NonNull
    public CryptoCurrency createCryptoCurrency(@NonNull CryptoCurrency cryptoCurrency) {
        return cryptoCurrencyRepository.save(cryptoCurrency);
    }

    @Override
    @NonNull
    public List<CryptoCurrency> getAllCurrency() {
        return cryptoCurrencyRepository.findByNormalizedOrder();
    }

    @Override
    public CryptoReportDto getCurrencyReport(@NonNull String cryptoName) {
        Object reportView = cryptoCurrencyRepository.generateReport(cryptoName);
        return ReportMapper.getDto(reportView);
    }

    @Override
    public CryptoCurrency getCurrencyForSpecificDay(@NonNull LocalDateTime dateTime) {
        return cryptoCurrencyRepository.findByNormalizedSpecificDay(dateTime.toInstant(ZoneOffset.UTC));
    }

    @Override
    public void deleteAllCryptoBySymbol(String symbol) {
        cryptoCurrencyRepository.deleteAllBySymbol(symbol);
    }
}
