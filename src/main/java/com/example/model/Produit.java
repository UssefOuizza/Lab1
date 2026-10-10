package com.example.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_id")
    private Long identifiant;

    @Column(name = "Product_nom")
    private String libelle;

    @Column(name = "Product_prix")
    private BigDecimal prixUnitaire;

    @ManyToOne
    @JoinColumn(name = "categorie")
    private Categorie categorie;


    public Produit() {
    }

    public Produit(String libelle, BigDecimal prixUnitaire) {
        this.libelle = libelle;
        this.prixUnitaire = prixUnitaire;
    }

    public String getLibelle() {
        return libelle;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public Long getIdentifiant() {
        return identifiant;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "identifiant=" + identifiant +
                ", libelle='" + libelle + '\'' +
                ", prixUnitaire=" + prixUnitaire +
                '}';
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
}
