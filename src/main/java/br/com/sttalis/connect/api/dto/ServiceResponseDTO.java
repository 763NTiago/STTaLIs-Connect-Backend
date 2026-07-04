package br.com.sttalis.connect.api.dto;

import br.com.sttalis.connect.domain.model.ServiceCategory;
import br.com.sttalis.connect.domain.model.Currency;

import java.math.BigDecimal;

import java.util.UUID;

public record ServiceResponseDTO(
        UUID id,
        String title,
        String description,
        BigDecimal price,
        Currency currency,
        ServiceCategory category,
        String providerName
) { }