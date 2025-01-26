package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.entity.Threshold;

import java.math.BigDecimal;
import java.util.List;

public interface ThresholdService {

    List<Threshold> findAll();

    Threshold findById(Long id);

    Threshold save(Threshold threshold);

    void deleteById(Long id);

    public Threshold findByAllPoints(BigDecimal p3, BigDecimal p3_5,
                                     BigDecimal p4, BigDecimal p4_5, BigDecimal p5);
}
