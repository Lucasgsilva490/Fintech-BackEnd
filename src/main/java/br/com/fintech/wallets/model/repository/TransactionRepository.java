package br.com.fintech.wallets.repository;

import br.com.fintech.wallets.model.entity.TransactionEntity;
import br.com.fintech.wallets.model.domain.enums.TargetType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query("SELECT SUM(t.value) FROM TransactionEntity t " +
            "WHERE t.walletEntity.accountEntity.accountId = :accountId AND t.type = :type")
    Double sumByAccountIdAndType(Long accountId, TargetType type);

    @Query("SELECT SUM(t.value) FROM TransactionEntity t WHERE t.walletEntity.walletId = :walletId")
    Double sumByWalletId(Long walletId);
    
    @Query("""
    SELECT t FROM TransactionEntity t
    WHERE t.walletEntity.accountEntity.accountId = :accountId
      AND (:type IS NULL OR t.type = :type)
""")
    Page<TransactionEntity> findAllByAccountAndType(
            @Param("accountId") Long accountId,
            @Param("type") TargetType type,
            Pageable pageable);
}
