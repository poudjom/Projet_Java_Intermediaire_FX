package ionis.projet.controller;

import ionis.projet.model.DataMapping;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

public class Formmatiere implements Initializable {

    public Button btCreerValidation;
    public TextField code;
    public TextField intitule;
    public TextField volumeHoraire;
    public TextField description;
    public TextField id;
    public Button idModifierValidation;
    public Label idEntete;

    ObservableList<Matiere> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idModifierValidation.setDisable(true);
        idEntete.setText("Créer une matière");
    }

    public void chargerMatiere(Matiere matiere){
        idEntete.setText("Modifier une matière");
        code.setText(matiere.getCode());
        id.setText(String.valueOf(matiere.getIdMatiere()));
        intitule.setText(matiere.getIntitule());
        volumeHoraire.setText(String.valueOf(matiere.getVolumeHoraire()));
        description.setText(matiere.getDescription());
        idModifierValidation.setDisable(false);
        btCreerValidation.setDisable(true);
    }

    // Cette fonction est appelé lorsque l'utilisateur clique sur le bouton valider pour valider la création d'une matière
    public void onCreerValidation(ActionEvent actionEvent) {
        int status;
        Alert alert;
        String query;
        DataMapping dataMapping = new DataMapping();
        try {
            query = "Insert into matiere (code, intitule, volumehoraire, description) values ('" +code.getText() + "'," +
                    "'"+intitule.getText()+"',"+ Double.parseDouble(volumeHoraire.getText()) +",'"+description.getText()+"')";
            status = dataMapping.insertion(query);
            dataMapping.closeConnexion();
            if (status == 1){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("");
                alert.setContentText("Création effectuée avec succès");
                alert.showAndWait();
                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            }
            else{
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erreur");
                alert.setHeaderText("");
                alert.setContentText("Une erreur s'est produite lors de la création");
                alert.showAndWait();
            }
        }catch (Exception ex){

        }
    }

    public void onModifierValidation(ActionEvent actionEvent){
        int status;
        Alert confirm;
        String query;
        DataMapping dataMapping = new DataMapping();
        try {
            query = "update matiere set code = '" +code.getText() + "', intitule = '" + intitule.getText()
                    +"', volumehoraire = "+ Double.parseDouble(volumeHoraire.getText())
                    +" , description = '"+description.getText()+"' where idmatiere = " + Integer.parseInt(id.getText());
            status = dataMapping.insertion(query);
            dataMapping.closeConnexion();
            if (status == 1){
                confirm = new Alert(Alert.AlertType.INFORMATION);
                confirm.setTitle("Confirmation");
                confirm.setHeaderText("");
                confirm.setContentText("Modification effectuée avec succès");
                confirm.showAndWait();
                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            }
            else{
                confirm = new Alert(Alert.AlertType.WARNING);
                confirm.setTitle("Erreur");
                confirm.setHeaderText("");
                confirm.setContentText("Une erreur s'est produite lors de la modification");
                confirm.showAndWait();
            }
        }catch (Exception ex){

        }
    }

}
