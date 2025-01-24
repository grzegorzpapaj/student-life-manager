package com.gpaqd.student_life_manager.dao;

import com.gpaqd.student_life_manager.entity.Test;
import com.gpaqd.student_life_manager.entity.pk.TestId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, TestId> {
}
