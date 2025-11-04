package br.com.fintech.wallets.model.dto.request;

import br.com.fintech.wallets.model.domain.enums.TargetType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class WalletRequest {

    @NotNull(message = "account_id é obrigatório")
    private Long accountId;

    @NotBlank(message = "name é obrigatório")
    @Size(max = 150, message = "name pode ter no máximo 150 caracteres")
    private String name;

    @NotNull(message = "type é obrigatório")
    private TargetType type;

    @PositiveOrZero(message = "financial_target deve ser maior ou igual a zero")
    private Double financialTarget;

    private LocalDate startDate;
    private LocalDate endDate;
}
