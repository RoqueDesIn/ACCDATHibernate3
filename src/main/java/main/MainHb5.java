package main;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import abstractDAO.HbSession;
import modelos.Empleados;
import modelos.Envios;
import modelos.Modulo;
import modelos.Productos;
import modelos.Profesor;

public class MainHb5 {

	public static void main(String[] args) {
				
		// abrir sesion Hibernate
		Session sesion = HbSession.openSession();
	
		
		// ejemplo paginación de las categorias que pertenecen a cada producto
		System.out.println("********************************************");
		System.out.println("\tPaginación de productos y sus categorias");
		System.out.println("********************************************");
		int paginaSize=10;
		int mostrarPagina=1;
		int numPag=0;
		
		// select para saber el numero de registros
		long numTotalObjetos = (Long) sesion.createQuery("SELECT count(*) FROM Productos p").uniqueResult();
		numPag =(int) Math.ceil((double)numTotalObjetos / (double)paginaSize);
		/*	
		// categoria a imprimir
		System.out.println("introduzca categoria a listar: ");
		Scanner sc = new Scanner (System.in);
		String catId= sc.nextLine();
		
		// query subconsulta 
		Query query = sesion.createQuery(
				"SELECT prod.producto,prod.precioUnidad "
				+"FROM Productos prod "
				+"WHERE prod.id =  "+
				"(SELECT  cat.id FROM Categorias cat where id = "+ catId +")"
				);
		listaQuery(query);
		*/
		
		// consulta 1
		System.out.println("\n 1.- Mostrar el nombre del producto y el nombre de la categoría de "
				+ "todos los productos que contengan la letra Q en el nombre. \n");
		Query query = sesion.createQuery(
				"SELECT prod.producto, cat.categoria "
				+"FROM Productos prod, Categorias cat "
				+"WHERE prod.producto like '%Q%' "
				+"and prod.categorias = cat.id "
				+"order by cat.categoria, prod.producto"
				);
		listaQuery (query);
		
		// consulta 2
		System.out.println("\n 2.- Mostrar el número de pedido y el país del cliente "
				+ "de los pedidos de mayo del año 1997 \n");
		query = sesion.createQuery(
				"SELECT ped.id, ped.fechaPedido, ped.clientes.pais "
				+"FROM Pedidos ped "
				+"WHERE YEAR(ped.fechaPedido) = 1997 "
				+ "and MONTH(ped.fechaPedido) = 5 "
				+ "order by ped.fechaPedido "

				);
		listaQuery (query);
		
		// consulta 3
				System.out.println("\n 3.- Mostrar fecha del pedido, cantidad y el nombre producto,"
						+ " y el código del pedido para " 
						+ " los códigos de pedido 10285 o 10298.7 \n");
				
				query = sesion.createQuery(
						"SELECT p.id, p.fechaPedido, d.cantidad, "
						+ "s.producto, d.precioUnidad " + 
						"FROM Pedidos p" + 
						" JOIN p.detalleses d" + 
						"  JOIN d.productos s" + 
						" WHERE p.id = 10285" + 
						"   OR p.id = 10298" + 
						"ORDER BY p.id, p.fechaPedido "
						);
				listaQuery (query);
		
				// consulta 4
				System.out.println("\n 4.- ¿Cuánto se factura cada mes? Mostrar el año, el mes y el total. \n");
				
				query = sesion.createQuery(
						"SELECT year(p.fechaPedido) , "
						+ " month(p.fechaPedido) , " + 
						"  SUM(d.cantidad *  d.precioUnidad * (1 - d.descuento))  " + 
						"FROM Pedidos p "
						+ " JOIN p.detalleses d " + 
						"GROUP BY 1, 2 " + 
						"ORDER BY 3"
						);
				listaQuery (query);
				
				// consulta 5
				System.out.println("\n 5.- Los pedidos que hizo la empleada Nancy. \n");
				
				query = sesion.createQuery(
						"Select p.id, p.fechaPedido, e.nombre "
						+ "FROM Pedidos p "
						+ " JOIN p.empleados e " + 
						"WHERE e.nombre='Nancy'"
						);
				listaQuery (query);
				
				// consulta 6
				System.out.println("\n 6.- Mostrar los pedidos de Anton (código cliente) \n");
				
				query = sesion.createQuery(
						"Select p.id, p.fechaPedido, c.codigo "
						+ "FROM Pedidos p "
						+ " JOIN p.clientes c " + 
						"WHERE c.codigo='ANTON'"
						);
				listaQuery (query);
				
				// consulta 7
				System.out.println("\n 7.- Cuántos productos hay de cada categoría y el precio medio \n");
				
				query = sesion.createQuery(
						"Select c.categoria, COUNT(p.id), AVG(p.precioUnidad) "
						+ "FROM Categorias c "
						+ "LEFT JOIN c.productoses p " + 
						"GROUP BY c.categoria"
						);
				listaQuery (query);
				
				// consulta 8
				System.out.println("\n 8.- Mostrar los pedidos que tienen productos en la categoría 2 o 3 \n");
				
				query = sesion.createQuery(
						"Select DISTINCT de.pedidos.id, de.pedidos.fechaPedido "
						+ "FROM Detalles de "
						+ "WHERE de.productos IN("
						+ 	"SELECT pr.id "
						+ 	"FROM Productos pr"
						+ 	" WHERE pr.categorias IN (2,3)"
						+ ")"
						+ " order by 1"
						);
				listaQuery (query);
				
				// consulta 9
				System.out.println("\n 9.- Clientes que pidieron queso en julio de 1997 \n");
				
				query = sesion.createQuery(
						"Select DISTINCT c.empresa, pr.producto "
						+ "FROM Clientes c "
						+ "JOIN c.pedidoses p "
						+ "JOIN p.detalleses d "
						+ "JOIN d.productos pr "
 						+ "WHERE p.fechaPedido BETWEEN '1997-07-01' AND '1997-07-31'" 
						+ " AND producto LIKE '%queso%'"
						);
				listaQuery (query);
				
		//cerramos sesion hibernate
		HbSession.closeSession(sesion);
	
		System.out.println("fin");
	}
	
	private static void listaQuery (Query query) {
		// listamos productos
		List<Object[]> consulta = query.list();
		
		for (Object[] ps : consulta) {
			for(int i=0;i<ps.length;i++) {
				System.out.print(ps[i].toString()+"   ");
			}
			System.out.println();
		}
	}
}
