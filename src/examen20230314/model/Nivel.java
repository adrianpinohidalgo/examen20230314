package examen20230314.model;

public class Nivel {
	private int id;
	private String descripcion;
	private CentroEducativo idCentro;

	public Nivel() {

	}

	public Nivel(int id, String descripcion, CentroEducativo idCentro) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.idCentro = idCentro;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public CentroEducativo getIdCentro() {
		return idCentro;
	}

	public void setIdCentro(CentroEducativo idCentro) {
		this.idCentro = idCentro;
	}

	@Override
	public String toString() {
		return descripcion;
	}

}