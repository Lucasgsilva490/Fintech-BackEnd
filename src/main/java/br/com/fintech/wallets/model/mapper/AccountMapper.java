package br.com.fintech.wallets.model.mapper;

import br.com.fintech.wallets.model.domain.Account;
import br.com.fintech.wallets.model.entity.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountEntity toEntity(Account domain);
    Account toDomain(AccountEntity entity);
}
