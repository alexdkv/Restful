package com.example.Restful.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Supplement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double caloriesPerServing;

    public Supplement() {
    }

    public Double getCaloriesPerServing() {
        return caloriesPerServing;
    }

    public void setCaloriesPerServing(Double caloriesPerServing) {
        this.caloriesPerServing = caloriesPerServing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}