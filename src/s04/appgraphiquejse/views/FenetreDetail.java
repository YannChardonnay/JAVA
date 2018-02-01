/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s04.appgraphiquejse.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import s04.appgraphiquejse.entities.Article;
import s04.appgraphiquejse.entities.Auteur;
import s04.appgraphiquejse.entities.Dvd;
import s04.appgraphiquejse.entities.Livre;
import s04.appgraphiquejse.entities.Personne;
import s04.appgraphiquejse.entities.Realisateur;
import s04.appgraphiquejse.main.Bdd;
import static s04.appgraphiquejse.main.S04AppGraphiqueJSE.*;

/**
 *
 * @author Julian
 */
public class FenetreDetail implements ActionListener {

//<editor-fold desc="listes correspondantes aux combobox réalisateur & auteurs">
    public ArrayList<Realisateur> lesRealisateurs;
    public ArrayList<Auteur> lesAuteurs;
//</editor-fold>

//<editor-fold desc="variables utilisées en dehors du constructeur">
    public JFrame fenetre;
    public JRadioButton realisateur, auteur, dvd, livre;
    public JButton ajouter, annuler;
    public JPanel panoGlobal, panoRadio, panoBtn, panoPersonne, panoArticle, panoDvd, panoLivre;
    public JTextField nom, prenom, dte, reference, designation, prix, isbn, nbPages, duree;
    public JComboBox comboRealisateur, comboAuteur;
    public int indexMod;
//</editor-fold>

    public FenetreDetail(int index, Object typeList) {
        
//<editor-fold desc="génération des listes auteurs et realisateur + JFrame + Panel global">
        generateListPersonnes();
        indexMod = index;
        fenetre = new JFrame();
        fenetre.setTitle("Ajouter");
        fenetre.setBounds(0, 0, 1000, 500);
        fenetre.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);
        panoGlobal = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
//</editor-fold>

//<editor-fold desc="panel radio bouton pour selection type enregistrement">
        panoRadio = new JPanel(new GridBagLayout());
        realisateur = new JRadioButton("Réalisateur");
        realisateur.addActionListener(this);
        auteur = new JRadioButton("Auteur");
        auteur.addActionListener(this);
        dvd = new JRadioButton("Dvd");
        dvd.addActionListener(this);
        livre = new JRadioButton("Livre");
        livre.addActionListener(this);
        ButtonGroup group = new ButtonGroup();
        if (typeList instanceof Dvd) {
            dvd.setSelected(true);
        }
        if (typeList instanceof Livre) {
            livre.setSelected(true);
        }
        if (typeList instanceof Realisateur) {
            realisateur.setSelected(true);
        }
        if (typeList instanceof Auteur) {
            auteur.setSelected(true);
        }
        group.add(realisateur);
        group.add(auteur);
        group.add(dvd);
        group.add(livre);

        panoRadio.add(realisateur);
        panoRadio.add(auteur);
        panoRadio.add(dvd);
        panoRadio.add(livre);
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        panoGlobal.add(panoRadio, c);
//</editor-fold>

//<editor-fold desc="panel infos personnes - commun auteur et réalisateur">
        panoPersonne = new JPanel(new GridBagLayout());
        nom = new JTextField("Nom");
        prenom = new JTextField("Prénom");
        dte = new JTextField("XX/XX/XXXX");
        GridBagConstraints b = new GridBagConstraints();
        b.gridy = 0;
        b.weightx = 1.0;
        b.fill = GridBagConstraints.HORIZONTAL;

        if (typeList instanceof Personne) {
            nom.setText(((Personne) typeList).getNom());
            prenom.setText(((Personne) typeList).getPrenom());
            dte.setText(((Personne) typeList).getDteNaissance().toString());
        }

        panoPersonne.add(nom, b);
        b.gridy = 1;
        panoPersonne.add(prenom, b);
        b.gridy = 2;
        panoPersonne.add(dte, b);
        c.gridy = 1;
        panoPersonne.setVisible(false);
        panoGlobal.add(panoPersonne, c);
//</editor-fold>

//<editor-fold desc="panel infos articles - commun livre et dvd">
        panoArticle = new JPanel(new GridBagLayout());
        reference = new JTextField("Référence");
        designation = new JTextField("Désignation");
        prix = new JTextField("Prix");
        b = new GridBagConstraints();
        b.gridy = 0;
        b.weightx = 1.0;
        b.fill = GridBagConstraints.HORIZONTAL;
        if (typeList instanceof Article) {
            reference.setText(((Article) typeList).getReference());
            designation.setText(((Article) typeList).getDesignation());
            prix.setText(((Article) typeList).getPrix().toString());
        }
        panoArticle.add(reference, b);
        b.gridy = 1;
        panoArticle.add(designation, b);
        b.gridy = 2;
        panoArticle.add(prix, b);
        c.gridy = 1;
        panoArticle.setVisible(false);
        panoGlobal.add(panoArticle, c);
//</editor-fold>

//<editor-fold desc="panel infos dvd">
        panoDvd = new JPanel(new GridBagLayout());
        duree = new JTextField("Durée");
        comboRealisateur = new JComboBox(lesRealisateurs.toArray());
        b = new GridBagConstraints();
        b.gridy = 0;
        b.weightx = 1.0;
        b.fill = GridBagConstraints.HORIZONTAL;
        if (typeList instanceof Dvd) {
            duree.setText(String.valueOf(((Dvd) typeList).getDuree()));
            comboRealisateur.setSelectedItem(((Dvd) typeList).getRealisateur());
        }
        panoDvd.add(duree, b);
        b.gridy = 1;
        c.gridy = 2;
        panoDvd.add(comboRealisateur, b);
        panoDvd.setVisible(false);
        panoGlobal.add(panoDvd, c);
//</editor-fold>

//<editor-fold desc="panel infos livre">
        panoLivre = new JPanel(new GridBagLayout());
        isbn = new JTextField("isbn");
        nbPages = new JTextField("nombre de pages");
        comboAuteur = new JComboBox(lesAuteurs.toArray());
        b = new GridBagConstraints();
        b.gridy = 0;
        b.weightx = 1.0;
        b.fill = GridBagConstraints.HORIZONTAL;
        if (typeList instanceof Livre) {
            nbPages.setText(String.valueOf(((Livre) typeList).getNbPages()));
            isbn.setText(((Livre) typeList).getIsbn());
            comboAuteur.setSelectedItem(((Livre) typeList).getAuteur());
        }
        panoLivre.add(isbn, b);
        b.gridy = 1;
        panoLivre.add(nbPages, b);
        b.gridy = 2;
        panoLivre.add(comboAuteur, b);
        panoLivre.setVisible(false);
        c.gridy = 3;
        panoGlobal.add(panoLivre, c);
//</editor-fold>

//<editor-fold desc="panel boutons ajouter et annuler">
panoBtn = new JPanel(new GridBagLayout());
ajouter = new JButton("Ajouter");
ajouter.addActionListener(this);
annuler = new JButton("Annuler");
annuler.addActionListener(this);
c.gridy = 4;
if (index != -1) {
    ajouter.setText("Modifier");
}
panoBtn.add(ajouter);
panoBtn.add(annuler);
panoBtn.setVisible(true);
panoGlobal.add(panoBtn, c);
//</editor-fold>

//<editor-fold desc="affichage des infos si entrée en modification">
fenetre.add(panoGlobal);
// fenetre.setExtendedState(MAXIMIZED_BOTH);
fenetre.setVisible(true);
if (typeList instanceof Livre) {
    panoArticle.setVisible(true);
    panoLivre.setVisible(true);
    panoRadio.setEnabled(false);
    
}
if (typeList instanceof Dvd) {
    panoArticle.setVisible(true);
    panoDvd.setVisible(true);
    panoRadio.setEnabled(false);
}
if (typeList instanceof Personne) {
    panoPersonne.setVisible(true);
}
if (index != -1) {
    dvd.setEnabled(false);
    livre.setEnabled(false);
    realisateur.setEnabled(false);
    auteur.setEnabled(false);
}
//</editor-fold>
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ajouter) {
            if (realisateur.isSelected()) {
                if (indexMod != -1) {
                    lesPersonnes.remove(indexMod);
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                LocalDate localDate = LocalDate.parse(dte.getText(), formatter);
                lesPersonnes.add(new Realisateur(nom.getText(), prenom.getText(), localDate));
                redirect();
            }
            if (auteur.isSelected()) {
                if (indexMod != -1) {
                    lesPersonnes.remove(indexMod);
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                LocalDate localDate = LocalDate.parse(dte.getText(), formatter);
                
                Bdd.createAuteur(new Auteur(nom.getText(), prenom.getText(), localDate));
                System.out.println("Oui");
                redirect();
            }
            if (dvd.isSelected()) {
                if (indexMod != -1) {
                    lesArticles.remove(indexMod);
                }
                lesArticles.add(new Dvd(Integer.valueOf(duree.getText()), reference.getText(), designation.getText(), Double.valueOf(prix.getText()), (Realisateur) comboRealisateur.getSelectedItem()));
                redirect();
            }
            if (livre.isSelected()) {
                if (indexMod != -1) {
                    lesArticles.remove(indexMod);
                }
                lesArticles.add(new Livre(isbn.getText(), Integer.valueOf(nbPages.getText()), reference.getText(), designation.getText(), Double.valueOf(prix.getText()), (Auteur) comboAuteur.getSelectedItem()));
                redirect();
            }
        }
        if (e.getSource() == annuler) {
            redirect();
        }
        //radio bouton
        if (e.getSource() == realisateur) {
            clearAll();
            panoPersonne.setVisible(true);
        }
        if (e.getSource() == auteur) {
            clearAll();
            panoPersonne.setVisible(true);
        }
        if (e.getSource() == dvd) {
            clearAll();
            panoArticle.setVisible(true);
            panoDvd.setVisible(true);
        }
        if (e.getSource() == livre) {
            clearAll();
            panoArticle.setVisible(true);
            panoLivre.setVisible(true);
        }
    }

    public void clearAll() {
        panoPersonne.setVisible(false);
        panoArticle.setVisible(false);
        panoDvd.setVisible(false);
        panoLivre.setVisible(false);
    }

    public void generateListPersonnes() {
        lesRealisateurs = new ArrayList<>();
        lesAuteurs = new ArrayList<>();
        lesPersonnes.forEach((p) -> {
            if (p instanceof Realisateur) {
                lesRealisateurs.add((Realisateur) p);
            } else {
                lesAuteurs.add((Auteur) p);
            }
        });
    }

    public void redirect() {
        fenetre.dispose();
        Fenetre f = new Fenetre("Gestion des articles");
    }
}
