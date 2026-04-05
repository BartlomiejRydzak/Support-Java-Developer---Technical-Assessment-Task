package com.example.demo.controller;

import com.example.demo.entity.Producer;
import com.example.demo.service.ProducerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProducerController {

    private ProducerService producerService;

    public ProducerController(ProducerService producerService){
        this.producerService = producerService;
    }

    @GetMapping("/producers")
    public List<Producer> findAll(){
        return producerService.findAll();
    }

    @PostMapping("/producers")
    @ResponseStatus(HttpStatus.CREATED)
    public Producer addProducer(@Valid @RequestBody Producer producer){
        producer.setId(null);
        return producerService.save(producer);
    }

    @PutMapping("/producers")
    public Producer updateProducer(@Valid @RequestBody Producer producer){
        return producerService.save(producer);
    }

    @DeleteMapping("/producers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        producerService.deleteById(id);
    }
}
