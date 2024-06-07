package capaPersistencia;

import capaDomini.estadistiques.UserStats;
import capaDomini.jocs.Joc;
import capaDomini.kenkens.Kenken;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CtrlPersistencia {
    public CtrlPersistencia() {
        DatabaseUsers.initializeDatabase();
    }



    ////////////////////////////////////////////////// DATABASE USERS //////////////////////////////////////////////////

    public void saveUser(String username, String password) {
        DatabaseUsers.saveUser(username, password);
    }

    public String[] loadUser(String username) {
        return DatabaseUsers.loadUser(username);
    }

    public void deleteUser(String username) {
        DatabaseUsers.deleteUser(username);
        DatabaseStats.deleteUserStats(username);
    }

    public void updateUser(String username, String newPassword) {
        DatabaseUsers.updateUser(username, newPassword);
    }

    public static void saveUserStats(String username, UserStats stats) {
        DatabaseStats.saveUserStats(username, stats);
    }

    public static UserStats loadUserStats(String username) {
        return DatabaseStats.loadUserStats(username);
    }

    public static void updateUserStats(String username, UserStats newStats) {
        DatabaseStats.updateUserStats(username, newStats);
    }

    public ArrayList<String> getAllUsernames() {
        return DatabaseStats.getAllUsernames();
    }

    ////////////////////////////////////////////////// DATABASE JOCS ///////////////////////////////////////////////////

    public static void saveJoc(String nomUsuari, Joc joc) {
        DatabaseJocs.saveJoc(nomUsuari, joc);
    }

    public static Joc loadJoc(String nomUsuari, int id) {
        return DatabaseJocs.loadJoc(nomUsuari, id);
    }

    public static void deleteJoc(String nomUsuari, int id) {
        DatabaseJocs.deleteJoc(nomUsuari, id);
    }

    ///////////////////////////////////////////////// DATABASE KENKENS /////////////////////////////////////////////////

    public void saveKenkenGenerat(String nomUsuari, Kenken k) {
        DatabaseKenkens.saveKenkenGenerat(nomUsuari, k);
    }

    public static void saveKenken(String nomUsuari, int id, String nom, String kenken) {
        DatabaseKenkens.saveKenken(nomUsuari, id, nom, kenken);
    }

    public static File loadKenken(String nomUsuari, int id) {
        return DatabaseKenkens.loadKenken(nomUsuari, id);
    }

    public List<Integer> getKenkens(String nombreUsuarioLoggeado) {
        return DatabaseKenkens.getKenkens(nombreUsuarioLoggeado);
    }

    public Map<String,File> getDatabaseOfKenkens(String nombreUsuarioLoggeado) {
        return DatabaseKenkens.getDatabaseOfKenkens(nombreUsuarioLoggeado);
    }

    public List<Integer> getJocs(String nombreUsuarioLoggeado) {
        return DatabaseJocs.getJocs(nombreUsuarioLoggeado);
    }

    public void deleteKenken(String username, int id) {
        DatabaseKenkens.deleteKenken(username, id);
    }
}