package br.com.adamcast.finance.controller;

import br.com.adamcast.finance.model.dto.DespesaDto;
import br.com.adamcast.finance.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/despesas")
public class DespesaController {

    @Autowired
    DespesaService despesaService;

    @GetMapping
    public List<DespesaDto> listaTodasDespesas(String descricao) {
        if (descricao == null) {
            return despesaService.buscaTodasDespesas();
        }
        return despesaService.buscaDespesaPelaDescricao(descricao);
    }

    @PostMapping
    public DespesaDto criarDespesa(@RequestBody DespesaDto despesaDto) {
        return despesaService.criarNovaDespesa(despesaDto);
    }
}
