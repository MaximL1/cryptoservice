package com.crypto.cryptoproject.business.bo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.lang.NonNull;

import com.crypto.cryptoproject.business.dto.CryptoReportDto;
import com.crypto.cryptoproject.data.entity.CryptoCurrency;

public interface CryptoCurrencyBusinessObject {

    /**
     * Create new cryptocurrency.
     *
     * @param cryptoCurrency the object to create.
     * @return created CryptoCurrency.
     */
    @NonNull
    CryptoCurrency createCryptoCurrency(@NonNull CryptoCurrency cryptoCurrency);

    /**
     * Get all cryptocurrency from db.
     *
     * @return founded cryptocurrency.
     */
    @NonNull
    List<CryptoCurrency> getAllCurrency();

    /**
     * Get report oldest/newest/min/max from given cryptocurrency name
     *
     * @param cryptoName the cryptocurrency name
     */
    @NonNull
    CryptoReportDto getCurrencyReport(@NonNull String cryptoName);

    /**
     * Get the crypto with the highest normalized range for a
     * specific day
     *
     * @param dateTime the specific day.
     */
    @NonNull
    CryptoCurrency getCurrencyForSpecificDay(@NonNull LocalDateTime dateTime);

    /**
     * Delete all existing cryptocurrency by symbol
     *
     * @param symbol the cryptocurrency symbol
     */
    void deleteAllCryptoBySymbol(String symbol);
}
