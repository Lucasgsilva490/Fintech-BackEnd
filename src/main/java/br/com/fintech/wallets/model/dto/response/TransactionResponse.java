package br.com.fintech.wallets.model.dto.response;

import br.com.fintech.wallets.model.domain.enums.TargetType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionResponse {
    private Long transactionId;
    private Long walletId;
    private String description;
    private TargetType type;
    private Double value;
    private LocalDateTime transactionAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
