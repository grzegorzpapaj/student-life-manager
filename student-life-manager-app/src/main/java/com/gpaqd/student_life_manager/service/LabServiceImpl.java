package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.dao.LabRepository;
import com.gpaqd.student_life_manager.entity.Lab;
import com.gpaqd.student_life_manager.entity.pk.LabId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabServiceImpl implements LabService{

    private LabRepository labRepository;

    @Autowired
    public LabServiceImpl(LabRepository labRepository) {
        this.labRepository = labRepository;
    }


    @Override
    public List<Lab> findAll() {
        return labRepository.findAll();
    }

    @Override
    public Lab findById(LabId id) {
        Optional<Lab> result = labRepository.findById(id);

        Lab lab = null;

        if(result.isPresent()) {
            lab = result.get();
        } else {
            throw new RuntimeException("Did not find lab id - " + id);
        }

        return lab;
    }

    @Override
    public Lab save(Lab lab) {
        return labRepository.save(lab);
    }

    @Override
    public void deleteById(LabId id) {
        labRepository.deleteById(id);
    }
}
