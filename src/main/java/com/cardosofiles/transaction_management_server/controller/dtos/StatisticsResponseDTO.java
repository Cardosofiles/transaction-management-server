package com.cardosofiles.transaction_management_server.controller.dtos;

public record StatisticsResponseDTO(Long count, Double sum, Double avg, Double max, Double min) {
}
