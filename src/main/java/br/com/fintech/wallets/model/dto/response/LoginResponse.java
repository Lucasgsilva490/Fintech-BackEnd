package br.com.fintech.wallets.model.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginResponse {
    private Long accountId;
    private String name;
    private String status;
    private String email;
}
