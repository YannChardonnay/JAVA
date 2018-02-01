/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s04.appgraphiquejse.main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import s04.appgraphiquejse.entities.Auteur;
import s04.appgraphiquejse.entities.Dvd;
import s04.appgraphiquejse.entities.Livre;
import s04.appgraphiquejse.entities.Realisateur;

/**
 *
 * @author yannchardonnay
 */
public final class Bdd {

    private static Connection cnxDirect;
    private static Statement stat;
    private static ResultSet res;

    public static void setClassForName(String classForName) {
        try {
            Class.forName(classForName);
            System.out.println("Driver chargé avec succès");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur pendant le chargement du pilote");
        }

    }

    public static void setConnection(String host, String port, String db, String user, String mdp) {
        try {
            cnxDirect = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db + "?user=" + user + "&password=" + mdp);
            System.out.println("Connexion établie avec succès --> bdd : " + db + " user : " + user);
        } catch (SQLException e) {
            System.out.println("Erreur pendant la connexion : " + e);
        }

    }

    public static void createAuteur(Auteur lAuteur) {
        try {
            stat = cnxDirect.createStatement();
            System.out.println("Objet Statement créé avec succès");
        } catch (SQLException e) {
            System.out.println("Erreur pendant la création de l'objet Statement : " + e);
        }
        try {
            stat.execute("INSERT INTO personne("
                    + "Nom, Prenom, DATE_NAISSANCE) VALUES ('"
                    + lAuteur.getNom() + "','" + lAuteur.getPrenom() + "','" + "DATE_NAISSANCE" + "')");
            System.out.println("Exécution de la requête avec succès");
        } catch (SQLException e) {
            System.out.println("Erreur pendant l'éxecution de la requête : " + e);
        }

    }

    public static void getAllAuteur() {
        try {
            stat = cnxDirect.createStatement();
            System.out.println("Objet Statement créé avec succès (AUTEUR)");
        } catch (SQLException e) {
            System.out.println("Erreur pendant la création de l'objet Statement : " + e);
        }
        try {
            res = stat.executeQuery("SELECT * FROM PERSONNE, AUTEUR WHERE PERSONNE.ID_PERSONNE = AUTEUR.FK_ID_PERSONNE");
            System.out.println("Exécution du select avec succès sur la table AUTEUR");
            while (res.next()) {
                Auteur lAuteur = new Auteur(res.getString("NOM"), res.getString("PRENOM"), res.getDate("DATE_NAISSANCE").toLocalDate());
                S04AppGraphiqueJSE.lesPersonnes.add(lAuteur);
            }
        } catch (SQLException e) {
            System.out.println("Erreur pendant l'exécution du select sur la table projet : " + e);
        }
    }

    public static void getAllRealisateur() {
        try {
            stat = cnxDirect.createStatement();
            System.out.println("Objet Statement créé avec succès (REALISATEUR)");
        } catch (SQLException e) {
            System.out.println("Erreur pendant la création de l'objet Statement : " + e);
        }
        try {
            res = stat.executeQuery("SELECT * FROM PERSONNE, REALISATEUR WHERE PERSONNE.ID_PERSONNE = REALISATEUR.FK_ID_PERSONNE");
            System.out.println("Exécution du select avec succès sur la table REALISATEUR");
            while (res.next()) {
                Realisateur leRealisateur = new Realisateur(res.getString("NOM"), res.getString("PRENOM"), res.getDate("DATE_NAISSANCE").toLocalDate());
                S04AppGraphiqueJSE.lesPersonnes.add(leRealisateur);
            }
        } catch (SQLException e) {
            System.out.println("Erreur pendant l'exécution du select sur la table projet : " + e);
        }
    }

    public static void getAllLivre() {
        try {
            stat = cnxDirect.createStatement();
            System.out.println("Objet Statement créé avec succès (LIVRE)");
        } catch (SQLException e) {
            System.out.println("Erreur pendant la création de l'objet Statement : " + e);
        }
        try {
            res = stat.executeQuery("SELECT * FROM livre INNER JOIN article ON FK_ID_Article = ID_Article INNER JOIN auteur ON FK_ID_Auteur = ID_Auteur INNER JOIN personne ON FK_ID_Personne = ID_Personne");
            System.out.println("Exécution du select avec succès sur la table LIVRE");
            while (res.next()) {
                Livre leLivre = new Livre(res.getString("NUMERO_ISBN"), res.getInt("NB_PAGES"), res.getString("REFERENCE"), res.getString("DESIGNATION"), res.getDouble("PRIX"), new Auteur(res.getString("NOM"), res.getString("PRENOM"), res.getDate("DATE_NAISSANCE").toLocalDate()));
                S04AppGraphiqueJSE.lesArticles.add(leLivre);
            }
        } catch (SQLException e) {
            System.out.println("Erreur pendant l'exécution du select sur la table projet : " + e);
        }
    }

    public static void getAllDvd() {
        try {
            stat = cnxDirect.createStatement();
            System.out.println("Objet Statement créé avec succès (DVD)");
        } catch (SQLException e) {
            System.out.println("Erreur pendant la création de l'objet Statement : " + e);
        }
        try {
            res = stat.executeQuery("SELECT * FROM DVD INNER JOIN ARTICLE ON FK_ID_Article = ID_Article INNER JOIN REALISATEUR ON FK_ID_REALISATEUR = ID_REALISATEUR INNER JOIN PERSONNE ON FK_ID_Personne = ID_Personne");
            System.out.println("Exécution du select avec succès sur la table DVD");
            while (res.next()) {
                Dvd leDvd = new Dvd(res.getInt("DUREE"), res.getString("REFERENCE"), res.getString("DESIGNATION"), res.getDouble("PRIX"), new Realisateur(res.getString("NOM"), res.getString("PRENOM"), res.getDate("DATE_NAISSANCE").toLocalDate()));
                S04AppGraphiqueJSE.lesArticles.add(leDvd);
            }
        } catch (SQLException e) {
            System.out.println("Erreur pendant l'exécution du select sur la table projet : " + e);
        }
    }

    public static void delPersonne(String nom, String prenom) {
        try {
            stat = cnxDirect.createStatement();
            System.out.println("Objet Statement créé avec succès (DVD)");

        } catch (Exception e) {
            System.out.println("Erreur pendant la création de l'objet Statement : " + e);
        }
        try {
            res = stat.executeQuery("DELETE FROM PERSONNE WHERE nom ="+ nom + "AND PRENOM = " + prenom);
        } catch (Exception e) {
        }
    }

}
