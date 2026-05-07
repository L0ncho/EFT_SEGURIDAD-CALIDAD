package cl.duoc.animales.repository;
import cl.duoc.animales.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
    
}
