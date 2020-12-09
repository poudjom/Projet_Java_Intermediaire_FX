package ionis.projet.controller;

import ionis.projet.model.DataMapping;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Jfxmatiere implements Initializable {

    public TableView<Matiere> idTabMatiere;
    public TableColumn<Matiere, Integer> colId;
    public TableColumn<Matiere, String> colCode;
    public TableColumn<Matiere, String> colIntitule;
    public TableColumn<Matiere, Float> colVolH;
    public TableColumn<Matiere, String> colDescription;
    public Button btSupprimer;
    public Button btCreer;
    public Button btCreerValidation;

    ObservableList<Matiere> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String query  = "Select * from matiere";
            chargementTableau(query);
        }catch (Exception ex){
            System.out.println(ex);
        }

    }

    public void chargementTableau(String query){
        DataMapping dataMapping = new DataMapping();
        try {
            ResultSet rs = dataMapping.selection(query);
            while(rs.next()){
                observableList.add(new Matiere(rs.getInt("IDMATIERE"), rs.getString("CODE"),
                        rs.getString("INTITULE"), rs.getFloat("VOLUMEHORAIRE"),
                        rs.getString("DESCRIPTION")));
            }
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
            Logger.getLogger(Jfxmatiere.class.getName()).log(Level.SEVERE, null, throwables);
        }
        colId.setCellValueFactory(new PropertyValueFactory<>("idMatiere"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colIntitule.setCellValueFactory(new PropertyValueFactory<>("intitule"));
        colVolH.setCellValueFactory(new PropertyValueFactory<>("volumeHoraire"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        idTabMatiere.setItems(observableList);
        idTabMatiere.refresh();
        try {
            dataMapping.closeConnexion();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void onRafraichir(ActionEvent actionEvent) {
        //Requete pour ajouter le dernier élément crée à la liste
        String query;
        query = "select * from matiere";
        idTabMatiere.getItems().clear();
        chargementTableau(query);
        idTabMatiere.refresh();
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        Matiere matiere = idTabMatiere.getSelectionModel().getSelectedItem();
        //System.out.println(idTabMatiere.getSelectionModel().getSelectedItems().stream().count());
    }

    // Fonction qui permet de supprimer un matière
    public void onSupprimer(ActionEvent actionEvent) throws SQLException {
        String liste = "(", query;
        DataMapping dataMapping = new DataMapping();
        Optional<ButtonType> result;
        int i = 0;
        for ( i = 0; i<idTabMatiere.getSelectionModel().getSelectedItems().stream().count(); i++){
            liste = liste + idTabMatiere.getSelectionModel().getSelectedItems().get(i).getIdMatiere() + "," ;
        }
        liste = liste.substring(0,liste.length()-1) + ")";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Attention");
        alert.setHeaderText("");
        alert.setContentText("Voulez-vous vraiment supprimer cet élément?");
        result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            query = "delete from matiere where IDMATIERE in " + liste;
            i = dataMapping.suppression(query);
            dataMapping.closeConnexion();
            idTabMatiere.getItems().remove(idTabMatiere.getSelectionModel().getFocusedIndex());
            idTabMatiere.refresh();
            Alert confir = new Alert(Alert.AlertType.INFORMATION);
            confir.setTitle("Confirmation");
            confir.setHeaderText("");
            confir.setContentText("Suppression effectuée avec succès");
            confir.showAndWait();
        }
    }

    // lorsqu'on clique sur le bouton créer
    public void onCreer(ActionEvent actionEvent) throws IOException {
        Stage creerMatiere = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../vue/form_matiere.fxml"));
        creerMatiere.setTitle("Matière");
        creerMatiere.setScene(new Scene(root, 400, 270));
        creerMatiere.show();
    }

    public void onModifier(ActionEvent actionEvent) throws IOException {
        Alert alert;
        if (idTabMatiere.getSelectionModel().getSelectedItems().stream().count() >= 1) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/form_matiere.fxml"));
            Parent root1 = loader.load();
            Formmatiere formmatiere = loader.getController();
            formmatiere.chargerMatiere(idTabMatiere.getSelectionModel().getSelectedItem());
            Stage creerMatiere = new Stage();
            creerMatiere.setTitle("Matière");
            creerMatiere.setScene(new Scene(root1, 400, 270));
            creerMatiere.show();
        }
        else{
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText("");
            alert.setContentText("Sélectionnez l'élément à modifier");
            alert.showAndWait();
        }
    }
}
