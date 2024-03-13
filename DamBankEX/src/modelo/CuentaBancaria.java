package modelo;

import java.util.ArrayList;

import controlador.Comprobaciones;
import excepcion.AvisarHaciendaException;
import excepcion.CuentaException;

public class CuentaBancaria {
	private String numCuenta;
	private double saldo;
	private ArrayList<Movimientos> movs;
	//ATRIBUTOS CONSTANTES DE LA CLASE
//	private static final double SALDO_MINIMO = -50.0;
//	private static final double MOV_MAXIMO = 3000.0;
	
	
	//constructor
	public CuentaBancaria(String numCuenta, double saldo) throws CuentaException{
		Comprobaciones.verificarIban(numCuenta);
		this.numCuenta = numCuenta;
		if(saldo <= 0) throw new CuentaException("El saldo no puede ser menor de 0");
		this.saldo = saldo;
		this.movs = new ArrayList<Movimientos>();
	}

	public String getNumCuenta() {
		return numCuenta;
	}
	public double getSaldo() throws CuentaException{
		if(this.saldo < 0 && this.saldo > -50) throw new CuentaException("AVISO: Saldo negaitvo");
		return saldo;
	}

	public ArrayList<Movimientos> getMovs() {
		return movs;
	}

	public void addMov(String tipo, double cantidad) {
		movs.add(new Movimientos(tipo, cantidad));
	}

	/*
	 * Metodos para pagar y ingresar (Equivalente al setSaldo)
	 */
	public void ingresar(String tipo,double cantidad) throws AvisarHaciendaException {
		this.saldo += cantidad;
		addMov(tipo, cantidad);
		if(cantidad >= 3000) throw new AvisarHaciendaException("Aviso: Notificar a hacienda");
	}
	
	public String getInfoCuentas() {
		return this.numCuenta + " : " + this.saldo;
	}
	
}
