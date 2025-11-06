package br.com.fintech.wallets.model.mapper;

import br.com.fintech.wallets.model.domain.Wallet;
import br.com.fintech.wallets.model.dto.request.WalletRequest;
import br.com.fintech.wallets.model.dto.response.WalletResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WalletDtoMapper {
    Wallet toDomain(WalletRequest request);

    @Mapping(
            target = "type",
            expression = "java(domain.getType() == null ? null : " +
                    "(domain.getType().name().equals(\"BUDGET\") ? \"receita\" : \"despesa\"))"
    )
    WalletResponse toResponse(Wallet domain);
}
