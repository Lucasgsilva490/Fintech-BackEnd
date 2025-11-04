package br.com.fintech.wallets.service;

import br.com.fintech.wallets.model.domain.Wallet;
import br.com.fintech.wallets.model.domain.enums.TargetType;

import java.util.List;

public interface WalletService {
    Wallet create(Wallet wallet);
    List<Wallet> findByAccountIdAndType(Long accountId, TargetType type);
    List<Wallet> findAll();
    Wallet update(Long id, Wallet wallet);
    void delete(Long id);
}
