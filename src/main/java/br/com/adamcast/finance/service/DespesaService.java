package br.com.adamcast.finance.service;

import br.com.adamcast.finance.mapper.DespesaMapper;
import br.com.adamcast.finance.model.Despesa;
import br.com.adamcast.finance.model.dto.DespesaDto;
import br.com.adamcast.finance.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DespesaService {

    @Autowired
    DespesaRepository despesaRepository;

    private final DespesaMapper despesaMapper = DespesaMapper.INSTANCE;

    public DespesaDto criarNovaDespesa(DespesaDto despesaDto) {
        Despesa despesaSalva = despesaRepository.save(despesaMapper.toModel(despesaDto));
        return despesaMapper.toDto(despesaSalva);
    }

    public List<DespesaDto> buscaTodasDespesas() {
        List<Despesa> todasAsDespesas = despesaRepository.findAll();
        return todasAsDespesas.stream().map(despesaMapper::toDto).collect(Collectors.toList());
    }

    public List<DespesaDto> buscaDespesaPelaDescricao(String descricao) {
        List<Despesa> todasAsDespesas = despesaRepository.findByDescricaoContainingIgnoreCase(descricao);
        return todasAsDespesas.stream().map(despesaMapper::toDto).collect(Collectors.toList());
    }
}
