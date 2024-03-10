package pfg.svfa.proyecto.baseDatos;

import org.mindrot.jbcrypt.BCrypt;

public class HashPassword {
    
    // Genera el hash de una contraseña
    public static String hashPassword(String password_plaintext) {
        int workload = 12;
        String salt = BCrypt.gensalt(workload);
        return BCrypt.hashpw(password_plaintext, salt);
    }

    // Verifica una contraseña con un hash dado
    public static boolean verifyPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if (null == stored_hash || !stored_hash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Hash inválido proporcionado para comparación");

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return password_verified;
    }

}
/*
 * public class TestPasswordHashing {
    public static void main(String[] args) {
        String originalPassword = "miSuperSecretaContraseña!";
        String generatedSecuredPasswordHash = HashPassword.hashPassword(originalPassword);
        System.out.println("Hash de la contraseña: " + generatedSecuredPasswordHash);
        
        boolean matched = HashPassword.verifyPassword("miSuperSecretaContraseña!", generatedSecuredPasswordHash);
        System.out.println("Contraseña verificada: " + matched);
        
        matched = HashPassword.verifyPassword("Ejempo_contraseña_Erronea", generatedSecuredPasswordHash);
        System.out.println("Contraseña verificada: " + matched);
    }
}

 * */
 