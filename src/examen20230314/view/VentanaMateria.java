package examen20230314.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import examen20230314.contoller.ControladorCentroEducativo;
import examen20230314.contoller.ControladorMateria;
import examen20230314.contoller.ControladorNivel;
import examen20230314.model.CentroEducativo;
import examen20230314.model.Materia;
import examen20230314.model.Nivel;

import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class VentanaMateria extends JFrame {
	private JTextField jtfId;
	private JTextField jtfNombre;
	private JTextField jtfCod;
	private JTextField jtfUrl;
	private JTextField jtfFecha;
	JComboBox jcbCentro, jcbNivel, jcbMateria;
	JCheckBox chckbxMatricula;

	/**
	 * Create the panel.
	 */
	public VentanaMateria() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Gestión de Materias");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Centro");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		jcbCentro = new JComboBox();
		GridBagConstraints gbc_jcbCentro = new GridBagConstraints();
		gbc_jcbCentro.insets = new Insets(0, 0, 5, 5);
		gbc_jcbCentro.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbCentro.gridx = 1;
		gbc_jcbCentro.gridy = 1;
		getContentPane().add(jcbCentro, gbc_jcbCentro);

		JButton btnCargarNiveles = new JButton("Cargar niveles");
		btnCargarNiveles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarJcbNivel();
			}
		});
		GridBagConstraints gbc_btnCargarNiveles = new GridBagConstraints();
		gbc_btnCargarNiveles.insets = new Insets(0, 0, 5, 0);
		gbc_btnCargarNiveles.gridx = 2;
		gbc_btnCargarNiveles.gridy = 1;
		getContentPane().add(btnCargarNiveles, gbc_btnCargarNiveles);

		JLabel lblNewLabel_2 = new JLabel("Nivel");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);

		jcbNivel = new JComboBox();
		GridBagConstraints gbc_jcbNivel = new GridBagConstraints();
		gbc_jcbNivel.insets = new Insets(0, 0, 5, 5);
		gbc_jcbNivel.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbNivel.gridx = 1;
		gbc_jcbNivel.gridy = 2;
		getContentPane().add(jcbNivel, gbc_jcbNivel);

		JButton btnCargarMaterias = new JButton("Cargar materias");
		btnCargarMaterias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarJcbMateria();
			}
		});
		GridBagConstraints gbc_btnCargarMaterias = new GridBagConstraints();
		gbc_btnCargarMaterias.insets = new Insets(0, 0, 5, 0);
		gbc_btnCargarMaterias.gridx = 2;
		gbc_btnCargarMaterias.gridy = 2;
		getContentPane().add(btnCargarMaterias, gbc_btnCargarMaterias);

		JLabel lblNewLabel_3 = new JLabel("Materia");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);

		jcbMateria = new JComboBox();
		GridBagConstraints gbc_jcbMateria = new GridBagConstraints();
		gbc_jcbMateria.insets = new Insets(0, 0, 5, 5);
		gbc_jcbMateria.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbMateria.gridx = 1;
		gbc_jcbMateria.gridy = 3;
		getContentPane().add(jcbMateria, gbc_jcbMateria);

		JButton btnVerMateria = new JButton("Ver Materia");
		btnVerMateria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarJtf();
			}
		});
		GridBagConstraints gbc_btnVerMateria = new GridBagConstraints();
		gbc_btnVerMateria.insets = new Insets(0, 0, 5, 0);
		gbc_btnVerMateria.gridx = 2;
		gbc_btnVerMateria.gridy = 3;
		getContentPane().add(btnVerMateria, gbc_btnVerMateria);

		JLabel lblNewLabel_4 = new JLabel("Datos de la materia");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.gridwidth = 3;
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 4;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Id:");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 5;
		getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);

		jtfId = new JTextField();
		jtfId.setEnabled(false);
		GridBagConstraints gbc_jtfId = new GridBagConstraints();
		gbc_jtfId.insets = new Insets(0, 0, 5, 0);
		gbc_jtfId.gridwidth = 2;
		gbc_jtfId.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfId.gridx = 1;
		gbc_jtfId.gridy = 5;
		getContentPane().add(jtfId, gbc_jtfId);
		jtfId.setColumns(10);

		JLabel lblNewLabel_5_1 = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNewLabel_5_1 = new GridBagConstraints();
		gbc_lblNewLabel_5_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5_1.gridx = 0;
		gbc_lblNewLabel_5_1.gridy = 6;
		getContentPane().add(lblNewLabel_5_1, gbc_lblNewLabel_5_1);

		jtfNombre = new JTextField();
		jtfNombre.setColumns(10);
		GridBagConstraints gbc_jtfNombre = new GridBagConstraints();
		gbc_jtfNombre.gridwidth = 2;
		gbc_jtfNombre.insets = new Insets(0, 0, 5, 0);
		gbc_jtfNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfNombre.gridx = 1;
		gbc_jtfNombre.gridy = 6;
		getContentPane().add(jtfNombre, gbc_jtfNombre);

		JLabel lblNewLabel_5_2 = new JLabel("Código");
		GridBagConstraints gbc_lblNewLabel_5_2 = new GridBagConstraints();
		gbc_lblNewLabel_5_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5_2.gridx = 0;
		gbc_lblNewLabel_5_2.gridy = 7;
		getContentPane().add(lblNewLabel_5_2, gbc_lblNewLabel_5_2);

		jtfCod = new JTextField();
		jtfCod.setColumns(10);
		GridBagConstraints gbc_jtfCod = new GridBagConstraints();
		gbc_jtfCod.gridwidth = 2;
		gbc_jtfCod.insets = new Insets(0, 0, 5, 0);
		gbc_jtfCod.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfCod.gridx = 1;
		gbc_jtfCod.gridy = 7;
		getContentPane().add(jtfCod, gbc_jtfCod);

		JLabel lblNewLabel_5_3 = new JLabel("URL Classroom:");
		GridBagConstraints gbc_lblNewLabel_5_3 = new GridBagConstraints();
		gbc_lblNewLabel_5_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5_3.gridx = 0;
		gbc_lblNewLabel_5_3.gridy = 8;
		getContentPane().add(lblNewLabel_5_3, gbc_lblNewLabel_5_3);

		jtfUrl = new JTextField();
		jtfUrl.setColumns(10);
		GridBagConstraints gbc_jtfUrl = new GridBagConstraints();
		gbc_jtfUrl.gridwidth = 2;
		gbc_jtfUrl.insets = new Insets(0, 0, 5, 0);
		gbc_jtfUrl.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfUrl.gridx = 1;
		gbc_jtfUrl.gridy = 8;
		getContentPane().add(jtfUrl, gbc_jtfUrl);

		JLabel lblNewLabel_5_4 = new JLabel("Fecha inicio:");
		GridBagConstraints gbc_lblNewLabel_5_4 = new GridBagConstraints();
		gbc_lblNewLabel_5_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5_4.gridx = 0;
		gbc_lblNewLabel_5_4.gridy = 9;
		getContentPane().add(lblNewLabel_5_4, gbc_lblNewLabel_5_4);

		jtfFecha = new JTextField();
		jtfFecha.setColumns(10);
		GridBagConstraints gbc_jtfFecha = new GridBagConstraints();
		gbc_jtfFecha.insets = new Insets(0, 0, 5, 0);
		gbc_jtfFecha.gridwidth = 2;
		gbc_jtfFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFecha.gridx = 1;
		gbc_jtfFecha.gridy = 9;
		getContentPane().add(jtfFecha, gbc_jtfFecha);

		chckbxMatricula = new JCheckBox("Admite matricula");
		GridBagConstraints gbc_chckbxMatricula = new GridBagConstraints();
		gbc_chckbxMatricula.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxMatricula.gridx = 1;
		gbc_chckbxMatricula.gridy = 10;
		getContentPane().add(chckbxMatricula, gbc_chckbxMatricula);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.insets = new Insets(0, 0, 0, 5);
		gbc_btnGuardar.gridx = 1;
		gbc_btnGuardar.gridy = 11;
		getContentPane().add(btnGuardar, gbc_btnGuardar);

		llenarJcbCentro();

	}

	private void llenarJcbCentro() {
		List<CentroEducativo> lc = ControladorCentroEducativo.findAll();
		for (CentroEducativo centro : lc) {
			jcbCentro.addItem(centro);
		}
	}

	private void llenarJcbNivel() {
		CentroEducativo c = (CentroEducativo) jcbCentro.getSelectedItem();
		List<Nivel> ln = ControladorNivel.cargarNivel(c.getId());

		try {
			jcbNivel.removeAllItems();
		} catch (Exception e) {
		}

		for (Nivel nivel : ln) {
			jcbNivel.addItem(nivel);
		}

	}

	private void llenarJcbMateria() {
		Nivel n = (Nivel) jcbNivel.getSelectedItem();
		List<Materia> lm = ControladorMateria.cargarMateria(n.getId());

		try {
			jcbMateria.removeAllItems();
		} catch (Exception e) {
		}

		for (Materia materia : lm) {
			jcbMateria.addItem(materia);
		}

	}

	private void llenarJtf() {
		Materia m = (Materia) jcbMateria.getSelectedItem();

		jtfId.setText("" + m.getId());
		jtfNombre.setText(m.getNombre());
		jtfCod.setText(m.getCodigo());
		jtfUrl.setText(m.getUrlClassroom());
		jtfFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(m.getFechaInicio()));
		chckbxMatricula.setSelected(m.isAdmiteMatricula());
	}

	private void guardar() {
		Materia m = new Materia();

		m.setId(Integer.parseInt(jtfId.getText()));
		m.setNombre(jtfNombre.getText());

		Nivel n = (Nivel) jcbNivel.getSelectedItem();
		m.setIdNivel(n.getId());

		if (comprobarCod()) {
			m.setCodigo(jtfCod.getText());

			if (esHttp()) {
				m.setUrlClassroom(jtfUrl.getText());
				m.setAdmiteMatricula(chckbxMatricula.isSelected());

				Date fechaSQL = getFecha(jtfFecha.getText());
				m.setFechaInicio((java.sql.Date) fechaSQL);

				JOptionPane.showMessageDialog(null, "Filas afectadas: " + ControladorMateria.update(m));
			} else {
				JOptionPane.showMessageDialog(null, "La URL no es válida.");
			}

		} else {
			JOptionPane.showMessageDialog(null, "El código no cumple las condiciones.");
		}

	}

	public static java.sql.Date getFecha(String fecha) {
		SimpleDateFormat sdfEntrada = new SimpleDateFormat("dd/MM/yyyy");

		Date date = new Date();
		try {
			date = sdfEntrada.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		SimpleDateFormat sdfSalida = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		long fechaMilis = date.getTime();
		java.sql.Date fechaSQL = new java.sql.Date(fechaMilis);

		return fechaSQL;

	}

	private boolean comprobarCod() {
		String str = jtfCod.getText();
		String nuevaStr = "";
		str = str.trim();

		String[] palabras = str.split(" {2,}");
		for (int i = 0; i < palabras.length; i++) {
			nuevaStr += palabras[i] + " ";

		}

		palabras = str.split("[ ]{1,}");
		for (int i = 0; i < palabras.length; i++) {
			boolean letras = false, tieneMayus = false, tieneDosMinus = false, tieneDig = false, tieneNoAlfaNum = false;
			int contMinus = 0, contMayus = 0;
			for (int j = 0; j < palabras[i].length(); j++) {
				char palabros = palabras[i].charAt(j);
				if (Character.isUpperCase(palabros)) {
					contMayus++;
					if (contMayus >= 3) {
						letras = true;
					}
				}
				if (Character.isLowerCase(palabros)) {
					contMinus++;
					if (contMinus >= 3) {
						letras = true;
					}
				}

			}
			if (!letras) {
				return false;
			} else {
				return true;
			}
		}
		return false;

	}

	private boolean esHttp() {
		String url = jtfUrl.getText();
		if (url.startsWith("http://") || url.startsWith("https://")) {
			return true;
		}
		return false;
	}

}
