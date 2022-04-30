package br.ufal.aracomp.valSw.banco.controller;
import static org.junit.jupiter.api.Assertions.*;

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
class TestesTransferir {
	
	private static ITransactionsController controller;
	
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		TestesTransferir.controller = new TransactionsController();
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
	 * Conta valida, senha OK, valor inferior
	 */
	@Test
	void testOKInferior() {
		try {
			Conta c1 = new Conta(1, "123456", 100);
			Conta c2 = new Conta(2, "654321", 200);
			controller.transferir(c1, c2, "123456", 10);
			assertEquals(90, c1.getSaldo());
			assertEquals(210, c2.getSaldo());
		} catch (ErroGeral | ErroAutenticaxao e) {
			fail("EXCEÇÃO INESPERADA: "+e.getStackTrace());
		}
	}
	
	/**
	 * Conta valida, senha OK, valor maximo
	 */
	@Test
	void testOKMaximo() {
		try {
			Conta c1 = new Conta(1, "123456", 100);
			Conta c2 = new Conta(2, "654321", 200);
			controller.transferir(c1, c2, "123456", 100);
			assertEquals(0, c1.getSaldo());
			assertEquals(300, c2.getSaldo());
		} catch (ErroGeral | ErroAutenticaxao e) {
			fail("EXCEÇÃO INESPERADA: "+e.getStackTrace());
		}
	}
	
	
	
	
	//************
	//ERRO VALOR
	//************
	
	/**
	 * Conta valida, senha OK, valor invalido(superior)
	 */
	@Test
	void testErroValorSuperior() {
		try {
			Conta c1 = new Conta(1, "123456", 100);
			Conta c2 = new Conta(2, "654321", 200);
			Exception e = assertThrows(ErroGeral.class, () -> {controller.transferir(c1, c2, "123456", 100.01f);});
			//todo posso analisar outros detalhes da exceção, caso desejado; e.g., mensagem de erro.
		} catch (ErroGeral e) {
			fail("EXCEÇÃO INESPERADA: "+e.getStackTrace());
		}
	}
	
	/**
	 * Conta valida, senha OK, valor negativo
	 */
	@Test
	void testErroValorNegativo() {
		try {
			Conta c1 = new Conta(1, "123456", 100);
			Conta c2 = new Conta(2, "654321", 200);
			Exception e = assertThrows(ErroGeral.class, () -> {controller.transferir(c1, c2, "123456", -10);});
			//todo posso analisar outros detalhes da exceção, caso desejado; e.g., mensagem de erro.
		} catch (ErroGeral e) {
			fail("EXCEÇÃO INESPERADA: "+e.getStackTrace());
		}
	}
	
	
	/**
	 * Conta valida, senha OK, valor 0
	 */
	@Test
	void testErroValorZero() {
		try {
			Conta c1 = new Conta(1, "123456", 100);
			Conta c2 = new Conta(2, "654321", 200);
			Exception e = assertThrows(ErroGeral.class, () -> {controller.transferir(c1, c2, "123456", 0);});
			//todo posso analisar outros detalhes da exceção, caso desejado; e.g., mensagem de erro.
		} catch (ErroGeral e) {
			fail("EXCEÇÃO INESPERADA: "+e.getStackTrace());
		}
	}
	
	
	
	//************
	//ERRO CONTA
	//************
	
	
	/**
	 * Conta1 NULL, valor valido
	 */
	@Test
	void testErroConta1Null() {
		try {
			Conta c1 = null;
			Conta c2 = new Conta(2, "654321", 200);
			Exception e = assertThrows(ErroGeral.class, () -> {controller.transferir(c1, c2, "123456", 10);});
			//todo posso analisar outros detalhes da exceção, caso desejado; e.g., mensagem de erro.
		} catch (ErroGeral e) {
			fail("EXCEÇÃO INESPERADA: "+e.getStackTrace());
		}
	}
	
	
	/**
	 * Conta2 NULL, valor valido
	 */
	@Test
	void testErroConta2Null() {
		try {
			Conta c1 = new Conta(1, "123456", 100);
			Conta c2 = null;
			Exception e = assertThrows(ErroGeral.class, () -> {controller.transferir(c1, c2, "123456", 10);});
			//todo posso analisar outros detalhes da exceção, caso desejado; e.g., mensagem de erro.
		} catch (ErroGeral e) {
			fail("EXCEÇÃO INESPERADA: "+e.getStackTrace());
		}
	}
	
	
	//************
	//ERRO SENHA
	//************
	
	/**
	 * Senha INvalida, valor OK
	 */
	@Test
	void testErroSenhaInvalida() {
		try {
			Conta c1 = new Conta(1, "123456", 100);
			Conta c2 = new Conta(2, "654321", 200);
			Exception e = assertThrows(ErroAutenticaxao.class, () -> {controller.transferir(c1, c2, "147", 10);});
			//todo posso analisar outros detalhes da exceção, caso desejado; e.g., mensagem de erro.
		} catch (ErroGeral e) {
			fail("EXCEÇÃO INESPERADA: "+e.getStackTrace());
		}
	}
	
	
	/**
	 * Senha INvalida, valor INvalido
	 */
	@Test
	void testErroSenhaInvalidaValorInvalido() {
		try {
			Conta c1 = new Conta(1, "123456", 100);
			Conta c2 = new Conta(2, "654321", 200);
			Exception e = assertThrows(ErroAutenticaxao.class, () -> {controller.transferir(c1, c2, "147", -10);});
			//todo posso analisar outros detalhes da exceção, caso desejado; e.g., mensagem de erro.
		} catch (ErroGeral e) {
			fail("EXCEÇÃO INESPERADA: "+e.getStackTrace());
		}
	}
	
}
