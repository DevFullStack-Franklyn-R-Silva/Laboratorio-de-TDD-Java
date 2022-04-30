package br.ufal.aracomp.valSw.banco.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.ufal.aracomp.valSw.banco.exceptions.ErroAutenticaxao;
import br.ufal.aracomp.valSw.banco.exceptions.ErroGeral;
import br.ufal.aracomp.valSw.banco.model.Conta;

/**
 * 
 * @author patrick
 * Testar o metodo "float consultarSaldo(Conta c, String senha)" da classe "TransactionsController"
 * Classes de equivalencia para Conta: null, conta invalida com saldo negativo, conta invalida com senha null, conta valida com saldo positivo, conta valida com saldo zero
 * Classes de equivalencia para senha: correta, incorreta, null 
 *
 */
class TestesSaldo {
	
	private static ITransactionsController controller;
	
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		TestesSaldo.controller = new TransactionsController();
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Executando caso de teste...");
	}
	
	@AfterEach
	void tearDown() throws Exception {
		System.out.println("Executado!");
	}
	
	
	
	
	
	//************
	//OK
	//************
	
	/**
	 * Conta valida, senha OK
	 * @throws ErroAutenticaxao 
	 */
	@Test
	void testOK() {
		try {
			Conta c = new Conta(1, "123456", 100);
			float retorno = controller.consultarSaldo(c, "123456");
			assertEquals(100, retorno);
		} catch (ErroGeral | ErroAutenticaxao e) {
			fail("EXCEÇÃO INESPERADA: "+e.getStackTrace());
		}
	}
	
	
	/**
	 * Conta saldo 0, senha OK
	 * @throws ErroAutenticaxao 
	 */
	@Test
	void testOKSaldo0()  {
		try {
			Conta c = new Conta(1, "123456", 0);
			float retorno = controller.consultarSaldo(c, "123456");
			assertEquals(0, retorno);
		} catch (ErroGeral | ErroAutenticaxao e) {
			fail("EXCEÇÃO INESPERADA: "+e.getStackTrace());
		}
	}
	
	
	//************
	//ERRO SENHA
	//************
	
	
	/**
	 * Conta valida, senha Errada
	 */
	@Test
	void testErroSenhaIncorreta() {
		try {
			Conta c = new Conta(1, "123456", 100);
			Exception e = assertThrows(ErroAutenticaxao.class, () -> {controller.consultarSaldo(c, "2254414");});
			//todo posso analisar outros detalhes da exceção, caso desejado; e.g., mensagem de erro.
		} catch (ErroGeral e) {
			fail("EXCEÇÃO INESPERADA: "+e.getStackTrace());
		}
//		Throwable exception = assertThrows(ErroGeral.class, () -> {
//	        throw new ErroGeral("Senha Incorreta!");
//	    });
//	    assertEquals("Senha Incorreta!", exception.getMessage());
	}
	
	
	/**
	 * Conta valida, senha NULL
	 */
	@Test
	void testErroSenhaNull() {
		try {
			Conta c = new Conta(1, "123456", 100);
			Exception e = assertThrows(ErroAutenticaxao.class, () -> {controller.consultarSaldo(c, null);});
			//todo posso analisar outros detalhes da exceção, caso desejado; e.g., mensagem de erro.
		} catch (ErroGeral e) {
			fail("EXCEÇÃO INESPERADA: "+e.getStackTrace());
		}
//		Throwable exception = assertThrows(ErroGeral.class, () -> {
//	        throw new ErroGeral("Saldo vazia!");
//	    });
//	    assertEquals("Saldo vazia!", exception.getMessage());
	}
	
	
	
	
	//************
	//ERRO CONTA
	//************
	
	
	
	/**
	 * Conta invalida saldo negativo, senha OK
	 */
	@Test
	void testErroContaSaldoNegativo() {
//			Exception e = assertThrows(ErroGeral.class, () -> {Conta c = new Conta(1, "123456", -100);});
			//com o objeto da excecao, posso analisar outros detalhes da exceção, caso desejado; e.g., mensagem de erro.
			
			try {
				Conta c = new Conta(1, "123456", -100);
				Exception e = assertThrows(ErroGeral.class, () -> {controller.consultarSaldo(c, "123456");});
			}catch (ErroGeral  e) {
				fail("EXCEÇÃO INESPERADA: "+e.getStackTrace());
			}
//			Throwable exception = assertThrows(ErroGeral.class, () -> {
//		        throw new ErroGeral("Saldo negativo!");
//		    });
//		    assertEquals("Saldo negativo!", exception.getMessage());
			
	}
	
	
	
	/**
	 * Conta invalida senha null, senha OK
	 */
	@Test
	void testErroContaSenhaNull() {
//			Exception e = assertThrows(ErroGeral.class, () -> {Conta c = new Conta(1, null, 100);});
			//com o objeto da excecao, posso analisar outros detalhes da exceção, caso desejado; e.g., mensagem de erro.
			Conta c = null;
			Exception e = assertThrows(ErroGeral.class, () -> {controller.consultarSaldo(c, "123");});
			
		
//			Throwable exception = assertThrows(ErroGeral.class, () -> {
//		        throw new ErroGeral("Senha da conta incorreta ou vazia!");
//		    });
//		    assertEquals("Senha da conta incorreta ou vazia!", exception.getMessage());
			
	}
	
	

}
