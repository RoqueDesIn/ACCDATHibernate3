package modelos;
// Generated 20-ene-2021 12:12:07 by Hibernate Tools 5.4.12.Final

/**
 * Correoelectronico generated by hbm2java
 */
public class Correoelectronico implements java.io.Serializable {

	private int idCorreo;
	private Profesor profesor;
	private String direccionCorreo;

	public Correoelectronico() {
	}

	public Correoelectronico(int idCorreo) {
		this.idCorreo = idCorreo;
	}

	public Correoelectronico(int idCorreo, Profesor profesor, String direccionCorreo) {
		this.idCorreo = idCorreo;
		this.profesor = profesor;
		this.direccionCorreo = direccionCorreo;
	}

	public int getIdCorreo() {
		return this.idCorreo;
	}

	public void setIdCorreo(int idCorreo) {
		this.idCorreo = idCorreo;
	}

	public Profesor getProfesor() {
		return this.profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public String getDireccionCorreo() {
		return this.direccionCorreo;
	}

	public void setDireccionCorreo(String direccionCorreo) {
		this.direccionCorreo = direccionCorreo;
	}

}
