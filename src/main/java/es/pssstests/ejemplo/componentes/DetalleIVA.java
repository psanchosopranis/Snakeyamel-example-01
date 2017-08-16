package es.pssstests.ejemplo.componentes;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DetalleIVA {
	
	protected BigDecimal porcentajeIVA; 
	protected BigDecimal baseImponible; 
	protected BigDecimal importeIVA; 
	
	public DetalleIVA() {
		
	}

	/**
	 * @param porcentajeIVA
	 * @param baseImponible
	 * @param importeIVA
	 */
	public DetalleIVA(BigDecimal porcentajeIVA, BigDecimal baseImponible, BigDecimal importeIVA) {
		this.porcentajeIVA = porcentajeIVA;
		this.baseImponible = baseImponible;
		this.importeIVA = importeIVA;
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
	public DetalleIVA(boolean useZeroValues) {
		if (useZeroValues) {
			this.porcentajeIVA = new BigDecimal(0);
			this.baseImponible = new BigDecimal(0);
			this.importeIVA = new BigDecimal(0);
		} else {
			this.porcentajeIVA = null;
			this.baseImponible = null;
			this.importeIVA = null;
		}
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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


