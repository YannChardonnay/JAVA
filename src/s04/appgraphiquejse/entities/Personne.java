/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s04.appgraphiquejse.entities;

import java.time.LocalDate;

/**
 *
 * @author Julian
 */
public abstract class Personne {
    private String nom;
    private String prenom;
    private LocalDate dteNaissance;

    public Personne(String nom, String prenom, LocalDate dteNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dteNaissance = dteNaissance;
    }

    public LocalDate getDteNaissance() {
        return dteNaissance;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setDteNaissance(LocalDate dteNaissance) {
        this.dteNaissance = dteNaissance;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "\n"+this.nom+"\n "+this.prenom + "\n - "+this.dteNaissance;
    }
    
    
}
