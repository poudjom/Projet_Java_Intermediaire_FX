package ionis.projet.controller;

import java.time.LocalDate;

public class Seance {
    private int idSeance;
    private int idMatiere;
    private int idClasse;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String heureDebut;
    private String heureFin;
    private String commentaire;
    private String intituleMatiere;
    private String intituleClasse;

    public Seance(int idSeance, int idMatiere, int idClasse, LocalDate dateDebut, LocalDate dateFin, String heureDebut, String heureFin, String commentaire, String intituleMatiere, String intituleClasse) {
        this.idSeance = idSeance;
        this.idMatiere = idMatiere;
        this.idClasse = idClasse;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.commentaire = commentaire;
        this.intituleMatiere = intituleMatiere;
        this.intituleClasse = intituleClasse;
    }

    public Seance(int idMatiere, int idClasse, LocalDate dateDebut, LocalDate dateFin, String heureDebut, String heureFin, String commentaire, String intituleMatiere, String intituleClasse) {
        this.idMatiere = idMatiere;
        this.idClasse = idClasse;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.commentaire = commentaire;
        this.intituleMatiere = intituleMatiere;
        this.intituleClasse = intituleClasse;
    }

    public Seance() {

    }

    public int getIdSeance() {
        return idSeance;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getIntituleMatiere() {
        return intituleMatiere;
    }


    public void setIdSeance(int idSeance) {
        this.idSeance = idSeance;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setIntituleMatiere(String intituleMatiere) {
        this.intituleMatiere = intituleMatiere;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public String getIntituleClasse() {
        return intituleClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    public void setIntituleClasse(String intituleClasse) {
        this.intituleClasse = intituleClasse;
    }
}
