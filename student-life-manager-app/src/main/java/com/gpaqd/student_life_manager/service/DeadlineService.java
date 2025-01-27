package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.entity.Lab;
import com.gpaqd.student_life_manager.entity.Project;
import com.gpaqd.student_life_manager.entity.MyTest;

import java.util.List;

/**
 * Service interface for fetching a limited number of upcoming deadlines 
 * (Tests, Projects, and Labs) for a given user.
 */
public interface DeadlineService {

    /**
     * Fetch up to 2 upcoming Tests (not exams) for the specified user.
     * 
     * @param username the user for whom tests are fetched
     * @return a list of up to 2 Test entities, sorted by earliest date
     */
    List<MyTest> getNext2Tests(String username);

    /**
     * Fetch up to 2 upcoming Projects for the specified user.
     *
     * @param username the user for whom projects are fetched
     * @return a list of up to 2 Project entities, sorted by earliest deadline
     */
    List<Project> getNext2Projects(String username);

    /**
     * Fetch up to 2 upcoming Labs for the specified user.
     *
     * @param username the user for whom labs are fetched
     * @return a list of up to 2 Lab entities, sorted by earliest deadline
     */
    List<Lab> getNext2Labs(String username);
}
