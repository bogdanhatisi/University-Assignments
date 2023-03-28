package domain;

public class Participant implements Identifiable<Integer>{
    private String nume;
    private Integer varsta;


    public Participant(String nume, Integer varsta) {
        this.nume = nume;
        this.varsta = varsta;

    }

    public String getNume() {
        return nume;
    }

    public Integer getVarsta() {
        return varsta;
    }


    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public void setId(Integer integer) {

    }

    @Override
    public Integer getId() {
        return null;
    }
}
