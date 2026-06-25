package br.com.sttalis.connect.api.dto;

import br.com.sttalis.connect.domain.model.Country;
import br.com.sttalis.connect.domain.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @NotBlank rejeita valores nulos
 */
public record UserRequestDTO(
        @NotBlank(message = "O nome não pode ser vazio") String name,
        @NotBlank @Email(message = "Formato de e-mail inválido") String email,
        @NotBlank String password,
        @NotNull UserRole role,
        @NotNull Country country
){}