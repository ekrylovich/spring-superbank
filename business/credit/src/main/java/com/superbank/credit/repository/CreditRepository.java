package com.superbank.credit.repository;

import com.superbank.credit.model.Credit;
import com.superbank.overdue.model.OverdueProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByUserId(final Long userId);

    @Query(value = "select sum(summa) as remainingSumma, MIN(start_date) as overdueDate, credit.user_id as userId " +
            "from payment_period, credit " +
            "where credit.id = payment_period.credit_id " +
            "and start_date < :date " +
            "group by credit.user_id", nativeQuery = true)
    List<OverdueProjection> findOverdue(@Param("date") final LocalDate date);

    @Query(value = "select sum(pp.summa) as remainingSumma, MIN(pp.startDate) as overdueDate, cr.userId as userId " +
            "from Credit cr " +
            "join cr.paymentPeriods pp " +
            "where pp.startDate < :date " +
            "group by cr.userId")
    List<OverdueProjection> findOverdueHql(@Param("date") final LocalDate date);
}
