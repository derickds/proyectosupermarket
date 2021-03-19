package minimarketdemo.model.facturacion.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.auditoria.managers.ManagerAuditoria;
import minimarketdemo.model.core.entities.FactCabecera;
import minimarketdemo.model.core.entities.FactDescuento;
import minimarketdemo.model.core.entities.FactDetalle;
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
