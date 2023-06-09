package com.crypto.cryptoproject.utility;

import org.springframework.web.multipart.MultipartFile;

import lombok.NonNull;

public interface CsvService {

    /**
     * Imports entities from a CSV file;
     *
     * @param csvFile the csv file;
     */
    void importFromCsv(@NonNull MultipartFile csvFile);
}
