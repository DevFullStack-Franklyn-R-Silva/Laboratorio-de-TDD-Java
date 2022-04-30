package br.ufal.aracomp.valSw.banco.view;

import br.ufal.aracomp.valSw.banco.controller.ITransactionsController;
import br.ufal.aracomp.valSw.banco.controller.TransactionsController;
import br.ufal.aracomp.valSw.banco.exceptions.ErroAutenticaxao;
import br.ufal.aracomp.valSw.banco.exceptions.ErroGeral;
import br.ufal.aracomp.valSw.banco.model.Conta;

public class Principal {
	public static void main(String[] args) {
		try {
			Conta c1 = new Conta(1, "123", 200);
			Conta c2 = new Conta(2, "654321", 100);
			Conta c3= new Conta(3, "654322", -100);
			Conta c4= new Conta(4, "123", 500);
			
			ITransactionsController controller = new TransactionsController();
			
//			System.out.println("INICIO:");
//			System.out.println(c1);
//			System.out.println(c2);
//			controller.transferir(c1, c2, "12", -10);
//			
//			System.out.println("\nAPOS TRANSFERENCIA:");
//			System.out.println(c1);
//			System.out.println(c2);
//			
			System.out.println();
//			controller.depositar(c3, 3000);
//			System.out.println(controller.consultarSaldo(c3, "654322"));
//			
//			controller.sacar(c3, "654322", 500);
			System.out.println(controller.consultarSaldo(c3, "654322"));
//			
//			System.out.println("=================");
//			System.out.println(controller.consultarSaldo(c4, "123"));
//			controller.depositar(c4, 100);
//			System.out.println(controller.consultarSaldo(c4, "123"));
		} catch (ErroGeral e) {
			System.err.println("ERRO: "+e.getMessage());
		} catch (ErroAutenticaxao e) {
			System.err.println("ERRO: "+e.getMessage());
		}
	}
}
