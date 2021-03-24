package minimarketdemo.model.inventario.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder.In;

import minimarketdemo.model.auditoria.managers.ManagerAuditoria;
import minimarketdemo.model.core.entities.InvProducto;
import minimarketdemo.model.core.entities.InvProveedor;
import minimarketdemo.model.core.entities.InvStock;
import minimarketdemo.model.core.entities.SegAsignacion;
import minimarketdemo.model.core.entities.SegModulo;
import minimarketdemo.model.core.entities.SegUsuario;
import minimarketdemo.model.core.entities.ThmCargo;
import minimarketdemo.model.core.entities.ThmEmpleado;
import minimarketdemo.model.core.managers.ManagerDAO;
import minimarketdemo.model.core.utils.ModelUtil;
import minimarketdemo.model.seguridades.dtos.LoginDTO;

/**
 * Implementa la logica de manejo de usuarios y autenticacion.
 * Funcionalidades principales:
 * <ul>
 * 	<li>Verificacion de credenciales de usuario.</li>
 *  <li>Asignacion de modulos a un usuario.</li>
 * </ul>
 */
@Stateless
@LocalBean
public class ManagerInventario {
	@EJB
	private ManagerDAO mDAO;
	@EJB
	private ManagerAuditoria mAuditoria;
    /**
     * Default constructor. 
     */
    public ManagerInventario() {
        
    }
    /**
     * Funcion de inicializacion de datos de usuarios.
     */
    
    
    /**
     * Funcion de autenticacion mediante el uso de credenciales.
     * @param idSegUsuario El codigo del usuario que desea autenticarse.
     * @param clave La contrasenia.
     * @param direccionIP La dirección IP V$ del cliente web.
     * @return La ruta de acceso al sistema.
     * @throws Exception
     */
    
   
    
    public List<InvProducto> findAllProductos(){
    	return mDAO.findAll(InvProducto.class, "idInvProducto");
    }
    public List<InvStock> findAllStock(){
    	return mDAO.findAll(InvStock.class);
    }
    public List<InvProveedor> findAllProveedor(){
    	return mDAO.findAll(InvProveedor.class);
    }
    
    public InvProducto findByIdInvProducto(int idInvProducto) throws Exception {
    	return (InvProducto) mDAO.findById(InvProducto.class, idInvProducto);
    }
    public InvProveedor findByIdInvProveedor(int idInvProveedor) throws Exception {
    	return (InvProveedor) mDAO.findById(InvProveedor.class, idInvProveedor);
    }
    public InvStock findByIdInvStock(int idInvStock) throws Exception {
    	return (InvStock) mDAO.findById(InvStock.class, idInvStock);
    }
    
    public void insertarProducto(InvProducto nuevoProducto, InvStock nuevoStock) throws Exception {
    	mDAO.insertar(nuevoProducto);
    	mDAO.insertar(nuevoStock);
    	
    }
    public void insertarProveedor(InvProveedor nuevoProveedor) throws Exception {
    	mDAO.insertar(nuevoProveedor);
    	
    	
    }
    
    public void actualizarProducto(InvProducto edicionProducto) throws Exception {
    	InvProducto producto= (InvProducto) mDAO.findById(InvProducto.class, edicionProducto.getIdInvProducto());
    	producto.setIdInvProducto(edicionProducto.getIdInvProducto());
    	producto.setInvProveedor(edicionProducto.getInvProveedor());
    	producto.setNombreProducto(edicionProducto.getNombreProducto());
    	producto.setPrecio(edicionProducto.getPrecio());
    	mDAO.actualizar(producto);
    	
    }
    public void actualizarProveedor(InvProveedor edicionProveedor) throws Exception {
    	InvProveedor proveedor=(InvProveedor) mDAO.findById(InvProveedor.class, edicionProveedor.getIdInvProveedor());
    	proveedor.setNombreProveedor(edicionProveedor.getNombreProveedor());
    	proveedor.setTelefonoProveedor(edicionProveedor.getTelefonoProveedor());
    	mDAO.actualizar(proveedor);
    	
    }
    public void actualizarStock(InvStock edicionStock) throws Exception {
    	InvStock stock=(InvStock) mDAO.findById(InvStock.class, edicionStock.getIdInvStock());
    	stock.setCantidadStockProducto(edicionStock.getCantidadStockProducto());
    	mDAO.actualizar(stock);
    	
    }
    
    
    
    public void eliminarProducto(int idInvProducto) throws Exception {
    	InvProducto producto=(InvProducto) mDAO.findById(InvProducto.class, idInvProducto);
    	List<InvStock> stock=mDAO.findWhere(InvStock.class,"id_inv_producto="+idInvProducto, null);
    	if(stock.get(0).getCantidadStockProducto()>0)
    		throw new Exception("No se puede elimininar el producto, aun posee stock");
    	mDAO.eliminar(InvStock.class,stock.get(0).getIdInvStock());
    	mDAO.eliminar(InvProducto.class, producto.getIdInvProducto());
    }
    public void eliminarProveedor(int idInvProveedor) throws Exception {
    	InvProveedor proveedor=(InvProveedor) mDAO.findById(InvProveedor.class, idInvProveedor);
    	List<InvStock> stock=mDAO.findAll(InvStock.class);
    	for(InvStock stockf:stock) {
    		if(stockf.getInvProducto().getInvProveedor().getIdInvProveedor()==idInvProveedor) {
    			throw new Exception("No se puede elimininar el proveedor, aun se encuentran productos");
    		}
    	}
    		
    	mDAO.eliminar(InvProveedor.class,proveedor.getIdInvProveedor());
    }
    
    
    
    /**
     * Permite asignar el acceso a un modulo del inventario de sistemas.
     * @param idSegUsuario El codigo del usuario.
     * @param idSegModulo El codigo del modulo que se va a asignar.
     * @throws Exception
     */
    
  ///conteo
    public int ContarProductos() {
    	int numeroProductos=0;
    	List<InvProducto> prod=mDAO.findAll(InvProducto.class);
    	for(InvProducto p:prod) {
    		numeroProductos=numeroProductos+1;
		}
		return numeroProductos;
    }
    //conteo proveedor
    public int ContarProveedores() {
    	int numeroProveedor=0;
    	List<InvProveedor> prove=mDAO.findAll(InvProveedor.class);
    	for(InvProveedor pro:prove) {
    		numeroProveedor=numeroProveedor+1;
		}
		return numeroProveedor;
    }
    

}
