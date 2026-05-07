package cl.duoc.animales.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class UserTest {

    @Test
    public void testUsuarioConstructorYGetters() {
        // Probamos el constructor
        User user = new User("pepito", "clave123", "ROLE_USER");

        Assertions.assertEquals("pepito", user.getUsername());
        Assertions.assertEquals("clave123", user.getPassword());
        Assertions.assertEquals("ROLE_USER", user.getRole());
    }

    @Test
    public void testUsuarioSetters() {
        // Probamos el constructor vacío y los setters, INCLUYENDO EL ID
        User user = new User();
        user.setId(1L);
        user.setUsername("juanito");
        user.setPassword("secreta");
        user.setRole("ROLE_ADMIN");

        Assertions.assertEquals(1L, user.getId());
        Assertions.assertEquals("juanito", user.getUsername());
        Assertions.assertEquals("secreta", user.getPassword());
        Assertions.assertEquals("ROLE_ADMIN", user.getRole());
    }
}