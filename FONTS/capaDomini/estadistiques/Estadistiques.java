package capaDomini.estadistiques;

import java.util.*;
import capaDomini.controladors.ControladorDomini;

public class Estadistiques {
    private final ControladorDomini ctrlDomini;
    private final CtrlEstadistiques controller;

    /**
     * @brief Constructora de la classe Estadistiques
     * @param ctrlDomini Controlador de domini
     * @post Crea una instància de CtrlEstadistiques
     */
    public Estadistiques(ControladorDomini ctrlDomini) {
        this.ctrlDomini = ctrlDomini;
        this.controller = new CtrlEstadistiques(ctrlDomini);
    }

    /**
     * @brief Guarda les estadístiques d'un usuari.
     * @param username Nom de l'usuari.
     * @param stats Estadistiques de l'usuari.
     * @post Les estadistiques de l'usuari s'han guardat.
     */
    public void addUserStats(String username, UserStats stats) {
        controller.saveUserStats(username, stats);
    }

    /**
     * @brief Carrega les estadistiques d'un usuari.
     * @param username Nom de l'usuari.
     * @return Estadistiques de l'usuari.
     */
    public UserStats getUserStats(String username) {
        return controller.loadUserStats(username);
    }

    /**
     * @brief Carrega totes les estadistiques.
     * @return Les estadistiques de tots els usuaris
     */
    public HashMap<String, UserStats> getAllStats() {
        return controller.loadStats();
    }

    /**
     * @brief Afegeix un temps a les estadistiques d'un usuari (en una dificultat)
     * @param username Nom de l'usuari
     * @param dificultat Dificultat de la partida
     * @param time Temps de la partida
     */
    public void addTimeToUser(String username, int dificultat, long time) {

        if (controller == null) {
            return;
        }

        controller.addTimeToUser(username, dificultat, time);
    }

}