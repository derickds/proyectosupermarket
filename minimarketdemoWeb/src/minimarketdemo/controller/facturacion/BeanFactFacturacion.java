package minimarketdemo.controller.facturacion;

import java.io.Serializable;
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
import minimarketdemo.model.core.entities.SegUsuario;
import minimarketdemo.model.facturacion.dtos.carritoDTO;
import minimarketdemo.model.facturacion.managers.ManagerFacturacion;
import minimarketdemo.model.seguridades.dtos.LoginDTO;

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
	private CliPersona clienteFact;
	private int descuentoFact;
	private double subTotal;
	private List<FactDescuento> listaDescuentos;
	private double iva;
	private double total;
	private LoginDTO loginDTO;
	private ManagerCliente mCliente;
	
	public BeanFactFacturacion() {
		// TODO Auto-generated constructor stub
	}
	
	@Inject
	private BeanCliPersona beanCliPersona;
	@Inject
	private BeanSegLogin beanSegLogin;
	
	@PostConstruct
	public void inicializar() {
		listaProductosStock = mFacturacion.findAllProductosDisponibles();
		loginDTO = beanSegLogin.getLoginDTO();
		clienteFact = new CliPersona();
		try {
			listaDescuentos = mFacturacion.findAllDescuentos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSFUtil.crearMensajeINFO("Registros encontrados: " + listaProductosStock.size());
	}
	
	
	public void actionSeleccionarCompra(int p) {
		try {
			System.out.println(""+p);
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
	
	public String actionListenerGenerarFactura() {
		FactDescuento descuento;
		SegUsuario user;
		try {
			beanCliPersona.actionListenerRegistrarClienteFactura();
			clienteFact = beanCliPersona.getClienteFact();
			if(clienteFact==null) {
				List<CliPersona> clientes = mCliente.findAllPersonas();
				clienteFact = clientes.get(clientes.size()-1);		
			}
			descuento = mFacturacion.findByIdDescuento(descuentoFact);
			user = mFacturacion.findUsuarioById(loginDTO.getIdSegUsuario());
			mFacturacion.generarFactura(clienteFact, descuento, user, carrito);
			
			JSFUtil.crearMensajeINFO("¡FACTURA REALIZADA!");
			return "menu";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "menu";
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

	public CliPersona getClienteFact() {
		return clienteFact;
	}

	public void setClienteFact(CliPersona clienteFact) {
		this.clienteFact = clienteFact;
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
