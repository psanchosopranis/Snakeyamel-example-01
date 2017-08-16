package es.pssstests.ejemplo.agregados;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import es.pssstests.ejemplo.componentes.DetalleIVA;
import es.pssstests.ejemplo.componentes.Producto;
import es.pssstests.ejemplo.componentes.Receptor;

public class Factura {

	protected Integer numeroDeFactura; 
	protected Date fechaFactura; 
	protected Date fechaProvistaDeEntrega;
	protected Receptor receptorFactura;
	protected Receptor receptorMercancia; 
	protected List<Producto> productos;
	protected List<DetalleIVA> detallesIVA;
	protected BigDecimal importeIVA;
	protected BigDecimal importeTotal;
	protected String comentarios;
	
	public Factura() {
	}

	
	
	/**
	 * @param numeroDeFactura
	 * @param fechaFactura
	 * @param fechaProvistaDeEntrega
	 * @param receptorFactura
	 * @param receptorMercancia
	 * @param productos
	 * @param comentarios
	 */
	public Factura(Integer numeroDeFactura, Date fechaFactura, Date fechaProvistaDeEntrega, Receptor receptorFactura,
			Receptor receptorMercancia, List<Producto> productos, String comentarios) {
		this.numeroDeFactura = numeroDeFactura;
		this.fechaFactura = fechaFactura;
		this.fechaProvistaDeEntrega = fechaProvistaDeEntrega;
		this.receptorFactura = receptorFactura;
		this.receptorMercancia = receptorMercancia;
		this.productos = productos;
		this.comentarios = comentarios;
		this.calculaImportes(this.productos);
	}

	private void calculaImportes(List<Producto> productos) {
		
		this.importeIVA = new BigDecimal(0);
		this.importeTotal = new BigDecimal(0);

		
		if (this.productos == null || this.productos.isEmpty()) {
			this.detallesIVA = null;
			return;
		} 
		
		this.detallesIVA = new ArrayList<DetalleIVA>();
		HashMap<BigDecimal, DetalleIVA> tablaDetallesIVA = new HashMap<BigDecimal, DetalleIVA>();
		for (Producto producto : productos) {
			
			this.importeIVA = this.importeIVA.add(producto.getImporteIVA());
			this.importeTotal = this.importeTotal.add(producto.getImporte());
			
			if (tablaDetallesIVA.containsKey(producto.getPorcentajeIVA())) {
				DetalleIVA detalle = tablaDetallesIVA.get(producto.getPorcentajeIVA());
				DetalleIVA detalleAct = 
						new DetalleIVA(
								producto.getPorcentajeIVA(), 
								detalle.getBaseImponible().add(producto.getBaseImponible()), 
								detalle.getImporteIVA().add(producto.getImporteIVA())
								);
				tablaDetallesIVA.put(producto.getPorcentajeIVA(), detalleAct);
			} else {
				tablaDetallesIVA.put(
						producto.getPorcentajeIVA(), 
						new DetalleIVA(
								producto.getPorcentajeIVA(), 
								producto.getBaseImponible(), 
								producto.getImporteIVA()));
			}
		}
		
		for (DetalleIVA cadaDetalleIVA : tablaDetallesIVA.values()) {
			this.detallesIVA.add(cadaDetalleIVA);
		}
		
	}

	/**
	 * @return the numeroDeFactura
	 */
	public Integer getNumeroDeFactura() {
		return numeroDeFactura;
	}



	/**
	 * @param numeroDeFactura the numeroDeFactura to set
	 */
	public void setNumeroDeFactura(Integer numeroDeFactura) {
		this.numeroDeFactura = numeroDeFactura;
	}



	/**
	 * @return the fechaFactura
	 */
	public Date getFechaFactura() {
		return fechaFactura;
	}



	/**
	 * @param fechaFactura the fechaFactura to set
	 */
	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}



	/**
	 * @return the fechaProvistaDeEntrega
	 */
	public Date getFechaProvistaDeEntrega() {
		return fechaProvistaDeEntrega;
	}



	/**
	 * @param fechaProvistaDeEntrega the fechaProvistaDeEntrega to set
	 */
	public void setFechaProvistaDeEntrega(Date fechaProvistaDeEntrega) {
		this.fechaProvistaDeEntrega = fechaProvistaDeEntrega;
	}



	/**
	 * @return the receptorFactura
	 */
	public Receptor getReceptorFactura() {
		return receptorFactura;
	}



	/**
	 * @param receptorFactura the receptorFactura to set
	 */
	public void setReceptorFactura(Receptor receptorFactura) {
		this.receptorFactura = receptorFactura;
	}



	/**
	 * @return the receptorMercancia
	 */
	public Receptor getReceptorMercancia() {
		return receptorMercancia;
	}



	/**
	 * @param receptorMercancia the receptorMercancia to set
	 */
	public void setReceptorMercancia(Receptor receptorMercancia) {
		this.receptorMercancia = receptorMercancia;
	}



	/**
	 * @return the productos
	 */
	public List<Producto> getProductos() {
		return productos;
	}



	/**
	 * @param productos the productos to set
	 */
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}



	/**
	 * @return the detallesIVA
	 */
	public List<DetalleIVA> getDetallesIVA() {
		return detallesIVA;
	}



	/**
	 * @param detallesIVA the detallesIVA to set
	 */
	public void setDetallesIVA(List<DetalleIVA> detallesIVA) {
		this.detallesIVA = detallesIVA;
	}



	/**
	 * @return the importeIVA
	 */
	public BigDecimal getImporteIVA() {
		return importeIVA;
	}



	/**
	 * @param importeIVA the importeIVA to set
	 */
	public void setImporteIVA(BigDecimal importeIVA) {
		this.importeIVA = importeIVA;
	}



	/**
	 * @return the importeTotal
	 */
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}



	/**
	 * @param importeTotal the importeTotal to set
	 */
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}



	/**
	 * @return the comentarios
	 */
	public String getComentarios() {
		return comentarios;
	}



	/**
	 * @param comentarios the comentarios to set
	 */
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}



	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
