package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.entity.Lab;
import com.gpaqd.student_life_manager.entity.Project;
import com.gpaqd.student_life_manager.entity.MyTest;

import java.util.List;


public interface DeadlineService {


    List<MyTest> getNext2Tests(String username);


    List<Project> getNext2Projects(String username);


    List<Lab> getNext2Labs(String username);
}
