package br.com.adamcast.finance.controller;

import br.com.adamcast.finance.model.dto.ResumoDoMesDto;
import br.com.adamcast.finance.service.ResumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/resumo")
public class ResumoController {

    @Autowired
    ResumoService resumoService;

    @GetMapping("/{ano}/{mes}")
    public ResumoDoMesDto geraResumoMensal(@PathVariable Integer ano, @PathVariable Integer mes) {
        return resumoService.geraResumoMensal(ano, mes);
    }
}
