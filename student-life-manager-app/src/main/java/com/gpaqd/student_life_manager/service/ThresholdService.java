package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.entity.Threshold;

import java.util.List;

public interface ThresholdService {

    List<Threshold> findAll();

    Threshold findById(Long id);

    Threshold save(Threshold threshold);

    void deleteById(Long id);
}
