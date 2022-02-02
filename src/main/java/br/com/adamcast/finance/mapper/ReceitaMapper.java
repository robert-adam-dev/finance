package br.com.adamcast.finance.mapper;

import br.com.adamcast.finance.model.Receita;
import br.com.adamcast.finance.model.dto.ReceitaDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReceitaMapper {

    ReceitaMapper INSTANCE = Mappers.getMapper(ReceitaMapper.class);

    ReceitaDto toDto(Receita receita);

    Receita toModel(ReceitaDto receitaDto);
}
