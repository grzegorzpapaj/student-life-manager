package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.entity.Test;
import com.gpaqd.student_life_manager.entity.pk.TestId;

import java.util.List;

public interface TestService {

    List<Test> findAll();

    Test findById(TestId id);

    Test save(Test test);

    void deleteById(TestId id);
}
