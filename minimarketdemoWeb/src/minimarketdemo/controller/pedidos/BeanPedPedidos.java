package minimarketdemo.controller.pedidos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.controller.cliente.BeanCliPersona;
import minimarketdemo.controller.seguridades.BeanSegLogin;
import minimarketdemo.model.cliente.managers.ManagerCliente;
import minimarketdemo.model.core.entities.CliPersona;
import minimarketdemo.model.core.entities.FactDescuento;
import minimarketdemo.model.core.entities.InvProducto;
import minimarketdemo.model.core.entities.InvStock;
import minimarketdemo.model.core.entities.PedOrden;
import minimarketdemo.model.core.entities.PedOrdenDetalle;
import minimarketdemo.model.core.entities.SegUsuario;
import minimarketdemo.model.facturacion.dtos.carritoDTO;
import minimarketdemo.model.facturacion.managers.ManagerFacturacion;
import minimarketdemo.model.pedidos.managers.ManagerPedidos;
import minimarketdemo.model.seguridades.dtos.LoginDTO;

/**
 * @author Usuario
 *
 */
@Named
@SessionScoped
public class BeanPedPedidos implements Serializable {
	@EJB
	private ManagerFacturacion mFacturacion;
	@EJB
	private ManagerPedidos mP;
	private List<InvStock> listaProductosStock;
	private List<carritoDTO> carrito;
	private int idStock;
	private int cantidad;
	private InvStock compra;
	private CliPersona clientePed;
	private int descuentoFact;
	private double subTotal;
	private List<FactDescuento> listaDescuentos;
	private double iva;
	private double total;
	private LoginDTO loginDTO;
	private List<PedOrden> listapedido;

	private PedOrden pedidoselec;
	private List<PedOrdenDetalle> detalles;
	
	public List<PedOrdenDetalle> getDetalles() {
		return detalles;
	}


	public void setDetalles(List<PedOrdenDetalle> detalles) {
		this.detalles = detalles;
	}


	public BeanPedPedidos() {
		// TODO Auto-generated constructor stub
	}
	
	@Inject
	private BeanCliPersona beanCliPersona;
	@Inject
	private BeanSegLogin beanSegLogin;
	
	public String cargarMenuLista() {
		loginDTO = beanSegLogin.getLoginDTO();
		clientePed = new CliPersona();
		listapedido = mP.findAllPedidos();
		pedidoselec=new PedOrden();
		loginDTO = beanSegLogin.getLoginDTO();
		clientePed = new CliPersona();
		listapedido = mP.findAllPedidos();
		pedidoselec=new PedOrden();
		listaProductosStock = mP.findAllProductosDisponibles();
		loginDTO = beanSegLogin.getLoginDTO();
		return "listapedidos";
	}
	public String cargarMenuNuevo() {
		
		loginDTO = beanSegLogin.getLoginDTO();
		clientePed = new CliPersona();
		listapedido = mP.findAllPedidos();
		pedidoselec=new PedOrden();
		listaProductosStock = mP.findAllProductosDisponibles();
		loginDTO = beanSegLogin.getLoginDTO();
		
		return "listapedidos";
	}
	public void actionListenerEliminarPedido(int idInvProveedor) {
		try {
			mP.eliminarPedido(idInvProveedor);
			loginDTO = beanSegLogin.getLoginDTO();
			clientePed = new CliPersona();
			listapedido = mP.findAllPedidos();
			pedidoselec=new PedOrden();
			listaProductosStock = mP.findAllProductosDisponibles();
			loginDTO = beanSegLogin.getLoginDTO();
			
			JSFUtil.crearMensajeINFO("Pedido eliminado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionSeleccionarCompra(int p) {
		try {
			compra=mFacturacion.findStockById(p);
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
					actionListenerCalcularSubtotal();
					actionListenerCalcularIva();
					actionListenerCalcularTotal();
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
			int i = 0;
			for(InvStock stockPro: listaProductosStock) {
				if(stockPro.getIdInvStock()==p.getIdStock()) {
					stockPro.setCantidadStockProducto(stockPro.getCantidadStockProducto()+p.getCantidadCompra());
					listaProductosStock.set(i, stockPro);
					actionListenerCalcularSubtotal();
					actionListenerCalcularIva();
					actionListenerCalcularTotal();
					break;
				}
				i++;
			}
			actionListenerCalcularSubtotal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actionListenerCalcularSubtotal() {
		subTotal = mFacturacion.calcularSubtotal(carrito);
	}
	
	public void actionListenerCalcularIva() {
		iva = mFacturacion.calcularIva(subTotal, 0.12);
	}
	
	public void actionListenerCalcularTotal() {
		try {
			FactDescuento descuento = mFacturacion.findByIdDescuento(descuentoFact);
			System.out.println(""+descuento.getPorcentajeDescuento());
			total = mFacturacion.calcularTotal(iva, descuento.getPorcentajeDescuento(), subTotal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actionRegistrarClienteFactura() {
		beanCliPersona.actionListenerRegistrarClienteFactura();
		clientePed = beanCliPersona.getClienteFact();
	}
public void actionSeleccionarPedido(int idPedido) {
		
		try {
			pedidoselec=mP.findPedidoById(idPedido);
			detalles=mP.findDetalleByOrden(pedidoselec.getPedOrden());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String actionListenerGenerarPedido() {
		SegUsuario user;
		actionRegistrarClienteFactura();
		beanCliPersona.setClienteFact(new CliPersona());
		try {
			user = mP.findUsuarioById(loginDTO.getIdSegUsuario());
			mP.generarPedido(clientePed, user, carrito);
			
			JSFUtil.crearMensajeINFO("PEDIDO REALIZADO");
			listaProductosStock = mP.findAllProductosDisponibles();
			clientePed = new CliPersona();
			subTotal = 0;
			total = 0;
			iva = 0;
			carrito = new ArrayList<carritoDTO>();
			return "menu?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "menu?faces-redirect=true";
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

	public CliPersona getClientePed() {
		return clientePed;
	}


	public void setClientePed(CliPersona clientePed) {
		this.clientePed = clientePed;
	}


	

	public List<PedOrden> getListapedido() {
		return listapedido;
	}


	public void setListapedido(List<PedOrden> listapedido) {
		this.listapedido = listapedido;
	}


	public PedOrden getPedidoselec() {
		return pedidoselec;
	}


	public void setPedidoselec(PedOrden pedidoselec) {
		this.pedidoselec = pedidoselec;
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

	public CliPersona getClienteFact() {
		return clientePed;
	}

	public void setClienteFact(CliPersona clienteFact) {
		this.clientePed = clienteFact;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public List<FactDescuento> getListaDescuentos() {
		return listaDescuentos;
	}

	public void setListaDescuentos(List<FactDescuento> listaDescuentos) {
		this.listaDescuentos = listaDescuentos;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getDescuentoFact() {
		return descuentoFact;
	}

	public void setDescuentoFact(int descuentoFact) {
		this.descuentoFact = descuentoFact;
	}


}
