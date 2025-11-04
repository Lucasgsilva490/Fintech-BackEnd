package br.com.fintech.wallets.model.repository;

import br.com.fintech.wallets.model.entity.WalletEntity;
import br.com.fintech.wallets.model.domain.enums.TargetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, Long> {
    List<WalletEntity> findByAccountEntity_AccountIdAndType(Long accountId, TargetType type);
}
