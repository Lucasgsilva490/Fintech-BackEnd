package br.com.fintech.wallets.model.domain;

import br.com.fintech.wallets.model.domain.enums.AccountStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Account {
    private Long accountId;
    private String name;
    private String cpf;
    private BigDecimal monthlyIncome;
    private AccountStatus status;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
