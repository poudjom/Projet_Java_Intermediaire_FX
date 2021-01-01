package ionis.projet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Controller {

    public void onButton(ActionEvent actionEvent) throws IOException {
        Stage ecranMatiere = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../vue/jfxmatiere.fxml"));
        ecranMatiere.setTitle("Matière");
        ecranMatiere.setScene(new Scene(root, 700, 500));
        ecranMatiere.show();
//        String d1,d2;
//        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
//        Date date = new Date();
//        d1 = format.format(date) + " 15:10";
//        d2 = format.format(date) + " 17:10";
//        Date
//        LocalDate date1 = LocalDate.parse(d1);
//        LocalDate date2 = LocalDate.parse(d2);
//        long diff = date1.get - date2.getTime();
//        long diffHours = diff/(60 **  60 **  1000) % 24;
//        System.out.println(d2);
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
