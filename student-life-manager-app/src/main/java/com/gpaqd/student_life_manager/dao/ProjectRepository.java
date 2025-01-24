package com.gpaqd.student_life_manager.dao;

import com.gpaqd.student_life_manager.entity.Project;
import com.gpaqd.student_life_manager.entity.pk.ProjectId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, ProjectId> {
}
