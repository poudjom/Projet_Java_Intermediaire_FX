package ionis.projet.controller;

import ionis.projet.model.DataMapping;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Formseance implements Initializable {
    public ComboBox idMatiere;
    public TextField idHeureFin;
    public TextField idCommentaire;
    public DatePicker idDateDebut;
    public DatePicker idDateFin;
    public TextField idSeance;
    public TextField idHeureDebut;
    public Button btCreerValidation;
    public Button idModifierValidation;
    public Label idEntete;
    public ComboBox idClasse;

    ObservableList<Matiere> observableList = FXCollections.observableArrayList();
    ObservableList<String> matierelist = FXCollections.observableArrayList();
    ObservableList<String> classeList = FXCollections.observableArrayList();
    Map<Integer,Integer> listMatiereCRUD = new HashMap<>();
    Map<Integer,Integer> listMatiereChargement = new HashMap<>();
    Map<Integer,Integer> listClasseCRUD = new HashMap<>();
    Map<Integer,Integer> listClasseChargement = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String query = "select * from matiere";
        DataMapping dataMapping = new DataMapping();
        ResultSet rs;
        int i = 0;
        try {
            rs = dataMapping.selection(query);
            while (rs.next()){
                matierelist.add(rs.getString("code") + " -> " +rs.getString("intitule"));
                listMatiereChargement.put(rs.getInt("idmatiere"),i);
                listMatiereCRUD.put(i,rs.getInt("idmatiere"));
                i = i + 1;
            }

            query = "select * from classe";
            rs = dataMapping.selection(query);
            i = 0;
            while (rs.next()){
                classeList.add(rs.getString("codeclasse") + " -> " + rs.getString("libelle"));
                listClasseChargement.put(rs.getInt("idclasse"),i);
                listClasseCRUD.put(i,rs.getInt("idclasse"));
                i = i + 1;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        idModifierValidation.setDisable(true);
        idEntete.setText("Programmer un séance");
        idMatiere.setItems(matierelist);
        idClasse.setItems(classeList);
        //dataMapping.closeConnexion();
    }

    public void chargerSeance(Seance seance){
        idEntete.setText("Modifier une séance");
        idSeance.setText(String.valueOf(seance.getIdSeance()));
        idCommentaire.setText(seance.getCommentaire());
        idHeureDebut.setText(seance.getHeureDebut());
        idHeureFin.setText(seance.getHeureFin());
        idDateDebut.setValue(seance.getDateDebut());
        idModifierValidation.setDisable(false);
        btCreerValidation.setDisable(true);
        idMatiere.getSelectionModel().select(listMatiereChargement.get(seance.getIdMatiere()).intValue());
        idClasse.getSelectionModel().select(listClasseChargement.get(seance.getIdClasse()).intValue());
    }

    // Cette fonction est appelé lorsque l'utilisateur clique sur le bouton valider pour valider la création d'une matière
    public void onCreerValidation(ActionEvent actionEvent) {
        int status = 0;
        Alert alert;
        String query;
        DataMapping dataMapping = new DataMapping();
        try {
            query = "Insert into seance (IDMATIERE, DATEDEBUT,HEUREDEBUT, HEUREFIN, COMMENTAIRE, IDCLASSE) " +
                    "values (" + listMatiereCRUD.get(idMatiere.getSelectionModel().getSelectedIndex()) +
                    ",'"+idDateDebut.getValue()+"','" +idHeureDebut.getText()+"','" + idHeureFin.getText() +"','"
                    + idCommentaire.getText() +"'," + listClasseCRUD.get(idClasse.getSelectionModel().getSelectedIndex()) + ")";
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
            System.out.println(ex);
        }
    }

    public void onModifierValidation(ActionEvent actionEvent){
        int status = 0;
        Alert confirm;
        String query = "";
        DataMapping dataMapping = new DataMapping();
        try {
            query = "update seance set IDMATIERE = " + listMatiereCRUD.get(idMatiere.getSelectionModel().getSelectedIndex()).intValue()
                    + ", DATEDEBUT = '"+idDateDebut.getValue()+"',HEUREDEBUT = '" + idHeureDebut.getText() +
                    "', HEUREFIN = '" + idHeureFin.getText() + "', COMMENTAIRE = '" + idCommentaire.getText() +"' " +
                    ", idclasse = " + listClasseCRUD.get(idClasse.getSelectionModel().getSelectedIndex()) +
                    " Where idSeance = " + idSeance.getText();
            //System.out.println(query);
            status = dataMapping.modification(query);
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
