package domain;

public class Proba implements Identifiable<Integer>{
    private String stil;
    private Integer distanta;

    public Proba(String stil, Integer distanta) {
        this.stil = stil;
        this.distanta = distanta;
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
    public void setId(Integer integer) {

    }

    @Override
    public Integer getId() {
        return null;
    }
}
