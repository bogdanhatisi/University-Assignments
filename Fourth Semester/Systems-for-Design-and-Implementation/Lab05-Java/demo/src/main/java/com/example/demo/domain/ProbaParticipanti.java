package com.example.demo.domain;

public class ProbaParticipanti implements Identifiable<Integer>{

    private String Proba;
    private int Participanti;

    public ProbaParticipanti(String numeProba, int nrParticipanti) {
        this.Proba = numeProba;
        this.Participanti = nrParticipanti;
    }


    @Override
    public void setId(Integer integer) {

    }

    @Override
    public Integer getId() {
        return null;
    }

    public String getProba() {
        return Proba;
    }

    public int getParticipanti() {
        return Participanti;
    }
}
