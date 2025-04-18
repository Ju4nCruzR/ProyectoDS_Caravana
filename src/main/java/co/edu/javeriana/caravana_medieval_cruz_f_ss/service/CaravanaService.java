package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Caravana;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CaravanaRepository;

@Service
public class CaravanaService {
    @Autowired
    private CaravanaRepository caravanaRepository;

    public List<Caravana> listarCaravanas() {
        return caravanaRepository.findAll();
    }
}
