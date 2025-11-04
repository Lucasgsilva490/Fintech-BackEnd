package br.com.fintech.wallets.model.dto.response;

import br.com.fintech.wallets.model.domain.enums.AccountStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AccountResponse {
    private Long accountId;
    private String name;
    private String cpf;
    private Double monthlyIncome;
    private AccountStatus status;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
