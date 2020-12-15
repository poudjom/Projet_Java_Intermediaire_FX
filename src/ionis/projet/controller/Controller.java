package ionis.projet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {


    public void GestionClasse(ActionEvent actionEvent) {
        try {
            Stage stage1 = new Stage();
            Parent root1;
            root1 = FXMLLoader.load(getClass().getResource("/ionis/projet/vue/listeClasse.fxml"));
            stage1.setTitle("Gestion des classes");
            stage1.setScene(new Scene(root1));
            stage1.initModality(Modality.APPLICATION_MODAL);
            stage1.show();
            stage1.setResizable(false);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void GestionEtudiant(ActionEvent actionEvent) {
        try {
            Stage stage1 = new Stage();
            Parent root1;
            root1 = FXMLLoader.load(getClass().getResource("/ionis/projet/vue/listeEtudiant.fxml"));
            stage1.setTitle("Gestion des Etudiants");
            stage1.setScene(new Scene(root1));
            stage1.initModality(Modality.APPLICATION_MODAL);
            stage1.show();
            stage1.setResizable(false);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
