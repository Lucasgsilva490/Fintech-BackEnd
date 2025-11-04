package br.com.fintech.wallets.service;

import br.com.fintech.wallets.model.domain.Account;
import br.com.fintech.wallets.model.domain.Login;

import java.util.List;

public interface AccountService {
    Account create(Account account);
    Account findById(Long id);
    List<Account> findAll();
    Account update(Long id, Account account);
    Account cancel(Long id);
    Account login(Login account);
}
