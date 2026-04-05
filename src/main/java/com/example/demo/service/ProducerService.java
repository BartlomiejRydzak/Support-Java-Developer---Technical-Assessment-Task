package com.example.demo.service;

import com.example.demo.entity.Producer;

import java.util.List;

public interface ProducerService {

    List<Producer> findAll();

    Producer save(Producer producer);

    void deleteById(Long id);
}
