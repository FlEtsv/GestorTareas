package pfg.svfa.proyecto.actualizaciones;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class DownloadUpdate {

    public static boolean DownloadFiles( String rutaGuardado) {
    	String urlArchivo = "https://api.github.com/repos/usuario/repositorio/releases/latest";
        try {
            // Mostrar atributos de la carpeta antes de la descarga
            try {
                BasicFileAttributes attrs = Files.readAttributes(Paths.get(rutaGuardado).getParent(), BasicFileAttributes.class);
                System.out.println("Atributos de la carpeta antes de la descarga:");
                System.out.println("Es directorio: " + attrs.isDirectory());
                System.out.println("Tamaño: " + attrs.size());
                System.out.println("Fecha de creación: " + attrs.creationTime());
                System.out.println("Último acceso: " + attrs.lastAccessTime());
                System.out.println("Última modificación: " + attrs.lastModifiedTime());
            } catch (IOException e) {
                System.out.println("Error al obtener atributos de la carpeta: " + e.getMessage());
            }

            System.out.println("Iniciando descarga...");
            URL url = new URL(urlArchivo);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            int codigoRespuesta = connection.getResponseCode();

            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
                try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
                     FileOutputStream fileOutputStream = new FileOutputStream(rutaGuardado)) {
                    byte dataBuffer[] = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                        fileOutputStream.write(dataBuffer, 0, bytesRead);
                    }
                    System.out.println("Archivo descargado con éxito en " + rutaGuardado);
                }
                return true;
            } else {
            	
                System.out.println("No se pudo descargar el archivo. Código de respuesta HTTP: " + codigoRespuesta);
                
            }
        } catch (IOException e) {
            System.out.println("Error al descargar el archivo: " + e.getMessage());
            e.printStackTrace();
        }
		return false;
    }
}
