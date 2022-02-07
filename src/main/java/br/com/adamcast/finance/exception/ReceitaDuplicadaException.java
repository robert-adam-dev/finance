package br.com.adamcast.finance.exception;

public class ReceitaDuplicadaException extends Exception {
    public ReceitaDuplicadaException() {
        super("Receita duplicada. Por favor verifique a descrição informada.");
    }
}
