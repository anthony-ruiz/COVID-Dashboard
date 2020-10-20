package com.anthonyra95.covid.domain;

import com.anthonyra95.covid.CovidApplication;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Data {

    /**  data sources refer to states by abreviation or full state name
     *    a hashmap "stateNameAbrevMap" is used in order to link the data sources togethere
     */

    private List<State> stateList = new ArrayList<State>();
    private HashMap<String, String> stateNameAbrevMap = new HashMap<String, String>();
    private HashMap<String, String> stateAbrevNameMap = new HashMap<String, String>();

    private static final Logger log = LoggerFactory.getLogger(CovidApplication.class);

    //populates all of the data 
    public void populateData() throws IOException {
        populateStateData();
        populateStateAbreviations();
        populateStatePopulation();
        populateStateNames();
    }

    //populates the covid related data for states. Data sourced from covidtracking.com
    private void populateStateData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        stateList = mapper.readValue(new URL("https://api.covidtracking.com/v1/states/current.json"),
                new TypeReference<List<State>>() {
                });
        //TESTING
        //print the states  and number of cases
        log.info("populating state data");
        for (State state : stateList) {
            log.info("saving state: " + state.toString());
        }

    }

    //return a List of all of the states
    public List<State> getStateList() {
        return stateList;
    }

    //returns all of the data for a certain state 
    public State getStateData(String stateName) {
        State myState = new State();
        for (State state : stateList) {
            if (state.getName().equals(stateName)) {
                return state;
            }
        }
        return myState;
    }

    //populates a HashMap of Key: stateName, Value: State Abreviation 
    private void populateStateAbreviations() throws IOException {
        ClassLoader loader = Data.class.getClassLoader();
        File FileToRead = new File(loader.getResource("static/media/state_names.txt").getFile());
        BufferedReader br = null;
        String line = "";
        String splitBy = ",";
        br = new BufferedReader(new FileReader(FileToRead));
        while ((line = br.readLine()) != null) {
            String[] stateLine = line.split(splitBy);
            stateNameAbrevMap.put(stateLine[0], stateLine[1]);
        }
        br.close();

        //TESTING
        //prints the Hashmap of states names and state Abreviations
        stateNameAbrevMap.forEach((key, value) -> {
            log.info(key + ":" + value);
        });
    }

    //populates a HashMap of Key: state Abreviation, Value: stateName 
    private void populateStateNames() throws IOException {
        ClassLoader loader = Data.class.getClassLoader();
        File FileToRead = new File(loader.getResource("static/media/state_names.txt").getFile());
        BufferedReader br = null;
        String line = "";
        String splitBy = ",";
        br = new BufferedReader(new FileReader(FileToRead));
        while ((line = br.readLine()) != null) {
            String[] stateLine = line.split(splitBy);
            stateAbrevNameMap.put(stateLine[1], stateLine[0]);
        }
        br.close();

        //TESTING
        //prints the Hashmap of states names and state Abreviations
        stateAbrevNameMap.forEach((key, value) -> {
            log.info(key + ":" + value);
        });
    }

    //Gets the population from each state and adds it to the state objects 
    //data sourced from census.gov
    private void populateStatePopulation() throws IOException {
        ClassLoader loader = Data.class.getClassLoader();
        File FileToRead = new File(loader.getResource("static/media/states_population.txt").getFile());
        BufferedReader br = null;
        String line = "";
        String splitBy = ",";
        br = new BufferedReader(new FileReader(FileToRead));
        while ((line = br.readLine()) != null) {
            String[] stateLine = line.split(splitBy);
            for (State state : stateList) {
                if (state.getName().equals(getStateAbreviation(stateLine[0]))) {
                    state.setPopulation(Integer.parseInt(stateLine[1]));
                    log.info(state.toString());
                }
            }
        }
        br.close();
    }

    //returns a state name given an abreviation
    public String getStateName(String stateAbreviaton) {
        return stateAbrevNameMap.get(stateAbreviaton.toUpperCase());
    }
    
    //returns an abreviation given a state Name
    public String getStateAbreviation(String stateName) {
        return stateNameAbrevMap.get(stateName.toUpperCase());
    }
    
}
