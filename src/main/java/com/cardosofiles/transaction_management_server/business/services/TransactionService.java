package com.cardosofiles.transaction_management_server.business.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Service;
import com.cardosofiles.transaction_management_server.controller.dtos.TransactionRequestDTO;
import com.cardosofiles.transaction_management_server.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final List<TransactionRequestDTO> listTransactions = new CopyOnWriteArrayList<>();

    public void addTransaction(TransactionRequestDTO transactionRequestDTO) {

        log.info("Iniciando validação da transação {}", transactionRequestDTO);

        if (transactionRequestDTO == null) {
            log.error("Transação não pode ser nula");
            throw new UnprocessableEntity("Transação não pode ser nula");
        }

        if (transactionRequestDTO.value() == null) {
            log.error("Valor da transação não pode ser nulo");
            throw new UnprocessableEntity("Valor da transação não pode ser nulo");
        }

        if (transactionRequestDTO.value() < 0) {
            log.error("Valor da transação é negativo");
            throw new UnprocessableEntity("Valor da transação não pode ser negativo");
        }

        // Usar sempre a data/hora atual do servidor em UTC
        OffsetDateTime currentTimeUTC = OffsetDateTime.now().toInstant().atOffset(java.time.ZoneOffset.UTC);
        TransactionRequestDTO transactionWithCurrentTime = new TransactionRequestDTO(
                transactionRequestDTO.value(),
                currentTimeUTC
        );

        listTransactions.add(transactionWithCurrentTime);
        log.info("Transação adicionada com sucesso com timestamp: {}", currentTimeUTC);
    }

    public void clearTransactions() {
        log.info("Iniciando remoção de todas as transações");
        listTransactions.clear();
        log.info("Todas as transações foram removidas com sucesso");
    }

    public List<TransactionRequestDTO> searchTransactions(Integer intervalSearch) {
        log.info("Iniciando busca de transações nos últimos {} segundos", intervalSearch);
        log.info("Total de transações armazenadas: {}", listTransactions.size());

        // Normalizar para UTC para comparação correta independente do timezone
        OffsetDateTime dateHourInterval = OffsetDateTime.now().toInstant()
                .atOffset(java.time.ZoneOffset.UTC).minusSeconds(intervalSearch);

        log.info("Data limite para busca (UTC): {}", dateHourInterval);

        List<TransactionRequestDTO> result = listTransactions.stream().filter(transaction -> {
            OffsetDateTime transactionTime =
                    transaction.dateHour().toInstant().atOffset(java.time.ZoneOffset.UTC);
            boolean isAfter = transactionTime.isAfter(dateHourInterval);
            log.info("Transação: {} | Horário UTC: {} | Depois do limite? {}", transaction.value(),
                    transactionTime, isAfter);
            return isAfter;
        }).toList();

        log.info("Retornando {} transações encontradas", result.size());
        return result;
    }
}
