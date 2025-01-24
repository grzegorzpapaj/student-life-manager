package com.gpaqd.student_life_manager.dao;

import com.gpaqd.student_life_manager.entity.MyTest;
import com.gpaqd.student_life_manager.entity.pk.MyTestId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<MyTest, MyTestId> {
}
