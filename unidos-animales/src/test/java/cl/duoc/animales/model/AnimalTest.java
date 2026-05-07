package cl.duoc.animales.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class AnimalTest {

    @Test
    public void testAnimalSettersYGetters() {
        Animal animal = new Animal();
        
        // Le pasamos datos a todas las variables
        animal.setId(1L);
        animal.setNombre("Firulais");
        animal.setEspecie("Perro");
        animal.setEstadoAdopcion("Disponible");

        // Comprobamos que todas las variables guardaron los datos correctamente
        Assertions.assertEquals(1L, animal.getId());
        Assertions.assertEquals("Firulais", animal.getNombre());
        Assertions.assertEquals("Perro", animal.getEspecie());
        Assertions.assertEquals("Disponible", animal.getEstadoAdopcion());
    }
}