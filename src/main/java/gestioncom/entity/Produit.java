package gestioncom.entity;

public class Produit {
    private int id;
    private String libelle;
    private double prix;
    private int quantite;
    private Categorie categorie; 

    public Produit() {}

    public Produit(int id, String libelle, double prix, int quantite, Categorie categorie) {
        this.id = id;
        this.libelle = libelle;
        this.prix = prix;
        this.quantite = quantite;
        this.categorie = categorie;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public Categorie getCategorie() { return categorie; }
    public void setCategorie(Categorie categorie) { this.categorie = categorie; }

    @Override
    public String toString() {
        String cat = (categorie != null) ? categorie.getLibelle() : "Aucune";
        return id + " - " + libelle + " | prix: " + prix + " | qté: " + quantite + " | cat: " + cat;
    }
}
