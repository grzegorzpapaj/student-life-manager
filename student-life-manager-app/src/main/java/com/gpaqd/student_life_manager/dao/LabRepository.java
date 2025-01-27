package com.gpaqd.student_life_manager.dao;

import com.gpaqd.student_life_manager.entity.Lab;
import com.gpaqd.student_life_manager.entity.pk.LabId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LabRepository extends JpaRepository<Lab, LabId> {

    @Query(value = """
        SELECT *
        FROM labs
        WHERE owned_by_user = :username
          AND deadline >= CURRENT_DATE
        ORDER BY deadline ASC
        LIMIT 2
        """, nativeQuery = true)
    List<Lab> findNext2UpcomingLabs(@Param("username") String username);
}
