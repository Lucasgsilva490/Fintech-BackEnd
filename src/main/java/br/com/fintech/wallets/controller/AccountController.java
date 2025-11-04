package br.com.fintech.wallets.controller;

import br.com.fintech.wallets.model.dto.request.AccountRequest;
import br.com.fintech.wallets.model.dto.request.LoginRequest;
import br.com.fintech.wallets.model.dto.response.AccountResponse;
import br.com.fintech.wallets.model.dto.response.LoginResponse;
import br.com.fintech.wallets.model.mapper.AccountDtoMapper;
import br.com.fintech.wallets.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService service;
    private final AccountDtoMapper dtoMapper;

    @PostMapping
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody AccountRequest request) {
        var domain = dtoMapper.toDomain(request);
        var created = service.create(domain);
        return ResponseEntity.ok(dtoMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> findById(@PathVariable Long id) {
        var account = service.findById(id);
        return ResponseEntity.ok(dtoMapper.toResponse(account));
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> findAll() {
        var list = service.findAll().stream()
                .map(dtoMapper::toResponse)
                .toList();
        return ResponseEntity.ok(list);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<AccountResponse> cancelAccount(@PathVariable Long id) {
        var cancelled = service.cancel(id);;
        return ResponseEntity.ok(dtoMapper.toResponse(cancelled));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        var domain = dtoMapper.toDomain(request);
        var login = service.login(domain);
        return ResponseEntity.ok(dtoMapper.toLoginResponse(login));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> update(@PathVariable Long id,
                                                  @Valid @RequestBody AccountRequest request) {
        var domain = dtoMapper.toDomain(request);
        var updated = service.update(id, domain);
        return ResponseEntity.ok(dtoMapper.toResponse(updated));
    }
}
