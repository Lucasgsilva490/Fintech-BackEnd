package br.com.fintech.wallets.model.dto.request;

import br.com.fintech.wallets.model.domain.enums.TargetType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionRequest {

    @NotNull
    private Long walletId;

    private String description;

    @NotNull
    private TargetType type;

    @NotNull
    private Double value;

    @NotNull
    private LocalDateTime transactionAt;
}
