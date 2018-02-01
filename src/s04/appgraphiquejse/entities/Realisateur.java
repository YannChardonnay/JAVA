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
public class Realisateur extends Personne{
    private ArrayList<Dvd> lesDvds;

    public Realisateur(String nom, String prenom, LocalDate dteNaissance) {
        super(nom, prenom, dteNaissance);
        this.lesDvds = new ArrayList<>();
    }

    public ArrayList<Dvd> getLesDvds() {
        return lesDvds;
    }

    public void setLesDvds(ArrayList<Dvd> lesDvds) {
        this.lesDvds = lesDvds;
    }

    @Override
    public String toString() {
        return super.toString(); 
    }
    
    
}
