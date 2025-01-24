package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.entity.MyTest;
import com.gpaqd.student_life_manager.entity.pk.MyTestId;

import java.util.List;

public interface TestService {

    List<MyTest> findAll();

    MyTest findById(MyTestId id);

    MyTest save(MyTest myTest);

    void deleteById(MyTestId id);
}
