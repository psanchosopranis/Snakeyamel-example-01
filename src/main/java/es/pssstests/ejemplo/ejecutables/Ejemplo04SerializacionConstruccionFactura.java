package es.pssstests.ejemplo.ejecutables;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import es.pssstests.ejemplo.agregados.Factura;
import es.pssstests.ejemplo.componentes.Direccion;
import es.pssstests.ejemplo.componentes.PersonaFisicaOJuridica;
import es.pssstests.ejemplo.componentes.Producto;
import es.pssstests.ejemplo.componentes.Receptor;

public class Ejemplo04SerializacionConstruccionFactura {

	
	public static void main(String[] args) {
		Yaml yaml = null;
		
		try {
			
			Factura factura = construyeEjemploDeFactura();
			
			System.out.println("\n===================================================");
			System.out.println("Un ejemplo de Objeto 'Factura':");
			System.out.println("===================================================\n");
			System.out.println(factura.toString());

			yaml = new Yaml();
			String facturaAsYamlString = yaml.dump(factura);
			
			System.out.println("\n===================================================");
			System.out.println("Serializado como String YAML:");		
			System.out.println("===================================================\n");			
			System.out.println(facturaAsYamlString);
			System.out.println("Nota: Puede observarse:");
			System.out.println("\t1) Serializacion de elementos con valor 'null' (ver 'direccion')");
			System.out.println("\t2) Serialización transparente de tipos complejos como:");
			System.out.println("\t   'enum' para Persona Física o Jurídica");
			System.out.println("\t   'BigDecimal' para Importes");
			System.out.println("\t3) Serialización transparente de Estructuras (y su anidación) como:");
			System.out.println("\t   'Arrays' (lineas de Descripción de Artículos)");
			System.out.println("\t   'Listas' (Productos, Detalles de Importes, ...)");
			System.out.println("\t4) Snakeyaml serializa objetos binarios (Ejemplo imágenes) en Base64");
			System.out.println("\t5) Snakeyaml combina a conveniencia la forma 'Estandar' con la 'compacta' de YAML.");
			System.out.println("\t6) Snakeyaml saca partido del uso de Referencias de Objetos. Por ejemplo:");
			System.out.println("\t   La Dirección del Receptor de Facturación y de Entrega es la misma.");
			System.out.println("\t7) Snakeyaml ha optado por serializar los atributos por orden alfabético de su nombre");

			System.out.println("\n===================================================");
			System.out.println("Usamos ahora un archivo YAML representativo del");
			System.out.println("mismo objeto de Factura, en forma completamente");	
			System.out.println("'Estandar'(BLOCK Style) incluyendo la forma de ");
			System.out.println("representación de Listas y Arrays: ");
			System.out.println("===================================================\n");	
			System.out.println("Notas:");
			System.out.println("\t1) El archivo Yaml recuperado como recurso se encuentra en:");
			System.out.println("\t   resources/ejemplos_yaml/Ejemplo_Factura_v2.yml");
			System.out.println("\t2) Siguiendo el estandar YAML su encoding es 'UTF-8'.");

			String archivoYAMLComoRecurso = "/ejemplos_yaml/Ejemplo_Factura_v2.yml";			
			yaml = new Yaml(new Constructor(Factura.class));
			factura = (Factura) yaml.load(ClassLoader.class.getResourceAsStream(archivoYAMLComoRecurso));
			
			System.out.println("\n===================================================");
			System.out.println("El ejemplo de Objeto 'Factura' resultante es:");		
			System.out.println("===================================================\n");	
			System.out.println(factura.toString());

			System.out.println("\n===================================================");
			System.out.println("Utilizaremos ahora una forma de serialización YAML:");
			System.out.println("Con 'DumperOptions':");
			System.out.println(" - 'FlowStyle.FLOW'"); 
			System.out.println(" - 'setPrettyFlow(true)'");
			System.out.println("===================================================\n");			
			DumperOptions options = new DumperOptions();
			options.setDefaultFlowStyle(DumperOptions.FlowStyle.FLOW);
			options.setPrettyFlow(true);
			yaml = new Yaml(options);
			System.out.println(yaml.dump(factura));

			System.out.println("\n===================================================");
			System.out.println("Utilizaremos ahora una forma de serialización YAML:");
			System.out.println("Con 'DumperOptions':");
			System.out.println(" - 'FlowStyle.BLOCK'"); 
			System.out.println(" - 'setPrettyFlow(true)'");
			System.out.println(" - 'options.setIndent(4)'");
			System.out.println("===================================================\n");	
			options = new DumperOptions();
			options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
			options.setPrettyFlow(true);
			options.setIndent(4);
			yaml = new Yaml(options);
			System.out.println(yaml.dump(factura));
			
		} catch (org.yaml.snakeyaml.constructor.ConstructorException ex) {	
			System.out.println("\n*********** EXCEPCION org.yaml.snakeyaml.constructor.ConstructorException ******");
			if (ex.getMessage() != null) {
				System.out.println(ex.getMessage());
			} else {
				System.out.println("(Mensaje de error no disponible)");
			}
			System.out.println("\n********************************************************************************");
			System.exit(-2);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	
	private static Factura construyeEjemploDeFactura() {
		
		Direccion direccion = new Direccion();
		direccion.setLinea1("Martínez Villergas, 52. Edificio B - Planta 6");
		direccion.setLocalidad("MADRID");
		direccion.setCodigoPostal("28027");
		direccion.setProvincia("MADRID");
		direccion.setTelefono1("+34 91 556 0013");
		
		Receptor receptorFactura = new Receptor(
				"Juan Hernández Pérez-Tuñón", 
				PersonaFisicaOJuridica.F, 
				"22222222X", 
				direccion);
				
		Receptor receptorMercancia = new Receptor(
				"Recepcionista", 
				PersonaFisicaOJuridica.F, 
				"N/A", 
				direccion);
		
		List<Producto> productos = new ArrayList<Producto>();
		productos.add(
			new Producto(
					"dg-161034_640", 
					"Vaso de Agua boca ancha terminación recta", 
					6, 
					"PZA", 
					new BigDecimal("1.24"), 
					new BigDecimal("16.00"), 
					"/imagenes/VasoDeAgua.png",
					new String[] {
							"Vaso de cristal transparente de alta resistencia a golpes y cambios bruscos de temperatura.",
							"Válido para lavavajillas.",
							"Disponible en color transparente y azul translúcido." 
					}
				));
		
		productos.add(
				new Producto(
						"vv-161134_820", 
						"Vaso de Vino boca ancha copa media terminación abombada", 
						6, 
						"PZA", 
						new BigDecimal("2.30"), 
						new BigDecimal("16.00"), 
						"/imagenes/CopaDeVino.png",
						new String[] {
								"Elaborado con cristal de Bohemia con resistencia moderada a baja a golpes y cambios bruscos de temperatura.",
								"NO válido para lavavajillas.",
								"Disponible exclusivamente en color transparente.",
								"Alto brillo y elaboración artesanal."
						}
					));
		
		
		productos.add(
				new Producto(
						"BA02-211450_AGE", 
						"Botella de Agua marca EL FONTANAL DE TRUEBA", 
						2, 
						"PZA", 
						new BigDecimal("0.80"), 
						new BigDecimal("8.00"), 
						"/imagenes/BotellaDeAgua.png",
						new String[] {
								"Botella de Agua mineral de mineralizacón baja.",
								"Botella PET baja transferencia ftalatos.",
								"Marca Propia."
						}
					));
		
		String comentarios = 
			   "Comentario de prueba con el primer parrafo de LOREM IPSUM:"
			 + "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
			 + "\nNam fringilla, tortor at varius mattis, lectus nunc ornare nunc, quis pellentesque mi nisi sed est."
			 + "\nMauris ultrices, enim eu aliquet imperdiet, sem mauris eleifend turpis, vel tempus velit nisl quis neque."
			 + "\nDonec placerat malesuada ultrices."
			 + "\nNulla augue ligula, vulputate non nulla in, posuere vulputate urna."
			 + "\nAenean ac lacus eget dui euismod mattis."
			 + "\nMaecenas quis varius odio."
			 + "\nIn aliquet turpis in sodales ultricies.";
		
		Date ahora = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(ahora); 
		c.add(Calendar.DATE, 4);
		Date fechaEntrega = c.getTime();
		
		return new Factura(
				new Integer("01234579"),
				ahora,
				fechaEntrega,
				receptorFactura,
				receptorMercancia,
				productos,
				comentarios
				);
	}
	
}
