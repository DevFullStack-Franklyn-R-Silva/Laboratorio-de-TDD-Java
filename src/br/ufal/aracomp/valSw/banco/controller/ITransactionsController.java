package br.ufal.aracomp.valSw.banco.controller;

import br.ufal.aracomp.valSw.banco.exceptions.ErroAutenticaxao;
import br.ufal.aracomp.valSw.banco.exceptions.ErroGeral;
import br.ufal.aracomp.valSw.banco.model.Conta;

public interface ITransactionsController {

	float consultarSaldo(Conta c, String senha) throws ErroGeral, ErroAutenticaxao;

	void depositar(Conta c, float valor) throws ErroGeral;

	void sacar(Conta c, String senha, float valor) throws ErroAutenticaxao, ErroGeral;

	void transferir(Conta origem, Conta destino, String senha, float valor) throws ErroAutenticaxao, ErroGeral;

}