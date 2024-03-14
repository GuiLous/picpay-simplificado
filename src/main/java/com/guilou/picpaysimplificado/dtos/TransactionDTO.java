package com.guilou.picpaysimplificado.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotNull;

public record TransactionDTO(
                @NotNull BigDecimal value,
                @NotNull UUID senderId,
                @NotNull UUID receiverId) {
}
