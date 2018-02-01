/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s04.appgraphiquejse.entities;

/**
 *
 * @author Julian
 */
public class Dvd extends Article{
    private int duree;
    private Realisateur realisateur;
    
    public Dvd(int duree, String reference, String designation, Double prix, Realisateur realisateur) {
        super(reference, designation, prix);
        this.duree = duree;
        this.realisateur = realisateur;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setRealisateur(Realisateur realisateur) {
        this.realisateur = realisateur;
    }

    public Realisateur getRealisateur() {
        return realisateur;
    }

    @Override
    public String toString() {
        return super.toString()+"\n - "+this.duree+" min";
    }
    
}
