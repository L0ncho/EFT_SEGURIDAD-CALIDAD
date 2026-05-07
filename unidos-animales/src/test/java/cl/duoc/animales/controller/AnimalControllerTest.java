package cl.duoc.animales.controller;

import cl.duoc.animales.model.Animal;
import cl.duoc.animales.repository.AnimalRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AnimalControllerTest {

    @Mock
    private AnimalRepository animalRepository; // El "clon falso" de la base de datos

    @InjectMocks
    private AnimalController animalController; // El controlador real que vamos a probar

    @Test
    public void testObtenerAnimales() {
        // 1. Preparamos una lista falsa de animales
        List<Animal> listaFalsa = new ArrayList<>();
        listaFalsa.add(new Animal()); // Agregamos un animal vacío para hacer bulto

        // 2. Le decimos al clon que hacer cuando el controlador lo llame
        Mockito.when(animalRepository.findAll()).thenReturn(listaFalsa);

        // 3. Ejecutamos el método real de tu controlador
        ResponseEntity<List<Animal>> respuesta = animalController.obtenerAnimales();

        // 4. Comprobamos que responda con éxito (Código 200 OK) y nos entregue la lista
        Assertions.assertTrue(respuesta.getStatusCode().is2xxSuccessful());
        Assertions.assertNotNull(respuesta.getBody());
        Assertions.assertEquals(1, respuesta.getBody().size());
    }
}