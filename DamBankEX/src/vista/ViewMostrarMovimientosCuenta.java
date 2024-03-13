package vista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import excepcion.CuentaException;
import excepcion.PersonaException;
import modelo.Movimientos;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewMostrarMovimientosCuenta extends JDialog {

	private static final long serialVersionUID = 1L;
	private static JTextField txtDNI;
	private static JTextField txtNombre;
	private static JTextField txtIBAN;
	private static JTextField txtSaldo;
	private static JTable table;
	private static DefaultTableModel modelo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewMostrarMovimientosCuenta dialog = new ViewMostrarMovimientosCuenta("","");
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
	public ViewMostrarMovimientosCuenta(String dni, String nCuent) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				//rellenar inputs que no son visibles , rellenta tabla
				
				 
				try {
					String nom = Controlador.buscarPersona(dni);
					double cantidad = Controlador.getSaldo(dni, nCuent);
				
					txtDNI.setText(dni);
					txtIBAN.setText(nCuent);
					txtNombre.setText(nom);
					txtSaldo.setText(cantidad+"€");
					
					
					for(Movimientos m: Controlador.getMovs(dni, nCuent)) {
						Object ob[] = new Object[]{m.getTipo(),m.getCantidad()+"€"};
						modelo.addRow(ob);
					}
					
					
					
				} catch (PersonaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Error: ", JOptionPane.ERROR_MESSAGE);
				} catch (CuentaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Error: ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		setBounds(100, 100, 450, 598);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DNI: ");
		lblNewLabel.setBounds(10, 26, 45, 13);
		getContentPane().add(lblNewLabel);
		
		txtDNI = new JTextField();
		txtDNI.setEditable(false);
		txtDNI.setBounds(46, 23, 104, 19);
		getContentPane().add(txtDNI);
		txtDNI.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre: ");
		lblNewLabel_1.setBounds(10, 66, 60, 13);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("IBAN: ");
		lblNewLabel_2.setBounds(10, 98, 45, 13);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Saldo: ");
		lblNewLabel_3.setBounds(10, 136, 45, 13);
		getContentPane().add(lblNewLabel_3);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(66, 63, 360, 19);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtIBAN = new JTextField();
		txtIBAN.setEditable(false);
		txtIBAN.setBounds(54, 95, 372, 19);
		getContentPane().add(txtIBAN);
		txtIBAN.setColumns(10);
		
		txtSaldo = new JTextField();
		txtSaldo.setEditable(false);
		txtSaldo.setBounds(54, 133, 96, 19);
		getContentPane().add(txtSaldo);
		txtSaldo.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 191, 416, 360);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		String[] titulos = {"Movimientos","Cantidad"};
		modelo = new DefaultTableModel(0,0);
		modelo.setColumnIdentifiers(titulos);
		modelo.setNumRows(0);
		
		table.setModel(modelo);
		
		scrollPane.setViewportView(table);

	}
}
