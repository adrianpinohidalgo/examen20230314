package examen20230314.contoller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examen20230314.model.CentroEducativo;

public class ControladorCentroEducativo {
	/**
	 * 
	 * @return
	 */
	public static CentroEducativo cargarPrimerRegistro() {
		try {
			Connection conn = ConnectionManagerV1.getConexion();

			CentroEducativo c = null;

			PreparedStatement ps = conn
					.prepareStatement("select * from nivelesymaterias.centroeducativo order by id limit 1");
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				c = new CentroEducativo();
				c.setId(rs.getInt("id"));
				c.setDescripcion(rs.getString("descripcion"));
			}

			rs.close();
			ps.close();
			conn.close();

			return c;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static CentroEducativo cargarSiguienteRegistro(CentroEducativo c) {
		try {
			Connection conn = ConnectionManagerV1.getConexion();

			PreparedStatement ps = conn.prepareStatement(
					"select * from nivelesymaterias.centroeducativo where id > " + c.getId() + " order by id limit 1");
			ResultSet rs = ps.executeQuery();

			CentroEducativo c1 = new CentroEducativo();

			if (rs.next()) {

				c1.setId(rs.getInt("id"));
				c1.setDescripcion(rs.getString("descripcion"));

				rs.close();
				ps.close();
				conn.close();

				return c1;
			}

			rs.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<CentroEducativo> findAll() {
		List<CentroEducativo> lc = new ArrayList<CentroEducativo>();
		CentroEducativo c = cargarPrimerRegistro();
		do {
			lc.add(c);
			c = cargarSiguienteRegistro(c);
		} while (c != null);
		return lc;
	}
}
