package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.dao.LabRepository;
import com.gpaqd.student_life_manager.dao.ProjectRepository;
import com.gpaqd.student_life_manager.dao.MyTestRepository;
import com.gpaqd.student_life_manager.entity.Lab;
import com.gpaqd.student_life_manager.entity.Project;
import com.gpaqd.student_life_manager.entity.MyTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of DeadlineService for fetching 2 upcoming Tests, Projects, and Labs.
 */
@Service
public class DeadlineServiceImpl implements DeadlineService {

    private final MyTestRepository testRepository;
    private final ProjectRepository projectRepository;
    private final LabRepository labRepository;

    @Autowired
    public DeadlineServiceImpl(MyTestRepository testRepository,
                               ProjectRepository projectRepository,
                               LabRepository labRepository) {
        this.testRepository = testRepository;
        this.projectRepository = projectRepository;
        this.labRepository = labRepository;
    }

    @Override
    public List<MyTest> getNext2Tests(String username) {
        // Calls a repository method that returns up to 2 tests for the user
        // (E.g. using "LIMIT 2" in a native query)
        return testRepository.findNext2UpcomingTests(username);
    }

    @Override
    public List<Project> getNext2Projects(String username) {
        // Calls a repository method that returns up to 2 projects for the user
        return projectRepository.findNext2UpcomingProjects(username);
    }

    @Override
    public List<Lab> getNext2Labs(String username) {
        // Calls a repository method that returns up to 2 labs for the user
        return labRepository.findNext2UpcomingLabs(username);
    }
}
