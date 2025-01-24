package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.dao.TestRepository;
import com.gpaqd.student_life_manager.entity.MyTest;
import com.gpaqd.student_life_manager.entity.pk.MyTestId;
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
    public List<MyTest> findAll() {
        return testRepository.findAll();
    }

    @Override
    public MyTest findById(MyTestId id) {
        Optional<MyTest> result = testRepository.findById(id);

        MyTest myTest = null;
        if(result.isPresent()) {
            myTest = result.get();
        } else {
            throw new RuntimeException("Did not find test id - " + id);
        }

        return myTest;
    }

    @Override
    public MyTest save(MyTest myTest) {
        return testRepository.save(myTest);
    }

    @Override
    public void deleteById(MyTestId id) {
        testRepository.deleteById(id);
    }
}
