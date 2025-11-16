package gestioncom.entity;

public class LigneCommande {
    private Produit produit;
    private int quantite;

    public LigneCommande(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }

    public Produit getProduit() { return produit; }
    public int getQuantite() { return quantite; }

    @Override
    public String toString() {
        return "LigneCommande{" +
                "produit=" + produit.getLibelle() +
                ", quantite=" + quantite +
                '}';
    }
}
