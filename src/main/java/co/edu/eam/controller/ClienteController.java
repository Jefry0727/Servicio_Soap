package co.edu.eam.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.Query;

import co.edu.eam.ejb.PersistenceManagerLocal;
import co.edu.eam.model.Cliente;

@Stateless
@WebService(name = "ClienteController", portName = "ClienteControlPort", targetNamespace = "http://co.edu.eam.ingsoft.distribuidos")
public class ClienteController {
	
	@EJB
	private PersistenceManagerLocal persistencia;
	
	/**
	 * Crea un cliente y guarda la info en la base de datos
	 * @param cliente
	 * @return
	 */
	@WebMethod(action = "crearCliente", operationName = "operacionCrear")
	public boolean guardarCliente(@WebParam(name = "cliente") Cliente cliente){
		try{
			
			//cliente.setId(1);
			
			persistencia.persist(cliente);
			
			return true;
			
		}catch (Exception e) {
			return false;
		}
		
		
	}
	
	@WebMethod(action = "buscarCliente", operationName = "operacionBuscar")
	public Cliente buscarCliente(@WebParam(name = "cedula") String cedula){
		
		Query query = persistencia
				.createQuery("SELECT c FROM Cliente c where c.cedula='" + cedula + "'");
		
		Cliente cliente = (Cliente)query.getSingleResult();
		
		return cliente;
		
	}
	
}
