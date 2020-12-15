package ionis.projet.controller;

import ionis.projet.model.DataMapping;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Classe implements Initializable {
    public TextField txtCodeClasse;
    public TextField txtFiliere;
    public TextArea txtLibelle;
    private int idClasse;
    private String codeClasse;
    private String libelle;
    private String filiere;

    public Classe(){
        super();
    }
    public Classe(int idClasse,String codeClasse, String libelle, String filiere){
        super();
        this.idClasse = idClasse;
        this.codeClasse = codeClasse;
        this.libelle = libelle;
        this.filiere = filiere;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    public String getCodeClasse() {
        return codeClasse;
    }

    public void setCodeClasse(String codeClasse) {
        this.codeClasse = codeClasse;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    @FXML
    private TableView<Classe> table;
    @FXML
    private TableColumn<Classe, Integer> col_id;
    @FXML
    private TableColumn<Classe, String> col_codeclasse;
    @FXML
    private TableColumn<Classe, String> col_libelle;
    @FXML
    private TableColumn<Classe, String > col_filiere;
    public ObservableList<Classe> data = FXCollections.observableArrayList();


    public void CreateClass(ActionEvent actionEvent) throws  SQLException {

        String sql = "insert into classe (codeclasse,libelle,filiere)values ('"+txtCodeClasse.getText()+"','"+txtLibelle.getText()+"','"+txtFiliere.getText()+"')";
        DataMapping dataMapping = new DataMapping();
        dataMapping.insertion(sql);
        dataMapping.closeConnexion();
    }

    @FXML
    public void rafraichirClasse(ActionEvent actionEvent) {

        String sql = "select * from classe;";
        DataMapping dataMapping3 = new DataMapping();
        try {
            ResultSet rs = dataMapping3.selection(sql);
            while (rs.next()){
                data.add(new Classe(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }
            dataMapping3.closeConnexion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        col_id.setCellValueFactory(new PropertyValueFactory<Classe,Integer>("idClasse"));
        col_codeclasse.setCellValueFactory(new PropertyValueFactory<Classe,String>("codeClasse"));
        col_libelle.setCellValueFactory(new PropertyValueFactory<Classe,String>("libelle"));
        col_filiere.setCellValueFactory(new PropertyValueFactory<Classe,String >("filiere"));
        table.setItems(data);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void ajouterClasse(ActionEvent actionEvent) {
        try {
            Stage stage1 = new Stage();
            Parent root1;
            root1 = FXMLLoader.load(getClass().getResource("/ionis/projet/vue/creationClasse.fxml"));
            stage1.setTitle("Ajouter une classe");
            stage1.setScene(new Scene(root1));
            stage1.initModality(Modality.APPLICATION_MODAL);
            stage1.show();
            stage1.setResizable(false);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
