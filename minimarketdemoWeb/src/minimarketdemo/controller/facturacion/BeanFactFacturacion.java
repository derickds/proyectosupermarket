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
	private int cantidad;
	private InvStock compra;
	public BeanFactFacturacion() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void inicializar() {
		listaProductosStock = mFacturacion.findAllProductosDisponibles();
		JSFUtil.crearMensajeINFO("Registros encontrados: " + listaProductosStock.size());
	}
	
	public void actionSeleccionarCompra(InvStock p) {
		try {
			compra=p;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void actionListenerAgregarProducto() {
		try {
			carrito = mFacturacion.agregarProductoCarrito(carrito, compra, cantidad);
			System.out.println("Cantidad: "+cantidad);
			int i = 0;
			for(InvStock stockPro: listaProductosStock) {
				if(stockPro.getIdInvStock()==compra.getIdInvStock()) {
					stockPro.setCantidadStockProducto(stockPro.getCantidadStockProducto()-cantidad);
					listaProductosStock.set(i, stockPro);
					break;
				}
				i++;
			}
			compra= new InvStock();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminarProducto(carritoDTO p) {
		try {
			carrito= mFacturacion.eliminarProductoCarrito(carrito, p.getIdStock());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actionListenerBuscarStockProducto() {
		try {
			listaProductosStock = mFacturacion.findProductoStockWhere(idStock);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public InvStock getCompra() {
		return compra;
	}

	public void setCompra(InvStock compra) {
		this.compra = compra;
	}


}
