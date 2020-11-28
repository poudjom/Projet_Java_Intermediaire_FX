package ionis.projet.bd;

import java.sql.*;
public class DataMapping {
    String host;
    String uName;
    String uPass;
    Connection connection;
    Statement statement;

    // Dans le constructeur on défini les paramétres d'acces à la base de données et on effectue une connexion
    public DataMapping(){
        this.host = "jdbc:mysql://localhost:3306/projet_java1?autoReconnect=true&useSSL=false";
        this.uName = "root";
        this.uPass = "";
        try {
            this.connection = DriverManager.getConnection(host, uName, uPass);
            this.statement = this.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    // fonction qui exécute les insertions dans la base de données
    public int insertion(String query) throws SQLException {
        return this.statement.executeUpdate(query);
    }


    // fonction qui effectue les selections dans la base de données
    public ResultSet selection(String query) throws SQLException {
        return this.statement.executeQuery(query);
    }

    // fonction qui effectue les modifications dans la base de données
    public int modification(String query) throws SQLException {
        return this.statement.executeUpdate(query);
    }

    // fonction qui effectue les suppressions dans la base de données
    public int suppression(String query) throws SQLException {
        return this.statement.executeUpdate(query);
    }



    public void closeConnexion() throws SQLException {
        this.statement.close();
        this.connection.close();
    }
}
