package pfg.svfa.proyecto.actualizaciones;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class CopyClass {
	/*use Nio
	 * para copiar un archivo de origen a destino
	 *
	 * @params direccionArchivo archivo origen
	 * @params destinoDireccion archivo destino
	 *
	 * String direccionArchivo = "/path/to/source/file.txt"
	 * String destinoDireccion = "/path/to/destination/file.txt"
	 * 		CopyClass.copyFile(origen, destino);
	 */
	public static void copyFile(String direccionArchivo, String destinoDireccion) {
		try {
			Path archivo = Path.of(direccionArchivo);
			Path destino = Path.of(destinoDireccion);


			// Copiar el archivo con la opción de reemplazar el archivo destino si existiese
			Files.copy(archivo, destino, StandardCopyOption.REPLACE_EXISTING);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}


}
