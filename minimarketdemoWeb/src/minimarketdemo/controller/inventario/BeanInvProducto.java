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
public class BeanInvProducto implements Serializable {
	@EJB
	private ManagerInventario managerInventario;
	
	private List<InvProducto> listaproducto;
	private List<InvProveedor> listaProveedor;
	
	private InvProducto nuevoProducto;
	private InvStock nuevoStock;
	private InvProveedor nuevoProveSelec;
	private InvProducto edicionProducto;
	private InvStock edicionStock;
	private List<InvStock> listaStock;
	private int cantidad;
	private int idProv;
	public int getIdProv() {
		return idProv;
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
	
	public BeanInvProducto() {
		
	}
	
	public String actionMenuProducto() {
		listaproducto=managerInventario.findAllProductos();
		listaStock=managerInventario.findAllStock();
		listaProveedor=managerInventario.findAllProveedor();
		nuevoProducto=new InvProducto();
		nuevoStock=new InvStock();
		edicionProducto=new InvProducto();
		edicionStock=new InvStock();
		idProv=1;
		return "producto";
	}
	
	
	
	public void actionListenerInsertarNuevoProducto() {
		try {
			nuevoProveSelec=managerInventario.findByIdInvProveedor(idProv);
			nuevoProducto.setInvProveedor(nuevoProveSelec);
			nuevoStock.setInvProducto(nuevoProducto);	
			managerInventario.insertarProducto(nuevoProducto,nuevoStock);
			listaproducto=managerInventario.findAllProductos();
			nuevoProducto=new InvProducto();
			nuevoStock=new InvStock();
			JSFUtil.crearMensajeINFO("Producto registrado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionSeleccionarEdicionProducto(InvProducto producto) {
		edicionProducto=producto;
		idProv=producto.getInvProveedor().getIdInvProveedor();
		
	}
	public void actionSeleccionarEdicionStock(int idstock) {
		try {
			edicionStock=managerInventario.findByIdInvStock(idstock);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void actionListenerActualizarProducto() {
		try {
			edicionProducto.setInvProveedor(managerInventario.findByIdInvProveedor(idProv));
			managerInventario.actualizarProducto(edicionProducto);
			listaproducto=managerInventario.findAllProductos();
			JSFUtil.crearMensajeINFO("Producto actualizado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerActualizarStockIng() {
		try {
			edicionStock.setCantidadStockProducto(edicionStock.getCantidadStockProducto()+cantidad);
			managerInventario.actualizarStock(edicionStock);
			listaproducto=managerInventario.findAllProductos();
			listaStock=managerInventario.findAllStock();
			JSFUtil.crearMensajeINFO("Cantidad actualizada.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerActualizarStockEg() {
		try {
			edicionStock.setCantidadStockProducto(edicionStock.getCantidadStockProducto()-cantidad);
			managerInventario.actualizarStock(edicionStock);
			listaproducto=managerInventario.findAllProductos();
			listaStock=managerInventario.findAllStock();
			JSFUtil.crearMensajeINFO("Cantidad actualizada.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminarProducto(int idInvProducto) {
		try {
			managerInventario.eliminarProducto(idInvProducto);
			listaproducto=managerInventario.findAllProductos();
			JSFUtil.crearMensajeINFO("Producto eliminado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
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
