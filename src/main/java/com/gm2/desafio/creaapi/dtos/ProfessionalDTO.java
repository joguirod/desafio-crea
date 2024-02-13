package com.gm2.desafio.creaapi.dtos;

import com.gm2.desafio.creaapi.domain.professional.ProfessionalType;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProfessionalDTO(
        @NotBlank(message = "Nome é um atributo obrigatório")
        String name,

        @NotBlank(message = "Email é um atributo obrigatório")
        String email,

        @NotBlank(message = "Senha é um atributo obrigatório")
        String password,

        @NotBlank(message = "Data de nascimento é um atributo obrigatório")
        LocalDate birthdate,

        @NotBlank(message = "Número de telefone é um atributo obrigatório")
        String phoneNumber,

        @NotBlank(message = "Tipo de profissional é um atributo obrigatório")
        ProfessionalType professionalType,

        LocalDate registrationDate,
        LocalDate visaDate
) {
}
