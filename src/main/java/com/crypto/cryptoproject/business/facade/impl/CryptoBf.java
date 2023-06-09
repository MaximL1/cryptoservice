package com.crypto.cryptoproject.business.facade.impl;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.crypto.cryptoproject.business.bo.CryptoBusinessObject;
import com.crypto.cryptoproject.business.bo.CryptoCurrencyBusinessObject;
import com.crypto.cryptoproject.business.dto.CryptoCurrencyDto;
import com.crypto.cryptoproject.business.dto.CryptoDto;
import com.crypto.cryptoproject.business.dto.CryptoReportDto;
import com.crypto.cryptoproject.business.facade.CryptoFacade;
import com.crypto.cryptoproject.business.mapper.CryptoCurrencyMapper;
import com.crypto.cryptoproject.data.entity.Crypto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CryptoBf implements CryptoFacade {

    private final CryptoCurrencyBusinessObject cryptoCurrencyBo;
    private final CryptoBusinessObject cryptoBo;
    private final CryptoCurrencyMapper cryptoMapper;

    @Override
    public List<CryptoCurrencyDto> getSortedAndNormalizedCrypto() {
        return cryptoMapper.toCryptoCurrencyDtos(cryptoCurrencyBo.getAllCurrency());
    }

    @Override
    public CryptoReportDto getCryptoReport(String cryptoName) {
        return cryptoCurrencyBo.getCurrencyReport(cryptoName);
    }

    @Override
    public CryptoCurrencyDto getCurrencyForSpecificDay(String dateTime) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(dateTime.concat(" 00:00:00 AM"), formatter);
        } catch (Exception e) {
            throw new InvalidParameterException(String.format("The dateTime format mismatch 'yyyy-MM-dd' %s", dateTime));
        }
        return cryptoMapper.toCryptoCurrencyDto(cryptoCurrencyBo.getCurrencyForSpecificDay(localDateTime));
    }

    @Override
    public CryptoDto createCrypto(@NonNull String crypto) {
        if (isBlank(crypto)) {
            throw new InvalidParameterException("New crypto can't be empty");
        }
        return cryptoMapper.toCryptoDto(cryptoBo.createCrypto(crypto));
    }

    @Override
    @Transactional
    public void deleteCrypto(Long id) {
        Crypto crypto = cryptoBo.findById(id)
                .orElseThrow(() -> new InvalidParameterException(String.format("For the given id: %d crypto not found", id)));
        cryptoBo.deleteCrypto(crypto);
        cryptoCurrencyBo.deleteAllCryptoBySymbol(crypto.getSymbol());
    }
}
