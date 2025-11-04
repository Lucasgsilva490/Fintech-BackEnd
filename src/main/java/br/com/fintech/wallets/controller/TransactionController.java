package br.com.fintech.wallets.controller;

import br.com.fintech.wallets.model.domain.enums.TargetType;
import br.com.fintech.wallets.model.dto.request.TransactionRequest;
import br.com.fintech.wallets.model.dto.request.enums.DirectionRequest;
import br.com.fintech.wallets.model.dto.request.enums.SortRequest;
import br.com.fintech.wallets.model.dto.response.TransactionResponse;
import br.com.fintech.wallets.model.mapper.TransactionDtoMapper;
import br.com.fintech.wallets.model.mapper.TransactionMapper;
import br.com.fintech.wallets.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;
    private final TransactionDtoMapper mapper;

    @PostMapping
    public ResponseEntity<TransactionResponse> create(@Valid @RequestBody TransactionRequest request) {
        var domain = mapper.toDomain(request);
        var created = service.create(domain);
        return ResponseEntity.ok(mapper.toResponse(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> update(@PathVariable Long id, @Valid @RequestBody TransactionRequest request) {
        var domain = mapper.toDomain(request);
        var updated = service.update(id, domain);
        return ResponseEntity.ok(mapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sum/account/{accountId}")
    public ResponseEntity<Double> getTotalByAccountAndType(
            @PathVariable Long accountId,
            @RequestParam TargetType type) {
        return ResponseEntity.ok(service.getTotalByAccountAndType(accountId, type));
    }

    @GetMapping("/sum/wallet/{walletId}")
    public ResponseEntity<Double> getTotalByWallet(@PathVariable Long walletId) {
        return ResponseEntity.ok(service.getTotalByWallet(walletId));
    }

    @GetMapping
    public ResponseEntity<Page<TransactionResponse>> list(
            @RequestParam Long accountId,
            @RequestParam(required = false) TargetType type,
            @RequestParam(defaultValue = "CREATED_AT") SortRequest sortBy,
            @RequestParam(defaultValue = "DESC") DirectionRequest direction,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        var sort = direction.name().equalsIgnoreCase("DESC")
                ? Sort.by(sortBy.getFieldDescription()).descending()
                : Sort.by(sortBy.getFieldDescription()).ascending();

        var pageable = PageRequest.of(page, size, sort);

        var result = service.listByAccountId(accountId, type, pageable).map(mapper::toResponse);
        return ResponseEntity.ok(result);
    }
}
