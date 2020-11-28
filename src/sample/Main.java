package sample;

import ionis.projet.model.DataMapping;
import ionis.projet.controller.Matiere;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.ResultSet;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../ionis/projet/vue/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();

        // Exemple d'insertion dans la base de données

        int retour;
        String query;

        query = "INSERT INTO classe(CODECLASSE, LIBELLE) " +
                "VALUES ('M2INFO','Master2 Informatique')";
        DataMapping dataMapping = new DataMapping();
        retour = dataMapping.insertion(query);
        dataMapping.closeConnexion();

        // Exemple de suppression dans la base de données
//        query = "Delete from classe ";
//        DataMapping dataMapping1 = new DataMapping();
//        retour = dataMapping1.suppression(query);
//        dataMapping1.closeConnexion();

        // Exemple de modification dans la base de données
//        query = "Update classe set CODECLASSE = 'M2DIGI', LIBELLE = 'Master 2 Digital' Where IDCLASSE = 4";
//        DataMapping dataMapping2 = new DataMapping();
//        retour = dataMapping2.modification(query);
//        dataMapping2.closeConnexion();

        query = "select * from classe where IDCLASSE > 2";
        DataMapping dataMapping3 = new DataMapping();
        ResultSet resultSet = dataMapping3.selection(query);
        while (resultSet.next()){
            System.out.println(resultSet.getString("CODECLASSE") + " => " +
                    resultSet.getString("LIBELLE"));
        }
        dataMapping3.closeConnexion();

        Matiere matiere = new Matiere("Maths", "Mathématiques", 12, "Je fais la mathématique");
        matiere.createMatiere(matiere);
        System.out.println(retour);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
