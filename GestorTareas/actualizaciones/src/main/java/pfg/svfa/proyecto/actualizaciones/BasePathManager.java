package pfg.svfa.proyecto.actualizaciones;

import java.io.File;

public class BasePathManager {
    // Variable para almacenar la ruta base del proyecto o de la aplicación
    private String basePath;

    public BasePathManager() {
        // Inicializa la ruta base con la ubicación actual desde donde se ejecuta la JVM
        // Esto es útil para construir rutas relativas dentro de la estructura del proyecto
        this.basePath = System.getProperty("user.dir");
    }

    // Método para obtener la ruta base del proyecto
    public String getBasePath() {
        return basePath;
    }

    // Método para construir una ruta completa a partir de una ruta relativa
    // Esto permite acceder a diferentes recursos del proyecto mediante rutas relativas
    public String buildPath(String relativePath) {
        return basePath + File.separator + relativePath;
    }

    // Método para obtener la ruta del directorio de descargas del sistema,
    // adaptándose al idioma o configuración específica del sistema operativo
    public String getDownloadsPath() {
        // Obtiene el nombre del sistema operativo
        String osName = System.getProperty("os.name").toLowerCase();
        // Variable para almacenar la ruta del directorio de descargas
        String downloadsPath;

        // Verifica si el sistema operativo es Windows
        if (osName.contains("win")) {
            // Asigna la ruta estándar del directorio de descargas en Windows
            downloadsPath = System.getProperty("user.home") + "\\Downloads";
        } else if (osName.contains("mac")) {
            // Asigna la ruta estándar del directorio de descargas en macOS
            downloadsPath = System.getProperty("user.home") + "/Downloads";
        } else {
            // Para otros sistemas operativos (e.g., Linux), asume una estructura similar a Unix
            downloadsPath = System.getProperty("user.home") + "/Downloads";
        }

        // Retorna la ruta del directorio de descargas
        return downloadsPath;
    }
}