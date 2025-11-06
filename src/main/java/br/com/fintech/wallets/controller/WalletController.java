package br.com.fintech.wallets.controller;

import br.com.fintech.wallets.model.dto.request.WalletRequest;
import br.com.fintech.wallets.model.dto.response.WalletResponse;
import br.com.fintech.wallets.model.mapper.WalletDtoMapper;
import br.com.fintech.wallets.service.WalletService;
import br.com.fintech.wallets.model.domain.enums.TargetType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService service;
    private final WalletDtoMapper dtoMapper;

    @PostMapping
    public ResponseEntity<WalletResponse> create(@Valid @RequestBody WalletRequest request) {
        var domain = dtoMapper.toDomain(request);
        var created = service.create(domain);
        return ResponseEntity.ok(dtoMapper.toResponse(created));
    }

    @GetMapping("/{accountId}/{type}")
    public ResponseEntity<List<WalletResponse>> findByAccountIdAndType(
            @PathVariable Long accountId,
            @PathVariable String type
    ) {
        TargetType targetType;
        try {
            targetType = TargetType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo inválido: " + type);
        }

        var domains = service.findByAccountIdAndType(accountId, targetType);
        var responses = domains.stream().map(dtoMapper::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping
    public ResponseEntity<List<WalletResponse>> findAll() {
        var domains = service.findAll();
        var responses = domains.stream().map(dtoMapper::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WalletResponse> update(@PathVariable Long id, @Valid @RequestBody WalletRequest request) {
        var domain = dtoMapper.toDomain(request);
        var updated = service.update(id, domain);
        return ResponseEntity.ok(dtoMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
