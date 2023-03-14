package examen20230314.contoller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import examen20230314.model.Materia;
import examen20230314.model.Nivel;

public class ControladorMateria {

	public static List<Materia> cargarMateria(int idNivel) {
		List<Materia> lm = new ArrayList<Materia>();
		try {
			Connection conn = ConnectionManagerV1.getConexion();

			PreparedStatement ps = conn
					.prepareStatement("select * from nivelesymaterias.materia where idNivel = " + idNivel);
			ResultSet rs = ps.executeQuery();

			Materia m = null;
			while (rs.next()) {
				m = new Materia();
				m.setId(rs.getInt("id"));
				m.setNombre(rs.getString("nombre"));

				Nivel n = new Nivel();
				n.setId(rs.getInt("idNivel"));

				m.setIdNivel(n.getId());
				m.setCodigo(rs.getString("codigo"));
				m.setUrlClassroom(rs.getString("urlClassroom"));
				m.setAdmiteMatricula(rs.getBoolean("admiteMatricula"));
				m.setFechaInicio(rs.getDate("fechaInicio"));

				lm.add(m);
			}

			rs.close();
			ps.close();
			conn.close();

			return lm;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int update(Materia m) {
		int answer = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres actualizar el registro?");
		if (answer == 0) {
			try {
				Connection conn = ConnectionManagerV1.getConexion();
				PreparedStatement ps = conn.prepareStatement(
						"UPDATE nivelesymaterias.materia SET nombre = ?, codigo = ?, urlClassroom = ?, admiteMatricula = ?, fechaInicio = ? WHERE id = ?");

				ps.setString(1, m.getNombre());
				ps.setString(2, m.getCodigo());
				ps.setString(3, m.getUrlClassroom());
				ps.setBoolean(4, m.isAdmiteMatricula());
				ps.setDate(5, m.getFechaInicio());
				ps.setInt(6, m.getId());

				int rows = ps.executeUpdate();

				ps.close();
				conn.close();

				return rows;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

}
