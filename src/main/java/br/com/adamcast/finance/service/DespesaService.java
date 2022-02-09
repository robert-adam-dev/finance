package br.com.adamcast.finance.service;

import br.com.adamcast.finance.exception.DespesaDuplicadaException;
import br.com.adamcast.finance.exception.DespesaNaoEncontradaException;
import br.com.adamcast.finance.mapper.DespesaMapper;
import br.com.adamcast.finance.model.Despesa;
import br.com.adamcast.finance.model.dto.DespesaDto;
import br.com.adamcast.finance.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DespesaService {

    @Autowired
    DespesaRepository despesaRepository;

    private final DespesaMapper despesaMapper = DespesaMapper.INSTANCE;

    @Transactional
    public DespesaDto cadastraDespesa(DespesaDto despesaDto) throws DespesaDuplicadaException {
        verificaSeDespesaJaExisteNoMesAtual(despesaDto);
        Despesa despesaSalva = despesaRepository.save(despesaMapper.toModel(despesaDto));

        return despesaMapper.toDto(despesaSalva);
    }

    public ResponseEntity<List<DespesaDto>> buscaTodasDespesas() {
        List<Despesa> todasAsDespesas = despesaRepository.findAll();

        return ResponseEntity.ok(todasAsDespesas.stream().map(despesaMapper::toDto).collect(Collectors.toList()));
    }

    public ResponseEntity<List<DespesaDto>> buscaDespesaPelaDescricao(String descricao) {
        List<Despesa> todasAsDespesas = despesaRepository.findByDescricaoContainingIgnoreCase(descricao);

        return ResponseEntity.ok(todasAsDespesas.stream().map(despesaMapper::toDto).collect(Collectors.toList()));
    }

    public ResponseEntity<DespesaDto> buscaDespesaPeloId(String id) throws DespesaNaoEncontradaException {
        Optional<Despesa> despesa = verificaSeDespesaExiste(id);

        return ResponseEntity.ok(despesaMapper.toDto(despesa.get()));
    }

    @Transactional
    public ResponseEntity<DespesaDto> atualizaDespesa(String id, DespesaDto despesaDto) throws DespesaNaoEncontradaException {

        verificaSeDespesaExiste(id);

        return ResponseEntity.ok(despesaMapper.toDto(Despesa.builder()
                .descricao(despesaDto.getDescricao())
                .valor(despesaDto.getValor())
                .data(despesaDto.getData())
                .build()));
    }

    @Transactional
    public ResponseEntity removerDespesa(String id) throws DespesaNaoEncontradaException {

        verificaSeDespesaExiste(id);

        despesaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private void verificaSeDespesaJaExisteNoMesAtual(DespesaDto despesaDto) throws DespesaDuplicadaException {
        Optional<Despesa> despesaEncontrada = despesaRepository.findByDescricaoIgnoreCase(despesaDto.getDescricao());

        if (despesaEncontrada.isPresent()) {
            if (despesaDto.getData().getMonth() == despesaEncontrada.get().getData().getMonth()) {
                throw new DespesaDuplicadaException();
            }
        }
    }

    private Optional<Despesa> verificaSeDespesaExiste(String id) throws DespesaNaoEncontradaException {
        Optional<Despesa> despesa = despesaRepository.findById(id);

        if (!despesa.isPresent()) {
            throw new DespesaNaoEncontradaException();
        }
        return despesa;
    }

    public List<DespesaDto> buscaDespesaPeloMesEAno(Integer ano, Integer mes) {
        List<Despesa> despesasEncontradas = despesaRepository.findAll();

        List<Despesa> despesasDentroDoRangeDesejado = new ArrayList<>();

        for (int i = 0; i < despesasEncontradas.size(); i++) {
            LocalDate despesa = despesasEncontradas.get(i).getData();
            if (despesa.getYear() == ano) {
                if (despesa.getMonthValue() == mes) {
                    despesasDentroDoRangeDesejado.add(despesasEncontradas.get(i));
                }
            }
        }

        return despesasDentroDoRangeDesejado.stream().map(despesaMapper::toDto).collect(Collectors.toList());
    }
}
