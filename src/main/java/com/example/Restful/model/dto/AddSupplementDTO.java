package com.example.Restful.model.dto;

public class AddSupplementDTO  {
    private String name;

    private String description;

    private Double caloriesPerServing;

    private String photoUrl;

    public Double getCaloriesPerServing() {
        return caloriesPerServing;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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
