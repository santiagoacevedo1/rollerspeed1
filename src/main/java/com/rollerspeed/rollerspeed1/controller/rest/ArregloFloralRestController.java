package com.rollerspeed.rollerspeed1.controller.rest;

import com.rollerspeed.rollerspeed1.model.ArregloFloral;
import com.rollerspeed.rollerspeed1.service.ArregloFloralService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/arreglos-florales")
@Tag(name = "Arreglos Florales", description = "Operaciones para gestión de arreglos florales")
public class ArregloFloralRestController {

    private final ArregloFloralService arregloFloralService;

    public ArregloFloralRestController(ArregloFloralService arregloFloralService) {
        this.arregloFloralService = arregloFloralService;
    }

    @Operation(summary = "Obtener todos los arreglos florales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de arreglos florales",
                    content = @Content(schema = @Schema(implementation = ArregloFloral.class)))
    })
    @GetMapping
    public ResponseEntity<List<ArregloFloral>> listarTodos() {
        return ResponseEntity.ok(arregloFloralService.listarTodos());
    }

    @Operation(summary = "Obtener un arreglo floral por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arreglo floral encontrado",
                    content = @Content(schema = @Schema(implementation = ArregloFloral.class))),
            @ApiResponse(responseCode = "404", description = "Arreglo floral no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ArregloFloral> obtenerPorId(
            @Parameter(description = "ID del arreglo floral") @PathVariable Long id) {
        ArregloFloral arregloFloral = arregloFloralService.obtenerPorId(id);
        return arregloFloral != null ? ResponseEntity.ok(arregloFloral) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Obtener arreglos florales por tipo de flor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de arreglos florales por tipo",
                    content = @Content(schema = @Schema(implementation = ArregloFloral.class)))
    })
    @GetMapping("/tipo-flor/{tipoFlorId}")
    public ResponseEntity<List<ArregloFloral>> obtenerPorTipoFlor(
            @Parameter(description = "ID del tipo de flor") @PathVariable Long tipoFlorId) {
        return ResponseEntity.ok(arregloFloralService.obtenerPorTipoFlor(tipoFlorId));
    }

    @Operation(summary = "Crear un nuevo arreglo floral")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Arreglo floral creado",
                    content = @Content(schema = @Schema(implementation = ArregloFloral.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<ArregloFloral> crear(@RequestBody ArregloFloral arregloFloral) {
        ArregloFloral guardado = arregloFloralService.guardar(arregloFloral);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @Operation(summary = "Actualizar un arreglo floral existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arreglo floral actualizado",
                    content = @Content(schema = @Schema(implementation = ArregloFloral.class))),
            @ApiResponse(responseCode = "404", description = "Arreglo floral no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ArregloFloral> actualizar(
            @Parameter(description = "ID del arreglo floral") @PathVariable Long id,
            @RequestBody ArregloFloral arregloFloral) {
        if (!arregloFloralService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        arregloFloral.setId(id);
        return ResponseEntity.ok(arregloFloralService.guardar(arregloFloral));
    }

    @Operation(summary = "Eliminar un arreglo floral")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Arreglo floral eliminado"),
            @ApiResponse(responseCode = "404", description = "Arreglo floral no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@Parameter(description = "ID del arreglo floral") @PathVariable Long id) {
        if (!arregloFloralService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        arregloFloralService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}