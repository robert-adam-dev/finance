package br.com.adamcast.finance.mapper;

import br.com.adamcast.finance.model.Despesa;
import br.com.adamcast.finance.model.dto.DespesaDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DespesaMapper {
    DespesaMapper INSTANCE = Mappers.getMapper(DespesaMapper.class);

    DespesaDto toDto(Despesa despesa);

    Despesa toModel(DespesaDto despesaDto);
}
