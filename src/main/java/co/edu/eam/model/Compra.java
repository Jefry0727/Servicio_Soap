package co.edu.eam.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the compra database table.
 * 
 */
@Entity
@NamedQuery(name="Compra.findAll", query="SELECT c FROM Compra c")
public class Compra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_compra")
	private Date fechaCompra;

	@Column(name="numero_compra")
	private String numeroCompra;

	@Column(name="valor_total")
	private double valorTotal;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	private Cliente cliente;

	//bi-directional many-to-one association to ItemCompra
//	@OneToMany(mappedBy="compra")
//	private List<ItemCompra> itemCompras;

	public Compra() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaCompra() {
		return this.fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public String getNumeroCompra() {
		return this.numeroCompra;
	}

	public void setNumeroCompra(String numeroCompra) {
		this.numeroCompra = numeroCompra;
	}

	public double getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

//	public List<ItemCompra> getItemCompras() {
//		return this.itemCompras;
//	}
//
//	public void setItemCompras(List<ItemCompra> itemCompras) {
//		this.itemCompras = itemCompras;
//	}
//
//	public ItemCompra addItemCompra(ItemCompra itemCompra) {
//		getItemCompras().add(itemCompra);
//		itemCompra.setCompra(this);
//
//		return itemCompra;
//	}
//
//	public ItemCompra removeItemCompra(ItemCompra itemCompra) {
//		getItemCompras().remove(itemCompra);
//		itemCompra.setCompra(null);
//
//		return itemCompra;
//	}

}