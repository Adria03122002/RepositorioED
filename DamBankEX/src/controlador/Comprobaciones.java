package controlador;

import excepcion.CuentaException;
import excepcion.DNIExcepcion;

public class Comprobaciones {
	//VALIDAR DNI
		 public static void verificarDNI(String dni) throws DNIExcepcion {
			 if (dni.matches("\\d{8}[A-Z]")) {
		            int numero = Integer.parseInt(dni.substring(0, 8));
		            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
		            char letraCalculada = letras.charAt(numero % 23);
		            char letraProporcionada = dni.charAt(8);
		            if(!(letraCalculada == letraProporcionada)) {
		            	throw new DNIExcepcion("Se esperava otra letra");
		            }
		        }else throw new DNIExcepcion("El dni esta mal formado");
		       
		    }
		 //Validar Iban
		 public static void verificarIban(String iban) throws CuentaException{
			// Verificar longitud
		        if (iban.length() < 4 || iban.length() > 34) {
		            throw new CuentaException("El iban tiene una longitud mal");
		        }
		        // Verificar formato
		       if (!iban.matches("^[A-Z]{2}\\d{2}[A-Z0-9]{4}\\d{7}([A-Z0-9]?\\d{1,8})?$")) {
		        	throw new CuentaException("El iban esta mal formado");
		        }
		       // si que funciona

		 }
}
