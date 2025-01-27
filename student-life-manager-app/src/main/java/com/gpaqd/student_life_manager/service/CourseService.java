package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.dto.CourseDetailsDTO;
import com.gpaqd.student_life_manager.entity.Course;
import com.gpaqd.student_life_manager.entity.pk.CourseId;

import java.util.List;

public interface CourseService {

    List<Course> findAll();

    List<Course> findAllByUser(String username);

    Course findById(CourseId id);

    Course save(Course course);

    void deleteById(CourseId id);

    Course createNewEmptyCourseForUser(String username);

    Course saveCourseWithDTO(CourseDetailsDTO dto, String username);

    CourseDetailsDTO getCourseDetailsDTO(CourseId courseId);

    CourseDetailsDTO getEditCourseDetailsDTO(String username, String courseName);

    Course updateCourseWithDTO(CourseDetailsDTO dto, String username);
}
