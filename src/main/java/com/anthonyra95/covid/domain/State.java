package com.anthonyra95.covid.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class State {

    private String name;
    private int positive;
    private int negative;
    private int hospitalizedCurrently;
    private int hospitalizedCumulative;
    private int inIcuCurrently;
    private int inIcuCumulative;
    private int recovered;
    private int population;
    private int deathConfirmed;
    private int deathIncrease;
    private int positiveIncrease;

    public State() {
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", positive=" + positive +
                ", negative=" + negative +
                ", hospitalizedCurrently=" + hospitalizedCurrently +
                ", hospitalizedCumulative=" + hospitalizedCumulative +
                ", inIcuCurrently=" + inIcuCurrently +
                ", inIcuCumulative=" + inIcuCumulative +
                ", recovered=" + recovered +
                ", population=" + population +
                '}';
    }

    //Tag is used in order to tell Jackson that our name field corresponds to data label "state"
    @JsonProperty("state")
    public String getName() {
        return name;
    }

    @JsonProperty("state")
    public void setName(String name) {
        this.name = name;
    }

    public int getPositiveIncrease() {
        return positiveIncrease;
    }

    public void setPositiveIncrease(int positiveIncrease) {
        this.positiveIncrease = positiveIncrease;
    }

    public int getDeathConfirmed() {
        return deathConfirmed;
    }

    public void setDeathConfirmed(int deathConfirmed) {
        this.deathConfirmed = deathConfirmed;
    }

    public int getDeathIncrease() {
        return deathIncrease;
    }

    public void setDeathIncrease(int deathIncrease) {
        this.deathIncrease = deathIncrease;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
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
}
