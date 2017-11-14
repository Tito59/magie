/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magiemagie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import magiemagie.Carte.TypeCarte;

/**
 *
 * @author Formation
 */
public class Jeu {

    private List<Joueur> joueurs = new ArrayList<>();
    private boolean exit = false;
    private Joueur joueurEnCours;
    Random random = new Random();
    int sortieGererPartie = 0;
    //   protected Sorts sort;

//    public enum Sorts {
//        INVISIBILITE,
//        FILTRE_D_AMOUR,
//        HYPNOSE,
//        DIVINATION,
//        SOMMEIL_PROFOND
//    }
    public void afficherMenuPrincipal() {

        Scanner choixMenuPrincipal = new Scanner(System.in);

        do {

            System.out.println("-----Menu Principal-----\n1]Nouveau Joueur\n2]Démarrer Partie\n3]Liste des joueurs\n4]Quitter");
            System.out.print("\nChoissisez : ");
            String choix = choixMenuPrincipal.next();

            switch (choix) {
                case "1":
                    afficherMenuNouveauJoueur();
                    break;
                case "2":
                    demarrerPartie();
                    break;
                case "3":
                    afficherListeJoueur();
                    break;
                case "4":
                    setExit(true);
                    break;
                default:
                    System.out.println("Erreur de saisie");
                    break;
            }
        } while (exit != true);
    }

    public void afficherListeJoueur() {

        System.out.println("\nListe des joueurs:");
        for (Joueur j : joueurs) {
            j.afficheJoueur();
        }
    }

    public void afficherMenuNouveauJoueur() {
        Scanner pseudo = new Scanner(System.in);
        Scanner choixMenu = new Scanner(System.in);
        boolean exitMenu = false;

        while (exitMenu != true) {

            System.out.println("\n-----Menu nouveau joueur-----\n\n1]Créer un joueur\n2]Retour au menu principal");
            System.out.print("\nChoissisez : ");
            String choix = choixMenu.next();

            switch (choix) {
                case "1":
                    System.out.print("Saissisez votre pseudo : ");
                    String nom = pseudo.next();

                    Joueur j = new Joueur(nom);
                    joueurs.add(j);
                    System.out.println(nom + " à était ajouté avec succes");
                    break;
                case "2":
                    exitMenu = true;
                    break;
                default:
                    System.out.println("Erreur de saisie");
                    break;
            }
        }
    }

    public void demarrerPartie() {

        TypeCarte[] tabTypCart = Carte.TypeCarte.values();
        Carte tempo;
        int sommeil = 0;
        int nbAuHazard;

        //Distribution de 7 cartes à chaque joueur
        for (Joueur j : joueurs) {
            //Traitements
            for (int i = 1; i <= 7; i++) {
                tempo = new Carte();
                nbAuHazard = random.nextInt(5);
                tempo.setType(tabTypCart[nbAuHazard]);
                j.getCartes().add(tempo);
            }
        }
        //le 1er joueur à la main
        joueurEnCours = joueurs.get(0);
        gererPartie();
    }

    public void gererPartie() {
        Scanner choixMenuAction = new Scanner(System.in);
        int n = 0;

        while (joueurEnCours != null) {

            for (Joueur j : joueurs) {

                do {
                    System.out.print("\n-----Menu D'action-----\n"
                            + "C'est au tour de " + joueurEnCours.getNom()
                            + "\nVos ingredients : " + j.getCartes()
                            + "\n1]Afficher la liste des sorts"
                            + "\n2]Combiner des ingredients"
                            + "\n3]Passer le tour"
                            + "\nChoissisez votre action :");

                    String choix = choixMenuAction.nextLine();

                    switch (choix) {
                        case "1"://Afficher la liste des sorts
                            afficherListeSort();
                            break;
                        case "2":
                            lancerSort();
                            break;
                        case "3"://Passer son tour
                            donnerCarteAleatoire(j);
                            sortieGererPartie = 1;
                            System.out.println(sortieGererPartie);
                            break;
                        default:
                            System.out.println("Erreur de saisie");
                            break;
                    }
                } while (sortieGererPartie == 0);
                if (joueurs.size() == 1) {
                    System.out.println("Vous avez gagner!\n");
                    joueurEnCours = null;
                } else if (n + 1 == joueurs.size()) { //Si on atteint la dernière personne de la liste on recommence avec le 1ère de la liste
                    n = 0;
                    joueurEnCours = joueurs.get(n);
                    sortieGererPartie = 0;
                } else {
                    n++;
                    joueurEnCours = joueurs.get(n);
                    sortieGererPartie = 0;
                }
            }
        }
    }

    public void lancerSort() {

        Carte carte1 = new Carte();
        Carte carte2 = new Carte();
        Carte carte3 = new Carte();
        Carte carte4 = new Carte();
        Carte carte5 = new Carte();

        carte1.setType(TypeCarte.MANDRAGORE);
        carte2.setType(TypeCarte.BAVE_DE_CRAPAUD);
        carte3.setType(TypeCarte.AILE_DE_CHAUVE_SOURIS);
        carte4.setType(TypeCarte.LAPIS_LAZULI);
        carte5.setType(TypeCarte.CORNE_DE_LICORNE);
        Scanner choixs = new Scanner(System.in);
        Scanner choixCible = new Scanner(System.in);
        Carte tempo;
        Joueur cible;
        List<Joueur> tempojoueurs = new ArrayList<>();
        //TypeCarte[] tabTypCart = Carte.TypeCarte.values();
        int chiffreAuHazard;
        int exitTour = 0;
        
        while (exitTour != 1) {
            //if (jactuel.getCartes().contains(Carte.TypeCarte.CORNE_DE_LICORNE) && jactuel.getCartes().contains(Carte.TypeCarte.BAVE_DE_CRAPAUD)) {
            if (joueurEnCours.getCartes().contains(carte5) && joueurEnCours.getCartes().contains(carte2)) {
                System.out.println("1]INVISIBILITE");
            }

            //if (jactuel.getCartes().contains(Carte.TypeCarte.CORNE_DE_LICORNE) && jactuel.getCartes().contains(Carte.TypeCarte.MANDRAGORE)) {
            if (joueurEnCours.getCartes().contains(carte5) && joueurEnCours.getCartes().contains(carte1)) {
                System.out.println("2]FILTRE D'AMOUR");
            }

            //if (jactuel.getCartes().contains(Carte.TypeCarte.LAPIS_LAZULI) && jactuel.getCartes().contains(Carte.TypeCarte.AILE_DE_CHAUVE_SOURIS)) {
            if (joueurEnCours.getCartes().contains(carte4) && joueurEnCours.getCartes().contains(carte3)) {
                System.out.println("3]DIVINATION");
            }

            //if (jactuel.getCartes().contains(Carte.TypeCarte.MANDRAGORE) && jactuel.getCartes().contains(Carte.TypeCarte.AILE_DE_CHAUVE_SOURIS)) {
            if (joueurEnCours.getCartes().contains(carte1) && joueurEnCours.getCartes().contains(carte3)) {
                System.out.println("4]SOMMEIL PROFOND");
            }

            //if (jactuel.getCartes().contains(Carte.TypeCarte.LAPIS_LAZULI) && jactuel.getCartes().contains(Carte.TypeCarte.BAVE_DE_CRAPAUD)) {
            if (joueurEnCours.getCartes().contains(carte4) && joueurEnCours.getCartes().contains(carte2)) {
                System.out.println("5]HYPNOSE");
            }

            System.out.println("6]RETOUR\n");

            exitTour = 0;
            System.out.print("Votre choix ?");
            String choixSort = choixs.next();
            int numjoueur;

            switch (choixSort) {
                case "1"://INVISIBILITE
                    if (joueurEnCours.getCartes().contains(carte5) && joueurEnCours.getCartes().contains(carte2)) {

                        for (Joueur j : joueurs) {
                            //Traitements
                            tempo = new Carte();
                            if (joueurEnCours != j) {
                                chiffreAuHazard = random.nextInt(j.getCartes().size());
                                joueurEnCours.getCartes().add(j.getCartes().get(chiffreAuHazard));
                                j.getCartes().remove(chiffreAuHazard);

                            }
                        }
                        joueurEnCours.getCartes().remove(carte5);
                        joueurEnCours.getCartes().remove(carte2);
                        exitTour = 1;
                        sortieGererPartie = 1;
                    }
                    break;
                case "2"://FILTRE D'AMOUR
                    if (joueurEnCours.getCartes().contains(carte5) && joueurEnCours.getCartes().contains(carte1)) {
                        System.out.println("Coming soon...");
                    }
                    break;
                case "3"://DIVINATION
                    if (joueurEnCours.getCartes().contains(carte2) && joueurEnCours.getCartes().contains(carte3)) {
                        for (Joueur j : joueurs) {
                            if (joueurEnCours != j) {
                                System.out.println(j);
                            }
                        }
                        exitTour = 1;
                        sortieGererPartie = 1;
                    }
                    break;
                case "4"://SOMMEIL PROFOND
                    if (joueurEnCours.getCartes().contains(carte4) && joueurEnCours.getCartes().contains(carte3)) {
                        numjoueur = 0;
                        System.out.println("-----Choix des cibles-----");
                        for (Joueur j : joueurs) {//Affiche la liste des victimes
                            if (joueurEnCours != j) {
                                System.out.println(numjoueur + "]" + "Nom : " + j.getNom());
                                tempojoueurs.add(j);
                                numjoueur++;
                            }
                        }
                        System.out.println("\n Votre cible ? ");
                        String choixCibleJoueur = choixCible.next();
                        int choixJoueur = Integer.parseInt(choixCibleJoueur);
                        cible = new Joueur();
                        cible = tempojoueurs.get(choixJoueur);
                    }
                    break;
                case "5"://HYPNOSE
                    if (joueurEnCours.getCartes().contains(carte1) && joueurEnCours.getCartes().contains(carte2)) {
                        System.out.println("Coming soon...");
                    }
                    break;
                case "6"://RETOUR
                    exitTour = 1;
                    break;
                default:
                    System.out.println("Erreur de saisie\n");
                    break;
            }
        }
    }

    public void donnerCarteAleatoire(Joueur actuel) {
        int chiffreAuHazard;
        Carte tempo = new Carte();
        TypeCarte[] tabTypCart = Carte.TypeCarte.values();

        chiffreAuHazard = random.nextInt(5);
        tempo.setType(tabTypCart[chiffreAuHazard]);
        actuel.getCartes().add(tempo);
    }

    public void afficherListeSort() {
        System.out.println("\n-----Liste des sorts-----\n"
                + "\n1]INVISIBLILITÉ (corne de licorne + bave de crapaud)"
                + "\n2]FILTRE D'AMOUR (corne de licorne + mandragore)"
                + "\n3]DIVINATION (bave de crapaud + lapis-lazuli)"
                + "\n4]SOMMEIL PROFOND (bave de crapaud + lapis-lazuli)"
                + "\n5]HYPNOSE (lapis-lazuli + aile-de chauve-souris)");
    }

    public void setJoueurEnCours(Joueur joueurEnCours) {
        this.joueurEnCours = joueurEnCours;
    }

    public Joueur getJoueurEnCours() {
        return joueurEnCours;
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
}
