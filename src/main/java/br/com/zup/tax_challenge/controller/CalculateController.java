package br.com.zup.tax_challenge.controller;

import br.com.zup.tax_challenge.service.CalculateImpostoService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
