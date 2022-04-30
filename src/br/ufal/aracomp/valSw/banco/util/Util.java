package br.ufal.aracomp.valSw.banco.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.ufal.aracomp.valSw.banco.exceptions.ErroGeral;

public class Util {
	 public static String gerarHash(String input) throws ErroGeral{
		 try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            md.reset();
	            byte[] buffer = input.getBytes("UTF-8");
	            md.update(buffer);
	            byte[] digest = md.digest();

	            String hexStr = "";
	            for (int i = 0; i < digest.length; i++) {
	                hexStr +=  Integer.toString( ( digest[i] & 0xff ) + 0x100, 16).substring( 1 );
	            }
	            return hexStr;
		 }
		 catch(NoSuchAlgorithmException e) {
			 throw new ErroGeral("Erro na geração da Hash", e);
		 }
		 catch(UnsupportedEncodingException e) {
			 throw new ErroGeral("Erro na geração da Hash", e);
		 }
	 }
}
