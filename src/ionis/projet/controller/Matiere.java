package ionis.projet.controller;

import ionis.projet.model.DataMapping;

import java.sql.SQLException;

public class Matiere {
    private int idMatiere;
    private String code;
    private String intitule;
    private float volumeHoraire;
    private String description;

    // Contructeur sans clé
    public Matiere(String code, String intitule, float volumeHoraire, String description) {
        this.code = code;
        this.intitule = intitule;
        this.volumeHoraire = volumeHoraire;
        this.description = description;
    }

    // Constructeur avec clé
    public Matiere(int idMatiere, String code, String intitule, float volumeHoraire, String description) {
        this.idMatiere = idMatiere;
        this.code = code;
        this.intitule = intitule;
        this.volumeHoraire = volumeHoraire;
        this.description = description;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public float getVolumeHoraire() {
        return volumeHoraire;
    }

    public void setVolumeHoraire(float volumeHoraire) {
        this.volumeHoraire = volumeHoraire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Matiere{" +
                "idMatiere=" + idMatiere +
                ", code='" + code + '\'' +
                ", intitule='" + intitule + '\'' +
                ", volumeHoraire=" + volumeHoraire +
                ", description='" + description + '\'' +
                '}';
    }

    // Fonction pour la création d'une matière
    public int createMatiere(Matiere matiere){
        DataMapping dataMapping = new DataMapping();
        String query;
        query = "Insert into matiere (CODE, INTITULE, VOLUMEHORAIRE, DESCRIPTION) " +
                "values('" + matiere.getCode() + "','" + matiere.getIntitule() + "'," + matiere.getVolumeHoraire() + ",'"
                + matiere.getDescription() +"') ";
        try {
            return dataMapping.insertion(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
        //System.out.println(query);
    }

    // Modification d'une matière
//    public void modifierMatiere(Matiere matiere){
//        DataMapping dataMapping = new DataMapping();
//        String query;
//        query = "Update Matiere set ";
//    }
}
