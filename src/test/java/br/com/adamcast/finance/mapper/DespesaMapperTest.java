package br.com.adamcast.finance.mapper;

import br.com.adamcast.finance.model.Categoria;
import br.com.adamcast.finance.model.Despesa;
import br.com.adamcast.finance.model.dto.DespesaDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DespesaMapperTest {

    @Test
    public void shouldMapDespesaDtoToModel() {

        DespesaDto despesaDto = new DespesaDto("123a", "Teste", new BigDecimal("500.00"), LocalDate.now(), Categoria.OUTRAS);

        Despesa despesa = DespesaMapper.INSTANCE.toModel(despesaDto);

        Assertions.assertEquals(despesaDto.getData().toString(), despesa.getData().toString());
        Assertions.assertEquals(despesaDto.getDescricao(), despesa.getDescricao());
        Assertions.assertEquals(despesaDto.getDescricao(), despesa.getDescricao());

    }

    @Test
    public void shouldMapDespesaModelToDespesaDto() {

        Despesa despesa = new Despesa("123a", "Teste", new BigDecimal("500"), LocalDate.now(), Categoria.OUTRAS);

        DespesaDto despesaDto = DespesaMapper.INSTANCE.toDto(despesa);

        Assertions.assertEquals(despesa.getData().toString(), despesaDto.getData().toString());
        Assertions.assertEquals(despesa.getDescricao(), despesaDto.getDescricao());
        Assertions.assertEquals(despesa.getDescricao(), despesaDto.getDescricao());

    }
}
