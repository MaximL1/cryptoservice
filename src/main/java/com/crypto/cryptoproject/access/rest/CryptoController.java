package com.crypto.cryptoproject.access.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.crypto.cryptoproject.access.rest.api.CryptoApi;
import com.crypto.cryptoproject.business.dto.CryptoCurrencyDto;
import com.crypto.cryptoproject.business.dto.CryptoReportDto;
import com.crypto.cryptoproject.business.facade.CryptoFacade;
import com.crypto.cryptoproject.utility.CsvService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CryptoController implements CryptoApi {

    private final CsvService csvService;
    private final CryptoFacade cryptoFacade;

    @Override
    public ResponseEntity<Void> importCurrencyFromCsv(MultipartFile file) {
        csvService.importFromCsv(file);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<CryptoCurrencyDto>> getAllCurrency() {
        return ResponseEntity.ok(cryptoFacade.getSortedAndNormalizedCrypto());
    }

    @Override
    public ResponseEntity<CryptoReportDto> getCurrencyReport(String cryptoName) {
        return ResponseEntity.ok(cryptoFacade.getCryptoReport(cryptoName));
    }

    @Override
    public ResponseEntity<CryptoCurrencyDto> getCurrencyForSpecificDay(String dateTime) {
        return ResponseEntity.ok(cryptoFacade.getCurrencyForSpecificDay(dateTime));
    }

    @Override
    public ResponseEntity<CryptoCurrencyDto> createCrypto(String crypto) {
        cryptoFacade.createCrypto(crypto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteCrypto(Long id) {
        cryptoFacade.deleteCrypto(id);
        return ResponseEntity.noContent().build();
    }
}
