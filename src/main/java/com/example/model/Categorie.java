package com.example.model;

import javax.persistence.*;
import java.util.List;

@Entity

public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @OneToMany(mappedBy = "categorie")
    List<Produit> produits;

    public Categorie() {
    }
}
