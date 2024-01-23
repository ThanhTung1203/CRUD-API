package com.example.demoapi.model;

import jakarta.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

   private Long idCate;
    private String name;

    public Category(Long idCate, String name) {
        this.idCate = idCate;
        this.name = name;
    }

    public Category() {

    }

    public Long getIdCate() {
        return idCate;
    }

    public void setIdCate(Long idCate) {
        this.idCate = idCate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
