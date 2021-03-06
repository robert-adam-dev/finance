package br.com.adamcast.finance.controller;

import br.com.adamcast.finance.exception.DespesaDuplicadaException;
import br.com.adamcast.finance.exception.DespesaNaoEncontradaException;
import br.com.adamcast.finance.model.dto.DespesaDto;
import br.com.adamcast.finance.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/despesas")
public class DespesaController {

    @Autowired
    DespesaService despesaService;

    @GetMapping
    public ResponseEntity<List<DespesaDto>> listaTodasDespesas(@RequestParam(required = false) String descricao) {
        if (descricao == null) {
            return despesaService.buscaTodasDespesas();
        }

        return despesaService.buscaDespesaPelaDescricao(descricao);
    }

    @PostMapping
    public ResponseEntity<DespesaDto> cadastraDespesa(@RequestBody @Valid DespesaDto despesaDto, UriComponentsBuilder uriBuilder) throws DespesaDuplicadaException {

        DespesaDto despesa = despesaService.cadastraDespesa(despesaDto);

        URI uri = uriBuilder.path("/api/v1/despesas/{id}").buildAndExpand(despesa.getId()).toUri();

        return ResponseEntity.created(uri).body(despesa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DespesaDto> mostraDespesaPeloId(@PathVariable String id) throws DespesaNaoEncontradaException {
        return despesaService.buscaDespesaPeloId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DespesaDto> atualizaDespesa(@PathVariable String id, @RequestBody @Valid DespesaDto despesaDto) throws DespesaNaoEncontradaException {
        return despesaService.atualizaDespesa(id, despesaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removerDespesa(@PathVariable String id) throws DespesaNaoEncontradaException {
        return despesaService.removerDespesa(id);
    }

    @GetMapping("/{ano}/{mes}")
    public List<DespesaDto> buscaDespesaPeloMesEAno(@PathVariable Integer ano, @PathVariable Integer mes) {
        return despesaService.buscaDespesaPeloMesEAno(ano, mes);
    }
}
