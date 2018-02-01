/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s04.appgraphiquejse.views;

import static java.awt.Frame.MAXIMIZED_BOTH;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import s04.appgraphiquejse.main.Bdd;

import static s04.appgraphiquejse.main.S04AppGraphiqueJSE.lesArticles;
import static s04.appgraphiquejse.main.S04AppGraphiqueJSE.lesPersonnes;

/**
 *
 * @author Julian
 */
public class Fenetre implements ActionListener {

    public JButton ajouter;
    public JButton supprimer;
    public JFrame fenetre;
    public int indexP;
    public int indexA;

    public Fenetre(String titre) {
        indexP = -1;
        indexA = -1;
        fenetre = new JFrame();
        fenetre.setTitle(titre);
        fenetre.setBounds(0, 0, 1000, 500);
        fenetre.setDefaultCloseOperation(EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);
        JPanel pano = new JPanel(new GridBagLayout());
        JPanel panoBtn = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        JList listArticles = new JList(lesArticles.toArray());
        listArticles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listArticles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                indexA = listArticles.locationToIndex(evt.getPoint());
                if (evt.getClickCount() == 2) {
                    indexA = listArticles.locationToIndex(evt.getPoint());
                    fenetre.dispose();
                    FenetreDetail fenetreAjouter = new FenetreDetail(indexA, lesArticles.get(indexA));
                }
            }
        });
        pano.add(new JScrollPane(listArticles), c);
        c.gridx = 0;
        c.gridy = 1;
        JList listPersonnes = new JList(lesPersonnes.toArray());
        listPersonnes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPersonnes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                indexP = listPersonnes.locationToIndex(evt.getPoint());
                if (evt.getClickCount() == 2) {
                    indexP = listPersonnes.locationToIndex(evt.getPoint());
                    fenetre.dispose();
                    FenetreDetail fenetreAjouter = new FenetreDetail(indexP, lesPersonnes.get(indexP));
                }
            }
        });
        pano.add(new JScrollPane(listPersonnes), c);

        ajouter = new JButton("Ajouter");
        ajouter.addActionListener(this);
        supprimer = new JButton("Supprimer");
        supprimer.addActionListener(this);
        GridBagConstraints b = new GridBagConstraints();
        b.gridx = 0;
        b.gridy = 0;
        panoBtn.add(ajouter, b);
        b.gridx = 1;
        panoBtn.add(supprimer, b);
        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        pano.add(panoBtn, c);
        fenetre.add(pano);
        //fenetre.setExtendedState(MAXIMIZED_BOTH);
        fenetre.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ajouter) {
            fenetre.dispose();
            FenetreDetail fenetreAjouter = new FenetreDetail(-1, null);
        }
        if (e.getSource() == supprimer) {
            if (indexA != -1) {

                lesArticles.remove(indexA);
                System.out.println("Ici ok 115");
                fenetre.dispose();
                indexA = -1;
                Fenetre f = new Fenetre("Gestion des articles");
            }
            if (indexP != -1) {
                String nom = lesPersonnes.get(indexA).getNom();
                String prenom = lesPersonnes.get(indexA).getPrenom();
                lesPersonnes.remove(indexP);
                Bdd.delPersonne(nom,prenom);
                fenetre.dispose();
                indexP = -1;
                Fenetre f = new Fenetre("Gestion des articles");
            }
        }
    }
}
