package minimarketdemo.model.pedidos.managers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.auditoria.managers.ManagerAuditoria;
import minimarketdemo.model.core.entities.CliPersona;
import minimarketdemo.model.core.entities.FactCabecera;
import minimarketdemo.model.core.entities.FactDescuento;
import minimarketdemo.model.core.entities.FactDetalle;
import minimarketdemo.model.core.entities.InvProducto;
import minimarketdemo.model.core.entities.InvStock;
import minimarketdemo.model.core.entities.PedOrden;
import minimarketdemo.model.core.entities.PedOrdenDetalle;
import minimarketdemo.model.core.entities.RelacMedio;
import minimarketdemo.model.core.entities.SegModulo;
import minimarketdemo.model.core.entities.SegUsuario;
import minimarketdemo.model.core.managers.ManagerDAO;
import minimarketdemo.model.facturacion.dtos.carritoDTO;

/**
 * Session Bean implementation class ManagerFacturacion
 */
@Stateless
@LocalBean
public class ManagerPedidos {

	@EJB
	private ManagerDAO mDAO;
	@EJB
	private ManagerAuditoria mAuditoria;
	
    public ManagerPedidos() {
        // TODO Auto-generated constructor stub
    }
    
  //Manejo Cabecera Factura  *********************************************************************************
    public List<PedOrden> findAllPedidos(){
    	return mDAO.findAll(PedOrden.class, null);
    }
    public List<PedOrdenDetalle> findAllDetalles(){
    	return mDAO.findAll(PedOrdenDetalle.class, null);
    }
    
    
    public List<InvStock> findAllProductosDisponibles(){
    	List<InvStock> listaStock = mDAO.findAll(InvStock.class, null);
    	List<InvStock> listaStock2 = new ArrayList<InvStock>();
    	
    	for(InvStock productoStock: listaStock) {
    		if(productoStock.getCantidadStockProducto()>0) {
    			listaStock2.add(productoStock);
    		}
    	}
    	return listaStock;
    }
    
    public List<carritoDTO> agregarProductoCarrito(List<carritoDTO> carrito, InvStock p,int cantidad)  throws Exception{
    	if(carrito==null) {
    		carrito = new ArrayList<carritoDTO>();
    		carritoDTO carritodto = new carritoDTO(
    				p.getIdInvStock(),
    				cantidad,
    				p.getInvProducto().getIdInvProducto(),
    				p.getInvProducto().getNombreProducto(),
    				 p.getInvProducto().getPrecio().doubleValue());
    		carrito.add(carritodto);
    	}else if(p.getCantidadStockProducto()==0){
    		throw new Exception("Producto sin stock");
    	}else {
    		carritoDTO carritodto = new carritoDTO(
    				p.getIdInvStock(),
    				cantidad,
    				p.getInvProducto().getIdInvProducto(),
    				p.getInvProducto().getNombreProducto(),
    				 p.getInvProducto().getPrecio().doubleValue());
    		carrito.add(carritodto);
    	}
    	return carrito;
    }
    
    public List<carritoDTO> eliminarProductoCarrito(List<carritoDTO> carrito, int codigoProducto)  throws Exception{
    	if(carrito==null) {
    		return null;
    	}else {
    		int i = 0;
    		for(carritoDTO p:carrito) {
    			if(p.getIdStock()==codigoProducto) {
        			carrito.remove(i);	
        			break;
    			}
    			i++;
    		}
    	}
    	return carrito;
    }
    
    public List<InvStock> findProductoStockWhere(int idStock)  throws Exception{
    	if(idStock==0) {
    		return findAllProductosDisponibles();
    	}else {
    	return mDAO.findWhere(InvStock.class, "id_inv_stock="+idStock, null);
    	}
    }
    
    public double calcularSubtotal(List<carritoDTO> carrito) {
    	double var = 0;
    	for(carritoDTO car: carrito) {
    		var = var +(car.getCantidadCompra()*car.getPrecio());
    	}
    	return var;
    }
    
    public double calcularIva(double subtotal, double iva) {
    	return subtotal * iva;
    }
    
    public double calcularTotal(double iva, int descuento, double subtotal) {
    	double descuentos =(subtotal * (descuento * 0.01));
    	double total = subtotal -descuentos + iva;
    	return total;
    }
    
    
    public void generarPedido(CliPersona persona, SegUsuario usuario, List<carritoDTO> carrito) {
    	PedOrden pedido = new PedOrden();
    	pedido.setCliPersona(persona);
    	
    	
    	try {
			mDAO.insertar(pedido);
			List<PedOrden> listaPedido = findAllPedidos();
			
	    	for(int i = 0; i < carrito.size(); i++) {
	    		PedOrdenDetalle dt = new PedOrdenDetalle();
	    		dt.setCantidadOrden(carrito.get(i).getCantidadCompra());
	    		dt.setPedOrdenBean(listaPedido.get(listaPedido.size()-1));
	    		dt.setInvProducto(findProductoById(carrito.get(i).getIdProducto()));
	    		mDAO.insertar(dt);
	    		InvStock inventario = (InvStock) mDAO.findById(InvStock.class,carrito.get(i).getIdProducto());
	    		inventario.setCantidadStockProducto(inventario.getCantidadStockProducto()-carrito.get(i).getCantidadCompra());
	    		mDAO.actualizar(inventario);
	    	}
	    	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public SegUsuario findUsuarioById(int idUsuario)throws Exception {
    	return (SegUsuario) mDAO.findById(SegUsuario.class, idUsuario);
    }
    
    public InvProducto findProductoById(int idProducto) throws Exception {
    	return (InvProducto) mDAO.findById(InvProducto.class, idProducto);
    }
    
    public InvStock findStockById(int idProducto) throws Exception {
    	return (InvStock) mDAO.findById(InvStock.class, idProducto);
    }
    public PedOrden findPedidoById(int idPedido) throws Exception {
    	return (PedOrden) mDAO.findById(PedOrden.class, idPedido);
    }
    public List<PedOrdenDetalle> findDetalleByOrden(int idPedido) throws Exception {
    	List<PedOrdenDetalle> deta=mDAO.findWhere(PedOrdenDetalle.class,"ped_orden="+idPedido, null);
    	return deta;
    }
    //Manejo Detalle Factura *********************************************************************************
    public List<FactDetalle> findAllDetallesFactura()  throws Exception{
    	return mDAO.findAll(FactDetalle.class, null);
    }
    
    public List<FactDetalle> findDetallesFacturaByIdCabecera(int idCabecera)  throws Exception{
    	return mDAO.findWhere(FactDetalle.class, "id_fact_cabecera="+idCabecera, null);
    }
    //MANEJO DESCUENTOS *********************************************************************************
    //Listado de todos los descuentos
    public List<FactDescuento> findAllDescuentos()  throws Exception{
    	return mDAO.findAll(FactDescuento.class, null);
    }
    //Agregar un nuevo descuento
    public void insertardescuento(FactDescuento nuevoDescuento) throws Exception {
    	FactDescuento descuento=new FactDescuento();
    	descuento.setNombreDescuento(nuevoDescuento.getNombreDescuento());
    	descuento.setPorcentajeDescuento(nuevoDescuento.getPorcentajeDescuento());
    	mDAO.insertar(descuento);
    }
    //Actualizar un descuento
    public void actualizarDescuento(FactDescuento edicionDescuento) throws Exception {
    	FactDescuento descuento=(FactDescuento) mDAO.findById(FactDescuento.class, edicionDescuento.getIdFactDescuento());
    	descuento.setNombreDescuento(edicionDescuento.getNombreDescuento());
    	descuento.setPorcentajeDescuento(edicionDescuento.getPorcentajeDescuento());
    	mDAO.actualizar(descuento);
    }
    //Eliminar un descuento
    
    public void eliminarDescuento(int idFactDescuento) throws Exception {
    	FactDescuento descuento=(FactDescuento)mDAO.findById(FactDescuento.class, idFactDescuento);
    	if(descuento.getFactCabeceras().size()>0)
    		throw new Exception("No se puede eliminar el Descuento porque existen Relaciones con este Medio");
    	mDAO.eliminar(FactDescuento.class, descuento.getIdFactDescuento());
    }
    public void eliminarPedido(int idOrden) throws Exception {
    	PedOrden orden=(PedOrden)mDAO.findById(PedOrden.class, idOrden);
    	List<PedOrdenDetalle> deta=findDetalleByOrden(orden.getPedOrden());
    	List<PedOrden> listaPedido = findAllPedidos();
		
    	for(int i = 0; i < deta.size(); i++) {
    		PedOrdenDetalle dt = new PedOrdenDetalle();
    		dt=deta.get(i);
    		InvStock stock=new InvStock();
    		stock=findStockById(dt.getInvProducto().getIdInvProducto());
    		stock.setCantidadStockProducto(stock.getCantidadStockProducto()+dt.getCantidadOrden());
    		mDAO.actualizar(stock);
    		mDAO.eliminar(PedOrdenDetalle.class,dt.getIdPedOrdenDetalle());
    	}
    	mDAO.eliminar(PedOrden.class, orden.getPedOrden());
    }
    //Buscar por id
    public FactDescuento findByIdDescuento(int idDescuento)  throws Exception{
    	return (FactDescuento) mDAO.findById(FactDescuento.class, idDescuento);
    }

}
