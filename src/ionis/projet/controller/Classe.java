package ionis.projet.controller;

import ionis.projet.model.DataMapping;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Classe {

    @FXML Button btnCreateClass;
    @FXML TextField txtCodeClasse;
    @FXML TextField txtFiliere;
    @FXML TextArea txtLibelle;
    private int idClasse;
    private String codeClasse;
    private String libelle;
    private String filiere;


    public Classe(String codeClasse, String libelle, String filiere){
        this.codeClasse = txtCodeClasse.getText();
        this.libelle = txtLibelle.getText();
        this.filiere = txtFiliere.getText();
    }

    public String getCodeClasse(){
        return codeClasse;
    }
    public void setCodeClasse(String codeClasse){
        this.codeClasse = codeClasse;
    }

    public String getLibelle(){
        return libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }

    public  String getFiliere(){
        return filiere;
    }
    public void setFiliere(String filiere){
        this.filiere = filiere;
    }

    public int createClasse(Classe classe){

        String query;
        query = "insert into classe (codeclasse,libelle,filiere) values ('"+classe.getCodeClasse()+"','"+classe.getLibelle()+"','"+classe.getFiliere()+"')";
        DataMapping dataMapping = new DataMapping();

        try {
            return dataMapping.insertion(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }

    }
}
