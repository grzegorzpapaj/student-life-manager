package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.dao.MyTestRepository;
import com.gpaqd.student_life_manager.dao.MyTestRepository;
import com.gpaqd.student_life_manager.entity.MyTest;
import com.gpaqd.student_life_manager.entity.pk.MyTestId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyTestServiceImpl implements MyTestService{

    private MyTestRepository myTestRepository;

    @Autowired
    public MyTestServiceImpl(MyTestRepository myTestRepository) {
        this.myTestRepository = myTestRepository;
    }

    @Override
    public List<MyTest> findAll() {
        return myTestRepository.findAll();
    }

    @Override
    public MyTest findById(MyTestId id) {
        return myTestRepository.findById(id).orElse(null);
    }

    @Override
    public MyTest save(MyTest myTest) {
        return myTestRepository.save(myTest);
    }

    @Override
    public void deleteById(MyTestId id) {
        myTestRepository.deleteById(id);
    }
}
