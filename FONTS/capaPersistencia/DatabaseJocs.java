package capaPersistencia;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import capaDomini.excepcions.ExcepcioKenken;
import capaDomini.kenkens.Kenken;
import capaDomini.jocs.Joc;

public class DatabaseJocs {

    /**
     * @param joc Joc a guardar.
     * @brief Guarda un joc.
     * @post El joc s'ha guardat.
     */
    public static void saveJoc(String nomUsuari, Joc joc) {
        // Buscar el directori de usuari
        File userDir = new File("capaPersistencia/Users", nomUsuari);
        if (!userDir.exists()) {
            return;
        }

        // Buscar el directori dels jocs de l'usuari
        File jocsDir = new File(userDir, "Jocs");
        if (!jocsDir.exists()) {
            jocsDir.mkdirs();
        }

        // Buscar el directori del joc
        File jocDir = new File(jocsDir, String.valueOf(joc.getId()));
        if (!jocDir.exists()) {
            jocDir.mkdirs();
        }

        // Crear el fitxer de dades del joc
        File jocFile = new File(jocDir, "data.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(jocFile))) {
            writer.write(joc.toFileString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Crear el fitxer de kenken del joc
        File puzzleFile = new File(jocDir, "puzzle.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(puzzleFile))) {
            writer.write(joc.getPuzzle().kenkenToString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param id Identificador del joc.
     * @return El joc carregat.
     * @brief Carrega un joc.
     */
    public static Joc loadJoc(String nomUsuari, int id) {
        // Buscar el directori de usuari
        File userDir = new File("capaPersistencia/Users", nomUsuari);
        if (!userDir.exists()) {
            return null;
        }

        // Buscar el directori dels jocs de l'usuari
        File jocsDir = new File(userDir, "Jocs");
        if (!jocsDir.exists()) {
            return null;
        }

        // Buscar el directori del joc
        File jocDir = new File(jocsDir, String.valueOf(id));
        if (!jocDir.exists() || !jocDir.isDirectory()) {
            return null;
        }

        // parametres d'un joc
        int jocId;
        long temps;
        String usuariId;
        int kenkenId;
        Kenken puzzle;
        Kenken solucio;
        int cellesBuides;
        int pistesRestants;

        // Carrega el fitxer de dades del joc
        File jocFile = new File(jocDir, "data.txt");
        if (!jocFile.exists() || jocFile.isDirectory()) {
            return null;
        }
        else {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(jocFile));
                String line = reader.readLine();
                String[] parts = line.split(",");
                jocId = Integer.parseInt(parts[0]);
                usuariId = parts[1];
                temps = Long.parseLong(parts[2]);
                cellesBuides = Integer.parseInt(parts[3]);
                pistesRestants = Integer.parseInt(parts[4]);
                kenkenId = Integer.parseInt(parts[5]);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }

        // Carrega el kenken del joc
        File puzzleFile = new File(jocDir, "puzzle.txt");
        if (!puzzleFile.exists() || puzzleFile.isDirectory()) {
            return null;
        }
        else {
            try {
                puzzle = new Kenken(puzzleFile);
            } catch (ExcepcioKenken e) {
                System.out.println(e.getMessage());
                return null;
            }
        }

        //crea joc carregat
        Joc joc = new Joc(jocId, usuariId, temps, cellesBuides, pistesRestants, puzzle, kenkenId);
        return joc;
    }

    /**
     * @param id Identificador del joc.
     * @brief Esborra un joc.
     * @post El joc s'ha esborrat.
     */
    public static void deleteJoc(String nomUsuari, int id) {
        // Buscar el directori de usuari
        File userDir = new File("capaPersistencia/Users", nomUsuari);
        if (!userDir.exists()) {
            return;
        }

        // Buscar el directori dels jocs de l'usuari
        File jocsDir = new File(userDir, "Jocs");
        if (!jocsDir.exists()) {
            return;
        }

        // Buscar el directori del joc
        File jocDir = new File(jocsDir, String.valueOf(id));
        if (!jocDir.exists() || !jocDir.isDirectory()) {
            return;
        }

        // Esborra el directori del joc
        Path jocDirPath = jocDir.toPath();
        try {
            Files.walk(jocDirPath)
                    .sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            Files.deleteIfExists(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> getJocs(String nomUsuari) {
        // Buscar el directori de usuari
        File userDir = new File("capaPersistencia/Users", nomUsuari);
        if (!userDir.exists()) {
            return null;
        }

        // Buscar el directori dels jocs de l'usuari
        File jocsDir = new File(userDir, "Jocs");
        if (!jocsDir.exists()) {
            return null;
        }

        // Crea la llista de jocs
        File[] listOfDirectories = jocsDir.listFiles(File::isDirectory);
        List<Integer> jocIds = new ArrayList<>();
        if (listOfDirectories != null) {
            for (File directory : listOfDirectories) {
                String dirName = directory.getName();
                try {
                    int id = Integer.parseInt(dirName);
                    jocIds.add(id);
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return jocIds;
    }
}