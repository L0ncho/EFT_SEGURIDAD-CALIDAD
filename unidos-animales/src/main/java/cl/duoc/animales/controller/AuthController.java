package cl.duoc.animales.controller;

import cl.duoc.animales.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        // Capturamos el usuario y clave que nos envían por la API
        String username = request.get("username");
        String password = request.get("password");

        // Le pedimos a Spring Security que verifique si existen en la Base de Datos
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        // ¡AQUÍ ESTÁ LA MAGIA NUEVA! Extraemos el rol que tiene ese usuario en la base de datos
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        // Si son correctos, generamos el Token JWT pasándole AMBOS datos (usuario y rol)
        String token = jwtUtil.generateToken(authentication.getName(), role);

        // Armamos la respuesta en formato JSON {"token": "eyJhG..."}
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        
        return ResponseEntity.ok(response);
    }
}