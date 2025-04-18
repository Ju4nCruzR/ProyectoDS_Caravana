package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CaravanaRepository;

@Service
public class CaravanaService {
    @Autowired
    private CaravanaRepository caravanaRepository;


}
