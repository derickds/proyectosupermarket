package minimarketdemo.controller.facturacion;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.core.entities.InvProducto;
import minimarketdemo.model.core.entities.InvStock;
import minimarketdemo.model.facturacion.dtos.carritoDTO;
import minimarketdemo.model.facturacion.managers.ManagerFacturacion;

@Named
@SessionScoped
public class BeanFactFacturacion implements Serializable {
	@EJB
	private ManagerFacturacion mFacturacion;
	private List<InvStock> listaProductosStock;
	private List<carritoDTO> carrito;
	private int idStock;
	public BeanFactFacturacion() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void inicializar() {
		listaProductosStock = mFacturacion.findAllProductosDisponibles();
		JSFUtil.crearMensajeINFO("Registros encontrados: " + listaProductosStock.size());
	}
	
	public void actionListenerAgregarProducto(InvStock p) {
		carrito = mFacturacion.agregarProductoCarrito(carrito, p);
	}
	
	public void actionListenerEliminarProducto(carritoDTO p) {
		carrito= mFacturacion.eliminarProductoCarrito(carrito, p.getIdStock());
	}
	
	public void actionListenerBuscarStockProducto() {
		listaProductosStock = mFacturacion.findProductoStockWhere(idStock);
	}
	
	public String actionCargarFacturar() {
		return "facturacion";
	}

	public List<InvStock> getListaProductosStock() {
		return listaProductosStock;
	}

	public void setListaProductosStock(List<InvStock> listaProductosStock) {
		this.listaProductosStock = listaProductosStock;
	}

	public List<carritoDTO> getCarrito() {
		return carrito;
	}

	public void setCarrito(List<carritoDTO> carrito) {
		this.carrito = carrito;
	}

	public int getIdStock() {
		return idStock;
	}

	public void setIdStock(int idStock) {
		this.idStock = idStock;
	}


}
