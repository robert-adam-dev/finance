package br.com.adamcast.finance.service;

import br.com.adamcast.finance.model.Categoria;
import br.com.adamcast.finance.model.dto.CategoriaDto;
import br.com.adamcast.finance.model.dto.DespesaDto;
import br.com.adamcast.finance.model.dto.ReceitaDto;
import br.com.adamcast.finance.model.dto.ResumoDoMesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ResumoService {

    @Autowired
    DespesaService despesaService;

    @Autowired
    ReceitaService receitaService;

    public ResumoDoMesDto geraResumoMensal(Integer ano, Integer mes) {
        List<DespesaDto> despesas = despesaService.buscaDespesaPeloMesEAno(ano, mes);
        List<ReceitaDto> receitas = receitaService.buscaReceitaPeloMesEAno(ano, mes);
        List<CategoriaDto> totalGastoPorCategoria = new ArrayList<>();

        Map<Categoria, BigDecimal> gastoPorCategoria = despesas.stream()
                .collect(Collectors.groupingBy(DespesaDto::getCategoria,
                        Collectors.mapping(DespesaDto::getValor,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        gastoPorCategoria.forEach((categoria, valorTotal) -> {
            totalGastoPorCategoria.add(CategoriaDto.builder()
                    .categoria(categoria)
                    .total(valorTotal)
                    .build());
        });

        BigDecimal totalDespesas = despesas.stream().map(DespesaDto::getValor).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        BigDecimal totalReceitas = receitas.stream().map(ReceitaDto::getValor).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        return ResumoDoMesDto.builder()
                .totalDespesas(totalDespesas)
                .totalReceitas(totalReceitas)
                .saldoFinal(totalReceitas.subtract(totalDespesas))
                .gastosPorCategoria(totalGastoPorCategoria)
                .build();
    }
}
