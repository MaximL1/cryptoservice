package com.crypto.cryptoproject.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crypto.cryptoproject.data.entity.Crypto;

@Repository
public interface CryptoRepository extends JpaRepository<Crypto, Long> {
}
