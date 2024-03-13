package excepcion;

public class AvisarHaciendaException extends Exception{
	private String msj;
	
	public AvisarHaciendaException(String msj) {
		super(msj);
	}
	
	public String toString() {
		return msj;
	}
}