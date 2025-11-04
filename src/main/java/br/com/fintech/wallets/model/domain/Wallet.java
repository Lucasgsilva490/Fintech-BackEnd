package br.com.fintech.wallets.model.domain;

import br.com.fintech.wallets.model.domain.enums.TargetType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Wallet {
    private Long walletId;
    private Long accountId;
    private String name;
    private TargetType type;
    private Double financialTarget;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
