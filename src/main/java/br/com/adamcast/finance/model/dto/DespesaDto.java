package br.com.adamcast.finance.model.dto;

import br.com.adamcast.finance.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DespesaDto {
    private String id;

    @NotNull
    @NotEmpty
    private String descricao;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 9, fraction = 2)
    private BigDecimal valor;

    @PastOrPresent
    private LocalDate data;

    private Categoria categoria;

    public Categoria getCategoria() {
        return Objects.isNull(categoria) ? Categoria.OUTRAS : categoria;
    }
}
