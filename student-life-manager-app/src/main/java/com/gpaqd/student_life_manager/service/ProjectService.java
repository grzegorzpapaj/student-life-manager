package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.entity.Project;
import com.gpaqd.student_life_manager.entity.pk.ProjectId;

import java.util.List;

public interface ProjectService {

    List<Project> findAll();

    Project findById(ProjectId id);

    Project save(Project project);

    void deleteById(ProjectId projectId);
}
