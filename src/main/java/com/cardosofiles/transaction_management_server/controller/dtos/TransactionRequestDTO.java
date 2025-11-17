package com.cardosofiles.transaction_management_server.controller.dtos;

import java.time.OffsetDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record TransactionRequestDTO(
        @NotNull(message = "Valor da transação não pode ser nulo") @PositiveOrZero(
                message = "Valor da transação não pode ser negativo") Double value,

        OffsetDateTime dateHour) {
}
