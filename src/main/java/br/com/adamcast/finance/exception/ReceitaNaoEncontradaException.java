package br.com.adamcast.finance.exception;

public class ReceitaNaoEncontradaException extends Exception {
    public ReceitaNaoEncontradaException() {
        super("Receita não encontrada. Verifique o ID informado.");
    }
}
