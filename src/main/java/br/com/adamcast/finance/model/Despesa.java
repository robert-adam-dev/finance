package br.com.adamcast.finance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "despesa")
public class Despesa {

    @Id
    private String id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private Categoria categoria;

}
