package br.com.fintech.wallets.service.impl;

import br.com.fintech.wallets.model.domain.Account;
import br.com.fintech.wallets.model.domain.Login;
import br.com.fintech.wallets.model.domain.enums.AccountStatus;
import br.com.fintech.wallets.model.mapper.AccountMapper;
import br.com.fintech.wallets.model.repository.AccountRepository;
import br.com.fintech.wallets.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final AccountMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Account create(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        var entity = mapper.toEntity(account);
        var saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Account findById(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));
        return mapper.toDomain(entity);
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Account update(Long id, Account account) {
        var existingEntity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));

        existingEntity.setName(account.getName());
        existingEntity.setCpf(account.getCpf());
        existingEntity.setMonthlyIncome(account.getMonthlyIncome());
        existingEntity.setEmail(account.getEmail());

        if (account.getPassword() != null) {
            existingEntity.setPassword(passwordEncoder.encode(account.getPassword()));
        }

        var saved = repository.save(existingEntity);
        return mapper.toDomain(saved);
    }

    @Override
    public Account cancel(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));

        entity.setStatus(AccountStatus.CANCELLED);

        var saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Account login(Login login) {
        var entity = repository.findByEmail(login.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email não encontrado"));

        if (!passwordEncoder.matches(login.getPassword(), entity.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Senha incorreta");
        }

        return mapper.toDomain(entity);
    }
}
