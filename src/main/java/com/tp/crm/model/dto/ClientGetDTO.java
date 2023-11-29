package com.tp.crm.model.dto;

import com.tp.crm.model.StateClient;

public class ClientGetDTO {
    private Integer idDuClient;
    private String entreprise;
    private String nom;
    private String prenom;
    private String mail;
    private String telephone;
    private String adresse;
    private String codePostal;
    private String ville;
    private String pays;
    private StateClient status;

    public Integer getIdDuClient() {
        return idDuClient;
    }

    public void setIdDuClient(Integer idDuClient) {
        this.idDuClient = idDuClient;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public StateClient getStatus() {
        return status;
    }

    public void setStatus(StateClient status) {
        this.status = status;
    }
}
