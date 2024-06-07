package capaPersistencia;

import java.io.*;

public class DatabaseUsers {
    private static final String USERS_DIR = "capaPersistencia/Users";

    /**
     * @brief Crea el directori d'usuaris si no existeix
     * @post S'ha creat el directori d'usuaris
     */
    public static void initializeDatabase() {
        File dir = new File(USERS_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * @brief Guarda un usuari amb el seu nom i contrasenya
     * @param username Nom de l'usuari
     * @param password Contrasenya de l'usuari
     * @post S'ha guardat l'usuari amb el seu nom i contrasenya
     */
    public static void saveUser(String username, String password) {
        File userDir = new File(USERS_DIR, username);
        if (!userDir.exists()) {
            userDir.mkdirs();
        }

        // Create Kenkens directory and text files inside the user's directory
        File kenkensDir = new File(userDir, "Kenkens");
        if (!kenkensDir.exists()) {
            kenkensDir.mkdirs();
        }

        // Create the first text file with specified content
        File txtFile = new File(kenkensDir, "0-.txt");
        if (!txtFile.exists()) {
            try {
                txtFile.createNewFile();
                // Write some text into the file
                try (FileWriter writer = new FileWriter(txtFile)) {
                    writer.write("3 5\n4 3 2 1 1 2 1\n4 2 2 1 2 1 3\n0 3 1 2 2\n0 2 1 2 3\n1 6 3 3 1 3 2 3 3");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        // Create the second text file with predefined content
        txtFile = new File(kenkensDir, "1-.txt");
        if (!txtFile.exists()) {
            try {
                txtFile.createNewFile();
                // Write some text into the file
                try (FileWriter writer = new FileWriter(txtFile)) {
                    writer.write("6 17\n4 2 2 1 1 2 1\n0 5 1 1 2\n3 12 2 1 3 1 4\n3 10 3 1 5 1 6 2 6\n2 5 2 2 2 2 3\n4 2 2 2 4 3 4\n5 4 2 2 5 3 5\n3 10 2 3 1 4 1\n0 6 1 3 2\n1 10 3 3 3 4 3 5 3\n3 12 2 3 6 4 6\n3 6 2 4 2 5 2\n3 12 2 4 4 4 5\n3 16 3 5 1 6 1 6 2\n3 18 3 5 4 5 5 5 6\n5 2 2 6 3 6 4\n3 18 2 6 5 6 6");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        // Create the third text file with specified content
        txtFile = new File(kenkensDir, "2-.txt");
        if (!txtFile.exists()) {
            try {
                txtFile.createNewFile();
                // Write some text into the file
                try (FileWriter writer = new FileWriter(txtFile)) {
                    writer.write("9 32\n2 5 2 1 1 1 2\n1 13 3 1 3 2 3 2 2\n3 672 4 1 4 2 4 3 4 3 5\n2 4 2 1 5 1 6\n3 56 3 1 7 1 8 2 8\n3 35 3 1 9 2 9 3 9\n1 26 4 2 1 3 1 4 1 5 1\n0 1 1 2 5\n3 192 3 2 6 3 6 4 6\n3 18 2 2 7 3 7\n5 5 2 3 2 4 2\n3 96 3 3 3 4 3 5 3\n3 108 3 3 8 4 8 4 9\n3 63 3 4 4 4 5 5 5\n1 9 3 4 7 5 7 5 6\n3 48 3 5 2 6 2 6 1\n1 15 3 5 4 6 4 7 4\n1 16 4 5 8 6 8 7 8 8 8\n1 11 3 5 9 6 9 7 9\n3 108 4 6 3 7 3 7 2 8 2\n0 8 1 6 5\n3 112 3 6 6 7 6 7 7\n0 3 1 6 7\n1 10 3 7 1 8 1 9 1\n3 30 2 7 5 8 5\n3 70 3 8 3 9 3 9 4\n0 4 1 8 4\n3 270 3 8 6 8 7 9 7\n0 8 1 8 9\n0 7 1 9 2\n5 1 2 9 5 9 6\n1 17 2 9 8 9 9");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        File passwordFile = new File(userDir, "password.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(passwordFile))) {
            writer.write(password + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @brief Carrega un usuari amb el seu nom i contrasenya
     * @param username Nom de l'usuari
     * @return Usuari amb el seu nom i contrasenya
     */
    public static String[] loadUser(String username) {
        // Find the user's directory
        File userDir = new File(USERS_DIR, username);
        if (!userDir.exists()) {
            return null;
        }

        // Find the password file inside the user's directory
        File passwordFile = new File(userDir, "password.txt");
        if (passwordFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(passwordFile))) {
                String[] userData = new String[2];
                userData[0] = username;
                userData[1] = reader.readLine();
                return userData;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    /**
     * @brief Esborra un usuari
     * @param username Nom de l'usuari
     * @post S'ha esborrat l'usuari
     */
    public static void deleteUser(String username) {
        File userDir = new File(USERS_DIR, username);
        if (userDir.exists()) {
            File[] allContents = userDir.listFiles();
            if (allContents != null) {
                for (File file : allContents) {
                    File[] innerContents = file.listFiles();
                    if (innerContents != null) {
                        for (File innerFile : innerContents) {
                            innerFile.delete();
                        }
                    }
                    file.delete();
                }
            }
            userDir.delete();
        }
    }

    /**
     * @brief Actualitza la contrasenya d'un usuari
     * @param username Nom de l'usuari
     * @param newPassword Nova contrasenya de l'usuari
     * @post S'ha actualitzat la contrasenya de l'usuari
     */
    public static void updateUser(String username, String newPassword) {
        saveUser(username, newPassword);
    }
}
