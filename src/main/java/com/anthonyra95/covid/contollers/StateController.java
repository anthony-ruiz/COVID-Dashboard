package com.anthonyra95.covid.contollers;

import com.anthonyra95.covid.domain.Data;
import com.anthonyra95.covid.domain.State;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
public class StateController {

    @ResponseBody
    @RequestMapping(value = "/stateRequested", method = RequestMethod.POST, produces = "application/json")
    public State stateRequest(@RequestBody String stateJSON) throws IOException { //
        String test = "this is a test";
        Data data = new Data();
        data.populateData();
        String stateName = stateJSON.toUpperCase();
        String abreviation = data.getStateAbreviation(stateName);
        State stateInfo = data.getStateData(abreviation);
        return stateInfo;

    }


}
