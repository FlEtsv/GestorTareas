package pfg.svfa.proyecto.baseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class InicioSesion {

    

    /**
     * Intenta iniciar sesión con el nombre de usuario o correo electrónico y contraseña proporcionados.
     *
     * @param usernameOrEmail Nombre de usuario o correo electrónico del usuario que intenta iniciar sesión.
     * @param password Contraseña proporcionada por el usuario.
     * @return id si el inicio es exitoso, en caso de fallar devuelve -1
     */
	public int iniciarSesion(String usernameOrEmail, String password) {
	    // Sentencia SQL para buscar el usuario por nombre de usuario o correo electrónico y obtener su id
	    String sql = "SELECT id, password_hash FROM usuarios WHERE username = ? OR email = ?";

	    try (Connection conn = ConexionDB.conectar();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        // Establece los parámetros de la consulta
	        pstmt.setString(1, usernameOrEmail);
	        pstmt.setString(2, usernameOrEmail);

	        // Ejecuta la consulta
	        try (ResultSet rs = pstmt.executeQuery()) {
	            // Si se encuentra el usuario, verifica la contraseña
	            if (rs.next()) {
	                String storedHash = rs.getString("password_hash");
	                // Verifica si la contraseña coincide
	                boolean passwordMatch = HashPassword.verifyPassword(password, storedHash);
	                if (passwordMatch) {
	                    // Devuelve el id del usuario si la contraseña es correcta
	                    return rs.getInt("id");
	                }
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al iniciar sesión: " + e.getMessage());
	    }
	    // Devuelve -1 si el usuario no se encuentra o si la contraseña no coincide
	    return -1;
	}/*
	public static void main(String [] args) {
		InicioSesion inicio = new InicioSesion();
		int variableInicio = inicio.iniciarSesion("Santi", "7007007");
		System.out.println(variableInicio);
	}*/
}
