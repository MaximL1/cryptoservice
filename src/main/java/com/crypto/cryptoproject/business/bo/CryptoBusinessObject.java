package com.crypto.cryptoproject.business.bo;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Named;
import org.springframework.lang.NonNull;

import com.crypto.cryptoproject.data.entity.Crypto;

public interface CryptoBusinessObject {

    /**
     * Create new cryptocurrency symbol
     *
     * @param crypto the cryptocurrency symbol;
     */
    Crypto createCrypto(@NonNull String crypto);

    /**
     * Find all accepted cryptocurrency
     *
     * @return all cryptocurrency
     */
    @Named("allCrypto")
    List<Crypto> findAllCrypto();

    /**
     * Delete crypto for given id
     *
     * @param crypto the crypto id.
     */
    void deleteCrypto(Crypto crypto);

    /**
     * find existing crypto
     *
     * @param id
     * @return
     */
    Optional<Crypto> findById(Long id);
}
