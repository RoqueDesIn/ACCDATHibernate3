package modelos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
 public class Alumno implements Serializable  {

     /**
	 * estados
	 */
	private static final long serialVersionUID = 1L;

	@Id
     @Column(name="Id")
	 private int id;
	
	 @Column(name="nombre")
	 private String nombre;
	
	 @Column(name="ape1")
	 private String ape1;
	
	 @Column(name="ape2")
	 private String ape2;
	
	 @Enumerated(EnumType.STRING)
	 private TipoAlumno tipoAlumno;
	
	 public Alumno(){
	 }

	 public Alumno(int id, String nombre, String ape1, String ape2, TipoAlumno tipoAlumno) {
	     this.id = id;
	     this.nombre = nombre;
	     this.ape1 = ape1;
	     this.ape2 = ape2;
	     this.tipoAlumno = tipoAlumno;
	 }
	 
	public enum TipoAlumno {
	    Carrera,
	    Practicas,
	    Interino
	} 
 }

