package br.com.adamcast.finance;

import br.com.adamcast.finance.mapper.ReceitaMapper;
import br.com.adamcast.finance.model.Receita;
import br.com.adamcast.finance.model.dto.ReceitaDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReceitaMapperTest {

    @Test
    public void shouldMapReceitaDtoToModel() {

        // Given
        ReceitaDto receitaDto = new ReceitaDto("123a", "Teste", new BigDecimal("500.00"), LocalDate.now());

        // When
        Receita receita = ReceitaMapper.INSTANCE.toModel(receitaDto);

        //

        Assertions.assertEquals(receitaDto.getData().toString(), receita.getData().toString());
        Assertions.assertEquals(receitaDto.getDescricao(), receita.getDescricao());

    }
}
