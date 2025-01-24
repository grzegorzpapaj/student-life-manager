package com.gpaqd.student_life_manager.dao;

import com.gpaqd.student_life_manager.entity.Course;
import com.gpaqd.student_life_manager.entity.pk.CourseId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, CourseId> {
}
