package gestioncom.view;

import java.util.Scanner;

import gestioncom.entity.Categorie;
import gestioncom.entity.Produit;
import gestioncom.services.CategorieService;
import gestioncom.services.ProduitService;

public class MenuStockView {

    private final CategorieService categorieService;
    private final ProduitService produitService;

    public MenuStockView(CategorieService categorieService, ProduitService produitService) {
        this.categorieService = categorieService;
        this.produitService = produitService;
    }

    public void afficherMenu() {
        Scanner sc = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\n--- RESPONSABLE STOCK ---");
            System.out.println("1 - Ajouter Catégorie");
            System.out.println("2 - Lister Catégories");
            System.out.println("3 - Ajouter Produit");
            System.out.println("4 - Lister Produits");
            System.out.println("5 - Filtrer Produits par Catégorie");
            System.out.println("6 - Quitter");
            System.out.print("Votre choix : ");
            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1 -> ajouterCategorie(sc);
                case 2 -> listerCategories();
                case 3 -> ajouterProduit(sc);
                case 4 -> listerProduits();
                case 5 -> filtrerParCategorie(sc);
                case 6 -> System.out.println("Retour...");
                default -> System.out.println("Choix invalide.");
            }

        } while (choix != 6);
    }

    private void ajouterCategorie(Scanner sc) {
        System.out.print("ID Catégorie : ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Libellé : ");
        String libelle = sc.nextLine();
        categorieService.addCategorie(new Categorie(id, libelle));
        System.out.println("Catégorie ajoutée !");
    }

    private void listerCategories() {
        System.out.println("\n--- Liste des Catégories ---");
        categorieService.getAll().forEach(System.out::println);
    }

    private void ajouterProduit(Scanner sc) {
        System.out.print("ID Produit : ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Libellé Produit : ");
        String nom = sc.nextLine();

        System.out.print("Prix : ");
        double prix = sc.nextDouble();

        System.out.print("Quantité : ");
        int qte = sc.nextInt();

        System.out.print("ID Catégorie (ou 0 pour aucune) : ");
        int idCat = sc.nextInt();
        sc.nextLine();

        Categorie cat = (idCat == 0) ? null : categorieService.getById(idCat);

        Produit p = new Produit(id, nom, prix, qte, cat);
        produitService.addProduit(p);

        System.out.println("Produit ajouté !");
    }

    private void listerProduits() {
        System.out.println("\n--- Liste des Produits ---");
        produitService.getAll().forEach(System.out::println);
    }

    private void filtrerParCategorie(Scanner sc) {
        System.out.print("ID Catégorie : ");
        int id = sc.nextInt();
        sc.nextLine();
        produitService.getByCategorie(id).forEach(System.out::println);
    }
}
