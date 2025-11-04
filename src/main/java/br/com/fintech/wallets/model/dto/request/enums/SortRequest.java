package br.com.fintech.wallets.model.dto.request.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SortRequest {
    CREATED_AT("createdAt"),
    VALUE("value");

    final String fieldDescription;
}
