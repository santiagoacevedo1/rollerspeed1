package com.rollerspeed.rollerspeed1.controller.rest;

import com.rollerspeed.rollerspeed1.model.TipoFlor;
import com.rollerspeed.rollerspeed1.service.TipoFlorService;
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
@RequestMapping("/api/tipos-flor")
@Tag(name = "Tipos de Flor", description = "Operaciones para gestión de tipos de flor")
public class TipoFlorRestController {

    private final TipoFlorService tipoFlorService;

    public TipoFlorRestController(TipoFlorService tipoFlorService) {
        this.tipoFlorService = tipoFlorService;
    }

    @Operation(summary = "Obtener todos los tipos de flor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tipos de flor",
                    content = @Content(schema = @Schema(implementation = TipoFlor.class)))
    })
    @GetMapping
    public ResponseEntity<List<TipoFlor>> listarTodos() {
        return ResponseEntity.ok(tipoFlorService.listarTodos());
    }

    @Operation(summary = "Obtener un tipo de flor por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de flor encontrado",
                    content = @Content(schema = @Schema(implementation = TipoFlor.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de flor no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TipoFlor> obtenerPorId(
            @Parameter(description = "ID del tipo de flor") @PathVariable Long id) {
        TipoFlor tipoFlor = tipoFlorService.obtenerPorId(id);
        return tipoFlor != null ? ResponseEntity.ok(tipoFlor) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo tipo de flor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tipo de flor creado",
                    content = @Content(schema = @Schema(implementation = TipoFlor.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<TipoFlor> crear(@RequestBody TipoFlor tipoFlor) {
        TipoFlor guardado = tipoFlorService.guardar(tipoFlor);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @Operation(summary = "Actualizar un tipo de flor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de flor actualizado",
                    content = @Content(schema = @Schema(implementation = TipoFlor.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de flor no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TipoFlor> actualizar(
            @Parameter(description = "ID del tipo de flor") @PathVariable Long id,
            @RequestBody TipoFlor tipoFlor) {
        if (!tipoFlorService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        tipoFlor.setId(id);
        return ResponseEntity.ok(tipoFlorService.guardar(tipoFlor));
    }

    @Operation(summary = "Eliminar un tipo de flor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tipo de flor eliminado"),
            @ApiResponse(responseCode = "404", description = "Tipo de flor no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@Parameter(description = "ID del tipo de flor") @PathVariable Long id) {
        if (!tipoFlorService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        tipoFlorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}