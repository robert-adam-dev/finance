package br.com.adamcast.finance.controller;

import br.com.adamcast.finance.model.dto.ReceitaDto;
import br.com.adamcast.finance.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/receitas")
public class ReceitaController {

    @Autowired
    ReceitaService receitaService;

    @GetMapping
    public ResponseEntity<List<ReceitaDto>> listaTodasReceitas(String descricao) {
        if (descricao == null) {
            return receitaService.buscaTodasReceitas();
        }

        return receitaService.buscaReceitasPelaDescricao(descricao);
    }

    @PostMapping
    public ResponseEntity<ReceitaDto> cadastraReceita(@RequestBody @Valid ReceitaDto receitaDto, UriComponentsBuilder uriBuilder) {

        ReceitaDto receita = receitaService.cadastraReceita(receitaDto);

        URI uri = uriBuilder.path("/api/v1/receitas/{id}").buildAndExpand(receita.getId()).toUri();

        return ResponseEntity.created(uri).body(receita);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaDto> mostraReceitaPeloId(@PathVariable String id) {
        return receitaService.buscaReceitaPeloId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceitaDto> atualizaReceita(@PathVariable String id, @RequestBody @Valid ReceitaDto receitaDto) {
        return receitaService.atualizaReceita(id, receitaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removerReceita(@PathVariable String id) {
        return receitaService.removerReceita(id);
    }
}
