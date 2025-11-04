package br.com.fintech.wallets.model.mapper;

import br.com.fintech.wallets.model.domain.Account;
import br.com.fintech.wallets.model.domain.Login;
import br.com.fintech.wallets.model.dto.request.AccountRequest;
import br.com.fintech.wallets.model.dto.request.LoginRequest;
import br.com.fintech.wallets.model.dto.response.AccountResponse;
import br.com.fintech.wallets.model.dto.response.LoginResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountDtoMapper {

    Account toDomain(AccountRequest request);

    Login toDomain(LoginRequest request);

    AccountResponse toResponse(Account domain);
    LoginResponse toLoginResponse(Account domain);

}
