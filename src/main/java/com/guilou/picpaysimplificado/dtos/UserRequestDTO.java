package com.guilou.picpaysimplificado.dtos;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

public record UserRequestDTO(
        @NotEmpty
        String firstName,
        @NotEmpty
        String lastName,
        @NotEmpty
        String document,
        @NotEmpty
        String email,
        @NotEmpty
        String password,
        BigDecimal balance,
        @NotEmpty
        String userType
) {
}
