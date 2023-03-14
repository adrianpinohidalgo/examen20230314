package examen20230314.contoller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examen20230314.model.CentroEducativo;
import examen20230314.model.Nivel;

public class ControladorNivel {
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static List<Nivel> cargarNivel(int id) {
		List<Nivel> ln = new ArrayList<Nivel>();
		try {
			Connection conn = ConnectionManagerV1.getConexion();

			PreparedStatement ps = conn.prepareStatement("select * from nivelesymaterias.nivel where idCentro = " + id);
			ResultSet rs = ps.executeQuery();

			Nivel n = null;

			while (rs.next()) {
				n = new Nivel();

				n.setId(rs.getInt("id"));
				n.setDescripcion(rs.getString("descripcion"));

				CentroEducativo c = new CentroEducativo();
				c.setId(rs.getInt("idCentro"));

				n.setIdCentro(c);

				ln.add(n);
			}

			rs.close();
			ps.close();
			conn.close();

			return ln;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
