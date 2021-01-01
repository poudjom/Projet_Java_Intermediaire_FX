package ionis.projet.controller;

import ionis.projet.model.DataMapping;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Jfxinscription implements Initializable {

    public TableView<Inscription> idTabInscrire;
    public TableColumn<Seance, Integer> colIdSeance;
    public TableColumn<Seance, Integer> colIdMatiere;
    public TableColumn<Seance, String> colMatricule;
    public TableColumn<Seance, String> colNom;
    public TableColumn<Seance, String> colClasse;
    public TableColumn<Seance, Integer>  colIdClasse;
    public TableColumn<Seance, String>  colEtat;
    public TableColumn colInscrire;
    public Button btInscrire;
    public TextField idRechercher;
    public CheckBox inscrire = new CheckBox();
    public TextField idSeance;
    public TextField idmatiere;
    public Label idMatiere;
    public Label idDate;
    public Label idPeriode;
    public ComboBox txtClasse;
    public static int codeSeance;
    public MenuItem mItemPresence;
    public MenuItem mItemAbsence;

    ObservableList<Inscription> observableList = FXCollections.observableArrayList();
    Map<Integer,Integer> listClasseCRUD = new HashMap<>();
    Map<Integer,Integer> listClasseChargement = new HashMap<>();
    ObservableList<String> classeList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String query;
        query = "SELECT * FROM participer as p, etudiant as e, classe as c " +
                "WHERE p.MATRICULE = e.MATRICULE and e.IDCLASSE = c.IDCLASSE and p.IDSEANCE = " + codeSeance;
        try{
            chargementTableau(query);
        }catch (Exception ex){
            System.out.println(ex);
        }

        DataMapping dataMapping = new DataMapping();
        ResultSet rs;
        int i = 0;
        try {
            rs = dataMapping.selection(query);
            query = "select * from classe";
            rs = dataMapping.selection(query);
            while (rs.next()){
                classeList.add(rs.getString("codeclasse") + " -> " + rs.getString("libelle"));
                listClasseChargement.put(rs.getInt("idclasse"),i);
                listClasseCRUD.put(i,rs.getInt("idclasse"));
                i = i + 1;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        txtClasse.setItems(classeList);
    }

    public void chargementTableau(String query){
        DataMapping dataMapping = new DataMapping();
        try {
            ResultSet rs = dataMapping.selection(query);
            while(rs.next()){
                observableList.add(new Inscription(rs.getInt("idclasse"), rs.getString("libelle"),
                        rs.getString("matricule"), rs.getString("nom") + " " +
                        rs.getString("prenom"), rs.getString("prenom"),
                        rs.getString("email"), rs.getString("codeclasse"),
                        rs.getString("telephone"),
                        rs.getString("filiere"), rs.getInt("etat")));
            }
        } catch (SQLException throwables) {
            Logger.getLogger(Jfxmatiere.class.getName()).log(Level.SEVERE, null, throwables);
        }
        colIdClasse.setCellValueFactory(new PropertyValueFactory<>("idclasse"));
        colMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colClasse.setCellValueFactory(new PropertyValueFactory<>("intituleClasse"));
        colEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        idTabInscrire.setItems(observableList);
        idTabInscrire.refresh();
        try {
            dataMapping.closeConnexion();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void onColInscrire(MouseEvent mouseEvent){
        System.out.println("toto");
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        if(idTabInscrire.getSelectionModel().getSelectedItem().getEtat() == "Présent"){
            mItemPresence.setVisible(false);
            mItemAbsence.setVisible(true);
        }
        else if(idTabInscrire.getSelectionModel().getSelectedItem().getEtat() == "Absent"){
            mItemAbsence.setVisible(false);
            mItemPresence.setVisible(true);
        }
    }

    public void onInscrire(ActionEvent actionEvent) throws SQLException {
        String query;
        Alert alert;
        DataMapping dataMapping = new DataMapping();
        DataMapping dataMapping1 = new DataMapping();
        DataMapping dataMappingInsertion = new DataMapping();
        ResultSet rs, rs1;
        int retour = 0, nbreInscription = 0;
        query = "select * from etudiant where idclasse  = " + listClasseCRUD.get(txtClasse.getSelectionModel().getSelectedIndex());
        rs =dataMapping.selection(query);
        while (rs.next()){
            query = "select count(*) as nbre from participer where matricule = '" + rs.getString("matricule") + "' and idseance = " + idSeance.getText();
            rs1 = dataMapping1.selection(query);
            while(rs1.next()){
                retour = rs1.getInt("nbre");
            }
            if (retour <= 0 ) {
                query = "insert into participer (matricule, idseance, etat) values " +
                        "('" + rs.getString("matricule") + "', " + idSeance.getText() + ", 1)";
                dataMappingInsertion.insertion(query);
                nbreInscription += 1;
            }
            //System.out.println(query);
        }
        dataMapping.closeConnexion();
        dataMapping1.closeConnexion();
        dataMappingInsertion.closeConnexion();
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("");
        alert.setContentText(nbreInscription + " inscription(s) effectuée(s)");
        alert.showAndWait();

        query = "SELECT * FROM participer as p, etudiant as e, classe as c " +
                "WHERE p.MATRICULE = e.MATRICULE and e.IDCLASSE = c.IDCLASSE and p.IDSEANCE = " + codeSeance;
        try{
            idTabInscrire.getItems().clear();
            chargementTableau(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
        //((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }


    public void initVariable(Seance selectedItem) {
        //codeSeance = String.valueOf(selectedItem.getIdSeance());
        idMatiere.setText(selectedItem.getIntituleMatiere());
        idPeriode.setText(selectedItem.getHeureDebut() + " - " + selectedItem.getHeureFin());
        idDate.setText(String.format(selectedItem.getDateDebut().toString(), "dd/mm/yyyy"));
        idmatiere.setText(String.valueOf(selectedItem.getIdMatiere()));
        idSeance.setText(String.valueOf(selectedItem.getIdSeance()));
    }


    public void Onsupprimer(ActionEvent actionEvent) throws SQLException {
        String query;
        String liste = "(";
        DataMapping dataMapping = new DataMapping();
        Optional<ButtonType> result;
        int i = 0;
        for ( i = 0; i<idTabInscrire.getSelectionModel().getSelectedItems().stream().count(); i++){
            liste = liste + "'" + idTabInscrire.getSelectionModel().getSelectedItems().get(i).getMatricule() + "'," ;
        }

        liste = liste.substring(0,liste.length()-1) + ")";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Attention");
        alert.setHeaderText("");
        alert.setContentText("Voulez-vous vraiment supprimer cet élément?");
        result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            query = "delete from participer where matricule in " + liste +" and " +
                    "IDSEANCE = " +idSeance.getText() ;
            i = dataMapping.suppression(query);
            dataMapping.closeConnexion();
            idTabInscrire.getItems().remove(idTabInscrire.getSelectionModel().getFocusedIndex());
            idTabInscrire.refresh();
            Alert confir = new Alert(Alert.AlertType.INFORMATION);
            confir.setTitle("Confirmation");
            confir.setHeaderText("");
            confir.setContentText("Suppression effectuée avec succès");
            confir.showAndWait();
        }
    }

    public void OnPresence(ActionEvent actionEvent) throws SQLException {
        String query;
        DataMapping dataMapping = new DataMapping();
        if (idTabInscrire.getSelectionModel().getSelectedItem().getEtat() == "Présent"){
            query = "update participer set etat = 0 where " +
                    "matricule = '" + idTabInscrire.getSelectionModel().getSelectedItem().getMatricule() + "' and idseance = " + idSeance.getText();
            dataMapping.modification(query);
            idTabInscrire.getSelectionModel().getSelectedItem().setEtat(0);
        }
        else if (idTabInscrire.getSelectionModel().getSelectedItem().getEtat() == "Absent") {
            query = "update participer set etat = 1 where " +
                    "matricule = '" + idTabInscrire.getSelectionModel().getSelectedItem().getMatricule() + "' and idseance = " + idSeance.getText();
            dataMapping.modification(query);
            idTabInscrire.getSelectionModel().getSelectedItem().setEtat(1);
        }
        dataMapping.closeConnexion();
        idTabInscrire.refresh();
    }

    public void onrecherche(KeyEvent keyEvent) {
        String query = "SELECT * FROM participer as p, etudiant as e, classe as c " +
                "WHERE p.MATRICULE = e.MATRICULE and e.IDCLASSE = c.IDCLASSE and p.IDSEANCE = " + idSeance.getText() +
                " and (e.NOM LIKE '" + idRechercher.getText() + "%' or e.PRENOM LIKE '" + idRechercher.getText() + "%' " +
                        "or c.CODECLASSE LIKE '" + idRechercher.getText() + "%' or c.LIBELLE LIKE '" + idRechercher.getText() + "%')";
        //System.out.println(query);
        try{
            idTabInscrire.getItems().clear();
            chargementTableau(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    //todo: Fonction d'impression des listes de pésence
}
