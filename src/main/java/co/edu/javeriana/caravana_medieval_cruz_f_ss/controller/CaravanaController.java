package co.edu.javeriana.caravana_medieval_cruz_f_ss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Caravana;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.CaravanaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/caravana")
public class CaravanaController {
    @Autowired
    private CaravanaService caravanaService;

    @GetMapping("/list")
    public ModelAndView listarCaravanas() {
        List<Caravana> caravanas = caravanaService.listarCaravanas();
        ModelAndView modelAndView = new ModelAndView("caravana-list");
        modelAndView.addObject("listaCaravanas", caravanas);
        return modelAndView;
    } 

}
