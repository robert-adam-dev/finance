package br.com.adamcast.finance.service;

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
    public ReceitaDto cadastraReceita(ReceitaDto receitaDto) {
        verificaSeReceitaJaExisteNoMesAtual(receitaDto);
        Receita receita = receitaRepository.save(receitaMapper.toModel(receitaDto));

        return receitaMapper.toDto(receita);
    }

    private void verificaSeReceitaJaExisteNoMesAtual(ReceitaDto receitaDto) {
        Optional<Receita> receitaEncontrada = receitaRepository.findByDescricaoIgnoreCase(receitaDto.getDescricao());
        if (receitaEncontrada.isPresent()) {
            if (receitaDto.getData().getMonth() == receitaEncontrada.get().getData().getMonth()) {
                throw new RuntimeException("Receita duplicada");
            }
        }
    }

    public ResponseEntity<List<ReceitaDto>> buscaTodasReceitas() {
        List<Receita> todasAsReceitas = receitaRepository.findAll();

        return ResponseEntity.ok(todasAsReceitas.stream().map(receitaMapper::toDto).collect(Collectors.toList()));
    }

    public ResponseEntity<List<ReceitaDto>> buscaReceitasPelaDescricao(String descricao) {
        List<Receita> todasAsReceitas = receitaRepository.findByDescricaoContainingIgnoreCase(descricao);

        return ResponseEntity.ok(todasAsReceitas.stream().map(receitaMapper::toDto).collect(Collectors.toList()));
    }

    public ResponseEntity<ReceitaDto> buscaReceitaPeloId(String id) {
        Optional<Receita> receita = receitaRepository.findById(id);

        if (receita.isPresent()) {
            return ResponseEntity.ok(receitaMapper.toDto(receita.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<ReceitaDto> atualizaReceita(String id, ReceitaDto receitaDto) {

        Optional<Receita> receita = receitaRepository.findById(id);

        if (receita.isPresent()) {
            Receita receitaASerAtualizada = receita.get();

            receitaASerAtualizada.setDescricao(receitaDto.getDescricao());
            receitaASerAtualizada.setValor(receitaDto.getValor());
            receitaASerAtualizada.setData(receitaDto.getData());

            Receita receitaAtualizada = receitaRepository.save(receitaASerAtualizada);

            return ResponseEntity.ok(receitaMapper.toDto(receitaAtualizada));
        }

        return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity removerReceita(String id) {
        Optional<Receita> receita = receitaRepository.findById(id);

        if (!receita.isPresent()) {
            return ResponseEntity.notFound().build();

        }

        receitaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
