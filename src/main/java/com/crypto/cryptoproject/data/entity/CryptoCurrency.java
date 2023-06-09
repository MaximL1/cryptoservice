package com.crypto.cryptoproject.data.entity;

import java.time.Instant;
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
@Table(name = "currency")
public class CryptoCurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency_id_gen")
    @SequenceGenerator(name = "currency_id_gen", sequenceName = "currency_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "price")
    private Double price;

    @Column(name = "date")
    private Instant date;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CryptoCurrency cryptoCurrency1 = (CryptoCurrency) o;
        return id != null && Objects.equals(id, cryptoCurrency1.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
