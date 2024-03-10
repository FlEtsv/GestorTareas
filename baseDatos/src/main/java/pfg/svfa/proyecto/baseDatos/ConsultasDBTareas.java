package pfg.svfa.proyecto.baseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ConsultasDBTareas {
	
	public static void crearTarea(int usuarioId, String titulo, String descripcion, String fechaLimite) {
	    String sql = "INSERT INTO tareas (usuario_id, titulo, descripcion, fecha_limite) VALUES (?, ?, ?, ?);";

	    try (Connection conn = ConexionDB.conectar()) {
	        // Desactivar auto-commit para manejar la transacción manualmente
	        conn.setAutoCommit(false);

	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, usuarioId);
	            pstmt.setString(2, titulo);
	            pstmt.setString(3, descripcion);
	            pstmt.setString(4, fechaLimite); // Asumiendo que fechaLimite ya está en formato adecuado
	            pstmt.executeUpdate();
	            
	            // Si no hay errores, commit la transacción
	            conn.commit();
	            System.out.println("Tarea creada exitosamente.");
	        } catch (SQLException e) {
	            // En caso de error, hacer rollback de la transacción
	            if (conn != null) {
	                try {
	                    conn.rollback();
	                } catch (SQLException ex) {
	                    System.out.println(ex.getMessage());
	                }
	            }
	            System.out.println(e.getMessage());
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	public void verTareas(int usuarioId) throws SQLException {
	    String sql = "SELECT * FROM tareas WHERE usuario_id = ?;";
	    try(Connection conn = ConexionDB.conectar()){
	    			    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		        pstmt.setInt(1, usuarioId);
		        ResultSet rs = pstmt.executeQuery();
		        while (rs.next()) {
		            // Procesa cada tarea
		            rs.getString("titulo");
		            rs.getString("descripcion");
		            rs.getString("fecha_limite");
		            // Añade aquí más campos según sea necesario
		        }
	    	}

	    }catch(SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	public void modificarTarea( int usuarioId, int tareaId, String nuevoTitulo, String nuevaDescripcion, String nuevoEstado, String nuevaFechaLimite) throws SQLException {
	    String sql = "UPDATE tareas SET titulo = ?, descripcion = ?, estado = ?, fecha_limite = ? WHERE id = ? AND usuario_id = ?;";
	    try(Connection conn = ConexionDB.conectar()){
	    	conn.setAutoCommit(false);
	    		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		        pstmt.setString(1, nuevoTitulo);
		        pstmt.setString(2, nuevaDescripcion);
		        pstmt.setString(3, nuevoEstado);
		        pstmt.setString(4, nuevaFechaLimite);
		        pstmt.setInt(5, tareaId);
		        pstmt.setInt(6, usuarioId);
		        pstmt.executeUpdate();
		        
		        conn.commit();
		        System.out.println("Tarea modificada exítosamente.");
		    }catch(SQLException e) {
		    	if(conn != null) {
		    		try {
		    			conn.rollback();
		    		}catch(SQLException ex){
		    			System.out.println(ex.getMessage());
		    		}
		    	}
		    	System.out.println(e.getMessage());
		    }
	    }catch(SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	    
	}
	public void eliminarTarea( int usuarioId, int tareaId) throws SQLException {
	    String sql = "DELETE FROM tareas WHERE id = ? AND usuario_id = ?;";
	    try(Connection conn = ConexionDB.conectar()){
	    	conn.setAutoCommit(false);
		    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		        pstmt.setInt(1, tareaId);
		        pstmt.setInt(2, usuarioId);
		        pstmt.executeUpdate();
		        
		        conn.commit();
		        System.out.println("Tarea eliminada correctamente.");
		    }catch(SQLException e) {
		    	if(conn != null) {
		    		try {
		    			conn.rollback();
		    		}catch(SQLException ex) {
		    			System.out.println(ex.getMessage());
		    		}
		    	}
		    	System.out.println(e.getMessage());
		    }
	    }catch(SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	}

}	
