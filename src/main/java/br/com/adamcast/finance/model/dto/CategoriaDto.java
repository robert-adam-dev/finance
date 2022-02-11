package br.com.adamcast.finance.model.dto;

import br.com.adamcast.finance.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDto {
    private Categoria categoria;
    private BigDecimal total;
}
