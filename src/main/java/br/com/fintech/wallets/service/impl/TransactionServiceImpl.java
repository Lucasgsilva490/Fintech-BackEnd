package br.com.fintech.wallets.service.impl;

import br.com.fintech.wallets.model.domain.Transaction;
import br.com.fintech.wallets.model.domain.enums.TargetType;
import br.com.fintech.wallets.model.entity.TransactionEntity;
import br.com.fintech.wallets.model.entity.WalletEntity;
import br.com.fintech.wallets.model.mapper.TransactionMapper;
import br.com.fintech.wallets.model.repository.WalletRepository;
import br.com.fintech.wallets.repository.TransactionRepository;
import br.com.fintech.wallets.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final TransactionMapper entityMapper;

    @Override
    public Transaction create(Transaction transaction) {
        TransactionEntity entity = entityMapper.toEntity(transaction);

        Long walletId = entity.getWalletEntity() != null ? entity.getWalletEntity().getWalletId() : null;
        if (walletId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "walletId é obrigatório");
        }

        WalletEntity wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wallet não encontrada"));

        entity.setWalletEntity(wallet);
        TransactionEntity saved = transactionRepository.save(entity);
        return entityMapper.toDomain(saved);
    }

    @Override
    public Transaction update(Long id, Transaction transaction) {
        TransactionEntity existing = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transação não encontrada"));

        existing.setDescription(transaction.getDescription());
        existing.setType(transaction.getType());
        existing.setValue(transaction.getValue());
        existing.setTransactionAt(transaction.getTransactionAt());

        TransactionEntity updated = transactionRepository.save(existing);
        return entityMapper.toDomain(updated);
    }

    @Override
    public void delete(Long id) {
        TransactionEntity existing = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transação não encontrada"));
        transactionRepository.delete(existing);
    }

    @Override
    public Double getTotalByAccountAndType(Long accountId, TargetType type) {
        Double sum = transactionRepository.sumByAccountIdAndType(accountId, type);
        return sum == null ? 0.0 : sum;
    }

    @Override
    public Double getTotalByWallet(Long walletId) {
        Double sum = transactionRepository.sumByWalletId(walletId);
        return sum == null ? 0.0 : sum;
    }

    @Override
    public Page<Transaction> listByAccountId(Long accountId, TargetType type, Pageable pageable) {
        Page<TransactionEntity> page = transactionRepository.findAllByAccountAndType(accountId,type, pageable);
        return page.map(entityMapper::toDomain);
    }
}
