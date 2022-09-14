package com.superbank.credit.repository;

import com.superbank.credit.model.Credit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByUserId(final Long userId);
}
