package ionis.projet.controller;

import java.time.LocalDate;

public class Etat extends Inscription{

    String nbreHeureAbsence;

    public Etat(int idClasse, String intituleClasse, String matricule, String nom, String prenom, String email, String codeClasse,
                String telephone, String filiere, int etat) {
        super(idClasse, intituleClasse, matricule, nom, prenom, email, codeClasse, telephone, filiere, etat);
        this.nbreHeureAbsence = String.valueOf(etat);
    }

    public String getNbreHeureAbsence() {
        return nbreHeureAbsence;
    }

    public void setNbreHeureAbsence(String nbreHeureAbsence) {
        this.nbreHeureAbsence = nbreHeureAbsence;
    }
}
