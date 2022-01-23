package br.com.adamcast.finance.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DespesaDto {
    private String id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
}
