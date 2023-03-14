package domain;

public class Participant implements Identifiable<Integer>{
    private String nume;
    private Integer varsta;
    private String probe;

    public Participant(String nume, Integer varsta, String probe) {
        this.nume = nume;
        this.varsta = varsta;
        this.probe = probe;
    }

    public String getNume() {
        return nume;
    }

    public Integer getVarsta() {
        return varsta;
    }

    public String getProbe() {
        return probe;
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
