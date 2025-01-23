package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.dao.ThresholdRepository;
import com.gpaqd.student_life_manager.entity.Threshold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Optional<Threshold> result = thresholdRepository.findById(id);

        Threshold threshold = null;

        if(result.isPresent()) {
            threshold = result.get();
        } else {
            throw new RuntimeException("Did not find threshold id - " + id);
        }

        return threshold;
    }

    @Override
    public Threshold save(Threshold threshold) {
        return thresholdRepository.save(threshold);
    }

    @Override
    public void deleteById(Long id) {
        thresholdRepository.deleteById(id);
    }
}
