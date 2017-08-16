package es.pssstests.ejemplo.componentes;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Producto {

	protected String referencia;
	protected String denominacion;
	protected int cantidad;
	protected String unidadMedida;
	protected BigDecimal precioUnitario;
	protected BigDecimal porcentajeIVA; 
	protected BigDecimal baseImponible; 
	protected BigDecimal importeIVA; 
	protected BigDecimal importe; 
	protected byte[] imagen;
	protected String[] descripcion;
	
	public Producto() {
	}
	
	/**
	 * Constructor que permite indicar si se desean
	 * valores para los atributos nulos o por el
	 * contrario utilizar "Zero Values" segun el
	 * tipo.
	 * Por ejemplo el 'zero value' es:
	 * 		'0' para tipos numericos,
	 *      'false' para tipos  booleanos, y
	 *      "" (the empty string) para strings.
	 * @param useZeroValues 
	 * 
	 */
	public Producto(boolean useZeroValues) {
		if (useZeroValues) {
			this.referencia = "";
			this.denominacion = "";
			this.cantidad = 0;
			this.unidadMedida = "";
			this.precioUnitario = new BigDecimal(0);
			this.porcentajeIVA = new BigDecimal(0);
			this.importe = new BigDecimal(0);
			this.baseImponible = new BigDecimal(0);
			this.importeIVA = new BigDecimal(0);
			this.imagen = obtenerImagenComoRecurso("/imagenes/NoDisponible.png");
			this.descripcion = new String[] {""};
		} else {
			this.referencia = null;
			this.denominacion = null;
			this.cantidad = 0;
			this.unidadMedida = null;
			this.precioUnitario = null;
			this.porcentajeIVA = null;
			this.importe = null;
			this.baseImponible = null;
			this.importeIVA = null;
			this.imagen = null;
			this.descripcion = null;
		}
	}

	/**
	 * @param referencia
	 * @param denominacion
	 * @param cantidad
	 * @param unidadMedida
	 * @param precioUnitario
	 * @param porcentajeIVA
	 * @param imagen
	 * @param descripcion
	 */
	public Producto(String referencia, String denominacion, int cantidad, String unidadMedida,
			BigDecimal precioUnitario, BigDecimal porcentajeIVA, byte[] imagen, String[] descripcion) {
		this.referencia = referencia;
		this.denominacion = denominacion;
		this.cantidad = cantidad;
		this.unidadMedida = unidadMedida;
		this.precioUnitario = precioUnitario;
		this.porcentajeIVA = porcentajeIVA;
		this.calcularImportes(cantidad, precioUnitario, porcentajeIVA);
		this.imagen = imagen;
		this.descripcion = descripcion;
	}

	/**
	 * @param referencia
	 * @param denominacion
	 * @param cantidad
	 * @param unidadMedida
	 * @param precioUnitario
	 * @param porcentajeIVA
	 * @param imagenComoRecurso
	 * @param descripcion
	 */
	public Producto(String referencia, String denominacion, int cantidad, String unidadMedida,
			BigDecimal precioUnitario, BigDecimal porcentajeIVA, String imagenComoRecurso, String[] descripcion) {
		this.referencia = referencia;
		this.denominacion = denominacion;
		this.cantidad = cantidad;
		this.unidadMedida = unidadMedida;
		this.precioUnitario = precioUnitario;
		this.porcentajeIVA = porcentajeIVA;
		this.calcularImportes(cantidad, precioUnitario, porcentajeIVA);
		this.imagen = obtenerImagenComoRecurso(imagenComoRecurso);
		this.descripcion = descripcion;
	}
	
	
	
	/**
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * @param referencia the referencia to set
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * @return the denominacion
	 */
	public String getDenominacion() {
		return denominacion;
	}

	/**
	 * @param denominacion the denominacion to set
	 */
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the unidadMedida
	 */
	public String getUnidadMedida() {
		return unidadMedida;
	}

	/**
	 * @param unidadMedida the unidadMedida to set
	 */
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	/**
	 * @return the precioUnitario
	 */
	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	/**
	 * @param precioUnitario the precioUnitario to set
	 */
	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	/**
	 * @return the porcentajeIVA
	 */
	public BigDecimal getPorcentajeIVA() {
		return porcentajeIVA;
	}

	/**
	 * @param porcentajeIVA the porcentajeIVA to set
	 */
	public void setPorcentajeIVA(BigDecimal porcentajeIVA) {
		this.porcentajeIVA = porcentajeIVA;
	}

	/**
	 * @return the baseImponible
	 */
	public BigDecimal getBaseImponible() {
		return baseImponible;
	}

	/**
	 * @param baseImponible the baseImponible to set
	 */
	public void setBaseImponible(BigDecimal baseImponible) {
		this.baseImponible = baseImponible;
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
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * @param importe the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * @return the imagen
	 */
	public byte[] getImagen() {
		return imagen;
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	/**
	 * @return the descripcion
	 */
	public String[] getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String[] descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	private static byte[] obtenerImagenComoRecurso(String imagenComoRecurso ) {
		InputStream is = ClassLoader.class.getResourceAsStream(imagenComoRecurso);
		try {
			return IOUtils.toByteArray(is);
		} catch (IOException e) {
			return null;
		}
	}
	
	private void calcularImportes(
			int cantidad,
			BigDecimal precioUnitario,
			BigDecimal porcentajeIVA ) {
		
		BigDecimal porcentajeIVAreal = this.porcentajeIVA.divide(new BigDecimal(100)).setScale(2, RoundingMode.CEILING);
		this.baseImponible = new BigDecimal(this.cantidad).multiply(this.precioUnitario).setScale(2, RoundingMode.CEILING);	
		this.importeIVA = this.baseImponible.multiply(porcentajeIVAreal).setScale(2, RoundingMode.CEILING);
		this.importe = this.baseImponible.add(this.importeIVA).setScale(2, RoundingMode.CEILING);		
	}
}
