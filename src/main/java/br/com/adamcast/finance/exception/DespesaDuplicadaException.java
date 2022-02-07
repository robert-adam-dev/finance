package br.com.adamcast.finance.exception;

public class DespesaDuplicadaException extends Exception {
    public DespesaDuplicadaException() {
        super("Despesa duplicada. Por favor verifique a descrição informada.");
    }
}
