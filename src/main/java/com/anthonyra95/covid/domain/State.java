package com.anthonyra95.covid.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

// Data tag creates setters and getters automatically
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

    public State() {
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


    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", positive=" + positive +
                '}';
    }
}
