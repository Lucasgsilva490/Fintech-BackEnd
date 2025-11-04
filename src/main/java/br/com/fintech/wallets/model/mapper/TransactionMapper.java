package br.com.fintech.wallets.model.mapper;

import br.com.fintech.wallets.model.domain.Transaction;
import br.com.fintech.wallets.model.entity.TransactionEntity;
import br.com.fintech.wallets.model.entity.WalletEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "walletEntity", source = "walletId", qualifiedByName = "mapWalletIdToEntity")
    TransactionEntity toEntity(Transaction domain);

    @Mapping(target = "walletId", source = "walletEntity.walletId")
    Transaction toDomain(TransactionEntity entity);

    @Named("mapWalletIdToEntity")
    default WalletEntity mapWalletIdToEntity(Long walletId) {
        if (walletId == null) {
            return null;
        }
        return WalletEntity.builder().walletId(walletId).build();
    }
}
