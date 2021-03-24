package minimarketdemo.controller.inventario;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder.In;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.controller.seguridades.BeanSegLogin;
import minimarketdemo.model.core.entities.InvProducto;
import minimarketdemo.model.core.entities.InvProveedor;
import minimarketdemo.model.core.entities.InvStock;
import minimarketdemo.model.core.entities.SegUsuario;
import minimarketdemo.model.inventario.managers.ManagerInventario;
import minimarketdemo.model.seguridades.managers.ManagerSeguridades;

@Named
@SessionScoped
public class BeanInvProveedor implements Serializable {
	@EJB
	private ManagerInventario managerInventario;
	
	private List<InvProducto> listaproducto;
	private List<InvProveedor> listaProveedor;
	
	private InvProducto nuevoProducto;
	private InvStock nuevoStock;
	private InvProveedor nuevoProveedor;
	private InvProveedor edicionProveedor;
	private InvProducto edicionProducto;
	private InvStock edicionStock;
	private List<InvStock> listaStock;
	private int cantidad;
	private int idProv;
	public int getIdProv() {
		return idProv;
	}

	public InvProveedor getNuevoProveedor() {
		return nuevoProveedor;
	}

	public void setNuevoProveedor(InvProveedor nuevoProveedor) {
		this.nuevoProveedor = nuevoProveedor;
	}

	public InvProveedor getEdicionProveedor() {
		return edicionProveedor;
	}

	public void setEdicionProveedor(InvProveedor edicionProveedor) {
		this.edicionProveedor = edicionProveedor;
	}

	public void setIdProv(int idProv) {
		this.idProv = idProv;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Inject
	private BeanSegLogin beanSegLogin;
	
	public BeanInvProveedor() {
		
	}
	
	public String actionMenuProveedor() {
		listaproducto=managerInventario.findAllProductos();
		listaStock=managerInventario.findAllStock();
		listaProveedor=managerInventario.findAllProveedor();
		nuevoProducto=new InvProducto();
		nuevoStock=new InvStock();
		edicionProducto=new InvProducto();
		edicionStock=new InvStock();
		nuevoProveedor=new InvProveedor();
		return "proveedor";
	}
	
	
	
	public void actionListenerInsertarNuevoProveedor() {
		try {
			managerInventario.insertarProveedor(nuevoProveedor);
			listaProveedor=managerInventario.findAllProveedor();
			nuevoProveedor= new InvProveedor();
			JSFUtil.crearMensajeINFO("Proveedor registrado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionSeleccionarEdicionProveedor(int idProveedor) {
		
		try {
			edicionProveedor=managerInventario.findByIdInvProveedor(idProveedor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void actionListenerActualizarProveedor() {
		try {
			managerInventario.actualizarProveedor(edicionProveedor);
			listaProveedor=managerInventario.findAllProveedor();
			JSFUtil.crearMensajeINFO("Proveedor actualizado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	public void actionListenerEliminarProveedor(int idInvProveedor) {
		try {
			managerInventario.eliminarProveedor(idInvProveedor);
			listaProveedor=managerInventario.findAllProveedor();
			JSFUtil.crearMensajeINFO("Proveedor eliminado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	//conteo modulos
		public int actionListenerContarProveedor() {
			return managerInventario.ContarProveedores();
		}

	public ManagerInventario getManagerInventario() {
		return managerInventario;
	}

	public void setManagerInventario(ManagerInventario managerInventario) {
		this.managerInventario = managerInventario;
	}

	public List<InvProducto> getListaproducto() {
		return listaproducto;
	}

	public void setListaproducto(List<InvProducto> listaproducto) {
		this.listaproducto = listaproducto;
	}

	public InvProducto getNuevoProducto() {
		return nuevoProducto;
	}

	public void setNuevoProducto(InvProducto nuevoProducto) {
		this.nuevoProducto = nuevoProducto;
	}

	public InvStock getNuevoStock() {
		return nuevoStock;
	}

	public void setNuevoStock(InvStock nuevoStock) {
		this.nuevoStock = nuevoStock;
	}

	public InvProducto getEdicionProducto() {
		return edicionProducto;
	}

	public void setEdicionProducto(InvProducto edicionProducto) {
		this.edicionProducto = edicionProducto;
	}

	public BeanSegLogin getBeanSegLogin() {
		return beanSegLogin;
	}

	public void setBeanSegLogin(BeanSegLogin beanSegLogin) {
		this.beanSegLogin = beanSegLogin;
	}

	public List<InvProveedor> getListaProveedor() {
		return listaProveedor;
	}

	public void setListaProveedor(List<InvProveedor> listaProveedor) {
		this.listaProveedor = listaProveedor;
	}

	public List<InvStock> getListaStock() {
		return listaStock;
	}

	public void setListaStock(List<InvStock> listaStock) {
		this.listaStock = listaStock;
	}

	public InvStock getEdicionStock() {
		return edicionStock;
	}

	public void setEdicionStock(InvStock edicionStock) {
		this.edicionStock = edicionStock;
	}

	
}
