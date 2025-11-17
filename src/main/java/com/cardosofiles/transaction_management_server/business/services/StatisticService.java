package com.cardosofiles.transaction_management_server.business.services;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import org.springframework.stereotype.Service;
import com.cardosofiles.transaction_management_server.controller.dtos.StatisticsResponseDTO;
import com.cardosofiles.transaction_management_server.controller.dtos.TransactionRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticService {

        public final TransactionService transactionService;

        public StatisticsResponseDTO calculateStatistics(Integer intervalSearch) {

                log.info("Iniciando a busca de estatísticas de transações nos últimos {} segundos",
                                intervalSearch);
                List<TransactionRequestDTO> transactions =
                                transactionService.searchTransactions(intervalSearch);

                if (transactions.isEmpty()) {
                        log.info("Nenhuma transação encontrada no intervalo especificado. Retornando estatísticas zeradas.");
                        return new StatisticsResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
                }


                DoubleSummaryStatistics statisticsTransactions = transactions.stream()
                                .mapToDouble(TransactionRequestDTO::value).summaryStatistics();

                log.info("Estatísticas calculadas com sucesso");
                return new StatisticsResponseDTO(statisticsTransactions.getCount(),
                                statisticsTransactions.getSum(),
                                statisticsTransactions.getAverage(),
                                statisticsTransactions.getMax(), statisticsTransactions.getMin());
        }
}
