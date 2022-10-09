import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.JTextArea;

public class VentanaUsuario extends JFrame {

	private JPanel contentPane;
	private JLabel lblNota, lblfNacimiento;
	private JTextField txtfFecha;
	private JButton btnGuardar;
	private JButton btnCargar;
	private JTextArea txtaNotas;

	public VentanaUsuario() {
		setTitle("Serializaci\u00F3n de objetos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		lblNota = new JLabel("Nota:");
		lblNota.setBounds(30, 62, 48, 14);
		contentPane.add(lblNota);
		
		lblfNacimiento = new JLabel("Fecha creacion:");
		lblfNacimiento.setBounds(31, 29, 111, 14);
		contentPane.add(lblfNacimiento);
		
		txtaNotas = new JTextArea();
		txtaNotas.setBounds(141, 57, 260, 153);
		contentPane.add(txtaNotas);
		
		txtfFecha = new JTextField();
		txtfFecha.setColumns(10);
		txtfFecha.setBounds(141, 26, 260, 20);
		contentPane.add(txtfFecha);
		
		btnGuardar = new JButton("Guardar Nota");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarObjeto ();
			}
		});
		btnGuardar.setBounds(20, 231, 189, 23);
		contentPane.add(btnGuardar);
		
		btnCargar = new JButton("Cargar Nota");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarObjeto();		
			}
		});
		btnCargar.setBounds(229, 231, 171, 23);
		contentPane.add(btnCargar);
		
		
	}
	
	private void guardarObjeto() {
		File rutaProyecto = new File(System.getProperty("user.dir"));
		JFileChooser fc = new JFileChooser(rutaProyecto);
		int seleccion = fc.showSaveDialog(contentPane);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			File fichero = fc.getSelectedFile();
			try {
				FileOutputStream fos = new FileOutputStream(fichero);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				Usuario miUsuario  = new Usuario (txtaNotas.getText(), new SimpleDateFormat("dd/MM/yyyy").parse(txtfFecha.getText()));
				oos.writeObject(miUsuario);
				fos.close();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void cargarObjeto() {
		File rutaProyecto = new File(System.getProperty("user.dir"));
		JFileChooser fc = new JFileChooser(rutaProyecto);
		int seleccion = fc.showOpenDialog(contentPane);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			try {
				File fichero = fc.getSelectedFile();
				FileInputStream fis = new FileInputStream(fichero);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Usuario miUsuario  = (Usuario) ois.readObject();
				txtaNotas.setText(miUsuario.getNotas());
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		        String dia = formatter.format(miUsuario.getfechaNota());
				txtfFecha.setText(dia);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
