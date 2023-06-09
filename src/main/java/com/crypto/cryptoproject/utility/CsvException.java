package com.crypto.cryptoproject.utility;

/**
* Used for exceptions related to CSV imports.
*/
public class CsvException extends RuntimeException
{
    /**
     * Constructs a new CsvException with the specified detail message.
     *
     * @param message the detail message
     */
    public CsvException(String message)
    {
        super(message);
    }

    /**
     * Constructs a {@code CsvException} with the specified message and cause.
     *
     * @param message the detail message
     * @param cause   the root cause
     */
    public CsvException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
