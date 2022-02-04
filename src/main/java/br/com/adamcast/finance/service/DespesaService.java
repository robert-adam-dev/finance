package br.com.adamcast.finance.service;

import br.com.adamcast.finance.mapper.DespesaMapper;
import br.com.adamcast.finance.model.Despesa;
import br.com.adamcast.finance.model.dto.DespesaDto;
import br.com.adamcast.finance.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DespesaService {

    @Autowired
    DespesaRepository despesaRepository;

    private final DespesaMapper despesaMapper = DespesaMapper.INSTANCE;

    @Transactional
    public DespesaDto cadastraDespesa(DespesaDto despesaDto) {
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

    public ResponseEntity<DespesaDto> buscaDespesaPeloId(String id) {
        Optional<Despesa> despesa = despesaRepository.findById(id);

        if (despesa.isPresent()) {
            return ResponseEntity.ok(despesaMapper.toDto(despesa.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<DespesaDto> atualizaDespesa(String id, DespesaDto despesaDto) {
        Optional<Despesa> despesa = despesaRepository.findById(id);

        if (despesa.isPresent()) {
            Despesa despesaASerAtualizada = despesa.get();

            despesaASerAtualizada.setDescricao(despesaDto.getDescricao());
            despesaASerAtualizada.setValor(despesaDto.getValor());
            despesaASerAtualizada.setData(despesaDto.getData());

            Despesa despesaAtualizada = despesaRepository.save(despesaASerAtualizada);

            return ResponseEntity.ok(despesaMapper.toDto(despesaAtualizada));
        }

        return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity removerDespesa(String id) {
        Optional<Despesa> despesa = despesaRepository.findById(id);

        if (!despesa.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        despesaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private void verificaSeDespesaJaExisteNoMesAtual(DespesaDto despesaDto) {
        Optional<Despesa> despesaEncontrada = despesaRepository.findByDescricaoIgnoreCase(despesaDto.getDescricao());
        if (despesaEncontrada.isPresent()) {
            if (despesaDto.getData().getMonth() == despesaEncontrada.get().getData().getMonth()) {
                throw new RuntimeException("Despesa duplicada");
            }
        }
    }
}
