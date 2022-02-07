package br.com.adamcast.finance.service;

import br.com.adamcast.finance.exception.ReceitaDuplicadaException;
import br.com.adamcast.finance.exception.ReceitaNaoEncontradaException;
import br.com.adamcast.finance.mapper.ReceitaMapper;
import br.com.adamcast.finance.model.Receita;
import br.com.adamcast.finance.model.dto.ReceitaDto;
import br.com.adamcast.finance.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceitaService {

    @Autowired
    ReceitaRepository receitaRepository;

    private final ReceitaMapper receitaMapper = ReceitaMapper.INSTANCE;

    @Transactional
    public ReceitaDto cadastraReceita(ReceitaDto receitaDto) throws ReceitaDuplicadaException {
        verificaSeReceitaJaExisteNoMesAtual(receitaDto);
        Receita receita = receitaRepository.save(receitaMapper.toModel(receitaDto));

        return receitaMapper.toDto(receita);
    }

    public ResponseEntity<List<ReceitaDto>> buscaTodasReceitas() {
        List<Receita> todasAsReceitas = receitaRepository.findAll();

        return ResponseEntity.ok(todasAsReceitas.stream().map(receitaMapper::toDto).collect(Collectors.toList()));
    }

    public ResponseEntity<List<ReceitaDto>> buscaReceitasPelaDescricao(String descricao) {
        List<Receita> todasAsReceitas = receitaRepository.findByDescricaoContainingIgnoreCase(descricao);

        return ResponseEntity.ok(todasAsReceitas.stream().map(receitaMapper::toDto).collect(Collectors.toList()));
    }

    public ResponseEntity<ReceitaDto> buscaReceitaPeloId(String id) throws ReceitaNaoEncontradaException {
        Optional<Receita> receita = verificaSeReceitaExiste(id);

        return ResponseEntity.ok(receitaMapper.toDto(receita.get()));
    }

    @Transactional
    public ResponseEntity<ReceitaDto> atualizaReceita(String id, ReceitaDto receitaDto) throws ReceitaNaoEncontradaException {

        verificaSeReceitaExiste(id);

        return ResponseEntity.ok(receitaMapper.toDto(Receita.builder()
                .descricao(receitaDto.getDescricao())
                .data(receitaDto.getData())
                .valor(receitaDto.getValor())
                .build()));
    }

    @Transactional
    public ResponseEntity removerReceita(String id) throws ReceitaNaoEncontradaException {
        verificaSeReceitaExiste(id);

        receitaRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

    private void verificaSeReceitaJaExisteNoMesAtual(ReceitaDto receitaDto) throws ReceitaDuplicadaException {
        Optional<Receita> receitaEncontrada = receitaRepository.findByDescricaoIgnoreCase(receitaDto.getDescricao());
        if (receitaEncontrada.isPresent()) {
            if (receitaDto.getData().getMonth() == receitaEncontrada.get().getData().getMonth()) {
                throw new ReceitaDuplicadaException();
            }
        }
    }

    private Optional<Receita> verificaSeReceitaExiste(String id) throws ReceitaNaoEncontradaException {
        Optional<Receita> receita = receitaRepository.findById(id);

        if (!receita.isPresent()) {
            throw new ReceitaNaoEncontradaException();
        }
        return receita;
    }
}
