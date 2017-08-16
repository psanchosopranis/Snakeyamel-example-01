package es.pssstests.ejemplo.ejecutables;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.apache.commons.io.IOUtils;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import es.pssstests.ejemplo.componentes.Producto;
import es.pssstests.ejemplo.componentes.Producto;

public class Ejemplo02SerializacionConstruccionProducto {

	public static void main(String[] args) {
		Yaml yaml = null;
		
		try {
			
			String[] descripcion = new String[] {
					"Vaso de cristal transparente de alta resistencia a golpes y cambios bruscos de temperatura.",
					"Válido para lavavajillas.",
					"Disponible en color transparente y azul translúcido." 
			};
					
			Producto producto = new Producto(
					"dg-161034_640", 
					"Vaso de Agua boca ancha terminación recta", 
					2, 
					"PZA", 
					new BigDecimal("1.24"), 
					new BigDecimal("16.00"), 
					"/imagenes/RectanguloRojo.png",
					descripcion);

			
			System.out.println("\n===================================================");
			System.out.println("Un ejemplo de Objeto 'Producto':");
			System.out.println("===================================================\n");
			System.out.println(producto.toString());

			yaml = new Yaml();
			String productoAsYamlString = yaml.dump(producto);
			
			System.out.println("\n===================================================");
			System.out.println("Objeto Producto serializado como String YAML:");		
			System.out.println("===================================================\n");			
			System.out.println(productoAsYamlString);
			System.out.println("Nota: Puede observarse:");
			System.out.println("\t1) Serializacion de elementos con valor binario en Base64");
			System.out.println("\t2) Serialización transparente de Arrays y tipos 'complejos' como BigDecimal");
			System.out.println("\t3) Snakeyaml ha optado por la forma 'Estandar' (BLOCK-Style) ");
			System.out.println("\t   combinada con la 'compacta' (FLOW) para el Array de 'descripcion'");			
			System.out.println("\t4) Snakeyaml ha optado por serializar los atributos por orden alfabético de su nombre");

			System.out.println("\n===================================================");
			System.out.println("Usamos ahora un archivo YAML representativo del");
			System.out.println("mismo objeto de Producto, en forma completamente");	
			System.out.println("'Estandar' (BLOCK) incluyendo la forma de representación");
			System.out.println("del Array del campo de Descripción: ");
			System.out.println("===================================================\n");	
			System.out.println("Notas:");
			System.out.println("\t1) El archivo Yaml recuperado como recurso se encuentra en:");
			System.out.println("\t   resources/ejemplos_yaml/Ejemplo_Producto_v1.yml");
			System.out.println("\t2) Siguiendo el estandar YAML su encoding es 'UTF-8'.");

			String archivoYAMLComoRecurso = "/ejemplos_yaml/Ejemplo_Producto_v1.yml";			
			yaml = new Yaml(new Constructor(Producto.class));
			producto = (Producto) yaml.load(ClassLoader.class.getResourceAsStream(archivoYAMLComoRecurso));
			
			System.out.println("\n===================================================");
			System.out.println("El ejemplo de Objeto 'Producto' resultante es:");		
			System.out.println("===================================================\n");	
			System.out.println(producto.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

}
