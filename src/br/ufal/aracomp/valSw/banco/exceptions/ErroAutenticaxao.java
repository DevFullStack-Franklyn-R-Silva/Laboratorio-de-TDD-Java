package br.ufal.aracomp.valSw.banco.exceptions;

public class ErroAutenticaxao extends Exception{

	private static final long serialVersionUID = -7697501564626828326L;
	
	public ErroAutenticaxao(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
	public ErroAutenticaxao(String mensagem) {
		super(mensagem);
	}

}
