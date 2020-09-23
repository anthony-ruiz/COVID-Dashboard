package com.anthonyra95.covid.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class State {

    private @Id
    @GeneratedValue Long id;
    private String name;
    private int positive;
    private int negative;
    private int hospitalizedCurrently;
    private int hospitalizedCumulative;
    private int inIcuCurrently;
    private int inIcuCumulative;
    private int recovered;

    public State() {
    }

//    public State(Long id, String name, int positive, int negative, int hospitalizedCurrently, int hospitalizedCumulative, int inIcuCurrently, int inIcuCumulative, int recovered) {
//        this.id = id;
//        this.name = name;
//        this.positive = positive;
//        this.negative = negative;
//        this.hospitalizedCurrently = hospitalizedCurrently;
//        this.hospitalizedCumulative = hospitalizedCumulative;
//        this.inIcuCurrently = inIcuCurrently;
//        this.inIcuCumulative = inIcuCumulative;
//        this.recovered = recovered;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("state")
    public String getName() {
        return name;
    }

    @JsonProperty("state")
    public void setName(String name) {
        this.name = name;
    }

    public int getPositive() {
        return positive;
    }

    public void setPositive(int positive) {
        this.positive = positive;
    }

    public int getNegative() {
        return negative;
    }

    public void setNegative(int negative) {
        this.negative = negative;
    }

    public int getHospitalizedCurrently() {
        return hospitalizedCurrently;
    }

    public void setHospitalizedCurrently(int hospitalizedCurrently) {
        this.hospitalizedCurrently = hospitalizedCurrently;
    }

    public int getHospitalizedCumulative() {
        return hospitalizedCumulative;
    }

    public void setHospitalizedCumulative(int hospitalizedCumulative) {
        this.hospitalizedCumulative = hospitalizedCumulative;
    }

    public int getInIcuCurrently() {
        return inIcuCurrently;
    }

    public void setInIcuCurrently(int inIcuCurrently) {
        this.inIcuCurrently = inIcuCurrently;
    }

    public int getInIcuCumulative() {
        return inIcuCumulative;
    }

    public void setInIcuCumulative(int inIcuCumulative) {
        this.inIcuCumulative = inIcuCumulative;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", positive=" + positive +
                '}';
    }
}
