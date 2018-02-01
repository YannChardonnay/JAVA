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
public abstract class Article {
    private String reference;
    private String designation;
    private Double prix;

    public Article(String reference, String designation, Double prix) {
        this.reference = reference;
        this.designation = designation;
        this.prix = prix;
    }

    public String getDesignation() {
        return designation;
    }

    public Double getPrix() {
        return prix;
    }

    public String getReference() {
        return reference;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "\n"+this.reference+"\n - "+this.designation +"\n  - "+this.prix+" €" ;
    }
    
    
}
