package com.anthonyra95.covid.contollers;

import com.anthonyra95.covid.CovidApplication;
import com.anthonyra95.covid.domain.Data;
import com.anthonyra95.covid.domain.State;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * recieves the name of a state and returns an object
 * for the corresponding state that contains all COVID related data
 */

@Controller
public class StateController {

    @ResponseBody
    @RequestMapping(value = "/stateRequested", method = RequestMethod.POST, produces = "application/json")
    public State stateRequest(@RequestBody String stateJSON) throws IOException {
        Data data = CovidApplication.getData();
//        String stateName = stateJSON.toUpperCase();
//        String abreviation = data.getStateAbreviation(stateName);
//        State stateInfo = data.getStateData(abreviation);
        State stateInfo = data.getStateData(data.getStateAbreviation(stateJSON));
        return stateInfo;

    }


}
