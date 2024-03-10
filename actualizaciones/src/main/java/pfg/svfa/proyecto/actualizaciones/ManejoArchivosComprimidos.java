package pfg.svfa.proyecto.actualizaciones;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ManejoArchivosComprimidos {
	/*
	 *     public static void main(String[] args) {
        String direccionArchivoZip = "/path/to/your/file.zip";
        String descompresionCarpeta = "/path/to/extract/to";
        ManejoArchivosComprimidos unzipper = new ManejoArchivosComprimidos();
        try {
            unzipper.unzip(direccionArchivoZip, descompresionCarpeta);
            System.out.println("Descompresión completada.");
        } catch (IOException e) {
            System.out.println("Error al descomprimir el archivo: " + e.getMessage());
        }
    }
}
	 */

	/**
	 * Descomprime el contenido de un archivo Zip a un directorio especificado
	 * @param direccionArchivoZip es la ruta del archivo que se va a descomprimir
	 * @param descompresionCarpeta donde se extraera el contenido de el archivo zip
	 */
	public void descomprimir(String direccionArchivoZip, String descompresionCarpeta) throws IOException{
		File descompreCarpeta = new File(descompresionCarpeta);
		if (!descompreCarpeta.exists()) {
			descompreCarpeta.mkdir();
		}

		try(ZipInputStream zipIn = new ZipInputStream(new FileInputStream(direccionArchivoZip))){
			ZipEntry entrada = zipIn.getNextEntry();
			//Itera sobre las entradas del archivo ZIP
			while(entrada != null) {
				String direccionArchivo = descompresionCarpeta + File.separator + entrada.getName();
				if(!entrada.isDirectory()) {
					// Si no es un directorio, extrae el archivo
					extraerArchivos(zipIn, direccionArchivo);
				}else {
					//Si es un directorio lo crea
					File dir = new File(direccionArchivo);
					dir.mkdir();
				}
				zipIn.closeEntry();
				entrada = zipIn.getNextEntry();

			}
		}
	}



	/**
	 * extrae un archivo del flujo ZIP a la ruta especificada.
	 * @paramm zipIn flujo del archivo ZIP
	 * @param direccionArchivo ruta del archivo a extraer
	 */
	private void extraerArchivos(ZipInputStream zipIn, String direccionArchivo) throws IOException {
		try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(direccionArchivo))){
			byte[] bytesIn = new byte[4096];
			int read = 0;
			while((read = zipIn.read(bytesIn)) != -1) {
				bos.write(bytesIn, 0, read);
			}
		}
	}
}
