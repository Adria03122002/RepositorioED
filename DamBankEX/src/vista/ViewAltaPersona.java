package vista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.Controlador;
import excepcion.DNIExcepcion;
import excepcion.PersonaException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewAltaPersona extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtDni;
	private JTextField txtNombre;
	private static String dni, nombre;
	private static ViewAltaPersona dialog;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dialog = new ViewAltaPersona();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),"Error: ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public ViewAltaPersona(){
		setTitle("Alta Persona");
		setBounds(100, 100, 355, 213);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DNI: ");
		lblNewLabel.setBounds(23, 39, 45, 13);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(23, 65, 58, 23);
		getContentPane().add(lblNombre);
		
		txtDni = new JTextField();
		txtDni.setBounds(66, 36, 138, 19);
		getContentPane().add(txtDni);
		txtDni.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(76, 67, 240, 19);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					dni = txtDni.getText();
					nombre = txtNombre.getText();
					Controlador.crearPersona(dni, nombre);
					
					ViewAccesoCuentasBancarias vacb = new ViewAccesoCuentasBancarias(dni, nombre);
					vacb.setVisible(true);
					dialog.setVisible(false);
				} catch (DNIExcepcion | PersonaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Error: ", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnGuardar.setBounds(64, 123, 93, 31);
		getContentPane().add(btnGuardar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					dialog.dispose();
				}
		});
		btnVolver.setBounds(179, 123, 93, 31);
		getContentPane().add(btnVolver);

	}
	
}
