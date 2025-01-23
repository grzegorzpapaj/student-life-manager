package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.dao.TestRepository;
import com.gpaqd.student_life_manager.entity.Test;
import com.gpaqd.student_life_manager.entity.pk.TestId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestServiceImpl implements TestService{

    private TestRepository testRepository;

    @Autowired
    public TestServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public List<Test> findAll() {
        return testRepository.findAll();
    }

    @Override
    public Test findById(TestId id) {
        Optional<Test> result = testRepository.findById(id);

        Test test = null;
        if(result.isPresent()) {
            test = result.get();
        } else {
            throw new RuntimeException("Did not find test id - " + id);
        }

        return test;
    }

    @Override
    public Test save(Test test) {
        return testRepository.save(test);
    }

    @Override
    public void deleteById(TestId id) {
        testRepository.deleteById(id);
    }
}
