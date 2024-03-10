package pfg.svfa.proyecto.actualizaciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class UpdateChecker {
	//CODIGOS DE ERROR
	 private static final int ERROR_IO = -1;
	 private static final int ERROR_FORMATO_NUMERO = -2;
	 private static final int ERROR_ARCHIVO_INEXISTENTE = -3;

    public UpdateChecker() {
        // Constructor vacío si no necesitas inicializar nada
    }

    public static double obtenerNumeroActualizacion() {
    	/**
    	 * Este método intenta leer un número de versión desde un archivo llamado "version" en el directorio actual del programa.
    	 *
    	 * Funcionamiento:
    	 * 1. Crea un objeto File para representar el archivo "version".
    	 * 2. Verifica si el archivo existe y es un archivo (no un directorio) usando exists() y isFile().
    	 *    - Si el archivo no existe o es un directorio, imprime un mensaje de error y devuelve -3.
    	 * 3. Si el archivo existe, utiliza BufferedReader en combinación con FileReader para abrir y leer el archivo.
    	 *    - Lee el contenido del archivo línea por línea, acumulándolo en un StringBuilder.
    	 * 4. Convierte el contenido acumulado del StringBuilder a String, y luego intenta parsearlo a un entero (int) con Integer.parseInt().
    	 *    - Si el contenido no es un número válido, captura NumberFormatException, imprime un mensaje de error y devuelve -2.
    	 * 5. Devuelve el número de versión leído del archivo como un entero.
    	 *
    	 * Notas:
    	 * - Utiliza try-with-resources para asegurar que el BufferedReader se cierre automáticamente, liberando recursos.
    	 * - Captura IOException para manejar posibles errores de entrada/salida durante la lectura del archivo.
    	 * - Utiliza trim() en el contenido del archivo para eliminar espacios en blanco y saltos de línea antes de parsear.
    	 *
    	 * @return El número de versión leído del archivo "version" como un entero, o -2 si ocurre un error o el contenido no es un número válido.
    	 */

    	
    	String direccionArchivo = ConfiguracionGlobal.VERSION_FILE;
        File archivo = new File(direccionArchivo);
        String stringNumVersion = "";

        if (archivo.exists() && archivo.isFile()) { // Corrección aquí
            StringBuilder constructorContenido = new StringBuilder();

            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String currentLine;
                while ((currentLine = br.readLine()) != null) {
                    constructorContenido.append(currentLine).append("\n");
                }
                stringNumVersion = constructorContenido.toString().trim(); // Usar trim() para eliminar espacios y saltos de línea
            } catch (IOException e) {
                e.printStackTrace();
                return ERROR_IO; // Retorna un valor de error en caso de excepción
            }

            try {
                return Double.parseDouble(stringNumVersion); // Intenta convertir el String a double
            } catch (NumberFormatException e) {
                System.out.println("El contenido del archivo 'version' no es un número válido.");
                return ERROR_FORMATO_NUMERO; // Retorna un valor de error si no se puede parsear
            }
        } else {
            System.out.println("El archivo 'version' no existe o es un directorio");
            return ERROR_ARCHIVO_INEXISTENTE; // Retorna un valor de error si el archivo no existe o es un directorio
        }
    }
}

