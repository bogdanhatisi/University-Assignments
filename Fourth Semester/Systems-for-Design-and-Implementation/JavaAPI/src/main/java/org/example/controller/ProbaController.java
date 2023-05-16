package org.example.controller;

import org.example.domain.Proba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.repository.ProbaRepository;

import java.util.List;

@RestController
@RequestMapping("/api/proba")
@CrossOrigin(origins = "http://localhost:4200")

public class ProbaController {

    private ProbaRepository probaRepository;

    @Autowired
    public void setProbaRepository(ProbaRepository probaRepository)
    {
        this.probaRepository = probaRepository;
    }



    @GetMapping("/test")
    public  String test(@RequestParam(value="name", defaultValue="Hello") String name) {
        return name.toUpperCase();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Proba> getAll(){
        System.out.println("GET toate probele ...");
        List<Proba> probe = (List<Proba>)probaRepository.findAll();
        return probe;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Integer id){
        System.out.println("Get by id "+id);
        Proba proba = probaRepository.findOne(id);
        if (proba==null)
            return new ResponseEntity<>("Proba nu a fost gasita",HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(proba, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id){
        System.out.println("Stergem proba... "+id);
        Proba proba = probaRepository.delete(id);
        return new ResponseEntity<>(proba, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Proba proba){
        System.out.println("Creaza proba ...");
        Proba proba1 = probaRepository.add(proba);
        if (proba1 == null)
            return new ResponseEntity<>("Eroare la adaugare proba", HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(proba1, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Proba proba){
        System.out.println("Updating flight ...");
        proba.setId(id);
        Proba proba1 = probaRepository.update(id,proba);
        if (proba1 == null)
            return new ResponseEntity<>("Eroare la updatat proba", HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(proba1, HttpStatus.OK);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userError(Exception e) {
        return e.getMessage();
    }

}
