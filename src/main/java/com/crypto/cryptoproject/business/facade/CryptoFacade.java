package com.crypto.cryptoproject.business.facade;

import java.util.List;

import com.crypto.cryptoproject.business.dto.CryptoCurrencyDto;
import com.crypto.cryptoproject.business.dto.CryptoDto;
import com.crypto.cryptoproject.business.dto.CryptoReportDto;

public interface CryptoFacade {
    List<CryptoCurrencyDto> getSortedAndNormalizedCrypto();

    CryptoReportDto getCryptoReport(String cryptoName);

    CryptoCurrencyDto getCurrencyForSpecificDay(String dateTime);

    CryptoDto createCrypto(String crypto);

    void deleteCrypto(Long id);
}
