package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.entity.Lab;
import com.gpaqd.student_life_manager.entity.pk.LabId;

import java.util.List;

public interface LabService {

    List<Lab> findAll();

    Lab findById(LabId id);

    Lab save(Lab lab);

    void deleteById(LabId id);
}
