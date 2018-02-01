/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s04.appgraphiquejse.main;

import java.time.LocalDate;
import java.util.ArrayList;
import s04.appgraphiquejse.entities.*;
import s04.appgraphiquejse.views.*;

/**
 *
 * @author Julian
 */
public class S04AppGraphiqueJSE {

    public static ArrayList<Article> lesArticles;
    public static ArrayList<Personne> lesPersonnes;

    public static String classForName = "com.mysql.jdbc.Driver";
    public static String host = "localhost";
    public static String port = "8889";
    public static String db = "s04_projet";
    public static String user = "root";
    public static String mdp = "root";

    //  public static String createDatabase = "CREATE DATABASE S04_mediatheque";
    public static String createPersonneSql = "CREATE TABLE PERSONNE("
            + "ID_PERSONNE INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
            + "NOM VARCHAR(255) NOT NULL, "
            + "PRENOM VARCHAR(255) NOT NULL,"
            + "DATE_NAISSANCE DATE NOT NULL "
            + ")";
    public static String createArticleSql = "CREATE TABLE ARTICLE("
            + "ID_ARTICLE INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
            + "REFERENCE VARCHAR(255) NOT NULL, "
            + "DESIGNATION VARCHAR(255) NOT NULL,"
            + "PRIX INT NOT NULL "
            + ")";

    public static String createAuteurSql = "CREATE TABLE AUTEUR("
            + "ID_AUTEUR INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
            + "FK_ID_PERSONNE INT,"
            + "FOREIGN KEY (FK_ID_PERSONNE)"
            + "REFERENCES PERSONNE(ID_PERSONNE)"
            + ")";

    public static String createRealisateurSql = "CREATE TABLE REALISATEUR("
            + "ID_REALISATEUR INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
            + "FK_ID_PERSONNE INT,"
            + "FOREIGN KEY (FK_ID_PERSONNE)"
            + "REFERENCES PERSONNE(ID_PERSONNE)"
            + ")";

    public static String createDvdSql = "CREATE TABLE DVD("
            + "ID_DVD INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
            + "DUREE INT NOT NULL,"
            + "FK_ID_ARTICLE INT,"
            + "FOREIGN KEY (FK_ID_ARTICLE)"
            + "REFERENCES ARTICLE(ID_ARTICLE),"
            + "FK_ID_REALISATEUR INT,"
            + "FOREIGN KEY (FK_ID_REALISATEUR)"
            + "REFERENCES REALISATEUR(ID_REALISATEUR)"
            + ")";

    public static String createLivreSql = "CREATE TABLE LIVRE("
            + "ID_LIVRE INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
            + "NUMERO_ISBN VARCHAR(255),"
            + "NB_PAGES INT NOT NULL,"
            + "FK_ID_ARTICLE INT,"
            + "FOREIGN KEY (FK_ID_ARTICLE)"
            + "REFERENCES ARTICLE(ID_ARTICLE),"
            + "FK_ID_AUTEUR INT,"
            + "FOREIGN KEY (FK_ID_AUTEUR)"
            + "REFERENCES AUTEUR(ID_AUTEUR)"
            + ")";
   
    public static void main(String[] args) {
        lesArticles = new ArrayList<>();
        lesPersonnes = new ArrayList<>();

        /*  Realisateur real1 = new Realisateur("Lucas", "Georges", LocalDate.of(1977, 10, 01));
        Dvd dvd1 = new Dvd(120, "AD45NB", "Star Wars", 19.99, real1);
        real1.getLesDvds().add(dvd1);
        dvd1.setRealisateur(real1);
        Auteur aut1 = new Auteur("De Saint Exupery", "Antoine", LocalDate.of(1966, 11, 14));
        Livre livre1 = new Livre("A12-45-78", 42, "AB45ML65", "Le Petit Prince", 7.99, aut1);
        aut1.getLesLivres().add(livre1);
        livre1.setAuteur(aut1);

        System.out.println(dvd1.getRealisateur());
        System.out.println(real1.getLesDvds());
        System.out.println(livre1.getAuteur());
        System.out.println(aut1.getLesLivres());

        lesArticles.add(dvd1);
        lesArticles.add(livre1);
        lesPersonnes.add(real1);
        lesPersonnes.add(aut1);
         */
        Bdd.setClassForName(classForName);
        Bdd.setConnection(host, port, db, user, mdp);
       // Bdd.createDatabase(createDatabase);
       /* Bdd.createTable(createPersonneSql);
        Bdd.createTable(createAuteurSql);
        Bdd.createTable(createRealisateurSql);
        Bdd.createTable(createArticleSql);
        Bdd.createTable(createDvdSql);
        Bdd.createTable(createLivreSql); */
       
       Bdd.getAllAuteur();
       Bdd.getAllRealisateur();
       Bdd.getAllLivre();
       Bdd.getAllDvd();

        Fenetre f = new Fenetre("Gestion des articles");
    }

}
