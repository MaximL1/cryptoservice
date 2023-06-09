package com.crypto.cryptoproject.business.services;

import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crypto.cryptoproject.business.bo.CryptoBusinessObject;
import com.crypto.cryptoproject.business.bo.CryptoCurrencyBusinessObject;
import com.crypto.cryptoproject.business.dto.CryptoCsvDto;
import com.crypto.cryptoproject.business.mapper.CryptoCurrencyMapper;
import com.crypto.cryptoproject.data.entity.Crypto;
import com.crypto.cryptoproject.data.entity.CryptoCurrency;
import com.crypto.cryptoproject.utility.AbstractCsvService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CryptoCsvService extends AbstractCsvService {

    private final CryptoCurrencyMapper cryptoCurrencyMapper;
    private final CryptoCurrencyBusinessObject cryptoCurrencyBo;
    private final CryptoBusinessObject cryptoBo;

    @Override
    protected void importCsvRow(CryptoCsvDto csvRow) {
        CryptoCurrency cryptoCurrency = cryptoCurrencyMapper.toCryptoCurrency(csvRow);
        Optional.ofNullable(cryptoBo.findAllCrypto())
                .orElse(Collections.emptyList())
                .stream()
                .map(Crypto::getSymbol)
                .filter(x -> x.equals(cryptoCurrency.getSymbol()))
                .findFirst()
                .orElseThrow(() -> new InvalidParameterException(
                        String.format("The cryptocurrency: %s is not supported", cryptoCurrency.getSymbol())));
        cryptoCurrencyBo.createCryptoCurrency(cryptoCurrency);
    }
}
