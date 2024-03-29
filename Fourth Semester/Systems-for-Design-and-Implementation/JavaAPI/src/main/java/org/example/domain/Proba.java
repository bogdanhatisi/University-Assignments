package org.example.domain;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Proba implements Identifiable<Integer>,Serializable{

    private int ID;
    private String stil;
    private Integer distanta;

    public Proba(String stil, Integer distanta) {
        this.stil = stil;
        this.distanta = distanta;
    }

    public Proba() {
    }

    public String getStil() {
        return stil;
    }

    public Integer getDistanta() {
        return distanta;
    }

    public void setStil(String stil) {
        this.stil = stil;
    }

    public void setDistanta(Integer distanta) {
        this.distanta = distanta;
    }

    @Override
    public void setId(Integer id) {
        ID= id;
    }

    @Override
    public Integer getId() {return ID;
    }

    @Override
    public String toString() {
        return "Proba{" +
                "stil='" + stil + '\'' +
                ", distanta=" + distanta +
                '}';
    }
}
