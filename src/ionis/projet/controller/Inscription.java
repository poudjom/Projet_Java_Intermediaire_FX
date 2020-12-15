package ionis.projet.controller;

import javax.swing.*;
import java.time.LocalDate;

public class Inscription extends Seance {
    private String matricule;
    private String nom;
    private String prenom;
    private String email;
    private String codeClasse;
    private String telephone;
    private String filiere;
    private String intituleClasse;
    private int idClasse;
    private String etat;

    public Inscription(int idSeance, int idMatiere, int idClasse, LocalDate dateDebut, LocalDate dateFin, String heureDebut, String heureFin, String commentaire, String intituleMatiere, String intituleClasse, String matricule, String nom, String prenom, String email, int etat) {
        super(idSeance,idMatiere,dateDebut,dateFin,heureDebut,heureFin,commentaire,intituleMatiere,intituleClasse);
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.idClasse = idClasse;
        if (etat == 1 ){
            this.etat = "Présent";
        }else{
            this.etat = "Absent";
        }
    }

    public Inscription(int idClasse, String intituleClasse, String matricule, String nom, String prenom, String email,
                       String codeClasse, String telephone, String filiere, int etat) {
        this.idClasse = idClasse;
        this.intituleClasse = intituleClasse;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.codeClasse = codeClasse;
        this.telephone = telephone;
        this.filiere = filiere;
        if (etat == 1 ){
            this.etat = "Présent";
        }else{
            this.etat = "Absent";
        }
    }

    public String getIntituleClasse() {
        return intituleClasse;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setIntituleClasse(String intituleClasse) {
        this.intituleClasse = intituleClasse;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodeClasse() {
        return codeClasse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setCodeClasse(String codeClasse) {
        this.codeClasse = codeClasse;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        if (etat == 1 ){
            this.etat = "Présent";
        }else{
            this.etat = "Absent";
        }
    }
}
