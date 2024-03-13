package excepcion;

public class DNIExcepcion extends Exception{
	private String msj;
	
	public DNIExcepcion(String msj) {
		super(msj);
	}
	
	public String toString() {
		return msj;
	}
	
	
}
