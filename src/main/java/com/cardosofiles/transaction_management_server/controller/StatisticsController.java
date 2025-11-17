package com.cardosofiles.transaction_management_server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cardosofiles.transaction_management_server.business.services.StatisticService;
import com.cardosofiles.transaction_management_server.controller.dtos.StatisticsResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/statistic")
@RequiredArgsConstructor
public class StatisticsController {

        private final StatisticService statisticService;

        @GetMapping
        @Operation(description = "Endpoint responsável por buscar as estatísticas das transações nos últimos 'n' segundos.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200",
                                        description = "Busca efetuada com sucesso."),
                        @ApiResponse(responseCode = "400",
                                        description = "Erro na busca de estatísticas."),
                        @ApiResponse(responseCode = "500",
                                        description = "Erro interno do servidor.")})
        public ResponseEntity<StatisticsResponseDTO> searchStatistics(
                        @RequestParam(value = "intervalSearch", required = false,
                                        defaultValue = "60") Integer intervalSearch) {
                return ResponseEntity.ok(statisticService.calculateStatistics(intervalSearch));
        }
}
