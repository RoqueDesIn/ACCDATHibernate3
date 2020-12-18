package modelos;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;


@Entity
public class Profesor implements Serializable {
	/**
	 * Estados
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	private String nombre;
	private String ape1;
	private String ape2;
	@OneToMany(mappedBy = "profesor")
	private Set<CorreoElectronico> correosElectronicos;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="ProfesorModulo", joinColumns={@JoinColumn(name="id")}, inverseJoinColumns={@JoinColumn(name="IdModulo")})
	private Set<Modulo> modulos=new HashSet<Modulo>();
	 
	 /**
	  * constructor
	  * @param id
	  * @param nombre
	  * @param ape1
	  * @param ape2
	  * @param correosElectronicos
	  */
	public Profesor(int id, String nombre, String ape1, String ape2) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ape1 = ape1;
		this.ape2 = ape2;
	}
	
	/**
	 * getters and setters
	 * @return
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApe1() {
		return ape1;
	}
	public void setApe1(String ape1) {
		this.ape1 = ape1;
	}
	public String getApe2() {
		return ape2;
	}
	public void setApe2(String ape2) {
		this.ape2 = ape2;
	}
	public Set<CorreoElectronico> getCorreosElectronicos() {
		return correosElectronicos;
	}
	public void setCorreosElectronicos(Set<CorreoElectronico> correosElectronicos) {
		this.correosElectronicos = correosElectronicos;
	}

	public Set<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(Set<Modulo> modulos) {
		this.modulos = modulos;
	}
	 
	 
}
