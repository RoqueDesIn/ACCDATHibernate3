package modelos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CorreoElectronico {
	@Id
	private int idCorreo;
	private String direccionCorreo;
	@ManyToOne
	@JoinColumn(name="IdProfesor")
	private Profesor profesor;
	
	protected CorreoElectronico() {
	}

	/**
	 * constructor
	 * @param idCorreo
	 * @param direccionCorreo
	 * @param profesor
	 */
	public CorreoElectronico(int idCorreo, String direccionCorreo, Profesor profesor) {
		super();
		this.idCorreo = idCorreo;
		this.direccionCorreo = direccionCorreo;
		this.profesor = profesor;
	}
	
	/**
	 * getters and setters
	 * @return
	 */
	public int getIdCorreo() {
		return idCorreo;
	}
	public void setIdCorreo(int idCorreo) {
		this.idCorreo = idCorreo;
	}
	public String getDireccionCorreo() {
		return direccionCorreo;
	}
	public void setDireccionCorreo(String direccionCorreo) {
		this.direccionCorreo = direccionCorreo;
	}
	public Profesor getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
	
}
