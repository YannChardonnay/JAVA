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
public class Livre extends Article{
    private String isbn;
    private int nbPages;
    private Auteur auteur;

    public Livre(String isbn, int nbPages, String reference, String designation, Double prix, Auteur auteur) {
        super(reference, designation, prix);
        this.isbn = isbn;
        this.nbPages = nbPages;
        this.auteur = auteur;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNbPages() {
        return nbPages;
    }

    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }
    
    @Override
    public String toString() {
        return super.toString()+"\n - "+this.isbn+"\n - "+this.nbPages;
    }
    
}
