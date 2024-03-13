package modelo;

import excepcion.AvisarHaciendaException;
import excepcion.CuentaException;
import excepcion.PersonaException;

public class Persona {
    private String dni;
    private String nombre;
    private CuentaBancaria[] cnb;

    public Persona(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
        cnb = new CuentaBancaria[3];
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDni() {
        return this.dni;
    }

    public String getInfo() {
        return this.dni + " y con nombre " + this.nombre;
    }

    public CuentaBancaria[] getCnb() {
		return cnb;
	}

	/***************************************************************
     ***********************Cuenta Bancaria ************************
     **************************************************************/

    // Crear cuenta
    public void crearCuenta(String numCuenta, double saldo) throws PersonaException, CuentaException {
       
        for (int i = 0; i < 3; i++) {
            if (cnb[i] == null) {
                cnb[i] = new CuentaBancaria(numCuenta, saldo); // Se supone que se crea
                break; // no me interesa que se llene todo el array con informacion repetida
            }
        }
        
    }

    // Devuelve true si la persona es morosa
    public boolean isMorosa() throws PersonaException, CuentaException  {
        boolean sem = false;
        for (int i = 0; i < 3; i++) {
            if (cnb[i] != null) {
                if (cnb[i].getSaldo() < 0) {
                    sem = true; // Si entra es morosa la persona
                    break; // Dice si es morosa en una cuenta asi que no hace falta seguir mirando
                }
            }
        }
        return sem;
    }

    // Existe la cuenta? Si o No;
    public boolean isExisteCuenta(String numCuenta) {
        boolean sem = false;
        for (int i = 0; i < 3; i++) {
            if (cnb[i].getNumCuenta().equals(numCuenta)) {
                sem = true;  // Existe por lo tanto no hay que seguir buscando
                break;
            }
        }
        return sem;
    }

    // Mostrar la informacion de las cuentas
    public String getInfoCuentas() {
        StringBuilder msj = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            if (cnb[i] != null) {
                msj.append(cnb[i].getInfoCuentas()).append("\n");
            } else {
                msj.append("Cuenta vacia \n");
            }
        }
        return msj.toString();
    }

    
    private void encontrarCuenta(String cuenta) throws PersonaException {
    	 boolean cuentaEncontrada = false;
    	    for (CuentaBancaria c : cnb) {
    	        if (c != null && c.getNumCuenta().equals(cuenta)) {
    	            cuentaEncontrada = true;
    	            break;
    	        }
    	    }
    	    if (!cuentaEncontrada) {
    	        throw new PersonaException("La persona no tiene una cuenta asociada con el número de cuenta proporcionado");
    	    }
    }
    
    
    // Opcion del switch
    public void ingresarOpagar(String cuenta, double saldo, int i) throws PersonaException ,AvisarHaciendaException{
        boolean sem = false;
        encontrarCuenta(cuenta);
        switch (i) {
            case 0:
                for (CuentaBancaria c : cnb) {
                    if (c != null) {
                        if (c.getNumCuenta().equals(cuenta)) {
                            c.ingresar("Ingreso",saldo);
                            sem = true;
                            break;
                        }
                    }
                }
                break;
            case 1:
                for (CuentaBancaria c : cnb) {
                    if (c != null) {
                        if (c.getNumCuenta().equals(cuenta)) {
                        	 c.ingresar("Retiro",-saldo);
                            sem = true;
                            break;
                        }
                    }
                }
                break;
        }
        if (!sem) {
            throw new PersonaException("Operación no permitida para esta persona");
        }
    }
}
