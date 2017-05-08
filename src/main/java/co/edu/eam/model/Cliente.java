package co.edu.eam.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String cedula;

	@Column(name="id_crm")
	private String idCrm;

	//bi-directional many-to-one association to Compra
//	@OneToMany(mappedBy="cliente")
//	private List<Compra> compras;

	public Cliente() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getIdCrm() {
		return this.idCrm;
	}

	public void setIdCrm(String idCrm) {
		this.idCrm = idCrm;
	}

//	public List<Compra> getCompras() {
//		return this.compras;
//	}
//
//	public void setCompras(List<Compra> compras) {
//		this.compras = compras;
//	}
//
//	public Compra addCompra(Compra compra) {
//		getCompras().add(compra);
//		compra.setCliente(this);
//
//		return compra;
//	}
//
//	public Compra removeCompra(Compra compra) {
//		getCompras().remove(compra);
//		compra.setCliente(null);
//
//		return compra;
//	}

}