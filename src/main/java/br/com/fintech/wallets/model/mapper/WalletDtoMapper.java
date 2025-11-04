package br.com.fintech.wallets.model.mapper;

import br.com.fintech.wallets.model.domain.Wallet;
import br.com.fintech.wallets.model.dto.request.WalletRequest;
import br.com.fintech.wallets.model.dto.response.WalletResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletDtoMapper {
    Wallet toDomain(WalletRequest request);

    WalletResponse toResponse(Wallet domain);
}
