package main;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import abstractDAO.HbSession;
import modelos.Alumno;
import modelos.Alumno.TipoAlumno;
import modelos.CorreoElectronico;
import modelos.Modulo;
import modelos.Profesor;
import modelos.TiposBasicos;

public class MainHb3 {

	public static void main(String[] args) {
				
		// abrir sesion Hibernate
		Session sesion = HbSession.openSession();
		// ejemplo uno a muchos
		EjUnoAMuchos(sesion);
		
		//ejemplo muchos a muchos
		EjMuchosAMuchos(sesion);
		
		// ejemplo tipos
		EjTiposBasicos(sesion);
		
		// ejemplo enum
		EjEnum(sesion);
		//cerramos sesion hibernate
		HbSession.closeSession(sesion);
	
		System.out.println("fin");
	}
	
	/**
	 * ejemplo enum
	 * @param sesion
	 */
	private static void EjEnum(Session sesion) {
		Alumno alumno= new Alumno(412, "Elias", "Rubio", "Sánchez",TipoAlumno.Interino);
		sesion.save(alumno);
	}
	
	/**
	 * ejemplo tipos basicos
	 * @param sesion
	 */
	private static void EjTiposBasicos(Session sesion) {
		Date date=new Date();
		byte array[]={(byte)0x45,(byte)0xF5,(byte)0x3A,(byte)0x67,(byte)0xFF};
		
		TiposBasicos tiposBasicos1=new TiposBasicos();
		tiposBasicos1.setInte(1);
		tiposBasicos1.setLong1(12);
		tiposBasicos1.setShort1((short)13);
		tiposBasicos1.setFloat1(14.1F);
		tiposBasicos1.setDouble1(15.2);
		tiposBasicos1.setCharacter1('W');
		tiposBasicos1.setByte1((byte)16);
		tiposBasicos1.setBoolean1(true);
		tiposBasicos1.setYesno1(true);
		tiposBasicos1.setTruefalse1(true);
		tiposBasicos1.setStri("Hola mundo");
		tiposBasicos1.setDateDate(date);
		tiposBasicos1.setDateTime(date);
		tiposBasicos1.setDateTimestamp(date);
		tiposBasicos1.setTexto("texto muyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy largo");
		tiposBasicos1.setBinario(array);
		tiposBasicos1.setBigDecimal(new BigDecimal("0.3"));
		tiposBasicos1.setBigInteger(new BigInteger("5345345324532"));
		
		TiposBasicos tiposBasicos2=new TiposBasicos();
		tiposBasicos2.setInte(2);
		tiposBasicos2.setLong1(12);
		tiposBasicos2.setShort1((short)13);
		tiposBasicos2.setFloat1(14.1F);
		tiposBasicos2.setDouble1(15.2);
		tiposBasicos2.setCharacter1('W');
		tiposBasicos2.setByte1((byte)16);
		tiposBasicos2.setBoolean1(false);   //<<--- Cambiado a false
		tiposBasicos2.setYesno1(false);     //<<--- Cambiado a false
		tiposBasicos2.setTruefalse1(false); //<<--- Cambiado a false
		tiposBasicos2.setStri("Hola mundo");
		tiposBasicos2.setDateDate(date);
		tiposBasicos2.setDateTime(date);
		tiposBasicos2.setDateTimestamp(date);
		tiposBasicos2.setTexto("texto muyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy largo");
		tiposBasicos2.setBinario(array);
		tiposBasicos2.setBigDecimal(new BigDecimal("0.3"));
		tiposBasicos2.setBigInteger(new BigInteger("5345345324532"));
		
		sesion.save(tiposBasicos1);
		sesion.save(tiposBasicos2);
	}

	/**
	 * Ejemplo muchos a muchos
	 * @param sesion
	 */
	private static void EjMuchosAMuchos(Session sesion) {
		// creamos profesor y módulos para ejemplo relación muchos a muchos
		Profesor profesor1=new Profesor(11, "Isabel", "Fuertes", "Gascón");
		Profesor profesor2=new Profesor(12, "Jose", "Valenciano", "Gimeno");
		Modulo modulo3=new Modulo(3, "Sistemas Informáticos");
		Modulo modulo2=new Modulo(2, "Entornos de desarrollo");
		Modulo modulo1=new Modulo(1, "Sistemas Operativos en Red");
		// asignamos los modulos a los profesores
		profesor1.getModulos().add(modulo1);
		profesor1.getModulos().add(modulo2);
		profesor2.getModulos().add(modulo3);
		// asignamos los profesores a los módulos
		modulo1.getProfesores().add(profesor1);
		modulo2.getProfesores().add(profesor1);
		modulo3.getProfesores().add(profesor2);
		// Guardamos profesor y módulos ejemplo relacion muchos a muchos
		sesion.save(profesor1);
		sesion.save(profesor2);		
	}

	/**
	 * ejemplo uno a muchos
	 * @param sesion
	 */
	private static void EjUnoAMuchos(Session sesion) {
		// creamos profesor y correos electrónicos para ejemplo uno a muchos
		Profesor profesor=new Profesor(7, "Sara", "Barrrera", "Salas");
		Set<CorreoElectronico> correosElectronicos=new HashSet<CorreoElectronico>();
		correosElectronicos.add(new CorreoElectronico(3, "sara@yahoo.com",profesor));
		correosElectronicos.add(new CorreoElectronico(2, "sara@hotmail.com",profesor));
		correosElectronicos.add(new CorreoElectronico(1, "sara@gmail.com",profesor));
		// asignamos los correos al profesosr
		profesor.setCorreosElectronicos(correosElectronicos);
		// guardamos profesor y correos ejemplo relación uno a muchos
		sesion.save(profesor);
		correosElectronicos.forEach(element -> sesion.save(element));
	}

}
