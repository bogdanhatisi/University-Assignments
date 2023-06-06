package com.example.bugmanager;

import com.example.bugmanager.model.Bug;
import com.example.bugmanager.model.Programmer;
import com.example.bugmanager.model.Verifier;
import com.example.bugmanager.repository.BugRepository;
import com.example.bugmanager.repository.ProgrammerRepository;
import com.example.bugmanager.repository.Repository;
import com.example.bugmanager.repository.VerifierRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Service {
    private BugRepository bugRepo;
    private ProgrammerRepository programmerRepo;
    private VerifierRepository verifierRepo;

    private int programmerId;

    public Service(BugRepository bugRepo, ProgrammerRepository programmerRepo, VerifierRepository verifierRepo) {
        this.bugRepo = bugRepo;
        this.programmerRepo = programmerRepo;
        this.verifierRepo = verifierRepo;
    }


    public void addBug(String description,String status,int programmerId)
    {
        bugRepo.add(new Bug(description,status,programmerId));
    }

    public void deleteBug(int bugId)
    {
        bugRepo.delete(bugId);
    }

    public void solveBug(Bug bug)
    {
        bug.setStatus("Closed");
        bugRepo.update(bug.getId(),bug);
    }
    public ObservableList<Bug> getAllBugs()
    {
        List<Bug> bugs = new ArrayList<>();;
        for(Bug bug:bugRepo.findAll())
        {
            System.out.println(bug.getDescription());
            bugs.add(bug);
        }

        return FXCollections.observableArrayList(bugs);
    }

    public ObservableList<Bug> getAllBugsFromProgrammer(int searchedPogrammerId)
    {
        List<Bug> bugs = new ArrayList<>();;
        for(Bug bug:bugRepo.findAll())
        {
            System.out.println(bug.getProgrammerId());
            if(bug.getProgrammerId() == searchedPogrammerId && bug.getStatus().equals("Open")) {
                System.out.println(bug.getDescription());
                bugs.add(bug);
            }
        }

        return FXCollections.observableArrayList(bugs);
    }

    public ObservableList<Bug> getAllBugsWithDescription(String description)
    {
        List<Bug> bugs = new ArrayList<>();;
        for(Bug bug:bugRepo.findAll())
        {
            if(bug.description.equals(description)) {
                bugs.add(bug);
            }
        }

        return FXCollections.observableArrayList(bugs);
    }

    public ObservableList<Bug> getAllBugsWithDescriptionFromProgrammer(String description,int searchedProgrammerId)
    {
        List<Bug> bugs = new ArrayList<>();;
        for(Bug bug:bugRepo.findAll())
        {
            if(bug.description.equals(description) && bug.getProgrammerId() == searchedProgrammerId && bug.getStatus().equals("Open")) {
                bugs.add(bug);
            }
        }

        return FXCollections.observableArrayList(bugs);
    }
    public boolean findVerifier(String username, String password)
    {
        Verifier verifier = verifierRepo.findByUserAndPass(username,password);

        if(verifier == null)
        {
            return false;
        }

        return true;
    }

    public int findProgrammer(String username, String password)
    {
        Programmer programmer = programmerRepo.findByUserAndPass(username,password);

        if(programmer == null)
        {
            return 0;
        }

        return programmer.getId();
    }

    public void setProgrammerId(int programmerId) {
        this.programmerId = programmerId;
    }

    public int getProgrammerId() {
        return programmerId;
    }

    public static <E> List<E> toList(final Iterable<E> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

}
