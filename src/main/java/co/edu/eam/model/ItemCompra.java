package co.edu.eam.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the item_compra database table.
 * 
 */
@Entity
@Table(name="item_compra")
@NamedQuery(name="ItemCompra.findAll", query="SELECT i FROM ItemCompra i")
public class ItemCompra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private Integer cantidad;

	@Column(name="id_producto")
	private String idProducto;

	@Column(name="valor_producto")
	private double valorProducto;

	//bi-directional many-to-one association to Compra
	@ManyToOne
	private Compra compra;

	public ItemCompra() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public double getValorProducto() {
		return this.valorProducto;
	}

	public void setValorProducto(double valorProducto) {
		this.valorProducto = valorProducto;
	}

	public Compra getCompra() {
		return this.compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

}