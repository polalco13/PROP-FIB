package capaDomini.jocs;

import capaDomini.excepcions.ExcepcioJoc;
import capaDomini.excepcions.ExcepcioKenken;
import capaDomini.kenkens.Kenken;
import capaPersistencia.CtrlPersistencia;

import java.util.ArrayList;
import java.util.List;

public class JocController {

    private Joc jocActual;
    private List<Integer> jocs;

    ///////////////////////////////// CONSTRUCTORES /////////////////////////////////

    /**
     * @brief Constructora de JocController
     * @post Crea un conjunt de jocs nou
     */
    public JocController() {
        jocs = new ArrayList<>();
    }

    ///////////////////////////////// METHOD /////////////////////////////////

    /**
     * @brief Crea un joc.
     * @param userId identificador de l'usuari
     * @param kenkenId identificador del kenken
     * @param puzzle kenken en proces per l'usuari
     * @post S'ha creat un joc amb l'id, usuari i conjunt de kenkens donat.
     *       Se li dona un id de joc automaticament basant-se en la seva posicio al conjunt de jocs total.
     */
    public void altaJoc(String userId, Kenken puzzle, int kenkenId) throws ExcepcioKenken {
        int id = jocs.size();
        jocs.add(id);
        jocActual = new Joc(id, userId, puzzle, kenkenId);
    }

    ///////////////////////////////// GETTERS /////////////////////////////////

    /**
     * @brief Retorna el joc actual
     * @return joc actual
     */
    public Joc getJocActual() throws ExcepcioJoc {
        if(jocActual == null) throw new ExcepcioJoc("No hi ha cap joc actual");
        return jocActual;
    }


    ///////////////////////////////// INPUT-OUTPUT ////////////////////////////////

    /**
     * @brief Continua un joc existent
     * @param nouJoc joc amb el que es vol continuar
     * @return s'actualitza el joc actual amb el joc amb id idJoc
     */
    public void continuaJoc(Joc nouJoc) {
        if (nouJoc == null) throw new IllegalArgumentException("JocController.continuaJoc: nouJoc cannot be null");
        jocActual = nouJoc;
    }

    public void initializeDatabase(String nombreUsuarioLoggeado, List<Integer> jocs) {
        if (jocs == null) return;
        for (int id : jocs) {
            Joc nouJoc = CtrlPersistencia.loadJoc(nombreUsuarioLoggeado, id);
            this.jocs.add(nouJoc.getId());
        }
    }

    public void deleteJoc(int idJoc) {
        jocs.remove(idJoc);
    }
}
