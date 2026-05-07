package cl.duoc.animales.controller;

import cl.duoc.animales.model.Animal;
import cl.duoc.animales.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/private/animales") // Fíjate que está en la ruta PRIVADA
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping
    public ResponseEntity<List<Animal>> obtenerAnimales() {
        List<Animal> animales = animalRepository.findAll();
        return ResponseEntity.ok(animales);
    }
}