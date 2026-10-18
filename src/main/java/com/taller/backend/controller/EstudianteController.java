package com.taller.backend.controller;

import com.taller.backend.model.Estudiante;
import com.taller.backend.service.EstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para la entidad {@link Estudiante}.
 *
 * Expone los endpoints bajo la ruta base: /api/estudiantes
 *
 * @RestController : combina @Controller + @ResponseBody (serializa a JSON automaticamente)
 * @RequestMapping : prefijo de ruta comun para todos los metodos
 * @CrossOrigin    : permite peticiones desde los servidores locales del frontend
 *                   (CRITICO para evitar errores CORS durante el taller)
 */
@CrossOrigin(origins = {
        "http://localhost:3000",
        "http://localhost:5500",
        "http://127.0.0.1:5500"
})
@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    // ================================================================
    // GET /api/estudiantes
    // Retorna la lista de todos los estudiantes
    // ================================================================
    @GetMapping
    public ResponseEntity<List<Estudiante>> obtenerTodos() {
        List<Estudiante> estudiantes = estudianteService.obtenerTodos();
        return ResponseEntity.ok(estudiantes);                    // 200 OK
    }

    // ================================================================
    // GET /api/estudiantes/{id}
    // Retorna un estudiante por su id
    // ================================================================
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> obtenerPorId(@PathVariable Long id) {
        return estudianteService.obtenerPorId(id)
                .map(ResponseEntity::ok)                          // 200 OK si existe
                .orElse(ResponseEntity.notFound().build());       // 404 Not Found si no existe
    }

    // ================================================================
    // POST /api/estudiantes
    // Crea un nuevo estudiante
    // Body (JSON): { "nombre": "...", "email": "...", "curso": "..." }
    // ================================================================
    @PostMapping
    public ResponseEntity<Estudiante> crearEstudiante(@RequestBody Estudiante estudiante) {
        Estudiante nuevo = estudianteService.crearEstudiante(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo); // 201 Created
    }

    // ================================================================
    // PUT /api/estudiantes/{id}
    // Actualiza todos los campos de un estudiante existente
    // Body (JSON): { "nombre": "...", "email": "...", "curso": "..." }
    // ================================================================
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizarEstudiante(
            @PathVariable Long id,
            @RequestBody Estudiante datosActualizados) {

        return estudianteService.actualizarEstudiante(id, datosActualizados)
                .map(ResponseEntity::ok)                          // 200 OK si se actualizo
                .orElse(ResponseEntity.notFound().build());       // 404 Not Found si no existia
    }

    // ================================================================
    // DELETE /api/estudiantes/{id}
    // Elimina un estudiante por su id
    // ================================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        if (estudianteService.eliminarEstudiante(id)) {
            return ResponseEntity.noContent().build();            // 204 No Content (exito)
        }
        return ResponseEntity.notFound().build();                 // 404 Not Found
    }
}
