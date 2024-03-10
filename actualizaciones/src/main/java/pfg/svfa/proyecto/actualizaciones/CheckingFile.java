package pfg.svfa.proyecto.actualizaciones;
import java.io.File;
import java.io.IOException;


public class CheckingFile {

    private String[] documentosRequeridos= {
            "bin2/codigo.txt",
            "bin2/codigo2.txt",
            "lib/",
            "data/",
            "temp/",
            "updates/",
            "backups",
            "logs/",
            "version.txt"
        };
    public void chequearYCrearDocumentos() {	
        for (String docPath : documentosRequeridos) {
            File documento = new File(docPath);

            // Determina si la ruta es de un directorio o un archivo basado en si la ruta termina en "/"
            boolean esDirectorio = docPath.endsWith("/");

            // Si el documento no existe, intenta crearlo
            if (!documento.exists()) {
                System.out.println("El documento requerido no existe: " + docPath);
                boolean resultado = false;

                try {
                    // Si la ruta termina con "/", se asume que es un directorio
                    if (esDirectorio) {
                        resultado = documento.mkdirs();
                    } else {
                        // Si es un archivo, asegurarse de que el directorio padre exista primero
                        File directorioPadre = documento.getParentFile();
                        if (directorioPadre != null && !directorioPadre.exists()) {
                            directorioPadre.mkdirs();
                        }
                        resultado = documento.createNewFile();
                    }

                    System.out.println(resultado ? "Creado exitosamente: " + docPath
                                                  : "No se pudo crear: " + docPath);
                } catch (IOException e) {
                    System.out.println("Error al crear el documento: " + docPath);
                    e.printStackTrace();
                }
            } else {
                System.out.println("El documento ya existe: " + docPath);
            }
        }
    }
}


/*
    public static void main(String[] args) {
        String[] documentos = {
        	"logs/logs.txt",


        };

        CheckingFile chequeo = new CheckingFile(documentos);
        chequeo.chequearYCrearDocumentos();

    }

}
*/