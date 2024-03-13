package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import excepcion.PersonaException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewMainDamBank extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDni;
	private static ViewMainDamBank frame;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ViewMainDamBank();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),"Error: ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public ViewMainDamBank() {
		setTitle("DamBank");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnIniciarSesion = new JButton("Iniciar sesión");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String dni = txtDni.getText();
					String nom = Controlador.buscarPersona(dni);
					
					ViewAccesoCuentasBancarias vacb = new ViewAccesoCuentasBancarias(dni, nom);
					vacb.setVisible(true);
					frame.setVisible(false);
				} catch (PersonaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Error: ", JOptionPane.ERROR_MESSAGE);
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "No existe la persona con ese dni","Error: ", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		btnIniciarSesion.setBounds(65, 110, 214, 32);
		contentPane.add(btnIniciarSesion);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewAltaPersona vap = new ViewAltaPersona();
				vap.setVisible(true);
			}
		});
		btnRegistrar.setBounds(65, 152, 214, 32);
		contentPane.add(btnRegistrar);
		
		JLabel lblNewLabel = new JLabel("DNI");
		lblNewLabel.setBounds(22, 68, 58, 13);
		contentPane.add(lblNewLabel);
		
		txtDni = new JTextField();
		txtDni.setBounds(65, 65, 214, 19);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
	}
}
