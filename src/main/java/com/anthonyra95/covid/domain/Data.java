package com.anthonyra95.covid.domain;

import com.anthonyra95.covid.CovidApplication;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Data {
    private List<State> stateList = new ArrayList<State>();
    private HashMap<String,String> stateNameAbrevMap = new HashMap<String,String>();

    //creates a logger
    private static final Logger log = LoggerFactory.getLogger(CovidApplication.class);

    public void populateData() throws IOException {
        populateStateData();
        populateStateAbreviations();
    }


    private void populateStateData()throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        stateList = mapper.readValue(new URL("https://api.covidtracking.com/v1/states/current.json"),
                new TypeReference<List<State>>() {
                });
        //print the states  and number of cases
        for(State state: stateList){

            log.info("saving state: " +  state.toString());
        }
        
    }
    
    public List<State> getStateList() {
        return stateList;
    }

    public State getStateData(String stateName){
        State myState = new State();
        for(State state : stateList){
            if(state.getName().equals(stateName)){
                return state;
            }
        }
        return myState;
    }

    private void populateStateAbreviations() throws IOException {
        String csvFileToRead = "state_names.txt";
        BufferedReader br = null;
        String line = "";
        String splitBy = ",";
        br = new BufferedReader(new FileReader(csvFileToRead));
        while ((line = br.readLine()) != null) {
            String[] stateLine = line.split(splitBy);
            stateNameAbrevMap.put(stateLine[0], stateLine[1]);
        } 
        br.close();

        
        stateNameAbrevMap.forEach((key, value) -> {
            log.info(key + ":" + value);

            });
        }

    public HashMap<String, String> getStateNameAbrevMap(){
        return stateNameAbrevMap;
    }
    public String getStateAbreviation(String stateName){
        return  stateNameAbrevMap.get(stateName);
    }
}
