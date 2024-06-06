package model;

public class Order {
    private int id_comanda;
    private int id_client;
    private int id_produs;
    private int cantitate;
    private int pret;

    public Order(int id_comanda, int id_client, int id_produs, int cantitate, int pret) {
        this.id_comanda = id_comanda;
        this.id_client = id_client;
        this.id_produs = id_produs;
        this.cantitate = cantitate;
        this.pret = pret;
    }

    public int getId_comanda() {
        return id_comanda;
    }

    public void setId_comanda(int id_comanda) {
        this.id_comanda = id_comanda;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_produs() {
        return id_produs;
    }

    public void setId_produs(int id_produs) {
        this.id_produs = id_produs;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id_comanda=" + id_comanda +
                ", id_client=" + id_client +
                ", id_produs=" + id_produs +
                ", cantitate=" + cantitate +
                ", pret=" + pret +
                '}';
    }
}
