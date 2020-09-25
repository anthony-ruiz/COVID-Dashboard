package com.anthonyra95.covid.contollers;

import com.anthonyra95.covid.CovidApplication;
import com.anthonyra95.covid.domain.Data;
import com.anthonyra95.covid.domain.State;
import org.springframework.stereotype.Controller;
import org.slf4j.*;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MapController {

    private static final Logger log = LoggerFactory.getLogger(CovidApplication.class);

    @ResponseBody
    @RequestMapping(value = "/casesPerMil", method = RequestMethod.POST, produces = "application/json")
    public Map casesPerMil(@RequestBody String stateJSON) throws IOException {
        Data data = CovidApplication.getData();
        HashMap<String,Integer> casesPerMilMap = new HashMap<String, Integer>();
        for(State state : data.getStateList()){
            if(state.getPopulation() != 0 ){
                int hundredThousandsOfPeople = state.getPopulation() / 100000;
                casesPerMilMap.put(state.toString(), state.getPositive() / hundredThousandsOfPeople);
                log.info(state.getName() + " : " + state.getPositive() / hundredThousandsOfPeople + " Cases per 100k");
            }
        }
        return casesPerMilMap;
    }


}
