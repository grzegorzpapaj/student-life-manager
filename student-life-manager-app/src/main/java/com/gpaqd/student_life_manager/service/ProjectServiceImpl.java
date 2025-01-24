package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.dao.ProjectRepository;
import com.gpaqd.student_life_manager.entity.Project;
import com.gpaqd.student_life_manager.entity.pk.ProjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService{

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(ProjectId id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void deleteById(ProjectId id) {
        projectRepository.deleteById(id);
    }
}
