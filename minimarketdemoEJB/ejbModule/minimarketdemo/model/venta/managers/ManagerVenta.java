package minimarketdemo.model.venta.managers;



import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.core.entities.FactDetalle;
import minimarketdemo.model.core.entities.ThmEmpleado;
import minimarketdemo.model.core.entities.VentRegistro;
import minimarketdemo.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerAuditoria
 */
@Stateless
@LocalBean
public class ManagerVenta {
	@EJB
	private ManagerDAO mDAO;
    /**
     * Default constructor. 
     */
    public ManagerVenta() {
        
    }
    /**
     * Metodo basico para mostrar mensajes de depuracion.
     * @param clase Informacion de la clase que se esta depurando.
     * @param nombreMetodo Metodo que genera el mensaje para depuracion.
     * @param mensaje El mensaje a desplegar.
     */
    //Ventas registros:
    public List<VentRegistro> findAllVenta(){
    	return mDAO.findAll(VentRegistro.class);
    }
    ///thm_empleados:
    public ThmEmpleado findByIdThmEmpleado(int idThmEmpleado) throws Exception {
    	return (ThmEmpleado) mDAO.findById(ThmEmpleado.class, idThmEmpleado);
    }
    public List<ThmEmpleado> findAllThmEmpleado(){
    	return mDAO.findAll(ThmEmpleado.class, "horasTrabajadas");
    }
    ///factura_detalles:
    public FactDetalle findByidFactDetalle(int idFactDetalle) throws Exception{
    	return (FactDetalle)mDAO.findById(FactDetalle.class, idFactDetalle);
    }
    public List<FactDetalle> findAllFactDetalle(){
    	return mDAO.findAll(FactDetalle.class, "costoUnitarioDetalle");
    }
    

}
