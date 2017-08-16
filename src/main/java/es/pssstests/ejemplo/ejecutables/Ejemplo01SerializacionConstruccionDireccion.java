package es.pssstests.ejemplo.ejecutables;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import es.pssstests.ejemplo.componentes.Direccion;

public class Ejemplo01SerializacionConstruccionDireccion {

	public static void main(String[] args) {
		Yaml yaml = null;
		
		try {
			
			Direccion direccion = new Direccion();
			direccion.setLinea1("Martínez Villergas, 52. Edificio B - Planta 6");
			direccion.setLocalidad("MADRID");
			direccion.setCodigoPostal("28027");
			direccion.setProvincia("MADRID");
			direccion.setTelefono1("+34 91 556 0013");
			
			System.out.println("\n===================================================");
			System.out.println("Un ejemplo de Objeto 'Direccion':");
			System.out.println("===================================================\n");
			System.out.println(direccion.toString());

			yaml = new Yaml();
			String direccionAsYamlString = yaml.dump(direccion);
			
			System.out.println("\n===================================================");
			System.out.println("Serializado como String YAML:");		
			System.out.println("=============================================\n");			
			System.out.println(direccionAsYamlString);
			System.out.println("\nNota: Puede observarse:");
			System.out.println("\t1) Serializacion de elementos con valor 'null'");
			System.out.println("\t2) Snakeyaml ha optado por la forma 'compacta' (FLOW-Style)");

			System.out.println("\n===================================================");
			System.out.println("Usamos ahora un String YAML representativo del");
			System.out.println("mismo objeto de direccion, igualmente en forma");	
			System.out.println("'compacta' (FLOW) pero, esta vez, SIN informar los ");
			System.out.println("campos a los que corresponde un valor 'null': ");
			System.out.println("===================================================\n");	
			
			direccionAsYamlString =
					"%YAML 1.1"
					+ "\n---"
					+ "\n!!es.pssstests.ejemplo.componentes.Direccion {"
					+ "\nlinea1: 'Martínez Villergas, 52. Edificio B - Planta 6', "
					+ "\nlocalidad: MADRID, "
					+ "\ncodigoPostal: '28027', "
					+ "\nprovincia: MADRID,  "
					+ "\ntelefono1: '+34 91 556 0013'"
					+ "\n}";
			System.out.println(direccionAsYamlString);
						
			yaml = new Yaml(new Constructor(Direccion.class));
			direccion = (Direccion) yaml.load(direccionAsYamlString);
			
			System.out.println("\n===================================================");
			System.out.println("El ejemplo de Objeto 'Direccion' resultante es:");		
			System.out.println("=============================================\n");	
			System.out.println(direccion.toString());
			System.out.println("\nNota: Puede observarse cómo:");
			System.out.println(
					"Los 'tags' ausentes inicializan los elementos correspondientes"
					+ "\ncon valor 'null' al construir el objeto.");

			System.out.println("\n===================================================");
			System.out.println("Usamos ahora un String YAML representativo del");
			System.out.println("mismo objeto de direccion, esta vez en forma");	
			System.out.println("'estandar' (BLOCK) e igualmente SIN informar los ");
			System.out.println("campos a los que corresponde un valor 'null': ");
			System.out.println("===================================================\n");
			direccionAsYamlString =
					"%YAML 1.1"
					+ "\n---"
					+ "\n!!es.pssstests.ejemplo.componentes.Direccion"
					+ "\nlinea1: 'Martínez Villergas, 52. Edificio B - Planta 6'"
					+ "\nlocalidad: MADRID"
					+ "\ncodigoPostal: '28027'"
					+ "\nprovincia: MADRID"
					+ "\ntelefono1: '+34 91 556 0013'";
			System.out.println(direccionAsYamlString);
			
			yaml = new Yaml(new Constructor(Direccion.class));
			direccion = (Direccion) yaml.load(direccionAsYamlString);
			
			System.out.println("\n===================================================");
			System.out.println("El ejemplo de Objeto 'Direccion' resultante es:");		
			System.out.println("=============================================\n");	
			System.out.println(direccion.toString());
			System.out.println("\nNota: Puede observarse cómo:");
			System.out.println(
					"Los 'tags' ausentes inicializan los elementos correspondientes"
					+ "\ncon valor 'null' al construir el objeto.");
						
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}
	
}
	
	



