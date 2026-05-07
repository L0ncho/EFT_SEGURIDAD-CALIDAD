package cl.duoc.animales.controller;

import cl.duoc.animales.model.Solicitud;
import cl.duoc.animales.repository.AnimalRepository;
import cl.duoc.animales.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private SolicitudRepository solicitudRepository;

    // Ruta publica (Inicio) - Ahora envía la lista de animales a la vista
    @GetMapping({"/", "/publica"})
    public String publica(Model model) {
        model.addAttribute("animales", animalRepository.findAll());
        return "publica";
    }

    // Ruta para procesar el formulario de adopcion
    @PostMapping("/enviar-solicitud")
    public String enviarSolicitud(Solicitud solicitud) {
        solicitudRepository.save(solicitud);
        return "redirect:/publica?exito";
    }

    // Ruta para el formulario de login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Ruta privada (Solo para voluntarios) - Muestra las solicitudes recibidas
    @GetMapping("/privada")
    public String privada(Model model) {
        model.addAttribute("solicitudes", solicitudRepository.findAll());
        return "privada";
    }
}