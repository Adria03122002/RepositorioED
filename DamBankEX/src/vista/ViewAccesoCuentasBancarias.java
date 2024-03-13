package vista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import excepcion.CuentaException;
import excepcion.PersonaException;
import modelo.CuentaBancaria;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewAccesoCuentasBancarias extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtDNI;
	private JTextField txtNombre;
	private static JTable tableCuentas;
	private JTextField txtNCuenta;
	private JTextField txtSaldo;
	private JScrollPane scrollPane;
	private static DefaultTableModel model;
	private static ViewOperarSobreCuenta vosc;
	private static String dn = "";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAccesoCuentasBancarias dialog = new ViewAccesoCuentasBancarias("","");
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
	public ViewAccesoCuentasBancarias(String dni, String nombre) {
		dn = dni;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					actualizarSaldo();
				} catch (PersonaException | CuentaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Error: ", JOptionPane.ERROR_MESSAGE);
				}
			}
			@Override
			public void windowActivated(WindowEvent e) {
				try {
					actualizarSaldo();
				} catch (PersonaException | CuentaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Error: ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		setTitle("Datos Persona");
		setBounds(100, 100, 476, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DNI: ");
		lblNewLabel.setBounds(10, 24, 45, 13);
		getContentPane().add(lblNewLabel);
		
		txtDNI = new JTextField(dni);
		txtDNI.setEditable(false);
		txtDNI.setBounds(50, 21, 133, 19);
		getContentPane().add(txtDNI);
		txtDNI.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre: ");
		lblNewLabel_1.setBounds(203, 24, 62, 13);
		getContentPane().add(lblNewLabel_1);
		
		txtNombre = new JTextField(nombre);
		txtNombre.setEditable(false);
		txtNombre.setBounds(255, 21, 201, 19);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 79, 434, 92);
		getContentPane().add(scrollPane);
		
		tableCuentas = new JTable();
		tableCuentas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int row = tableCuentas.getSelectedRow();
				Object ob = model.getValueAt(row, 0);
//				System.out.println(ob.toString());
				vosc = new ViewOperarSobreCuenta(dni, ob.toString());
				vosc.setVisible(true);
				
			}
		});
		scrollPane.setViewportView(tableCuentas);
		model = new DefaultTableModel(
			    new Object[][] {},
			    new String[] { "Número de Cuentas", "Saldo"}
				){
			    /****/
					private static final long serialVersionUID = 5421867691675798442L;

				@Override
			    public boolean isCellEditable(int row, int column) {
			        return false; // Todas las celdas son no editables
			    }
			};

		tableCuentas.setModel(model);
		
		JLabel lblNewLabel_2 = new JLabel("Número de cuenta: ");
		lblNewLabel_2.setBounds(10, 193, 133, 13);
		getContentPane().add(lblNewLabel_2);
		
		txtNCuenta = new JTextField();
		txtNCuenta.setBounds(131, 189, 280, 19);
		getContentPane().add(txtNCuenta);
		txtNCuenta.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Saldo: ");
		lblNewLabel_3.setBounds(20, 221, 45, 13);
		getContentPane().add(lblNewLabel_3);
		
		txtSaldo = new JTextField();
		txtSaldo.setBounds(60, 218, 123, 19);
		getContentPane().add(txtSaldo);
		txtSaldo.setColumns(10);
		
		JButton btnAnyadirCuenta = new JButton("AñadirCuenta");
		btnAnyadirCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String dni = txtDNI.getText();
					String nCu = txtNCuenta.getText();
					String cantidad = txtSaldo.getText();
					Double saldo = Double.parseDouble(cantidad);
				
					CuentaBancaria cnb = Controlador.crearCuenta(dni, nCu, saldo);
					
					Object ob[] = new Object[]{cnb.getNumCuenta(),cnb.getSaldo()+"€"};
					model.addRow(ob);
					
					txtNCuenta.setText("");
					txtSaldo.setText("");
					
				} catch (CuentaException | PersonaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Error: ", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnAnyadirCuenta.setBounds(283, 217, 114, 21);
		getContentPane().add(btnAnyadirCuenta);
	}
	
	public void actualizarSaldo() throws PersonaException, CuentaException  {
		
		for(int i = model.getRowCount() ; i > 0; i--) {
			model.removeRow(0);
		}
		
		CuentaBancaria[] cnb = Controlador.obtenerCuenta(dn);
			for(CuentaBancaria c : cnb) {
				if(c != null) {
					Object[] ob = new Object[]{c.getNumCuenta(), c.getSaldo() +"€"};
					model.addRow(ob);
				}
		 
		}
		
		
	}
	
}
