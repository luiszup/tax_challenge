package br.com.zup.tax_challenge.controller;

import br.com.zup.tax_challenge.dto.CalculateRequestDTO;
import br.com.zup.tax_challenge.dto.CalculateResponseDTO;
import br.com.zup.tax_challenge.service.CalculateImpostoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculo")
public class CalculateController {

    @Autowired
    private CalculateImpostoService calculateImpostoService;

    public CalculateController(CalculateImpostoService calculateImpostoService) {
        this.calculateImpostoService = calculateImpostoService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CalculateResponseDTO> calculate(@RequestBody @Valid CalculateRequestDTO request) {
        if (request.getTipoImpostoId() == null || request.getValorBase() < 0) {
            throw new IllegalArgumentException("Tipo de imposto ou valor base inválido");
        }

        CalculateResponseDTO response = calculateImpostoService.calculateImposto(request.getTipoImpostoId(), request.getValorBase());
        return ResponseEntity.ok(response);
    }
}
