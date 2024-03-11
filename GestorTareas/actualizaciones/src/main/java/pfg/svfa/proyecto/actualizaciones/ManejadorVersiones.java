package pfg.svfa.proyecto.actualizaciones;


import java.io.File;


public class ManejadorVersiones {
		
		public void  comparedVersion() {
			//iniciar a recuperar la version local
			double localVersion = UpdateChecker.obtenerNumeroActualizacion();
			 double versionServidor = ConnectionToServer.ConnectToServer();
			if(localVersion < versionServidor /*&& dialogoDescargaEvaluacion == true*/) {
				//dialogo de confirmacion
				DescargaDescomprimir operacionDD = new DescargaDescomprimir();
				String updateFolder = ConfiguracionGlobal.UPDATE_FOLDER;
				operacionDD.descargar(updateFolder + File.separator + versionServidor +  ".zip");
				
			}else {
				//dialogo no hay actualizaciones
			}
			
			
		}
	}