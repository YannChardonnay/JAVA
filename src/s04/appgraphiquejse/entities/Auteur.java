/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s04.appgraphiquejse.entities;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Julian
 */
public class Auteur extends Personne{
    private ArrayList<Livre> lesLivres;

    public Auteur(String nom, String prenom, LocalDate dteNaissance) {
        super(nom, prenom, dteNaissance);
        this.lesLivres = new ArrayList<>();
    }

    public ArrayList<Livre> getLesLivres() {
        return lesLivres;
    }

    public void setLesLivres(ArrayList<Livre> lesLivres) {
        this.lesLivres = lesLivres;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    
}
