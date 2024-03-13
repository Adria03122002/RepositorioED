package excepcion;

public class PersonaException extends Exception{
	private String msj;
	
	public PersonaException(String msj) {
		super(msj);
	}
	
	public String toString() {
		return msj;
	}
	
}
