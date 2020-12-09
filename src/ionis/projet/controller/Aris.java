package ionis.projet.controller;

import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.SQLException;

public class Aris {
    public TextField intitule;
    public TextField code;
    public TextField volumeHoraire;
    public TextField description;

    public void onMatiereEnregistrer(ActionEvent actionEvent) throws SQLException {
        if (code.getText() != ""){
            Matiere matiere = new Matiere(code.getText(), intitule.getText(), 10, description.getText());
            matiere.createMatiere(matiere);
//            Stage dialog = new Stage();
//            dialog.initStyle(StageStyle.UTILITY);
//            Scene scene = new Scene(new Group(new Text(225, 35, "Opération effectué")));
//            dialog.setScene(scene);
//            dialog.show();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aristide");
            alert.showAndWait();
        }
    }
}
