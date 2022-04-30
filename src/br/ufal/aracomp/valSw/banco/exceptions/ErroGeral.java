package br.ufal.aracomp.valSw.banco.exceptions;

public class ErroGeral extends Exception{

	private static final long serialVersionUID = -3189681524941285236L;

	public ErroGeral(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
	public ErroGeral(String mensagem) {
		super(mensagem);
	}

}
