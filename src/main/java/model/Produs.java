package model;

public class Produs {
    private int id;
    private String nume;
    private int pret;
    private int cantitate_stoc;

    public Produs(int id, String nume, int pret, int cantitate_stoc) {
        this.id = id;
        this.nume = nume;
        this.pret = pret;
        this.cantitate_stoc = cantitate_stoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public int getCantitate_stoc() {
        return cantitate_stoc;
    }

    public void setCantitate_stoc(int cantitate_stoc) {
        this.cantitate_stoc = cantitate_stoc;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", pret=" + pret +
                ", cantitate_stoc=" + cantitate_stoc +
                '}';
    }
}
