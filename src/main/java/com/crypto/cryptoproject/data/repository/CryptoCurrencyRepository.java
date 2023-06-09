package com.crypto.cryptoproject.data.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crypto.cryptoproject.data.entity.CryptoCurrency;

@Repository
public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {

    @Query(value = """
                        select id, symbol, price, date from
                        (select id, symbol, price, date,
                               (1.00*(price - MIN(price) OVER ())/(MAX(price) over () - MIN(price) OVER ())) as normal
                                from currency c2) as norm
                        order by normal DESC""",
           nativeQuery = true)
    List<CryptoCurrency> findByNormalizedOrder();

    @Query(value = """
                        select oldest, newest, minimum, maximun from
                        (select c.date from currency c where c.symbol = :cryptoName order by c.date asc limit 1) as oldest,
                        (select c.date from currency c where c.symbol = :cryptoName order by c.date desc limit 1) as newest,
                        (select c.price from currency c where c.symbol = :cryptoName order by c.price asc limit 1) as minimum,
                        (select c.price from currency c where c.symbol = :cryptoName order by c.price desc limit 1) as maximun""",
            nativeQuery = true)
    Object generateReport(String cryptoName);

    @Query(value = """
                        select id, symbol, price, date from
                        (select id, symbol, price, date,
                               (1.00*(price - MIN(price) OVER ())/(MAX(price) over () - MIN(price) OVER ())) as normal
                                from currency c2) as norm
                        where "date" = :date order by normal DESC  limit 1""",
            nativeQuery = true)
    CryptoCurrency findByNormalizedSpecificDay(Instant date);

    void deleteAllBySymbol(String symbol);
}
