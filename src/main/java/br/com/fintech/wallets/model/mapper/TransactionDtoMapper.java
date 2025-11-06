package br.com.fintech.wallets.model.mapper;

import br.com.fintech.wallets.model.domain.Transaction;
import br.com.fintech.wallets.model.dto.request.TransactionRequest;
import br.com.fintech.wallets.model.dto.response.TransactionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionDtoMapper {

    @Mapping(target = "transactionId", ignore = true)
    Transaction toDomain(TransactionRequest request);

    @Mapping(
            target = "type",
            expression = "java(domain.getType() == null ? null : " +
                    "(domain.getType().name().equals(\"BUDGET\") ? \"receita\" : \"despesa\"))"
    )
    TransactionResponse toResponse(Transaction domain);
}
