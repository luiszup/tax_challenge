package br.com.zup.tax_challenge.controller;

import br.com.zup.tax_challenge.model.TipoImposto;
import br.com.zup.tax_challenge.service.TipoImpostoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tipos")
public class ImpostoController {
    private TipoImpostoService tipoImpostoService;

    public ImpostoController(TipoImpostoService tipoImpostoService) {
        this.tipoImpostoService = tipoImpostoService;
    }

    @GetMapping
    public ResponseEntity<List<TipoImposto>> listAll() {
        List<TipoImposto> impostos = tipoImpostoService.listAll();
        return ResponseEntity.ok(impostos);
    }
}
