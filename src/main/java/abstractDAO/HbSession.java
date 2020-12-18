package abstractDAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public abstract class HbSession {

	public static Session openSession() {
		// abre sesion en hibernate
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
    	SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
    	Session sesion = sf.openSession();
 
    	// comienza transacción
    	sesion.getTransaction().begin();
    	return sesion;
	};
	
	public static void closeSession(Session sesion) {
		//cierra sesion en hibernate
		sesion.getTransaction().commit();
		sesion.close();
	}
}
