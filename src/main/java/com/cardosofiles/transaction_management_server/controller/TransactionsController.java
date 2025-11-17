package com.cardosofiles.transaction_management_server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cardosofiles.transaction_management_server.business.services.TransactionService;
import com.cardosofiles.transaction_management_server.controller.dtos.TransactionRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionsController {
        private final TransactionService transactionService;

        @PostMapping
        @Operation(description = "Endpoint responsável por adicionar transações")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201",
                                        description = "Transação gravada com sucesso."),
                        @ApiResponse(responseCode = "422",
                                        description = "Campos não atendem os requisitos de validação."),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida."),
                        @ApiResponse(responseCode = "500",
                                        description = "Erro interno do servidor.")})
        public ResponseEntity<Void> createTransaction(
                        @Valid @RequestBody TransactionRequestDTO transactionRequestDTO) {
                transactionService.addTransaction(transactionRequestDTO);
                return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        @DeleteMapping
        @Operation(description = "Endpoint responsável por deletar as transações.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204",
                                        description = "Transações deletadas com sucesso."),
                        @ApiResponse(responseCode = "500",
                                        description = "Erro interno do servidor.")})
        public ResponseEntity<Void> deleteTransactions() {
                transactionService.clearTransactions();
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
}
