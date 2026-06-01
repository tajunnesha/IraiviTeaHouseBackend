package com.example.iraivibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.iraivibackend.model.Account;

import java.time.LocalDateTime;
import java.util.List;

public interface AccountRepository
        extends JpaRepository<Account, Long> {

    @Query("""
            SELECT a
            FROM Account a
            WHERE a.date BETWEEN :start AND :end
            """)
    List<Account> findByDateRange(

            @Param("start")
            LocalDateTime start,

            @Param("end")
            LocalDateTime end
    );



    @Query(
        value = """
            SELECT *
            FROM daily_accounts
            WHERE TO_CHAR(date, 'YYYY-MM') = :month
            """,
        nativeQuery = true
    )
    List<Account> findByMonth(
            @Param("month") String month);
}