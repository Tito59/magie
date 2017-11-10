/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magiemagie;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Formation
 */
public class Joueur extends Jeu {

    private String nom;
    private List<Carte> cartes = new ArrayList<>();

    public Joueur() {
    }

    public Joueur(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Nom:" + this.nom + " Cartes:" + this.cartes;
    }

    public void afficheJoueur() {
        System.out.println(getNom() + "\n" + "Carte" + cartes);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Carte> getCartes() {
        return cartes;
    }

    public void setCartes(List<Carte> cartes) {
        this.cartes = cartes;
    }
}
