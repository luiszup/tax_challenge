package br.com.zup.tax_challenge.controller;

import br.com.zup.tax_challenge.dto.TipoImpostoRequestDTO;
import br.com.zup.tax_challenge.dto.TipoImpostoResponseDTO;
import br.com.zup.tax_challenge.model.TipoImposto;
import br.com.zup.tax_challenge.service.TipoImpostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos")
public class ImpostoController {

    @Autowired
    private TipoImpostoService tipoImpostoService;

    public ImpostoController(TipoImpostoService tipoImpostoService) {
        this.tipoImpostoService = tipoImpostoService;
    }

    @GetMapping
    public ResponseEntity<List<TipoImpostoResponseDTO>> listAll() {
        List<TipoImpostoResponseDTO> impostos = tipoImpostoService.listAll();
        return ResponseEntity.ok(impostos);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TipoImpostoResponseDTO> saveTipoImposto(@RequestBody TipoImpostoRequestDTO imposto) {
        TipoImpostoResponseDTO novoImposto = tipoImpostoService.saveTipoImposto(imposto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoImposto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoImpostoResponseDTO> findTipoImposto(@PathVariable Long id) {
        return tipoImpostoService.findTipoImposto(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTipoImposto(@PathVariable Long id) {
        tipoImpostoService.deleteTipoImposto(id);
        return ResponseEntity.noContent().build();
    }
}
