package co.edu.eam.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.Query;

import co.edu.eam.dto.ItemsDTO;
import co.edu.eam.ejb.PersistenceManagerLocal;
import co.edu.eam.model.Cliente;
import co.edu.eam.model.Compra;
import co.edu.eam.model.ItemCompra;

@Stateless
@WebService(name = "CompraController", portName = "CompraCtlPort", targetNamespace = "http://co.edu.eam.ingsoft.distribuidos")
public class CompraController {

	@EJB
	private PersistenceManagerLocal persistencia;

	@WebMethod(action = "crearCompra", operationName = "operacionCrearCompra")
	public boolean guardarCompra(@WebParam(name = "id_Cliente") int id,
			@WebParam(name = "valor_total") double valor_total, @WebParam(name = "items_compra") List<ItemsDTO> items) {

		try {

			Date fecha = new Date();

			/*
			 * Se crea el cliente que realiza la compra
			 */
			Cliente cliente = new Cliente();

			cliente.setId(id);

			/**
			 * Obtiene el ultimo id y suma una unidad mas para formar la nueva
			 * compra
			 */

			Query query = persistencia.createQuery("SELECT MAX(c.id) FROM Compra c");

			/**
			 * Nuevo numero
			 */
			int aux = ((Integer) query.getSingleResult()).intValue() + 1;

			/**
			 * Nuevo numero mas un C antes del numero
			 */
			String num_compra = "C " + aux;

			/**
			 * Objeto Factura que se va a guardar en la base de datos
			 */

			Compra compra = new Compra();

			compra.setCliente(cliente);
			compra.setFechaCompra(fecha);
			compra.setValorTotal(valor_total);
			compra.setNumeroCompra(num_compra);
			compra.setId(1);

			persistencia.persist(compra);

			query = persistencia.createQuery("SELECT c FROM Compra c where c.numeroCompra='" + num_compra + "'");

			/**
			 * Compra obtenida en la busqueda
			 */
			compra = (Compra) query.getSingleResult();
			
//			System.out.println("valor");
//			System.out.println(items.get(0).getValorProducto());
//			System.out.println("sigue el for");
			

			for (ItemsDTO itemCompra : items) {

				ItemCompra compras = new ItemCompra();

				compras.setCantidad(Integer.parseInt(itemCompra.getCantidad()));
				compras.setCompra(compra);
				compras.setIdProducto(itemCompra.getIdProducto());
				compras.setValorProducto(Double.parseDouble(itemCompra.getValorProducto()));
				compras.setId(generaId());

				persistencia.persist(compras);

			}

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}

	}

	public int generaId() {
		Random r = new Random();
		return (int) r.nextInt(9999);
	}

}
