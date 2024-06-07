package capaDomini.jocs;

import capaDomini.excepcions.ExcepcioJoc;
import capaDomini.excepcions.ExcepcioKenken;
import capaDomini.kenkens.Kenken;
import capaDomini.utils.StopWatch;

public class Joc {
    int id; //id del joc
    StopWatch temps; //temporitzador
    int kenkenId; //id del kenken que es juga
    Kenken puzzle; //kenken en process per l'usuari
    String idUsuari; //id del usuari que juga
    int cellesBuides; //nombre de celles buides
    int pistesRestants; //si s'ha usat la pista

    ///////////////////////////////// CONSTRUCTORES /////////////////////////////////

    /**
     * @brief Constructora de Joc
     * @param id identificador de joc
     * @param userId identificador de l'usuari que juga
     * @param solucio kenken solucionat
     * @param puzzle kenken en proces per l'usuari
     * @param kenkenId identificador del kenken
     * @exception ExcepcioKenken si els parametres son invalids
     * @post Crea un Joc amb l'id, usuari i conjunt de kenkens donat. Es crea el cronometre i el contador de celles buides.
     */
    public Joc(int id, String userId, Kenken puzzle, int kenkenId) throws ExcepcioKenken {
        if (puzzle == null || userId == null || id < 0) {
            throw new IllegalArgumentException("Joc.normal: id, userId and puzzle cannot be null, and id must be greater or equal to 0");
        }
        this.id = id;
        this.idUsuari = userId;
        this.temps = new StopWatch();
        this.kenkenId = kenkenId;
        this.puzzle = new Kenken(puzzle);
        int tamanyKenken = puzzle.getMida();
        this.cellesBuides = tamanyKenken * tamanyKenken;
        this.pistesRestants = puzzle.getMida()/3;
    }

    /**
     * @brief Constructora de Joc per parametres
     * @param id identificador de joc
     * @param userId identificador de l'usuari que juga
     * @param temps temps transcorregut
     * @param cellesBuides nombre de celles buides
     * @param pistesRestants nombre de pistes disponibles
     * @param puzzle kenken en proces per l'usuari
     * @param kenkenId identificador del kenken
     * @exception ExcepcioKenken si els parametres son invalids
     * @post Crea un Joc amb l'id, usuari i conjunt de kenkens donat. Es crea el cronometre i el contador de celles buides.
     */
    public Joc(int id, String userId, long temps, int cellesBuides, int pistesRestants, Kenken puzzle, int kenkenId) {
        if (puzzle == null || userId == null || id < 0) {
            throw new IllegalArgumentException("Joc.params: id, userId and puzzle cannot be null, and id must be greater or equal to 0");
        }
        this.id = id;
        this.idUsuari = userId;
        this.temps = new StopWatch(temps);
        this.kenkenId = kenkenId;
        this.puzzle = puzzle;
        this.cellesBuides = cellesBuides;
        this.pistesRestants = pistesRestants;
    }

    //////////////////////////////////// SETTERS ////////////////////////////////////

    /**
     * @brief Assigna un valor a una posicio del kenken
     * @param posX
     * @param posY
     * @param v valor a assignar
     * @exception ExcepcioKenken si la posicio no es valida o el valor no es valid
     * @post Assigna el valor v a la posicio (posX, posY) del kenken i actualitza el comptador de celles buides.
     */
    public void assignaValor(int posX, int posY, int v) throws ExcepcioKenken {
        if (this.puzzle.getValue(posX, posY) == 0) this.cellesBuides--;
        this.puzzle.setValue(posX, posY, v);
    }

    /**
     * @brief Desassigna un valor a una posicio del kenken
     * @param posX
     * @param posY
     * @param v valor a desassignar
     * @exception ExcepcioKenken si la posicio no es valida o el valor no es valid
     * @post Desassigna el valor v a la posicio (posX, posY) del kenken i actualitza el comptador de celles buides.
     */
    public void dessasignaValor(int posX, int posY, int v) throws ExcepcioKenken {
        if (this.puzzle.getValue(posX, posY) != 0) this.cellesBuides++;
        this.puzzle.unsetValue(posX, posY, v);
    }

    //////////////////////////////////// GETTERS ////////////////////////////////////

    /**
     * @brief Get elapsed time
     * @return temps elapsat en format long
     */
    public long getTemps() {
        return this.temps.getTime();
    }

    /**
     * @brief Get elapsed time
     * @return temps elapsat en format string hh:mm:ss
     */
    public String getFormattedTime() {
        return this.temps.getFormattedTime();
    }

    /**
     * @brief Get id
     * @return id del joc
     */
    public int getId() {
        return this.id;
    }

    /**
     * @brief Get idKenken
     * @return id del kenken
     */
    public int getKenkenId() {
        return this.kenkenId;
    }

    /**
     * @brief Get idUsuari
     * @return id de l'usuari
     */
    public String getIdUsuari() {
        return idUsuari;
    }

    /**
     * @brief Get Kenken
     * @return Kenken en proces
     */
    public Kenken getPuzzle() {
        return this.puzzle;
    }

    /**
     * @brief Get celles buides
     * @return nombre de pistes restants
     */
    public int getPistesRestants() {
        return pistesRestants;
    }

    //////////////////////////////////// METHODS ////////////////////////////////////

    /**
     * @brief Comenca el cronometre
     */
    public void comencaTemps() {
        this.temps.start();
    }

    /**
     * @brief Pausa el cronometre
     */
    public void pausaTemps() {
        this.temps.stop();
    }

    /**
     * @brief Comprova si el joc esta omplert
     * @return si el taulell esta omplert
     */
    public boolean comprovaAcabat() {
        return cellesBuides == 0;
    }

    /**
     * @brief Utilitza una pista
     * @return resta 1 al nombre de pistes restants
     */
    public int usaPista() throws ExcepcioJoc {
        if (pistesRestants == 0) throw new ExcepcioJoc("No queden pistes");
        return --pistesRestants;
    }

    ///////////////////////////////// INPUT-OUTPUT //////////////////////////////////

    /**
     * @brief Converteix el joc en strings per la capa persistencia
     */
    public String toFileString() {
        return id + "," + idUsuari + "," + temps.getTime() + "," + cellesBuides + "," + pistesRestants + "," + kenkenId;
    }
}
