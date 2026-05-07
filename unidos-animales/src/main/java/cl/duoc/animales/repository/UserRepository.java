package cl.duoc.animales.repository;

import cl.duoc.animales.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Este método nos permitirá buscar un usuario por su nombre al hacer login
    Optional<User> findByUsername(String username);
}