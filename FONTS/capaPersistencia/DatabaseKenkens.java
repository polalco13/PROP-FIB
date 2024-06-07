package capaPersistencia;

import capaDomini.kenkens.Kenken;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseKenkens {

    /**
     * @brief Guarda un Kenken generat.
     * @param nomUsuari Nom de l'usuari.
     * @param k Kenken a guardar.
     * @post El Kenken s'ha guardat.
     */
    public static void saveKenkenGenerat(String nomUsuari, Kenken k) {
        // Find the user's directory
        File userDir = new File("capaPersistencia/Users", nomUsuari);
        if (!userDir.exists()) {
            return;
        }

        // Create the Kenkens directory inside the user's directory
        File kenkensDir = new File(userDir, "Kenkens");
        if (!kenkensDir.exists()) {
            kenkensDir.mkdirs();
        }

        // Create the Kenken file inside the Kenkens directory
        File kenkenFile = new File(kenkensDir, k.getId() + "-" + k.getNom() + ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(kenkenFile))) {
            writer.write(k.kenkenToString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @brief Guarda un Kenken.
     * @param nomUsuari Nom de l'usuari.
     * @param id Identificador del Kenken.
     * @param nom Nom del Kenken.
     * @param kenken Kenken a guardar.
     * @post El Kenken s'ha guardat.
     */
    public static void saveKenken(String nomUsuari, int id, String nom, String kenken) {
        // Find the user's directory
        File userDir = new File("capaPersistencia/Users", nomUsuari);
        if (!userDir.exists()) {
            return;
        }

        // Create the Kenkens directory inside the user's directory
        File kenkensDir = new File(userDir, "Kenkens");
        if (!kenkensDir.exists()) {
            kenkensDir.mkdirs();
        }

        // Create the Kenken file inside the Kenkens directory
        File kenkenFile = new File(kenkensDir, id + "-" + nom + ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(kenkenFile))) {
            writer.write(kenken);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @brief Carrega un Kenken.
     * @param nomUsuari Nom de l'usuari.
     * @param id Identificador del Kenken.
     * @return El Kenken carregat.
     */
    public static File loadKenken(String nomUsuari, int id) {
        // Find the user's directory
        File userDir = new File("capaPersistencia/Users", nomUsuari);
        if (!userDir.exists()) {
            return null;
        }

        // Find the Kenkens directory inside the user's directory
        File kenkensDir = new File(userDir, "Kenkens");
        if (!kenkensDir.exists()) {
            return null;
        }

        // Find the Kenken file inside the Kenkens directory
        File[] listOfFiles = kenkensDir.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().startsWith(id + "-")) {
                    return file;
                }
            }
        }
        return null;
    }

    /**
     * @brief Obte els Kenkens d'un usuari.
     * @param nombreUsuarioLoggeado Nom de l'usuari.
     * @return Llista d'identificadors dels Kenkens.
     */
    public static List<Integer> getKenkens(String nombreUsuarioLoggeado) {
        // Find the user's directory
        File userDir = new File("capaPersistencia/Users", nombreUsuarioLoggeado);
        if (!userDir.exists()) {
            return null;
        }

        // Find the Kenkens directory inside the user's directory
        File kenkensDir = new File(userDir, "Kenkens");
        if (!kenkensDir.exists()) {
            return null;
        }

        // Get the list of Kenken ids
        File[] listOfFiles = kenkensDir.listFiles();
        List<Integer> kenkenIds = new ArrayList<>();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    int dashIndex = fileName.indexOf('-');
                    if (dashIndex != -1) {
                        String idString = fileName.substring(0, dashIndex);
                        try {
                            int id = Integer.parseInt(idString);
                            kenkenIds.add(id);
                        } catch (NumberFormatException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        }
        return kenkenIds;
    }

    /**
     * @brief Obte els Kenkens d'un usuari.
     * @param username Nom de l'usuari.
     * @return Mapa amb els Kenkens.
     */
    public static Map<String,File> getDatabaseOfKenkens(String username) {
        File userFolder = new File("capaPersistencia/Users", username);
        if (!userFolder.exists()) {
            return null;
        }

        File kenkenFolder = new File(userFolder, "Kenkens");
        if (!kenkenFolder.exists()) {
            return null;
        }

        File[] listOfKenkens = kenkenFolder.listFiles(); // Tots el kenken.txt
        Map<String, File> kenkens = new HashMap<>();

        if (listOfKenkens != null) {
            for (File kenken : listOfKenkens) {
                if (kenken.isFile()) {
                    String nom = kenken.getName();
                    kenkens.put(nom, kenken);
                }
            }
            return kenkens;
        }
        return null;
        //Si devolvemos null es que no hay o carpetas o kenkens o
        // no noy kenkens dentro de la carpeta o no existe el usurio,
    }

    /**
     * @brief Elimina un Kenken.
     * @param username Nom de l'usuari.
     * @param id Identificador del Kenken.
     */
    public static void deleteKenken(String username, int id) {
        // Find the user's directory
        File userDir = new File("capaPersistencia/Users", username);
        if (!userDir.exists()) {
            return;
        }

        // Find the Kenkens directory inside the user's directory
        File kenkensDir = new File(userDir, "Kenkens");
        if (!kenkensDir.exists()) {
            return;
        }

        // Find the Kenken file inside the Kenkens directory
        File[] listOfFiles = kenkensDir.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().startsWith(id + "-")) {
                    file.delete();
                    return;
                }
            }
        }
    }
}
