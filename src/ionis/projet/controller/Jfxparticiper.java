package ionis.projet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Jfxparticiper implements Initializable {
    public TableColumn colIdSeance;
    public TableColumn colIdClasse;
    public TableColumn colIdMatiere;
    public TableColumn colClasse;
    public TableColumn colMatiere;
    public TableColumn colHeureDebut;
    public TableColumn colHeureFin;
    public TableColumn colEtat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
    }

    public void onCreer(ActionEvent actionEvent) {
    }

    public void onModifier(ActionEvent actionEvent) {
    }

    public void onSupprimer(ActionEvent actionEvent) {
    }

    public void onRafraichir(ActionEvent actionEvent) {
    }

    public void onInscrire(ActionEvent actionEvent) {
    }
}
