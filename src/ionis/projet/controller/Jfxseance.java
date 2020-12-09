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
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Jfxseance implements Initializable {

    public Button btCreer;
    public Button idModifier;
    public Button btSupprimer;
    public Button btRafraichir;
    public TableColumn<Seance, LocalDate> colDateDebut;
    public TableColumn<Seance, Date> colDateFin;
    public TableColumn<Seance, String> colHeureFin;
    public TableColumn<Seance, String> colCommentaire;
    public TableColumn<Seance, String> colMatiere;
    public TableColumn<Seance, Integer> colIdSeance;
    public TableColumn<Seance, String> colHeureDebut;
    public TableColumn<Seance, Integer> colIdMatiere;
    public TableView<Seance> idTabSeance;
    public TableColumn<Seance, String> colClasse;
    public TableColumn<Seance, Integer>  colIdClasse;

    ObservableList<Seance> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String query = "SELECT * FROM seance as s, matiere as m, classe as c " +
                "WHERE s.IDMATIERE = m.IDMATIERE and s.idclasse = c.IDCLASSE";
        try{
            chargementTableau(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void chargementTableau(String query){
        DataMapping dataMapping = new DataMapping();
        try {
            ResultSet rs = dataMapping.selection(query);
            //System.out.println(rs.ge);
            while(rs.next()){
                observableList.add(new Seance(rs.getInt("idseance"), rs.getInt("idmatiere"),
                        rs.getInt("idclasse"), rs.getDate("datedebut").toLocalDate(),
                        rs.getDate("datefin").toLocalDate(), rs.getString("heuredebut"),
                        rs.getString("heurefin"),rs.getString("commentaire"),
                        rs.getString("intitule"),rs.getString("codeclasse")));
            }
        } catch (SQLException throwables) {
            Logger.getLogger(Jfxmatiere.class.getName()).log(Level.SEVERE, null, throwables);
        }
        colIdSeance.setCellValueFactory(new PropertyValueFactory<>("idSeance"));
        colIdMatiere.setCellValueFactory(new PropertyValueFactory<>("idMatiere"));
        colIdClasse.setCellValueFactory(new PropertyValueFactory<>("idClasse"));
        colMatiere.setCellValueFactory(new PropertyValueFactory<>("intituleMatiere"));
        colDateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        colHeureDebut.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
        colHeureFin.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
        colCommentaire.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        colClasse.setCellValueFactory(new PropertyValueFactory<>("intituleClasse"));
        idTabSeance.setItems(observableList);
        idTabSeance.refresh();
        try {
            dataMapping.closeConnexion();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void onModifier(ActionEvent actionEvent) throws IOException {
        Alert alert;
        if (idTabSeance.getSelectionModel().getSelectedItems().stream().count() >= 1) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/form_seance.fxml"));
            Parent root1 = loader.load();
            Formseance formmatiere = loader.getController();
            formmatiere.chargerSeance(idTabSeance.getSelectionModel().getSelectedItem());
            Stage creerSeance = new Stage();
            creerSeance.setTitle("Séance");
            creerSeance.setScene(new Scene(root1, 400, 340));
            creerSeance.show();
        }
        else{
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText("");
            alert.setContentText("Sélectionnez l'élément à modifier");
            alert.showAndWait();
        }
    }

    public void onCreer(ActionEvent actionEvent) throws IOException {
        Stage creerSeance = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../vue/form_seance.fxml"));
        creerSeance.setTitle("Matière");
        creerSeance.setScene(new Scene(root, 400, 340));
        creerSeance.show();
    }

    public void onSupprimer(ActionEvent actionEvent) throws SQLException {
        String liste = "(", query;
        DataMapping dataMapping = new DataMapping();
        Optional<ButtonType> result;
        int i = 0;
        for ( i = 0; i<idTabSeance.getSelectionModel().getSelectedItems().stream().count(); i++){
            liste = liste + idTabSeance.getSelectionModel().getSelectedItems().get(i).getIdSeance() + "," ;
        }

        liste = liste.substring(0,liste.length()-1) + ")";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Attention");
        alert.setHeaderText("");
        alert.setContentText("Voulez-vous vraiment supprimer cet élément?");
        result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            query = "delete from seance where idseance in " + liste;
            i = dataMapping.suppression(query);
            dataMapping.closeConnexion();
            idTabSeance.getItems().remove(idTabSeance.getSelectionModel().getFocusedIndex());
            idTabSeance.refresh();
            Alert confir = new Alert(Alert.AlertType.INFORMATION);
            confir.setTitle("Confirmation");
            confir.setHeaderText("");
            confir.setContentText("Suppression effectuée avec succès");
            confir.showAndWait();
        }
    }

    public void onRafraichir(ActionEvent actionEvent) {
        String query = "SELECT * FROM seance as s, matiere as m, classe as c " +
                "WHERE s.IDMATIERE = m.IDMATIERE and s.idclasse = c.IDCLASSE";
        idTabSeance.getItems().clear();
        chargementTableau(query);
        idTabSeance.refresh();
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
    }
}
