package com.crypto.cryptoproject.business.bo.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.crypto.cryptoproject.business.bo.CryptoBusinessObject;
import com.crypto.cryptoproject.data.entity.Crypto;
import com.crypto.cryptoproject.data.repository.CryptoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CryptoBo implements CryptoBusinessObject {

    private final CryptoRepository cryptoRepository;

    @Override
    public Crypto createCrypto(@NonNull String symbol) {
        Crypto crypto = new Crypto();
        crypto.setSymbol(symbol);
        return cryptoRepository.save(crypto);
    }

    @Override
    @Cacheable(cacheNames = "allCrypto", cacheManager = "requestScopedCacheManager")
    public List<Crypto> findAllCrypto() {
        return cryptoRepository.findAll();
    }

    @Override
    public void deleteCrypto(Crypto crypto) {
        cryptoRepository.delete(crypto);
    }

    @Override
    public Optional<Crypto> findById(Long id) {
        return cryptoRepository.findById(id);
    }
}
