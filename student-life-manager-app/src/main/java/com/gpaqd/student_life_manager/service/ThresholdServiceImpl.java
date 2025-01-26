package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.dao.ThresholdRepository;
import com.gpaqd.student_life_manager.entity.Threshold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ThresholdServiceImpl implements ThresholdService{

    private ThresholdRepository thresholdRepository;

    @Autowired
    public ThresholdServiceImpl(ThresholdRepository thresholdRepository) {
        this.thresholdRepository = thresholdRepository;
    }

    @Override
    public List<Threshold> findAll() {
        return thresholdRepository.findAll();
    }

    @Override
    public Threshold findById(Long id) {
        return thresholdRepository.findById(id).orElse(null);
    }

    @Override
    public Threshold save(Threshold threshold) {
        return thresholdRepository.save(threshold);
    }

    @Override
    public void deleteById(Long id) {
        thresholdRepository.deleteById(id);
    }

    @Override
    public Threshold findByAllPoints(BigDecimal p3, BigDecimal p3_5, BigDecimal p4, BigDecimal p4_5, BigDecimal p5) {
        return thresholdRepository.findByAllPoints(p3, p3_5, p4, p4_5, p5).orElse(null);
    }
}
