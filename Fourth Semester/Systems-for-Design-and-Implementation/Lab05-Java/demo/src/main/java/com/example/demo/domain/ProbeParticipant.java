package com.example.demo.domain;

public class ProbeParticipant implements Identifiable<Integer>{

    private Integer id;
    private Participant participant;
    private Proba proba;

    public ProbeParticipant(Integer id, Participant participant, Proba proba) {
        this.id = id;
        this.participant = participant;
        this.proba = proba;
    }


    @Override
    public void setId(Integer integer) {

    }

    @Override
    public Integer getId() {
        return null;
    }

    public Participant getParticipant() {
        return participant;
    }

    public Proba getProba() {
        return proba;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public void setProba(Proba proba) {
        this.proba = proba;
    }
}
