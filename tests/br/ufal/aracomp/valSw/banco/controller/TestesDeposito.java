package br.ufal.aracomp.valSw.banco.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
class TestesDeposito {
	
	private static ITransactionsController controller;
	
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		TestesDeposito.controller = new TransactionsController();
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
	 * Conta valida, valor OK
	 */
	@Test
	void testOK() {
		try {
			Conta c = new Conta(1, "123456", 100);
			controller.depositar(c, 10);
			assertEquals(110, c.getSaldo());
		} catch (ErroGeral e) {
			fail("EXCE√á√ÉO INESPERADA: "+e.getStackTrace());
		}
	}
	
	
	//************
	//ERRO VALOR
	//************
	
	/**
	 * Conta valida, valor negativo
	 */
	@Test
	void testErroValorNegativo() {
		try {
			Conta c = new Conta(1, "123456", 100);
		Exception e = assertThrows(ErroGeral.class, () -> {controller.depositar(c, -10);});
			//todo posso analisar outros detalhes da exce√ß√£o, caso desejado; e.g., mensagem de erro.
		} catch (ErroGeral e) {
			fail("EXCE√á√ÉO INESPERADA: "+e.getStackTrace());
		}
//		Throwable exception = assertThrows(ErroGeral.class, () -> {
//	        throw new ErroGeral("N„o È possÌvel depositar um valor negativo!");
//	    });
//	    assertEquals("N„o È possÌvel depositar um valor negativo!", exception.getMessage());
	   
	}
	
	
	
	/**
	 * Conta valida, valor 0
	 */
	@Test
	void testErroValorZero() {
		try {
			Conta c = new Conta(1, "123456", 100);
			Exception e = assertThrows(ErroGeral.class, () -> {controller.depositar(c, 0);});
			//todo posso analisar outros detalhes da exce√ß√£o, caso desejado; e.g., mensagem de erro.
		} catch (ErroGeral e) {
			fail("EXCE√á√ÉO INESPERADA: "+e.getStackTrace());
		}
//		Throwable exception = assertThrows(ErroGeral.class, () -> {
//	        throw new ErroGeral("N„o È possÌvel depositar um valor nulo!");
//	    });
//	    assertEquals("N„o È possÌvel depositar um valor nulo!", exception.getMessage());
	}
	
	
	
	
	//************
	//ERRO CONTA
	//************
	
	/**
	 * Conta NULL, valor valido
	 */
	@Test
	void testErroContaNull() {
		Conta c = null;
		Exception e = assertThrows(ErroGeral.class, () -> {controller.depositar(c, 10);});
		//todo posso analisar outros detalhes da exce√ß√£o, caso desejado; e.g., mensagem de erro.
	}
	

}
