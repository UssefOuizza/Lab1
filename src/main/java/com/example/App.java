package com.example;

import com.example.model.Produit;
import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            Server.createWebServer("-web", "-webPort", "8082").start();
            System.out.println("Console H2 disponible sur : http://localhost:8082");
        } catch (Exception e) {
            System.out.println("Erreur lors du démarrage de la console H2");
            e.printStackTrace();
        }
        // Création de l'EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-demo");

        // Insertion de produits
        insererProduits(emf);

        // Lecture des produits
        lireProduits(emf);

        // Fermeture de l'EntityManagerFactory
        emf.close();
    }

    private static void insererProduits(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Création de quelques produits
            Produit p1 = new Produit("PC", new BigDecimal("1099.99"));
            Produit p2 = new Produit("Telephone", new BigDecimal("799.99"));
            Produit p3 = new Produit("TV", new BigDecimal("199.99"));
            Produit p4 = new Produit("Smartwatch", new BigDecimal("999.99"));
            // Persistance des produits
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.persist(p4);
            em.getTransaction().commit();
            System.out.println("Produits saved !");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private static void lireProduits(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        try {
            // Requête JPQL pour récupérer tous les produits
            List<Produit> produits = em.createQuery("SELECT p FROM Produit p", Produit.class)
                    .getResultList();

            System.out.println("\nProduct list :");
            for (Produit produit : produits) {
                System.out.println(produit);
            }

            // Recherche d'un produit par ID
            System.out.println("\nSearching for product with id = 2 :");
            Produit produit = em.find(Produit.class, 2L);
            if (produit != null) {
                System.out.println(produit);
            } else {
                System.out.println("Can't find product");
            }
        } finally {
            em.close();
        }
    }

    private static void modifierProduitPrix(EntityManagerFactory emf, long id, BigDecimal prix) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Produit p = em.find(Produit.class, id);
            p.setPrixUnitaire(prix);
            em.merge(p);

            em.getTransaction().commit();
            System.out.println("Produits est mis a jour!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    private static void supprimerproduit(EntityManagerFactory emf, long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Produit p = em.find(Produit.class, id);

            em.remove(p);

            em.getTransaction().commit();
            System.out.println("Produits supprime");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
