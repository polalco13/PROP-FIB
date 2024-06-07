package capaDomini.estadistiques;

import java.util.HashMap;
import capaDomini.controladors.ControladorDomini;

public class CtrlEstadistiques {

    private final ControladorDomini ctrlDomini;

    /**
     * @brief Constructora per defecte.
     * @param ctrlDomini Controlador de domini.
     * @post S'ha creat una instància de CtrlEstadistiques.
     */
    public CtrlEstadistiques(ControladorDomini ctrlDomini) {
        this.ctrlDomini = ctrlDomini;
    }

    /**
     * @brief Guarda les estadistiques d'un usuari.
     * @param username Nom de l'usuari.
     * @param stats Estadistiques de l'usuari.
     * @post Les estadistiques de l'usuari s'han guardat.
     */
    public void saveUserStats(String username, UserStats stats) {
        ctrlDomini.saveUserStats(username, stats);
    }

    /**
     * @brief Carrega les estadistiques d'un usuari.
     * @param username Nom de l'usuari.
     * @return Estadistiques de l'usuari.
     */
    public UserStats loadUserStats(String username) {
        return ctrlDomini.loadUserStats(username);
    }

    /**
     * @brief Carrega totes les estadistiques.
     * @return Les estadistiques de tots els usuaris
     */
    public HashMap<String, UserStats> loadStats() {
        HashMap<String, UserStats> allStats = new HashMap<>();
        for (String username : ctrlDomini.getAllUsernames()) {
            allStats.put(username, ctrlDomini.loadUserStats(username));
        }
        return allStats;
    }

    /**
     * @brief Afegeix un temps a les estadistiques d'un usuari (en una dificultat)
     * @param username Nom de l'usuari
     * @param dificultat Dificultat de la partida
     * @param time Temps de la partida
     */
    public void addTimeToUser(String username, int dificultat, long time) {
        if (username == null) {
            throw new NullPointerException("username is null");
        }

        UserStats stats = loadUserStats(username);
        if (stats == null) {
            throw new NullPointerException("stats is null for username: " + username);
        }

        stats.addTime(dificultat, time);

        if (ctrlDomini == null) {
            throw new NullPointerException("ctrlDomini is null");
        }

        ctrlDomini.updateUserStats(username, stats);
    }


    /**
     * @brief Afegeix una partida a les estadistiques d'un usuari
     * @param username Nom de l'usuari
     * @param dificultat Dificultat de la partida
     */
    public int getKenkensJugats(String username) {
        UserStats stats = loadUserStats(username);
        if (stats == null) return 0;
        else {
            return stats.getAllTotalGames();
        }
    }

    /**
     * @brief Afegeix una partida a les estadistiques d'un usuari
     * @param username Nom de l'usuari
     * @param dificultat Dificultat de la partida
     */
    public Long getMillorTemps(String username) {
        UserStats stats = loadUserStats(username);
        if (stats == null) return 0L;
        else {
            return stats.getGlobalBestTime();
        }

    }

    /**
     * @brief Afegeix una partida a les estadistiques d'un usuari
     * @param username Nom de l'usuari
     * @param dificultat Dificultat de la partida
     */
    public Long getMitjanaTemps(String username) {
        UserStats stats = loadUserStats(username);
        if (stats == null) return 0L;
        else {
            return stats.getGlobalAverageTime();
        }
    }

    /**
     * @brief Afegeix una partida a les estadistiques d'un usuari
     * @param username Nom de l'usuari
     * @param dificultat Dificultat de la partida
     */
    public int getKenkensJugatsPerNivell(String username, int nivell) {
        UserStats stats = loadUserStats(username);
        if (stats == null) return 0;
        else {
            return stats.getGamesPlayedPerLevel(nivell);
        }
    }

    /**
     * @brief Afegeix una partida a les estadistiques d'un usuari
     * @param username Nom de l'usuari
     * @param dificultat Dificultat de la partida
     */
    public Long getMillorTempsPerNivell(String username, int nivell) {
        UserStats stats = loadUserStats(username);
        if (stats == null) return 0L;
        else {
            return stats.getBestTime(nivell);
        }
    }

    /**
     * @brief Afegeix una partida a les estadistiques d'un usuari
     * @param username Nom de l'usuari
     * @param dificultat Dificultat de la partida
     */
    public Long getMitjanaTempsPerNivell(String username, int nivell) {
        UserStats stats = loadUserStats(username);
        if (stats == null) return 0L;
        else {
            return stats.getAverageTime(nivell);
        }
    }
}