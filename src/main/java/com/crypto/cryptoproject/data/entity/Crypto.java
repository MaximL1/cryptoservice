package com.crypto.cryptoproject.data.entity;

import java.util.Objects;

import org.hibernate.Hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "crypto")
public class Crypto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crypto_id_gen")
    @SequenceGenerator(name = "crypto_id_gen", sequenceName = "crypto_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "symbol")
    private String symbol;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Crypto crypto1 = (Crypto) o;
        return id != null && Objects.equals(id, crypto1.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
