package pfg.svfa.proyecto.baseDatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:sqlite:gestor_tareas.db"; // Ajusta el nombre del archivo de tu base de datos

    public static Connection conectar() {
        Connection conn = null;
        try {
            // Crea una conexión a la base de datos
            conn = DriverManager.getConnection(URL);
            System.out.println("Conexión establecida.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
