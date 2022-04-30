package br.ufal.aracomp.valSw.banco.model;

import br.ufal.aracomp.valSw.banco.exceptions.ErroGeral;
import br.ufal.aracomp.valSw.banco.util.Util;

public class Conta {
	private int numero;
	private String hashSenha;
	private float saldo;
	
	
	public Conta(int numero, String senha, float saldo) throws ErroGeral {
		this.numero = numero;
		this.saldo = saldo;
		this.hashSenha = Util.gerarHash(senha);
	}
	
	public int getNumero() {
		return numero;
	}
	
	public boolean validarSenha(String senha) {
		try {
			if(Util.gerarHash(senha).equals(hashSenha))
				return true;
			else
				return false;
		} catch (ErroGeral e) {
			return false;
		}
	}
	
	public float getSaldo(){
		return saldo;
	}
	
	public void adicionar(float valor) {
			this.saldo+=valor;
	}
	
	public void retirar(float valor) {
		this.saldo-=valor;
	}
	
	@Override
	public String toString() {
		String out = "Conta: " + this.numero + " ==> " + this.saldo;
		return out;
	}
}
