package capaPersistencia;

import capaDomini.estadistiques.UserStats;
import java.io.*;
import java.util.*;

public class DatabaseStats {
    private static final String STATS_DIR = "capaPersistencia/Users";

    /**
     * @brief Crea el directori d'estadistiques si no existeix
     * @post S'ha creat el directori d'estadistiques
     */
    public static void initializeDatabase() {
        File dir = new File(STATS_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * @brief Guarda les estadístiques d'un usuari
     * @param username Nom de l'usuari
     * @param stats Estadístiques de l'usuari
     * @post S'han guardat les estadístiques de l'usuari
     */
    public static void saveUserStats(String username, UserStats stats) {
        initializeDatabase();
        File userDir = new File(STATS_DIR, username);
        if (!userDir.exists()) {
            userDir.mkdirs();
        }
        File statsFile = new File(userDir, "estadisticas.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(statsFile))) {
            for (int dificultat = 3; dificultat <= 9; dificultat++) {
                TreeSet<Long> times = stats.getTimes(dificultat);
                for (Long time : times) {
                    writer.write(dificultat + "," + time + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @brief Carrega les estadístiques d'un usuari
     * @param username Nom de l'usuari
     * @return Estadístiques de l'usuari
     */
    public static UserStats loadUserStats(String username) {
        File userDir = new File(STATS_DIR, username);
        File statsFile = new File(userDir, "estadisticas.txt");
        if (statsFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(statsFile))) {
                UserStats stats = new UserStats();
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    int dificultat = Integer.parseInt(parts[0]);
                    long time = Long.parseLong(parts[1]);
                    stats.addTime(dificultat, time);
                }
                return stats;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return new UserStats(); // Devuelve un nuevo UserStats si no se encuentran datos
    }

    /**
     * @brief Elimina les estadístiques d'un usuari
     * @param username Nom de l'usuari
     * @post S'han eliminat les estadístiques de l'usuari
     */
    public static void deleteUserStats(String username) {
        File userDir = new File(STATS_DIR, username);
        File statsFile = new File(userDir, "estadisticas.txt");
        if (statsFile.exists()) {
            statsFile.delete();
        }
    }

    /**
     * @brief Actualitza les estadístiques d'un usuari
     * @param username Nom de l'usuari
     * @param newStats Noves estadístiques de l'usuari
     * @post S'han actualitzat les estadístiques de l'usuari
     */
    public static void updateUserStats(String username, UserStats newStats) {
        saveUserStats(username, newStats);
    }

    /**
     * @brief Retorna tots els noms d'usuari
     * @return Llista amb tots els noms d'usuari
     */
    public static ArrayList<String> getAllUsernames() {
        File dir = new File(STATS_DIR);
        String[] files = dir.list();
        ArrayList<String> usernames = new ArrayList<>();
        if (files != null) {
            for (String file : files) {
                File userDir = new File(dir, file);
                if (userDir.isDirectory()) {
                    usernames.add(file);
                }
            }
        }
        return usernames;
    }

}
