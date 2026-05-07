package cl.duoc.animales.config;

import cl.duoc.animales.model.User;
import cl.duoc.animales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        
        // 1. ELIMINAMOS TODOS LOS USUARIOS VIEJOS
        userRepository.deleteAll();
            
        // 2. CREAMOS LOS 3 USUARIOS NUEVOS CON CLAVES ENCRIPTADAS
        userRepository.save(new User("admin", passwordEncoder.encode("admin123"), "ROLE_ADMIN"));
        userRepository.save(new User("voluntario", passwordEncoder.encode("voluntario123"), "ROLE_USER"));
        userRepository.save(new User("invitado", passwordEncoder.encode("invitado123"), "ROLE_GUEST"));
            
        System.out.println("✅ ¡Base de datos limpiada y los 3 usuarios fueron creados exitosamente!");
        
    }
}