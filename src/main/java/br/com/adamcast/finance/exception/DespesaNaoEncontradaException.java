package br.com.adamcast.finance.exception;

public class DespesaNaoEncontradaException extends Exception {
    public DespesaNaoEncontradaException() {
        super("Despesa não encontrada. Verifique o ID informado.");
    }
}
