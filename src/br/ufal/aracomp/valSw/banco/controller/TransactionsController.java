package br.ufal.aracomp.valSw.banco.controller;

import br.ufal.aracomp.valSw.banco.exceptions.ErroAutenticaxao;
import br.ufal.aracomp.valSw.banco.exceptions.ErroGeral;
import br.ufal.aracomp.valSw.banco.model.Conta;

public class TransactionsController implements ITransactionsController {

	@Override
	public float consultarSaldo(Conta c, String senha) throws ErroGeral,ErroAutenticaxao {
//		if (c.validarSenha(senha)) {
//			if (c.getSaldo() < 0) {
//				throw new ErroGeral("Saldo negativo!");
//			} else {
//				System.out.print("Seu saldo é: ");
//				return c.getSaldo();
//			}
//		} else if (senha == null) {
//			throw new ErroGeral("Senha vazia!");
//		} else if (senha.contentEquals(senha) != c.validarSenha(senha)) {
//			throw new ErroGeral("Senha Incorreta!");
//		}
//		throw new ErroGeral("Senha da conta incorreta ou vazia!");
		if(c != null) {
			if(c.getSaldo() < 0) {
				throw new ErroGeral("Saldo negativo");
			}else if(senha == null) {
				throw new ErroAutenticaxao("Senha null");
			} else if(!c.validarSenha(senha)) {
				throw new ErroAutenticaxao("Senha invalida");
			}else {
				return c.getSaldo();
			}
		}else {
			throw new ErroGeral("Conta null");
		}
		
	}

	@Override
	public void depositar(Conta c, float valor) throws ErroGeral {
		if (c != null) {
			if (valor < 0) {
				throw new ErroGeral("Não é possível depositar um valor negativo!");
			} else if (valor == 0.0f) {
				throw new ErroGeral("Não é possível depositar um valor nulo!");
			} else {
				c.adicionar(valor);
			}
		} else {
			throw new ErroGeral("Conta null");
		}
	}

	@Override
	public void sacar(Conta c, String senha, float valor) throws ErroAutenticaxao, ErroGeral {
		if (c != null) {
			if (senha != null) {
				if(c.validarSenha(senha)) {
					if (valor == 0.0f) {
						throw new ErroGeral("Impossivel sacar 0");
					} else if (valor < 0) {
						throw new ErroGeral("Valor negativo");
					} else if (valor > c.getSaldo()) {
						throw new ErroGeral("Valor acima do permitido");
					} else if (valor <= c.getSaldo()) {
						c.retirar(valor);
					}
				}else {
					throw new ErroAutenticaxao("Senha invalida");
				}
			} else {
				throw new ErroGeral("Senha null");
			}
		} else {
			throw new ErroGeral("Conta null");
		}

	}

	@Override
	public void transferir(Conta origem, Conta destino, String senha, float valor) throws ErroAutenticaxao, ErroGeral {
		if (origem != null && destino != null) {
			if (valor == 0) {
				throw new ErroGeral("Valor nulo!");
			} else if (!origem.validarSenha(senha) && valor < 0) {
				throw new ErroAutenticaxao("Senha invalida, valor invalido");
			} else if (valor < 0) {
				throw new ErroGeral("Valor negativo!");
			} else if (valor > origem.getSaldo()) {
				throw new ErroGeral("Valor acima do permitido !");

			} else if (!origem.validarSenha(senha)) {
				throw new ErroAutenticaxao("Senha invalida");

			} else {
				origem.retirar(valor);
				destino.adicionar(valor);
			}
		} else {
			throw new ErroGeral("Contas nulas!");
		}

	}
}