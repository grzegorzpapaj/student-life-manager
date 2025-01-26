package com.gpaqd.student_life_manager.dao;

import com.gpaqd.student_life_manager.entity.Threshold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface ThresholdRepository extends JpaRepository<Threshold, Long> {

    @Query("SELECT t FROM Threshold t WHERE t.points3 = :p3 AND t.points3_5 = :p3_5 " +
            "AND t.points4 = :p4 AND t.points4_5 = :p4_5 AND t.points5 = :p5")
    Optional<Threshold> findByAllPoints(BigDecimal p3, BigDecimal p3_5,
                                        BigDecimal p4, BigDecimal p4_5, BigDecimal p5);
}
