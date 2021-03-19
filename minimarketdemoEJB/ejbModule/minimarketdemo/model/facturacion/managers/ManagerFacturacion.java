package minimarketdemo.model.facturacion.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.auditoria.managers.ManagerAuditoria;
import minimarketdemo.model.core.entities.FactCabecera;
import minimarketdemo.model.core.entities.FactDescuento;
import minimarketdemo.model.core.entities.FactDetalle;
import minimarketdemo.model.core.entities.InvProducto;
import minimarketdemo.model.core.entities.InvStock;
import minimarketdemo.model.core.entities.RelacMedio;
import minimarketdemo.model.core.entities.SegModulo;
import minimarketdemo.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerFacturacion
 */
@Stateless
@LocalBean
public class ManagerFacturacion {

	@EJB
	private ManagerDAO mDAO;
	@EJB
	private ManagerAuditoria mAuditoria;
	
    public ManagerFacturacion() {
        // TODO Auto-generated constructor stub
    }
    
  //Manejo Cabecera Factura *********************************************************************************
    public List<FactCabecera> findAllCabecerasFactura(){
    	return mDAO.findAll(FactCabecera.class, null);
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
    
    public List<InvStock> agregarProductoCarrito(List<InvStock> carrito, InvStock p){
    	if(carrito==null) {
    		carrito = new ArrayList<InvStock>();
    	}else {
    		carrito.add(p);
    	}
    	return carrito;
    }
    
    public List<InvStock> eliminarProductoCarrito(List<InvStock> carrito, int codigoProducto){
    	if(carrito==null) {
    		return null;
    	}else {
    		int i = 0;
    		for(InvStock p:carrito) {
    			if(p.getIdInvStock()==codigoProducto) {
        			carrito.remove(i);	
        			break;
    			}
    			i++;
    		}
    	}
    	return carrito;
    }
    
    //Manejo Detalle Factura *********************************************************************************
    public List<FactDetalle> findAllDetallesFactura(){
    	return mDAO.findAll(FactDetalle.class, null);
    }
    
    public List<FactDetalle> findDetallesFacturaByIdCabecera(int idCabecera){
    	return mDAO.findWhere(FactDetalle.class, "id_fact_cabecera="+idCabecera, null);
    }
    //MANEJO DESCUENTOS *********************************************************************************
    //Listado de todos los descuentos
    public List<FactDescuento> findAllDescuentos(){
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
    

}
