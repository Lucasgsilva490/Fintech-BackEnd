package br.com.fintech.wallets.service;

import br.com.fintech.wallets.model.domain.Transaction;
import br.com.fintech.wallets.model.domain.enums.TargetType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {
    Transaction create(Transaction transaction);
    Transaction update(Long id, Transaction transaction);
    void delete(Long id);

    Double getTotalByAccountAndType(Long accountId, TargetType type);
    Double getTotalByWallet(Long walletId);

    Page<Transaction> listByAccountId(Long accountId, TargetType type, Pageable pageable);
}
