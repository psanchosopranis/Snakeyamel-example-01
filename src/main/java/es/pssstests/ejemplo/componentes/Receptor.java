package es.pssstests.ejemplo.componentes;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Receptor {

	protected String nombre;
	protected PersonaFisicaOJuridica pFisJur;
	protected String idFiscal;
	protected Direccion direccion;
	
	public Receptor() {
	}
	
	

	/**
	 * @param nombre
	 * @param pFisJur
	 * @param idFiscal
	 * @param direccion
	 */
	public Receptor(String nombre, PersonaFisicaOJuridica pFisJur, String idFiscal, Direccion direccion) {
		this.nombre = nombre;
		this.pFisJur = pFisJur;
		this.idFiscal = idFiscal;
		this.direccion = direccion;
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
	public Receptor(boolean useZeroValues) {
		if (useZeroValues) {
			this.nombre = "";
			this.pFisJur = PersonaFisicaOJuridica.F;
			this.idFiscal = "";
			this.direccion = new Direccion(true);
		} else {
			this.nombre = null;
			this.pFisJur = null;
			this.idFiscal = null;
			this.direccion = null;
		}
	}
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}



	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	/**
	 * @return the pFisJur
	 */
	public PersonaFisicaOJuridica getpFisJur() {
		return pFisJur;
	}



	/**
	 * @param pFisJur the pFisJur to set
	 */
	public void setpFisJur(PersonaFisicaOJuridica pFisJur) {
		this.pFisJur = pFisJur;
	}



	/**
	 * @return the idFiscal
	 */
	public String getIdFiscal() {
		return idFiscal;
	}



	/**
	 * @param idFiscal the idFiscal to set
	 */
	public void setIdFiscal(String idFiscal) {
		this.idFiscal = idFiscal;
	}



	/**
	 * @return the direccion
	 */
	public Direccion getDireccion() {
		return direccion;
	}



	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}



	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
