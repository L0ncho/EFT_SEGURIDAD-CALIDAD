package cl.duoc.animales.security;

import cl.duoc.animales.model.User;
import cl.duoc.animales.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository; // Simulamos la base de datos

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService; // El servicio real

    @Test
    public void testUsuarioEncontrado_CaminoExito() {
        // RAMA 1: Simulamos que la base de datos SÍ encuentra al usuario
        User mockUser = new User();
        mockUser.setUsername("admin");
        mockUser.setPassword("12345");
        mockUser.setRole("ROLE_ADMIN");

        Mockito.when(userRepository.findByUsername("admin")).thenReturn(Optional.of(mockUser));

        UserDetails result = customUserDetailsService.loadUserByUsername("admin");

        Assertions.assertNotNull(result);
        Assertions.assertEquals("admin", result.getUsername());
    }

    @Test
    public void testUsuarioNoEncontrado_CaminoError() {
        // RAMA 2: Simulamos que la base de datos NO encuentra al usuario (viene vacío)
        Mockito.when(userRepository.findByUsername("fantasma")).thenReturn(Optional.empty());

        // Verificamos que el código pase por el orElseThrow y lance la excepción correctamente
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername("fantasma");
        });
    }
}