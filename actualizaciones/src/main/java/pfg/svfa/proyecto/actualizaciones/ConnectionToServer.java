package pfg.svfa.proyecto.actualizaciones;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class ConnectionToServer {
	
	

	//System.out.println("Versión más reciente: " + versionServidor);


    public static double ConnectToServer() {
        double latestVersion = 0.0; // Inicializa la última versión con un valor predeterminado
        String urlString =  "https://api.github.com/repos/usuario/repositorio/releases/latest";
        try {
            // Nota: La URL ahora debería ser la URL de la API de GitHub para obtener releases de un repositorio.
            // Ejemplo: https://api.github.com/repos/{owner}/{repo}/releases/latest
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Accept", "application/vnd.github.v3+json"); // Especifica la versión de la API

            int responseCode = connection.getResponseCode();
            System.out.println("Respuesta de servidor: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parsea la respuesta JSON para obtener la última versión
                JSONObject releaseInfo = new JSONObject(response.toString());
                String tagName = releaseInfo.getString("tag_name"); // Obtiene el tag_name del último release
                
                System.out.println("Última versión en GitHub: " + tagName);
                
                // Intenta convertir el tag_name a double (Asume que el tag_name es numérico)
                try {
                    latestVersion = Double.parseDouble(tagName.replace("v", "")); // Remueve la 'v' si está presente
                } catch (NumberFormatException e) {
                    System.err.println("No se pudo parsear la última versión.");
                    e.printStackTrace();
                }
                
            } else {
                System.out.println("No se pudo conectar al servidor de actualizaciones");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return latestVersion; // Retorna la última versión obtenida de GitHub o 0.0 si hubo un error
    }
}
