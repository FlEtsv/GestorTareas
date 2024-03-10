package pfg.svfa.proyecto.actualizaciones;



public class ConfiguracionGlobal {
    public static final String UPDATE_FOLDER;
    public static final String BACKUP_FOLDER;
    public static final String BIN_FOLDER;
    public static final String TEMP_FOLDER;
    public static final String LIB_FOLDER;
    public static final String VERSION_FILE;

    
    // Otras rutas como constantes estáticas

    static {
        BasePathManager basePathManager = new BasePathManager();
        UPDATE_FOLDER = basePathManager.buildPath("updates");
        BACKUP_FOLDER = basePathManager.buildPath("backups");
        BIN_FOLDER  = basePathManager.buildPath("bin");
        TEMP_FOLDER = basePathManager.buildPath("temp");
        LIB_FOLDER =  basePathManager.buildPath("lib");
        VERSION_FILE = basePathManager.buildPath("version");
        
  
    }
}