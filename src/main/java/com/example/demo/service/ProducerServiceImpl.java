package com.example.demo.service;

import com.example.demo.entity.Producer;
import com.example.demo.repository.ProducerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerServiceImpl implements ProducerService{

    private ProducerRepository producerRepository;

    @Autowired
    public ProducerServiceImpl(ProducerRepository producerRepository){
        this.producerRepository = producerRepository;
    }

    @Override
    public List<Producer> findAll() {
        return producerRepository.findAll();
    }

    @Override
    @Transactional
    public Producer save(Producer producer) {
        return producerRepository.save(producer);
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        producerRepository.deleteById(id);
    }


}
