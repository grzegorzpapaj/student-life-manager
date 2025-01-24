package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.entity.Course;
import com.gpaqd.student_life_manager.entity.pk.CourseId;

import java.util.List;

public interface CourseService {

    List<Course> findAll();

    Course findById(CourseId id);

    Course save(Course course);

    void deleteById(CourseId id);
}
