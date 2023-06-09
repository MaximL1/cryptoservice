package com.crypto.cryptoproject.business.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.postgresql.util.PGobject;

import com.crypto.cryptoproject.business.dto.CryptoReportDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReportMapper {

    private ReportMapper() {
    }

    public static CryptoReportDto getDto(Object obj) {
         LocalDateTime oldest = null;
         LocalDateTime newest = null;
         Double min = null;
         Double max = null;


        try {
            Object[] listObj = (Object[]) obj;
            if (listObj[0] != null) {
                oldest = formatDate(((PGobject) listObj[0]).getValue());
            }
            if (listObj[1] != null) {
                newest = formatDate(((PGobject) listObj[1]).getValue());
            }
            if (listObj[2] != null) {
                min = formatDouble(((PGobject) listObj[2]).getValue());
            }
            if (listObj[3] != null) {
                max = formatDouble(((PGobject) listObj[3]).getValue());
            }
        } catch (Exception e){
            log.info("Cant deserialize data from db: " + e.getMessage());
        }
        return new CryptoReportDto(oldest, newest, min, max);
    }

    private static Double formatDouble(String value) {
        if (value == null) {
            return null;
        }
        String number = value.replace("(", "")
                .replace(")", "");
        return Double.parseDouble(number);
    }

    private static LocalDateTime formatDate(String value) {

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
        LocalDateTime localDateTime = null;
        try {
            localDateTime = LocalDateTime.parse(
                    value.replace("(", "")
                    .replace(")", "")
                    .concat(" 00:00:00 AM"), formatter);
        } catch (Exception e) {
            log.info("Cant deserialize data from db: " + e.getMessage());
        }
        return localDateTime;
    }
}
