package com.example.walkfromhome;

public class DataUser {
    private String gender;
    private Integer step;
    private Double height;
    private Double weight;

    public String getGender() {
        return gender;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
