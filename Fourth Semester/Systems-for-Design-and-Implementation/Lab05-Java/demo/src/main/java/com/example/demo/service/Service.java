package com.example.demo.service;

import com.example.demo.domain.ProbaParticipanti;
import com.example.demo.domain.ProbeParticipant;
import com.example.demo.repository.ParticipantRepository;
import com.example.demo.repository.ProbaRepository;
import com.example.demo.repository.Repository;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

public class Service {

    private ParticipantRepository participantRepository;
    private ProbaRepository probaRepository;
    private ProbeParticipant probeParticipantRepository;

    public Service(ParticipantRepository participantRepository, ProbaRepository probaRepository, ProbeParticipant probeParticipantRepository) {
        this.participantRepository = participantRepository;
        this.probaRepository = probaRepository;
        this.probeParticipantRepository = probeParticipantRepository;
    }

    public ObservableList<ProbaParticipanti> getProbeParticipanti(){

    }
}
