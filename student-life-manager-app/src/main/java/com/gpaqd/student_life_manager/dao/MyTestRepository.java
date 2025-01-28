package com.gpaqd.student_life_manager.dao;

import com.gpaqd.student_life_manager.entity.MyTest;
import com.gpaqd.student_life_manager.entity.pk.MyTestId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MyTestRepository extends JpaRepository<MyTest, MyTestId> {

    @Query(value = """
        SELECT * 
        FROM tests
        WHERE owned_by_user = :username
          AND is_exam = FALSE
          AND date >= CURRENT_DATE
        ORDER BY date ASC
        LIMIT 2
        """, nativeQuery = true)  
    List<MyTest> findNext2UpcomingTests(@Param("username") String username);

    @Query(value = """
        SELECT * 
        FROM tests
        WHERE owned_by_user = :username
          AND is_exam = TRUE
          AND date >= CURRENT_DATE
        ORDER BY date ASC
        LIMIT 2
        """, nativeQuery = true)
    List<MyTest> findNext2UpcomingExams(@Param("username") String username);
}

