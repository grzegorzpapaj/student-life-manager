package com.gpaqd.student_life_manager.dao;

import com.gpaqd.student_life_manager.entity.Project;
import com.gpaqd.student_life_manager.entity.pk.ProjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, ProjectId> {

    @Query(value = """
        SELECT * 
        FROM projects
        WHERE owned_by_user = :username
          AND deadline >= CURRENT_DATE
        ORDER BY deadline ASC
        LIMIT 2
        """, nativeQuery = true)
    List<Project> findNext2UpcomingProjects(@Param("username") String username);
}
