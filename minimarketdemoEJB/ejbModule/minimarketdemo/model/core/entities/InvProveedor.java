package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the inv_proveedor database table.
 * 
 */
@Entity
@Table(name="inv_proveedor")
@NamedQuery(name="InvProveedor.findAll", query="SELECT i FROM InvProveedor i")
public class InvProveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_inv_proveedor", unique=true, nullable=false)
	private Integer idInvProveedor;

	@Column(name="nombre_proveedor", nullable=false, length=100)
	private String nombreProveedor;

	@Column(name="telefono_proveedor", nullable=false, length=10)
	private String telefonoProveedor;

	//bi-directional many-to-one association to InvProducto
	@OneToMany(mappedBy="invProveedor")
	private List<InvProducto> invProductos;

	public InvProveedor() {
	}

	public Integer getIdInvProveedor() {
		return this.idInvProveedor;
	}

	public void setIdInvProveedor(Integer idInvProveedor) {
		this.idInvProveedor = idInvProveedor;
	}

	public String getNombreProveedor() {
		return this.nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getTelefonoProveedor() {
		return this.telefonoProveedor;
	}

	public void setTelefonoProveedor(String telefonoProveedor) {
		this.telefonoProveedor = telefonoProveedor;
	}

	public List<InvProducto> getInvProductos() {
		return this.invProductos;
	}

	public void setInvProductos(List<InvProducto> invProductos) {
		this.invProductos = invProductos;
	}

	public InvProducto addInvProducto(InvProducto invProducto) {
		getInvProductos().add(invProducto);
		invProducto.setInvProveedor(this);

		return invProducto;
	}

	public InvProducto removeInvProducto(InvProducto invProducto) {
		getInvProductos().remove(invProducto);
		invProducto.setInvProveedor(null);

		return invProducto;
	}

}