package gestioncom.entity;

import java.util.ArrayList;
import java.util.List;

public class Commande {
    private int id;
    private String client;
    private List<LigneCommande> lignes;

    public Commande(int id, String client) {
        this.id = id;
        this.client = client;
        this.lignes = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getClient() { return client; }
    public List<LigneCommande> getLignes() { return lignes; }

    public void addLigne(LigneCommande ligne) {
        lignes.add(ligne);
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", client='" + client + '\'' +
                ", lignes=" + lignes.size() +
                '}';
    }
}
