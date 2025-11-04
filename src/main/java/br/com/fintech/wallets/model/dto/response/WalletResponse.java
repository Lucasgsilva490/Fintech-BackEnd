package br.com.fintech.wallets.model.dto.response;

import br.com.fintech.wallets.model.domain.enums.TargetType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class WalletResponse {
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
