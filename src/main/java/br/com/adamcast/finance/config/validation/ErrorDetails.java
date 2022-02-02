package br.com.adamcast.finance.config.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorDetails {
    private String campo;
    private String erro;
}
