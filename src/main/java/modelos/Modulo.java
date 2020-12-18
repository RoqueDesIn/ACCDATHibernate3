package modelos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Modulo implements Serializable {
	/**
	 * Estados
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="IdModulo")
	private int idModulo;
	@Column(name="nombre")
	private String nombre;
    @ManyToMany(cascade = {CascadeType.ALL},mappedBy="modulos")
    private Set<Profesor> profesores=new HashSet<Profesor>();
    
    /*
     * Constructor vacio
     */
    public Modulo() {
    }
    /**
     * Constructor con parámetros
     * @param idModulo
     * @param nombre
     */
    public Modulo(int idModulo, String nombre) {
	    this.idModulo = idModulo;
	    this.nombre = nombre;
     }
    /**
     * Getters and Setters
     */
	public int getIdModulo() {
		return idModulo;
	}
	public void setIdModulo(int idModulo) {
		this.idModulo = idModulo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Set<Profesor> getProfesores() {
		return profesores;
	}
	public void setProfesores(Set<Profesor> profesores) {
		this.profesores = profesores;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
