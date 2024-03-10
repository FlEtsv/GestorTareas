package pfg.svfa.proyecto.actualizaciones;
import java.io.IOException;



public class DescargaDescomprimir {
	public void descargar(String folderPath) {
		String updateFolder = ConfiguracionGlobal.UPDATE_FOLDER;
		String tempFolder = ConfiguracionGlobal.TEMP_FOLDER;
		boolean descargado = DownloadUpdate.DownloadFiles( updateFolder);
		if(descargado == true) {
			ManejoArchivosComprimidos descomprimir = new ManejoArchivosComprimidos();
			try {
				descomprimir.descomprimir(folderPath, tempFolder);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			
			System.out.println("la descarga no se ha completado adecuadamente");
		}
		
	}
}
