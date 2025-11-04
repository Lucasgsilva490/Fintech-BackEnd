package br.com.fintech.wallets.model.domain;

import br.com.fintech.wallets.model.domain.enums.TargetType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Transaction {
    private Long transactionId;
    private Long walletId;
    private String description;
    private TargetType type;
    private Double value;
    private LocalDateTime transactionAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
