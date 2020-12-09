package ionis.projet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    public void onButton(ActionEvent actionEvent) throws IOException {
        Stage ecranMatiere = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../vue/jfxmatiere.fxml"));
        ecranMatiere.setTitle("Matière");
        ecranMatiere.setScene(new Scene(root, 700, 500));
        ecranMatiere.show();
    }

    public void onMiMatiere(ActionEvent actionEvent) throws IOException {
        Stage ecranMatiere = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../vue/jfxmatiere.fxml"));
        ecranMatiere.setTitle("Matière");
        ecranMatiere.setScene(new Scene(root, 750, 410));
        ecranMatiere.show();
    }

    public void OnMiSceance(ActionEvent actionEvent) throws IOException {
        Stage ecranMatiere = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../vue/jfxseance.fxml"));
        ecranMatiere.setTitle("Séance de cours");
        ecranMatiere.setScene(new Scene(root, 900, 500));
        ecranMatiere.show();
    }
}
