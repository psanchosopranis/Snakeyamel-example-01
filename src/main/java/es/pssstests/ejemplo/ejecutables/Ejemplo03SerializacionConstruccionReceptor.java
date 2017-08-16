package es.pssstests.ejemplo.ejecutables;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import es.pssstests.ejemplo.componentes.Direccion;
import es.pssstests.ejemplo.componentes.PersonaFisicaOJuridica;
import es.pssstests.ejemplo.componentes.Receptor;

public class Ejemplo03SerializacionConstruccionReceptor {

	public static void main(String[] args) {
		Yaml yaml = null;
		
		try {
			
			Direccion direccion = new Direccion();
			direccion.setLinea1("Martínez Villergas, 52. Edificio B - Planta 6");
			direccion.setLocalidad("MADRID");
			direccion.setCodigoPostal("28027");
			direccion.setProvincia("MADRID");
			direccion.setTelefono1("+34 91 556 0013");

			Receptor receptor = new Receptor(
					"Juan Hernández Pérez-Tuñón", 
					PersonaFisicaOJuridica.F, 
					"22222222X", 
					direccion);
			
			System.out.println("\n===================================================");
			System.out.println("Un ejemplo de Objeto 'Receptor':");
			System.out.println("===================================================\n");
			System.out.println(receptor.toString());

			yaml = new Yaml();
			String receptorAsYamlString = yaml.dump(receptor);
			
			System.out.println("\n===================================================");
			System.out.println("Serializado como String YAML:");		
			System.out.println("===================================================\n");			
			System.out.println(receptorAsYamlString);
			System.out.println("Nota: Puede observarse:");
			System.out.println("\t1) Serializacion de elementos con valor 'null'");
			System.out.println("\t2) Serialización transparente del 'enum' Persona Física o Jurídica");
			System.out.println("\t3) Snakeyaml ha optado por la forma 'Estandar' (BLOCK Style) ");
			System.out.println("\t   combinada con la 'compacta' (FLOW) para el objeto de 'direccion'");
			System.out.println("\t4) Snakeyaml ha optado por serializar los atributos por orden alfabético de su nombre");


			System.out.println("\n===================================================");
			System.out.println("Usamos ahora un String YAML representativo del");
			System.out.println("mismo objeto de 'receptor', pero esta vez en forma");	
			System.out.println("'estandar' (BLOCK) y SIN informar los campos a los que ");
			System.out.println("corresponde un valor 'null': ");
			System.out.println("===================================================\n");	
			
			receptorAsYamlString =
					"%YAML 1.1"
					+ "\n---"
					+ "\n!!es.pssstests.ejemplo.componentes.Receptor"
					+ "\nnombre: Juan Hernández Pérez-Tuñón"
					+ "\npFisJur: F"
					+ "\nidFiscal: 22222222X"
					+ "\ndireccion:"
					+ "\n  linea1: 'Martínez Villergas, 52. Edificio B - Planta 6' "
					+ "\n  localidad: MADRID"
					+ "\n  codigoPostal: '28027'"
					+ "\n  provincia: MADRID"
					+ "\n  telefono1: +34 91 556 0013";
			System.out.println(receptorAsYamlString);
						
			yaml = new Yaml(new Constructor(Receptor.class));
			receptor = (Receptor) yaml.load(receptorAsYamlString);
			
			System.out.println("\n===================================================");
			System.out.println("El ejemplo de Objeto 'Receptor' resultante es:");		
			System.out.println("===================================================\n");	
			System.out.println(receptor.toString());
			System.out.println("\nNota: Puede observarse cómo:");
			System.out.println(
					"Los 'tags' ausentes inicializan los elementos correspondientes"
					+ "\ncon valor 'null' al construir el objeto.");

			System.out.println("\n===================================================");
			System.out.println("Veamos que ocurre cuando utilizamos un valor para");
			System.out.println("'Persona Física o Jurídica' como 'X' que no forma");
			System.out.println("parte de la enumeración 'F,J':");
			System.out.println("===================================================\n");	
			
			receptorAsYamlString =
					"%YAML 1.1"
					+ "\n---"
					+ "\n!!es.pssstests.ejemplo.componentes.Receptor"
					+ "\nnombre: Juan Hernández Pérez-Tuñón"
					+ "\npFisJur: X"
					+ "\nidFiscal: 22222222X"
					+ "\ndireccion:"
					+ "\n  linea1: 'Martínez Villergas, 52. Edificio B - Planta 6' "
					+ "\n  localidad: MADRID"
					+ "\n  codigoPostal: '28027'"
					+ "\n  provincia: MADRID"
					+ "\n  telefono1: +34 91 556 0013";
			System.out.println(receptorAsYamlString);
						
			yaml = new Yaml(new Constructor(Receptor.class));
			receptor = (Receptor) yaml.load(receptorAsYamlString);
			
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

}
