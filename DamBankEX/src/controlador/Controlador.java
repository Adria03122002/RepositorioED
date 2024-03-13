package controlador;

import java.util.ArrayList;
import java.util.Hashtable;

import excepcion.AvisarHaciendaException;
import excepcion.CuentaException;
import excepcion.DNIExcepcion;
import excepcion.PersonaException;
import modelo.CuentaBancaria;
import modelo.Movimientos;
import modelo.Persona;

public class Controlador {
	
	private static Hashtable<String,Persona> misPersonas = new Hashtable<String,Persona>();
			/**************PERSONAS************/

		public static void crearPersona(String dni, String nom) throws DNIExcepcion,PersonaException {
			Comprobaciones.verificarDNI(dni);
			isExistPersona(dni);
				if(!nom.isEmpty()) {
					Persona p = new Persona(dni, nom);
					misPersonas.put(dni, p);
				}else throw new PersonaException("El campo nombre no puede estar vacio");
		}
	
		private static void isExistPersona(String dni) throws PersonaException {
			if(misPersonas.containsKey(dni))
				throw new PersonaException("Ya existe la persona con dni: " + dni);
		}
		
			/*Cuentas Bancarias */

//	Crea una cuenta de banco a una persona EXISTENTE
		public static CuentaBancaria crearCuenta(String dni, String numCuenta, double saldo) throws CuentaException, PersonaException {
		    // Verificar si la persona existe
		    if (misPersonas.containsKey(dni)) {
		        // Obtener la persona
		        
		        //verificar si ya existe esa cuenta para alguien
		    	for(Persona per: misPersonas.values()) {
					for(int i = 0; i < 3; i++) {
						if(per.getCnb()[i] != null) {
							if(per.getCnb()[i].getNumCuenta().equals(numCuenta))
								throw new CuentaException("Esa cuenta ya existe para otra persona");
						}
					}
				}
		        Persona p = misPersonas.get(dni);
		        // Si no se encontró una cuenta bancaria con el mismo número, se crea una nueva
		        p.crearCuenta(numCuenta, saldo);
		        // Obtener la cuenta bancaria recién creada
		        for (CuentaBancaria c : p.getCnb()) {
		            if (c != null && c.getNumCuenta().equals(numCuenta)) {
		                return c;
		            }
		        }
		    } else {
		        throw new PersonaException("La persona con DNI " + dni + " no existe");
		    }
		    throw new CuentaException("La cuenta no se ha podido crear"); // En caso de que no se haya creado la cuenta, devolvemos null
		}

	//devolver el saldo
		public static double getSaldo(String dni, String nCuent) throws CuentaException {
			Persona p = misPersonas.get(dni);
			for(CuentaBancaria c : p.getCnb()) {
				if(c.getNumCuenta().equals(nCuent)) {
					return c.getSaldo();
				}
			}
			throw new CuentaException("No se ha encontrado el numero de cuenta");
		}

	public static CuentaBancaria[] obtenerCuenta(String dni) throws PersonaException {
	    	if(misPersonas.containsKey(dni)) {
	    		Persona p = misPersonas.get(dni);
		    	return p.getCnb();
	    	}
			throw new PersonaException("La cuenta registrada no existe");
	}
	
	public static String buscarPersona(String dni) throws PersonaException {	
		 		return misPersonas.get(dni).getNombre();
	}
	
		//Cuentas Bancarias
		public static void ingresarOpagar(String dni, String numCuenta, double cantidad, int operacion) throws AvisarHaciendaException,PersonaException {
			
				Persona p = misPersonas.get(dni);
				p.ingresarOpagar(numCuenta, cantidad, operacion);
				
		}
		
		public static String verArray() {
			String msj = "";
			for(String key : misPersonas.keySet()) {
				Persona p = misPersonas.get(key);
				msj += "Persona: " + p.getInfo() + "\n" ;
			}	
			return msj;
		}
		
		
		//devolver movimientos es un arrayList
		
		public static ArrayList<Movimientos> getMovs(String dni, String nCuent) throws CuentaException{
			Persona p = misPersonas.get(dni);
			
			for(CuentaBancaria c: p.getCnb()) {
				if(c.getNumCuenta().equals(nCuent)) {
					return c.getMovs();
				}
			}
			
			throw new CuentaException("No se ha encontrado la cuenta bancaria");
		}
		
		
		
		public static String verCuentas(String dni) {
			if(misPersonas.containsKey(dni)) {
				return misPersonas.get(dni).getInfoCuentas();
			}
				return "Algo no ha salido";
		}
}