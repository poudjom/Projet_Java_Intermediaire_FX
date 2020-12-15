package ionis.projet.controller;

import ionis.projet.model.DataMapping;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Etudiant implements Initializable {
    private String matricule;
    private int idEtudiant;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private int telephone;

    public Etudiant(){
        super();
    }
    public Etudiant(String matricule, int idEtudiant, String nom, String prenom, String email, String adresse, int telephone){
        this.matricule = matricule;
        this.idEtudiant = idEtudiant;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse =  adresse;
        this.telephone = telephone;

    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    @FXML
    private TableView<Etudiant> table;

    @FXML
    private TableColumn<Etudiant, String> col_mat;

    @FXML
    private TableColumn<Etudiant, String> col_noms;

    @FXML
    private TableColumn<Etudiant, String > col_prenoms;

    @FXML
    private TableColumn<Etudiant, String> col_email;

    @FXML
    private TableColumn<Etudiant, String > col_adresse;

    @FXML
    private TableColumn<Etudiant, Integer> col_telephone;
    public ObservableList<Etudiant> data = FXCollections.observableArrayList();

    @FXML
    void ajouterEtudiant(ActionEvent event) {

    }

    @FXML
    void rafraichirEtudiant(ActionEvent event) {
        String sql = "select * from etudiant;";
        DataMapping dataMapping = new DataMapping();
        try {
            ResultSet rs = dataMapping.selection(sql);
            while (rs.next()){
                data.add(new Etudiant(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7)));
            }
            dataMapping.closeConnexion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        col_mat.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("matricule"));
        col_noms.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("nom"));
        col_prenoms.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("prenom"));
        col_email.setCellValueFactory(new PropertyValueFactory<Etudiant,String >("email"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<Etudiant,String >("adresse"));
        col_telephone.setCellValueFactory(new PropertyValueFactory<Etudiant,Integer>("telephone"));
        table.setItems(data);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
