package vista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import excepcion.AvisarHaciendaException;
import excepcion.CuentaException;
import excepcion.PersonaException;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;


public class ViewOperarSobreCuenta extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtIban;
	private JTextField txtSaldo;
	private final ButtonGroup bGrOperaciones = new ButtonGroup();
	private JRadioButton rbtnIngresar;
	private JRadioButton rbtnRetirar;
	private static JSpinner spCantidad ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewOperarSobreCuenta dialog = new ViewOperarSobreCuenta("","");
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
	public ViewOperarSobreCuenta(String dni, String nCuent) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					String nombre = Controlador.buscarPersona(dni);
					
					
					double saldo = Controlador.getSaldo(dni, nCuent);
					
					txtDni.setText(dni);
					txtIban.setText(nCuent);
					txtSaldo.setText(saldo+"€");
					txtNombre.setText(nombre);
					
				} catch (PersonaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Error: ", JOptionPane.ERROR_MESSAGE);
				} catch (CuentaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Error: ", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		setTitle("Datos Cuenta");
		setBounds(100, 100, 596, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DNI: ");
		lblNewLabel.setBounds(25, 27, 45, 13);
		getContentPane().add(lblNewLabel);
		
		txtDni = new JTextField();
		txtDni.setEditable(false);
		txtDni.setBounds(58, 24, 141, 19);
		getContentPane().add(txtDni);
		txtDni.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre: ");
		lblNewLabel_1.setBounds(242, 27, 81, 13);
		getContentPane().add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(297, 24, 275, 19);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("IBAN: ");
		lblNewLabel_2.setBounds(25, 73, 45, 13);
		getContentPane().add(lblNewLabel_2);
		
		txtIban = new JTextField();
		txtIban.setEditable(false);
		txtIban.setBounds(80, 70, 383, 19);
		getContentPane().add(txtIban);
		txtIban.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Saldo: ");
		lblNewLabel_3.setBounds(25, 123, 45, 13);
		getContentPane().add(lblNewLabel_3);
		
		txtSaldo = new JTextField();
		txtSaldo.setEditable(false);
		txtSaldo.setBounds(83, 120, 129, 19);
		getContentPane().add(txtSaldo);
		txtSaldo.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Operaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 164, 562, 89);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		rbtnIngresar = new JRadioButton("Ingresar");
		rbtnIngresar.setBounds(20, 37, 90, 21);
		panel.add(rbtnIngresar);
		
		rbtnRetirar = new JRadioButton("Retirar");
		rbtnRetirar.setBounds(111, 37, 75, 21);
		panel.add(rbtnRetirar);
		
		bGrOperaciones.add(rbtnIngresar);
		bGrOperaciones.add(rbtnRetirar);
		
		JLabel lblNewLabel_4 = new JLabel("Importe: ");
		lblNewLabel_4.setBounds(192, 41, 75, 13);
		panel.add(lblNewLabel_4);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				if(rbtnIngresar.isSelected()){
						rbtnIngresar.setSelected(false);
						Object value = spCantidad.getValue();
						double cant = Double.parseDouble(value.toString());
						if(cant > 0) {
							Controlador.ingresarOpagar(dni, nCuent, cant, 0);
							double saldo = Controlador.getSaldo(dni, nCuent);
							txtSaldo.setText(saldo+"€");
						}
						spCantidad.setValue(0);
						
				}
				if(rbtnRetirar.isSelected()) {
						rbtnRetirar.setSelected(false);
						Object value = spCantidad.getValue();
						double cant = Double.parseDouble(value.toString());
						if(cant > 0) {
							Controlador.ingresarOpagar(dni, nCuent, cant, 1);
							double saldo = Controlador.getSaldo(dni, nCuent);
							txtSaldo.setText(saldo+"€");
						}
						spCantidad.setValue(0);
				}
				
				
				
				
				} catch (AvisarHaciendaException | PersonaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Error: ", JOptionPane.ERROR_MESSAGE);
				} catch (CuentaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Error: ", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnAceptar.setBounds(411, 37, 100, 21);
		panel.add(btnAceptar);
		
		spCantidad = new JSpinner();
		spCantidad.setModel(new SpinnerNumberModel(Double.valueOf(0), null, null, Double.valueOf(1)));
		spCantidad.setEditor(new javax.swing.JSpinner.NumberEditor(spCantidad, "########.##€"));
		spCantidad.setBounds(255, 38, 100, 20);
		panel.add(spCantidad);
		
		
		JButton btnMovimientos = new JButton("Movimientos");
		btnMovimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewMostrarMovimientosCuenta vmmc = new ViewMostrarMovimientosCuenta(dni,nCuent);
				vmmc.setVisible(true);
			}
		});
		btnMovimientos.setBounds(410, 112, 129, 35);
		getContentPane().add(btnMovimientos);

	}
}
