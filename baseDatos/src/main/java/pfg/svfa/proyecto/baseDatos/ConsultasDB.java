package pfg.svfa.proyecto.baseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class ConsultasDB {
    public void mostrarUsuarios() {
        String sql = "SELECT id, username, email, password_hash FROM usuarios";

        try (Connection conn = ConexionDB.conectar();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {
            
            // Bucle a través del conjunto de resultados y mostrar los usuarios
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getString("username") + "\t" +
                                   rs.getString("email")+ "\t" +
                                   rs.getString("password_hash"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /*public static void main(String [] args) {
    	ConsultasDB consultas = new ConsultasDB();
    	consultas.mostrarUsuarios();
    }*/
    public void eliminarUsuarios(String username, String email) {
        /**
         * Elimina usuarios basado en el nombre de usuario y el correo electrónico con manejo de transacciones.
         *
         * @param username El nombre de usuario del usuario a eliminar.
         * @param email El correo electrónico del usuario a eliminar.
         */
    	
        // Sentencia SQL para eliminar usuarios con condiciones específicas
        String sql = "DELETE FROM usuarios WHERE username = ? OR email = ?";

        // Conexión a la base de datos fuera del try para poder manejar transacciones
        Connection conn = null;
        try {
            // Abre la conexión
             conn = ConexionDB.conectar();
            // Desactiva el modo de confirmación automática para manejar la transacción manualmente
            conn.setAutoCommit(false);

            // Prepara la sentencia con la conexión
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Establece los parámetros
                pstmt.setString(1, username);
                pstmt.setString(2, email);

                // Ejecuta la sentencia de eliminación
                int affectedRows = pstmt.executeUpdate();
                System.out.println(affectedRows + " filas afectadas.");

                // Si todo va bien, confirma la transacción
                conn.commit();
            } catch (Exception e) {
                // Si hay errores, revierte la transacción
                if (conn != null) {
                    conn.rollback();
                }
                throw e; // Lanza de nuevo la excepción para manejo externo si es necesario
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar el usuario: " + e.getMessage());
        } finally {
            // Asegura que la conexión se cierre al final
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }
    /*
    public static void main(String [] args) {
    	DeleteDB borrar = new DeleteDB();
    	borrar.deleteUsuarios("Santi", null);
    }*/ 
    public void insertarUsuario(String username, String password, String email) {
        /**
         * Inserta un nuevo usuario en la base de datos con manejo de transacciones.
         *
         * @param username El nombre de usuario.
         * @param passwordHash El hash de la contraseña.
         * @param email El correo electrónico.
         */
    	
        // Sentencia SQL para insertar un nuevo usuario
        String sql = "INSERT INTO usuarios(username,password_hash,email) VALUES(?,?,?)";
        Connection conn = null;
        String passwordHash = HashPassword.hashPassword(password);
        try {
            // Obtiene la conexión y desactiva la confirmación automática
            conn = ConexionDB.conectar();
            conn.setAutoCommit(false); // Comienza la transacción

            // Prepara y ejecuta la sentencia SQL
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, passwordHash);
                pstmt.setString(3, email);
                pstmt.executeUpdate();
                System.out.println("Usuario insertado.");

                conn.commit(); // Confirma la transacción si todo es correcto
            } catch (SQLException e) {
                // Si hay un error, revierte los cambios
                if (conn != null) {
                    conn.rollback();
                }
                throw e; // Propaga la excepción
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // Asegúrate de cerrar la conexión
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }/*
    public static void main(String [] args) {
    	InsertarDB insertar = new InsertarDB();
    	insertar.insertarUsuario("Santi", "7007007", "santi@marinaalfaro.es");
    }*/
}
