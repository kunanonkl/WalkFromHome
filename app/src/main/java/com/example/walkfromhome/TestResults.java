package com.example.walkfromhome;

import java.time.LocalDate;
import java.util.Date;

public class TestResults {
    private String firstName;
    private String lastName;
    private Integer exhaustion;
    private Double heartRate;
    private Double bloodOxygen;
    private Double bloodPress;
    private String gender;
    private Integer step;
    private Double height;
    private Double weight;
    private String stride_length;
    private String walking_information;
    private Double walking_distance;
    private Long id;
    private String date;


    public Integer getExhaustion() {
        return exhaustion;
    }

    public void setExhaustion(Integer exhaustion) {
        this.exhaustion = exhaustion;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Double heartRate) {
        this.heartRate = heartRate;
    }

    public Double getBloodOxygen() {
        return bloodOxygen;
    }

    public void setBloodOxygen(Double bloodOxygen) {
        this.bloodOxygen = bloodOxygen;
    }

    public Double getBloodPress() {
        return bloodPress;
    }

    public void setBloodPress(Double bloodPress) {
        this.bloodPress = bloodPress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStride_length() {
        return stride_length;
    }

    public void setStride_length(String stride_length) {
        this.stride_length = stride_length;
    }

    public String getWalking_information() {
        return walking_information;
    }

    public void setWalking_information(String walking_information) {
        this.walking_information = walking_information;
    }

    public Double getWalking_distance() {
        return walking_distance;
    }

    public void setWalking_distance(Double walking_distance) {
        this.walking_distance = walking_distance;
    }

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
