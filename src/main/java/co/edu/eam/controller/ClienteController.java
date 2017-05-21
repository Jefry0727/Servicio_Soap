package co.edu.eam.controller;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintViolationCreationContext;

import co.edu.eam.ejb.PersistenceManagerLocal;
import co.edu.eam.model.Cliente;

@Stateless
@WebService(name = "ClienteController", portName = "ClienteControlPort", targetNamespace = "http://co.edu.eam.ingsoft.distribuidos")
public class ClienteController {

	@EJB
	private PersistenceManagerLocal persistencia;

	/**
	 * Crea un cliente y guarda la info en la base de datos
	 * 
	 * @param cliente
	 * @return
	 */
	@WebMethod(action = "crearCliente", operationName = "operacionCrear")
	public String guardarCliente(@WebParam(name = "cliente") Cliente cliente) {

		try {

			persistencia.persist(cliente);

			return "Ok";

		} catch (RollbackException p) {

			return "Dublicidad";

		} catch (ConstraintViolationException ee) {

			return "ERROR";

		} catch (Exception e) {

			return "ERROR";

		}
	}

	@WebMethod(action = "buscarCliente", operationName = "operacionBuscar")
	public Cliente buscarCliente(@WebParam(name = "cedula") String cedula) {

		try {

			Query query = persistencia.createQuery("SELECT c FROM Cliente c where c.cedula='" + cedula + "'");

			Cliente cliente = (Cliente) query.getSingleResult();

			return cliente;

		} catch (Exception e) {

			return null;
			
		}

	}

}
