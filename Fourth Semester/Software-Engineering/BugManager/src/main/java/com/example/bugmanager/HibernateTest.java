package com.example.bugmanager;

import com.example.bugmanager.model.Programmer;
import com.example.bugmanager.repository.ProgrammerRepository;
import com.example.bugmanager.repository.Repository;


import java.util.Date;
import java.util.List;


public class HibernateTest {


    public static void main(String... arg) {
        Repository repo =  new ProgrammerRepository();
        repo.update(2,new Programmer("test4","test1",2));
        Iterable<Programmer> programmers = repo.findAll();
        for(Programmer programmer: programmers)
        {
            System.out.println(programmer.getUsername());
        }

        Programmer programmer = (Programmer) repo.findOne(1);
        System.out.println(programmer.getUsername());

    }
}
