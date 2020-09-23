package com.anthonyra95.covid.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
This is the controler for the main application.
 responds when localhost:8080/mapController is called
 */

@Controller
public class MapController {

    @GetMapping("/mapController")
    public String mapController(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model){
        model.addAttribute("name", name);
        return "map";
    }

}
