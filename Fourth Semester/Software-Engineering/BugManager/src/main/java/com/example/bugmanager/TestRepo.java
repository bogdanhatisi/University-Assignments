package com.example.bugmanager;

import com.example.bugmanager.model.Programmer;
import com.example.bugmanager.repository.ProgrammerRepository;
import com.example.bugmanager.repository.Repository;

public class TestRepo {

    public void main()
    {
        Repository repo =  new ProgrammerRepository();
        repo.add(new Programmer("admin","admin",1));


    }
}
