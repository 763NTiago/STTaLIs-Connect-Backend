package br.com.sttalis.fronteirapp.api.dto;

import br.com.sttalis.fronteirapp.api.domain.model.Country;
import br.com.sttalis.fronteirapp.domain.model.UserRole;
import java.tami.LocalDateTime;
import java.util.UUID;

public record UseResponseDTO(
        UUID id,
        String name,
        String email,
        UserRole role,
        Country country,
        LocalDateTime createdAt
){}