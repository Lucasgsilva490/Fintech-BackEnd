package br.com.fintech.wallets.model.mapper;

import br.com.fintech.wallets.model.domain.Wallet;
import br.com.fintech.wallets.model.entity.AccountEntity;
import br.com.fintech.wallets.model.entity.WalletEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    @Mapping(target = "accountEntity", source = "accountId", qualifiedByName = "mapAccountIdToEntity")
    WalletEntity toEntity(Wallet domain);

    @Mapping(target = "accountId", source = "accountEntity.accountId")
    Wallet toDomain(WalletEntity entity);

    @Named("mapAccountIdToEntity")
    default AccountEntity mapAccountIdToEntity(Long accountId) {
        if (accountId == null) {
            return null;
        }
        return AccountEntity.builder()
                .accountId(accountId)
                .build();
    }
}
