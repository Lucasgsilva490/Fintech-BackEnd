package br.com.fintech.wallets.model.domain;

import lombok.Data;

@Data
public class Login {
    private String email;

    private String password;
}
