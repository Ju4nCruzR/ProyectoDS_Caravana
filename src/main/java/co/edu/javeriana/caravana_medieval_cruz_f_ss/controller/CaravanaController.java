package co.edu.javeriana.caravana_medieval_cruz_f_ss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.CaravanaService;

@Controller
@RequestMapping("/caravana")
public class CaravanaController {
    @Autowired
    private CaravanaService caravanaService;


}
