package excepcion;

public class CuentaException extends Exception{
	private String msj;
	
	public CuentaException(String msj) {
		super(msj);
	}
	
	public String toString() {
		return msj;
	}
}