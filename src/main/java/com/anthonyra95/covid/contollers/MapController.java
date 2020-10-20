package com.anthonyra95.covid.contollers;

import com.anthonyra95.covid.CovidApplication;
import com.anthonyra95.covid.domain.Data;
import com.anthonyra95.covid.domain.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Controls data requests that determine how the map will be colored 
 */

@Controller
public class MapController {
    private static final Logger log = LoggerFactory.getLogger(CovidApplication.class);
    Data data = CovidApplication.getData();

    //returns a HashMap (Key: states , Value: Covid cases per 1000 people )
    @ResponseBody
    @RequestMapping(value = "/casesPerMil", method = RequestMethod.POST, produces = "application/json")
    public Map casesPerMil(@RequestBody String stateJSON) throws IOException {

        HashMap<String, Integer> casesPerMilMap = new HashMap<String, Integer>();
        for (State state : data.getStateList()) {
            if (state.getPopulation() != 0) {
                int hundredThousandsOfPeople = state.getPopulation() / 100000;
                casesPerMilMap.put(data.getStateName(state.getName()), state.getPositive() / hundredThousandsOfPeople);
                log.info(data.getStateName(state.getName()) + " : " + state.getPositive() / hundredThousandsOfPeople + " Cases per 100k");
            }
        }
        return casesPerMilMap;
    }

     //returns a HashMap (Key: states , Value: New cases on the state )
    @ResponseBody
    @RequestMapping(value = "/newCases", method = RequestMethod.POST, produces = "application/json")
    public Map newCases(@RequestBody String stateJSON) throws IOException {

        HashMap<String, Integer> casesInLastDay = new HashMap<String, Integer>();
        for (State state : data.getStateList()) {
            casesInLastDay.put(data.getStateName(state.getName()), state.getPositiveIncrease());
            log.info(data.getStateName(state.getName()) + " : " + state.getPositiveIncrease() + " Cases increase");
        }
        return casesInLastDay;
    }

    //returns a HashMap (Key: states , Value: New deaths in the state )
    @ResponseBody
    @RequestMapping(value = "/newDeaths", method = RequestMethod.POST, produces = "application/json")
    public Map newDeaths(@RequestBody String stateJSON) throws IOException {

        HashMap<String, Integer> deadthsInLastDay = new HashMap<String, Integer>();
        for (State state : data.getStateList()) {
            deadthsInLastDay.put(data.getStateName(state.getName()), state.getDeathIncrease());
            log.info(data.getStateName(state.getName()) + " : " + state.getDeathIncrease() + " Death increase");
        }
        return deadthsInLastDay;
    }

}
