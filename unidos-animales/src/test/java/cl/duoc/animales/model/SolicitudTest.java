package cl.duoc.animales.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class SolicitudTest {

    @Test
    public void testSolicitudSettersYGetters() {
        Solicitud solicitud = new Solicitud();
        
        solicitud.setId(1L);
        solicitud.setNombreSolicitante("Alonso");
        solicitud.setCorreo("alonso@mail.com");
        solicitud.setNombreMascota("Firulais");
        solicitud.setMensaje("Quiero adoptar a este perrito");

        Assertions.assertEquals(1L, solicitud.getId());
        Assertions.assertEquals("Alonso", solicitud.getNombreSolicitante());
        Assertions.assertEquals("alonso@mail.com", solicitud.getCorreo());
        Assertions.assertEquals("Firulais", solicitud.getNombreMascota());
        Assertions.assertEquals("Quiero adoptar a este perrito", solicitud.getMensaje());
    }
}