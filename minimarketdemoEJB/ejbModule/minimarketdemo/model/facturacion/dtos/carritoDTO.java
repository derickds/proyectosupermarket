package minimarketdemo.model.facturacion.dtos;

public class carritoDTO {
	private int idStock;
	private int cantidadCompra;
	private int idProducto;
	private String nombreProducto;
	private double precio;
	
	public carritoDTO(int idStock, int cantidadCompra, int idProducto, String nombreProducto, double precio) {
		super();
		this.idStock = idStock;
		this.cantidadCompra = cantidadCompra;
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.precio = precio;
	}

	public carritoDTO() {
		super();
	}

	public int getIdStock() {
		return idStock;
	}

	public void setIdStock(int idStock) {
		this.idStock = idStock;
	}

	public int getCantidadCompra() {
		return cantidadCompra;
	}

	public void setCantidadCompra(int cantidadCompra) {
		this.cantidadCompra = cantidadCompra;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
}
