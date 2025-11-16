package gestioncom;

import gestioncom.repository.memory.CategorieRepository;
import gestioncom.repository.memory.ProduitRepository;
import gestioncom.repository.memory.CommandeRepository;
import gestioncom.services.CategorieService;
import gestioncom.services.ProduitService;
import gestioncom.services.CommandeService;
import gestioncom.view.MenuStockView;
import gestioncom.view.MenuCommandeView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        
        CategorieRepository categorieRepo = new CategorieRepository();
        ProduitRepository produitRepo = new ProduitRepository();
        CommandeRepository commandeRepo = new CommandeRepository();

        
        CategorieService categorieService = new CategorieService(categorieRepo);
        ProduitService produitService = new ProduitService(produitRepo, categorieService);
        CommandeService commandeService = new CommandeService(commandeRepo);

        
        MenuStockView menuStock = new MenuStockView(categorieService, produitService);
        MenuCommandeView menuCommande = new MenuCommandeView(commandeService, produitService);

        
        Scanner sc = new Scanner(System.in);
        int choix;
        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1 - Gestion Stock");
            System.out.println("2 - Gestion Commandes");
            System.out.println("3 - Quitter");
            System.out.print("Votre choix : ");
            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1 -> menuStock.afficherMenu();
                case 2 -> menuCommande.afficherMenu();
                case 3 -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide.");
            }
        } while (choix != 3);
    }
}
