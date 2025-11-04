package br.com.fintech.wallets.service.impl;

import br.com.fintech.wallets.model.domain.Wallet;
import br.com.fintech.wallets.model.entity.WalletEntity;
import br.com.fintech.wallets.model.mapper.WalletMapper;
import br.com.fintech.wallets.model.repository.AccountRepository;
import br.com.fintech.wallets.model.repository.WalletRepository;
import br.com.fintech.wallets.service.WalletService;
import br.com.fintech.wallets.model.domain.enums.TargetType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository repository;
    private final AccountRepository accountRepository;
    private final WalletMapper mapper;

    @Override
    public Wallet create(Wallet wallet) {
        var accountId = wallet.getAccountId();

        accountRepository.findById(accountId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));

        WalletEntity entity = mapper.toEntity(wallet);

        WalletEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public List<Wallet> findByAccountIdAndType(Long accountId, TargetType type) {
        return repository.findByAccountEntity_AccountIdAndType(accountId, type)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Wallet> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Wallet update(Long id, Wallet wallet) {
        WalletEntity existing = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wallet not found with id: " + id));

        existing.setName(wallet.getName());
        existing.setType(wallet.getType());
        existing.setFinancialTarget(wallet.getFinancialTarget());
        existing.setStartDate(wallet.getStartDate());
        existing.setEndDate(wallet.getEndDate());
        existing.setUpdatedAt(LocalDateTime.now());

        WalletEntity updated = repository.save(existing);
        return mapper.toDomain(updated);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wallet not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
