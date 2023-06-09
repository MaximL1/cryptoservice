package com.crypto.cryptoproject.utility;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import com.crypto.cryptoproject.business.dto.CryptoCsvDto;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractCsvService implements CsvService {

    @Override
    @Transactional
    public void importFromCsv(@NonNull MultipartFile csvFile) {
        try {
            log.info("Starting import ");

            Reader reader = new InputStreamReader(csvFile.getInputStream());
            new CsvToBeanBuilder<>(reader)
                    .withType(CryptoCsvDto.class)
                    .withSkipLines(1)
                    .withIgnoreEmptyLine(true)
                    .withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
                    .build()
                    .forEach(csvRow -> importCsvRow((CryptoCsvDto) csvRow));
        } catch (IOException e) {
            throw new CsvException("read failed.", e);
        }
    }

    /**
     * Import a single row of a CSV file.
     * @param csvRow the one row.
     */
    protected abstract void importCsvRow(CryptoCsvDto csvRow);
}
