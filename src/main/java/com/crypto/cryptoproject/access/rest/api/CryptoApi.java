package com.crypto.cryptoproject.access.rest.api;

import static com.crypto.cryptoproject.access.rest.api.CryptoApi.ENDPOINT;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.crypto.cryptoproject.business.dto.CryptoCurrencyDto;
import com.crypto.cryptoproject.business.dto.CryptoReportDto;
import com.crypto.cryptoproject.business.dto.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Crypto")
@RequestMapping(path = ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
public interface CryptoApi {
    String ENDPOINT = "/api/v1/crypto";

    @Operation(summary = "Import crypto from a CSV.",
            description = """
                The csv file should only contain the new statistical period that was not
                previously imported. All data is stored in the memory database.""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "OK, currency imported."),
            //TODO add authorization
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized, invalid authorization token.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400",
                    description = "Bad request, the file has not been validated or is invalid.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(value = "/import", consumes = MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Void> importCurrencyFromCsv(@RequestPart MultipartFile file);


    @Operation(summary = "Get descending sorted list of all the cryptos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "OK, crypto found.",
                    content = @Content(schema = @Schema(implementation = CryptoCurrencyDto.class))),
            //TODO add authorization
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized, invalid authorization token.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @GetMapping
    ResponseEntity<List<CryptoCurrencyDto>> getAllCurrency();

    @Operation(summary = "Return the oldest/newest/min/max values for a requested crypto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "OK, crypto found.",
                    content = @Content(schema = @Schema(implementation = CryptoReportDto.class))),
            //TODO add authorization
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized, invalid authorization token.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @GetMapping(value = "/report")
    ResponseEntity<CryptoReportDto> getCurrencyReport(@RequestParam String cryptoName);

    @Operation(summary = "Return the crypto with the highest normalized range for a specific day")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "OK, crypto found.",
                    content = @Content(schema = @Schema(implementation = CryptoCurrencyDto.class))),
            //TODO add authorization
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized, invalid authorization token.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @GetMapping(value = "/highestPerDay")
    ResponseEntity<CryptoCurrencyDto> getCurrencyForSpecificDay(
            @Parameter(description = "Format of dateTime must be 'yyyy-MM-dd'")
            @RequestParam("dateTime") String dateTime);

    @Operation(summary = "Add new cryptocurrency")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "OK, crypto created.",
                    content = @Content(schema = @Schema(implementation = CryptoCurrencyDto.class))),
            //TODO add authorization
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized, invalid authorization token.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @PostMapping(value = "/addCrypto")
    ResponseEntity<CryptoCurrencyDto> createCrypto(@RequestParam("crypto") String crypto);

    @Operation(summary = "Delete the crypto and all imported cryptocurrency")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "NO CONTENT, crypto deleted"),
            //TODO add authorization
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized, invalid authorization token.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteCrypto(@PathVariable("id") Long id);
}
