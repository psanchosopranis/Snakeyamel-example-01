package es.pssstests.ejemplo.componentes;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Direccion {

	protected String linea1;
	protected String linea2;
	protected String localidad;
	protected String codigoPostal;
	protected String provincia;
	protected String telefono1;
	protected String telefono2;
	protected String fax;
	
	public Direccion() {
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
	public Direccion(boolean useZeroValues) {
		if (useZeroValues) {
			this.linea1 = "";
			this.linea2 = "";
			this.localidad = "";
			this.codigoPostal = "";
			this.provincia = "";
			this.telefono1 = "";
			this.telefono2 = "";
			this.fax = "";
		} else {
			this.linea1 = null;
			this.linea2 = null;
			this.localidad = null;
			this.codigoPostal = null;
			this.provincia = null;
			this.telefono1 = null;
			this.telefono2 = null;
			this.fax = null;
		}
	}
	
	/**
	 * @param linea1
	 * @param linea2
	 * @param localidad
	 * @param codigoPostal
	 * @param provincia
	 * @param telefono1
	 * @param telefono2
	 * @param fax
	 */
	public Direccion(String linea1, String linea2, String localidad, String codigoPostal, String provincia,
			String telefono1, String telefono2, String fax) {
		this.linea1 = linea1;
		this.linea2 = linea2;
		this.localidad = localidad;
		this.codigoPostal = codigoPostal;
		this.provincia = provincia;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.fax = fax;
	}

	/**
	 * @return the linea1
	 */
	public String getLinea1() {
		return linea1;
	}

	/**
	 * @param linea1 the linea1 to set
	 */
	public void setLinea1(String linea1) {
		this.linea1 = linea1;
	}

	/**
	 * @return the linea2
	 */
	public String getLinea2() {
		return linea2;
	}

	/**
	 * @param linea2 the linea2 to set
	 */
	public void setLinea2(String linea2) {
		this.linea2 = linea2;
	}

	/**
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * @param localidad the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	/**
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * @param codigoPostal the codigoPostal to set
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return the telefono1
	 */
	public String getTelefono1() {
		return telefono1;
	}

	/**
	 * @param telefono1 the telefono1 to set
	 */
	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	/**
	 * @return the telefono2
	 */
	public String getTelefono2() {
		return telefono2;
	}

	/**
	 * @param telefono2 the telefono2 to set
	 */
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
