package capaDomini.controladors;
import capaDomini.estadistiques.CtrlEstadistiques;
import capaDomini.estadistiques.Estadistiques;
import capaDomini.estadistiques.UserStats;
import capaDomini.excepcions.ExcepcioJoc;
import capaDomini.excepcions.ExcepcioKenken;
import capaDomini.excepcions.ExcepcioUsuari;
import capaDomini.kenkens.*;
import capaDomini.jocs.*;
import capaDomini.usuaris.*;
import capaDomini.rankings.*;
import capaPersistencia.CtrlPersistencia;

import java.io.File;
import java.util.*;

public class ControladorDomini {
    private final CtrlUsuari ctrlUsuari;
    private final KenkenController ctrlKenken;
    private final JocController ctrlJoc;
    private final Ranking ranking;
    private final Estadistiques estadistiques;

    private final CtrlEstadistiques ctrlEstadistiques;
    private final CtrlPersistencia ctrlPersistencia;

    /**
     * @brief Crea un controlador de domini
     * @post S ha creat un controlador de domini
     */
    public ControladorDomini() {
        ctrlKenken = new KenkenController();
        ctrlUsuari = new CtrlUsuari();
        ctrlJoc = new JocController(); // Pass current instance of ControladorDomini
        estadistiques = new Estadistiques(this); // Correct assignment to the member variable
        ranking = new Ranking(estadistiques);
        ctrlPersistencia = new CtrlPersistencia();
        ctrlEstadistiques = new CtrlEstadistiques(this); // Pass current instance of ControladorDomini
    }

    private void iniDomini() throws ExcepcioKenken {
        Map<String,File> kenkenDatabase = ctrlPersistencia.getDatabaseOfKenkens(ctrlUsuari.getNombreUsuarioLoggeado());
        ctrlKenken.initializeDatabase(kenkenDatabase);

        List<Integer> kenkens = ctrlPersistencia.getKenkens(ctrlUsuari.getNombreUsuarioLoggeado());
        ctrlUsuari.getUsuariLoguejat().initializeDatabaseKenkens(kenkens);

        List<Integer> jocs = ctrlPersistencia.getJocs(ctrlUsuari.getNombreUsuarioLoggeado());
        ctrlJoc.initializeDatabase(ctrlUsuari.getNombreUsuarioLoggeado(), jocs);
        ctrlUsuari.getUsuariLoguejat().initializeDatabaseJocs(jocs);
    }

    ////////////////////////////////////////////////////// USUARI /////////////////////////////////////////////////////

    /**
        * @brief Crea un usuari amb el nom i contrasenya donats
     * @param nom Nom de l usuari
     * @param contrasenya Contrasenya de l usuari
     * @pre El nom d usuari no pot ser buit
     * @pre L usuari no pot existir
     * @post S ha creat un usuari amb el nom i contrasenya donats
     * @throws ExcepcioUsuari si l usuari ja existeix
     * @throws ExcepcioUsuari si el nom d usuari es buit
     * @throws ExcepcioUsuari si la contrasenya es buida
     */
    public void crearUsuari(String nom, String contrasenya) throws ExcepcioUsuari, ExcepcioKenken {
        if(ctrlPersistencia.loadUser(nom) != null) {
            throw new ExcepcioUsuari("El nom d'usuari ja existeix");
        }
        else {
            this.ctrlUsuari.creaUsuari(nom, contrasenya);
            ctrlPersistencia.saveUser(nom, contrasenya);
            iniDomini();
        }
    }

    /**
        * @brief Logueja l usuari amb nom i contrasenya donats
     * @param nom Nom de l usuari
     * @param contrasenya Contrasenya de l usuari
     * @pre L usuari ha d existir
     * @pre La contrasenya ha de ser correcta
     * @post S ha loguejat l usuari amb nom i contrasenya donats
     * @throws ExcepcioUsuari si l usuari no existeix
     * @throws ExcepcioUsuari si la contrasenya es incorrecta
     * @throws ExcepcioUsuari si el nom d usuari es buit
     * @throws ExcepcioUsuari si la contrasenya es buida
     */
    public void login(String nom, String contrasenya) throws ExcepcioUsuari, ExcepcioKenken {
        String[] userData = ctrlPersistencia.loadUser(nom);
        if (userData == null) throw new ExcepcioUsuari("L'usuari no existeix");
        ctrlUsuari.login(nom, contrasenya, userData);
        iniDomini();
    }

    /**
     * @brief Retorna el nom de l usuari loggejat
     * @pre L usuari ha d estar loggejat
     * @post Retorna el nom de l usuari loggejat
     * @throws ExcepcioUsuari si l usuari no esta loggejat
     */
    public String getNombreUsuarioLoggeado() {
        return ctrlUsuari.getNombreUsuarioLoggeado();
    }

    /**
     * @brief Retorna si existeix un usuari concret
     * @pre cert
     * @post Retorna si existeix un usuari concret
    */
    public boolean existeixUsuariConcret(String nom) {
        return ctrlPersistencia.loadUser(nom) != null;
    }

    /**
     * @brief Esborra l usuari loguejat
     * @param contrasenya Contrasenya de l usuari loguejat
     * @pre L usuari ha d estar loguejat
     * @pre La contrasenya ha de ser correcta
     * @post S ha esborrat l usuari loguejat
     */
    public void esborrarUsuari(String contrasenya) throws ExcepcioUsuari {
        String u = ctrlUsuari.esborrarUsuari(contrasenya);
        ctrlPersistencia.deleteUser(u);
    }

    /**
     * @brief Canvia la contrasenya de l usuari loguejat
     * @param contrasenyaActual Contrasenya actual de l usuari loguejat
     * @param novaContrasenya Nova contrasenya de l usuari loguejat
     * @pre L usuari ha d estar loguejat
     * @pre La contrasenya actual ha de ser correcta
     * @post S ha canviat la contrasenya de l usuari loguejat
     */
    public void canviarContrasenya(String contrasenyaActual, String novaContrasenya) throws ExcepcioUsuari {
        if (contrasenyaActual.equals(novaContrasenya)) throw new ExcepcioUsuari("La contrasenya nova no pot ser igual a l'antiga");
        //Comprovar si la contrasenya actual es correcta
        if (!comprovarContrasenya(contrasenyaActual)) throw new ExcepcioUsuari("La contrasenya actual no es correcta");
        //Canviar contrasenya
        ctrlUsuari.canviarContrasenya(contrasenyaActual, novaContrasenya);
        String nom = ctrlUsuari.getNombreUsuarioLoggeado();
        ctrlPersistencia.updateUser(nom, novaContrasenya);
    }

    /**
     * @brief Comprova si la contrasenya donada es correcta
     * @param contrasenya
     * @return
     */
    public boolean comprovarContrasenya(String contrasenya) {
        Usuari usuari = this.ctrlUsuari.getUsuariLoguejat();
        return usuari.getContrasenya().equals(contrasenya);
    }

    /**
     * @brief Retorna l usuari loguejat
     * @return
     */
    public Usuari getUsuariLoguejat() {
        return this.ctrlUsuari.getUsuariLoguejat();
    }

    /**
     * @brief Retorna la contrasenya de l usuari loguejat
     * @return
     */
    public String getPasswordUserLoged() {
        return this.ctrlUsuari.getContrasenyaUsuariLoguejat();
    }

    /**
     * @brief Retorna si hi ha un usuari loguejat
     * @return Cert si hi ha un usuari loguejat, fals altrament
     */
    public boolean isUsuariLoguejat() {
        return this.ctrlUsuari.isUsuariLoguejat();
    }

    /**
     * @brief Llista els jocs guardats
     * @post S han llistat els jocs guardats
     */
    public void logout() throws ExcepcioUsuari {
        this.ctrlUsuari.logout();
    }

    public void continuaJoc(int idJoc) {
        String nomUsuari = ctrlUsuari.getNombreUsuarioLoggeado();

        Joc nouJoc = CtrlPersistencia.loadJoc(nomUsuari, idJoc);
        ctrlJoc.continuaJoc(nouJoc);
    }

    /**
     * @brief Guarda el joc actual
     * @post S'ha guardat el joc actual
     */
    public void guardaJoc() throws ExcepcioUsuari, ExcepcioJoc {
        Joc jocActual = this.ctrlJoc.getJocActual();
        int idJoc = jocActual.getId();
        String nomUsuari = ctrlUsuari.getNombreUsuarioLoggeado();

        ctrlUsuari.guardarJoc(idJoc); //guarda el joc a la llista de jocs de l'usuari
        CtrlPersistencia.saveJoc(nomUsuari, jocActual); //guarda el joc a la bd
    }

    //////////////////////////////////////////////////////// JOC ///////////////////////////////////////////////////////

    /**
     * @brief Crea un joc amb el kenken donat
     * @param idKenken Identificador del kenken
     * @pre L'usuari ha d'estar loguejat
     * @pre L'id del kenken ha de ser valid
     * @post S'ha creat un joc amb el kenken donat per a l'usuari identificat per userId
     */
    public void creaJoc(int idKenken) throws ExcepcioKenken {
        Kenken puzzle = this.ctrlKenken.getKenken(idKenken);
        String userId = ctrlUsuari.getNombreUsuarioLoggeado();

        this.ctrlJoc.altaJoc(userId, puzzle, idKenken);
    }

    /**
     * @param posX  Posicio X
     * @param posY  Posicio Y
     * @param valor Valor a assignar
     * @return Cert si el taulell esta omplert, fals altrament.
     * @throws ExcepcioKenken si la posicio no es valida o el valor no es valid
     * @throws ExcepcioJoc si el joc no esta comencat
     * @brief Assigna un valor a una posicio del kenken
     * @post Assigna el valor a la posicio del kenken
     */
    public boolean assignaValor(int posX, int posY, int valor) throws ExcepcioKenken, ExcepcioJoc {
        Joc jocActual = ctrlJoc.getJocActual();

        jocActual.assignaValor(posX, posY, valor);

        if (jocActual.comprovaAcabat()) {
            return jocActual.getPuzzle().getSolution() != null;
        }
        return false;
    }

    /**
     * @brief Desassigna un valor a una posicio del kenken
     * @param posX Posicio X
     * @param posY Posicio Y
     * @param v Valor a desassignar
     * @throws ExcepcioKenken si la posicio no es valida o el valor no es valid
     * @throws ExcepcioJoc si el joc no esta comencat
     * @post Desassigna el valor a la posicio del kenken
     */
    public void desassignaValor(int posX, int posY, int v) throws ExcepcioKenken, ExcepcioJoc {
        Joc jocActual = ctrlJoc.getJocActual();
        jocActual.dessasignaValor(posX, posY, v);
    }

    /**
     * @brief Comenca el cronometre del joc
     * @pre El joc s'ha de haver creat previament
     * @post El cronometre comenca a contar el temps
     * @throws ExcepcioJoc si el joc no esta comencat
     */
    public void comencaTemps() throws ExcepcioJoc {
        Joc jocActual = ctrlJoc.getJocActual();
        jocActual.comencaTemps();
    }

    /**
     * @brief Retorna el temps del joc actual
     * @return temps del joc actual
     * @throws ExcepcioJoc si el joc no esta comencat
     */
    public void pausaTemps() throws ExcepcioJoc {
        Joc jocActual = ctrlJoc.getJocActual();
        jocActual.pausaTemps();
    }

    /**
     * @brief Retorna el temps del joc actual
     * @return temps del joc actual en format hh:mm:ss
     * @throws ExcepcioJoc si el joc no esta comencat
     */
    public String getFormattedTime() throws ExcepcioJoc {
        Joc jocActual = ctrlJoc.getJocActual();
        return jocActual.getFormattedTime();
    }

    /**
     * @brief Actualitza les estadistiques d'un usuari
     * @post s'afegeix el temps del joc actual a les estadistiques de l'usuari
     */
    public void enviaStats() {
        // Check if ctrlUsuari or ctrlJoc is null
        if (ctrlUsuari == null) return;
        if (ctrlJoc == null) return;

        // Get the username and log if it's null
        String username = ctrlUsuari.getNombreUsuarioLoggeado();
        if (username == null) return;

        // Get the current game and log if it's null
        Joc jocActual = null;
        try {
            jocActual = ctrlJoc.getJocActual();
        } catch (ExcepcioJoc e) {
            return;
        }

        // Get the puzzle and log if it's null
        Kenken puzzle = jocActual.getPuzzle();

        // Get the size and log if it's invalid
        int mida = puzzle.getMida();
        if (mida < 3 || mida > 9) return;

        // Get the time
        long time = jocActual.getTemps();

        // Check if estadistiques is null
        if (estadistiques == null) return;

        estadistiques.addTimeToUser(username, mida, time);
    }

    /**
     * @brief Acaba el joc actual
     * @pre El joc s'ha de haver comencat previament
     * @post El joc actual s'ha acabat, s'elimina el joc de la base de dades
     * @throws ExcepcioJoc si el joc no esta comencat
     */
    public void acabaPartida() throws ExcepcioJoc {
        Joc jocActual = ctrlJoc.getJocActual();
        int idJoc = jocActual.getId();
        String nomUsuari = ctrlUsuari.getNombreUsuarioLoggeado();

        pausaTemps(); //pausa temps
        enviaStats(); //guarda estadistiques
        CtrlPersistencia.deleteJoc(nomUsuari, idJoc); //elimina joc acabat de la bd
        ctrlUsuari.deleteJocGuardat(idJoc); //elimina joc de la llista de jocs de l'usuari
    }

    /**
     * @brief Utilitza una pista del joc actual
     * @pre El joc s'ha de haver comencat previament
     * @post S'ha utilitzat una pista del joc actual
     * @throws ExcepcioJoc si el joc no esta comencat
     */
    public int usaPista() throws ExcepcioJoc {
        Joc jocActual = ctrlJoc.getJocActual();
        return jocActual.usaPista();
    }

    /**
     * @brief Retorna el nombre de pistes restants del joc actual
     * @return Nombre de pistes restants del joc actual
     * @throws ExcepcioJoc si el joc no esta comencat
     */
    public int getPistesRestants() throws ExcepcioJoc {
        Joc jocActual = ctrlJoc.getJocActual();
        return jocActual.getPistesRestants();
    }

    /**
     * @brief Elimina el joc amb identificador idJoc
     * @param idJoc Identificador del joc
     * @post S'ha eliminat el joc amb identificador idJoc de la base de dades i de l'usuari
     * @throws ExcepcioJoc si el joc no esta comencat
     */
    public void deleteJoc(int idJoc) {
        String nomUsuari = ctrlUsuari.getNombreUsuarioLoggeado();
        CtrlPersistencia.deleteJoc(nomUsuari, idJoc);
        ctrlUsuari.deleteJocGuardat(idJoc);
        ctrlJoc.deleteJoc(idJoc);
    }

    ////////////////////////////////////////////////////// KENKEN //////////////////////////////////////////////////////

    /**
     * @brief Retorna l'identificador del Kenken importat
     * @param path del fitxer a importar
     * @return Identificador del Kenken importat
     * @pre cert
     * @post Retorna l'identificador del Kenken importat
     */
    public void importFile(File kenkenFile) throws ExcepcioKenken {
        String username = ctrlUsuari.getNombreUsuarioLoggeado();
        int importedId = ctrlKenken.importKenken(kenkenFile);
        Kenken k = ctrlKenken.getKenken(importedId);
        ctrlUsuari.getUsuariLoguejat().agregarKenken(importedId);
        CtrlPersistencia.saveKenken(username,importedId, k.getNom(), k.kenkenToString());
    }

    /**
     * @brief Retorna la solució del Kenken amb identificador id, si existeix
     * @param cages Cages del Kenken, llista de les regions
     * @return La solució del Kenken amb identificador id, si existeix
     */
    public int[][] getSolution(List<List<Integer>> cages) throws ExcepcioKenken {
        return ctrlKenken.getSolution(cages);
    }

    /**
     * @brief Retorna el kenken amb identificador id
     * @param id Identificador del kenken
     * @return Kenken en format llista de llistes amb identificador id
     */
    public List<List<Integer>> generarKenken(String nom, int mida) throws ExcepcioKenken {
        List<List<Integer>> kenken = ctrlKenken.generarKenken(nom,mida);
        return kenken;
    }

    /**
     * @brief Guarda el kenken generat
     * @param nom Nom del kenken
     * @param kenken Kenken a guardar
     */
    public void saveKenkenGenerat(String nom, List<List<Integer>> kenken) throws ExcepcioKenken {
        Kenken k = ctrlKenken.saveKenkenGenerat(nom, kenken);
        ctrlUsuari.getUsuariLoguejat().agregarKenken(k.getId());
        ctrlPersistencia.saveKenkenGenerat(ctrlUsuari.getUsuariLoguejat().getNom(), k);
    }

    /**
     * @brief Retorna el kenken en format de llista de llistes
     * @return Kenken en format de llista de llistes
     */
    public List<List<Integer>> loadKenken() throws ExcepcioJoc {
        Kenken puzzle = ctrlJoc.getJocActual().getPuzzle();
        return puzzle.kenkenToList();
    }

    ////////////////////////////////////////////////////// RANKING /////////////////////////////////////////////////////

    /**
     * @brief Calcula el ranking
     * @pre cert
     * @post S'ha calculat el ranking
     */
    public void calcularRanking() {
        ranking.generarRanking();
    }

    /**
     * @brief Mostra el ranking per mida
     * @param mida Mida dels kenkens
     * @pre cert
     * @post S'ha mostrat el ranking per mida
     */
    public Ranking getRanking() {
        return ranking;
    }

    /////////////////////////////////////////////////// ESTADISTIQUES //////////////////////////////////////////////////

    /**
     * @brief Guarda les estadistiques de l'usuari
     * @param username Nom de l'usuari
     * @param stats Estadistiques de l'usuari
     * @post S'han guardat les estadistiques de l'usuari
     */
    public void saveUserStats(String username, UserStats stats) {
        if (username == null || stats == null) return;
        CtrlPersistencia.saveUserStats(username, stats);
    }

    /**
     * @brief Carrega les estadistiques de l'usuari
     * @param username Nom de l'usuari
     * @return Estadistiques de l'usuari
     */
    public UserStats loadUserStats(String username) {
        if (username == null) return null;
        UserStats stats = CtrlPersistencia.loadUserStats(username);
        if (stats == null) {
            stats = new UserStats();
            CtrlPersistencia.saveUserStats(username, stats); // Save the new stats
        }
        return stats;
    }

    /**
     * @brief Retorna tots els noms d'usuari
     * @return Noms de tots els usuaris
     */
    public ArrayList<String> getAllUsernames() {
        return ctrlPersistencia.getAllUsernames();
    }

    /**
     * @brief Actualitza les estadistiques de l'usuari
     * @param username Nom de l'usuari
     * @param stats Estadistiques de l'usuari
     * @post S'han actualitzat les estadistiques de l'usuari
     */
    public void updateUserStats(String username, UserStats stats) {
        if (username == null || stats == null) return;
        CtrlPersistencia.updateUserStats(username, stats);
    }

    /**
     * @brief Retorna el nombre de kenkens jugats
     * @pre cert
     * @post Retorna el nombre de kenkens jugats
     */
    public int getKenkensJugats() {
        String user = ctrlUsuari.getNombreUsuarioLoggeado();
        if(user == null) return 0;
        return ctrlEstadistiques.getKenkensJugats(user);
    }

    /**
     * @brief Retorna el millor temps
     * @pre cert
     * @post Retorna el millor temps
     */
    public Long getMillorTemps() {
        String user = ctrlUsuari.getNombreUsuarioLoggeado();
        if(user == null) return 0L;
        return ctrlEstadistiques.getMillorTemps(user);
    }

    /**
     * @brief Retorna la mitjana de temps
     * @pre cert
     * @post Retorna la mitjana de temps
     */
    public Long getMitjanaTemps() {
        String user = ctrlUsuari.getNombreUsuarioLoggeado();
        if(user == null) return 0L;
        return ctrlEstadistiques.getMitjanaTemps(user);
    }

    /**
     * @brief Retorna el nombre de kenkens jugats per nivell
     * @param nivell Nivell dels kenkens
     * @pre cert
     * @post Retorna el nombre de kenkens jugats per nivell
     */
    public int getKenkensJugatsPerNivell(int nivell) {
        String user = ctrlUsuari.getNombreUsuarioLoggeado();
        if(user == null) return 0;
        return ctrlEstadistiques.getKenkensJugatsPerNivell(user, nivell);
    }

    /**
     * @brief Retorna el millor temps per nivell
     * @param nivell Nivell dels kenkens
     * @pre cert
     * @post Retorna el millor temps per nivell
     */
    public Long getMillorTempsPerNivell(int nivell) {
        String user = ctrlUsuari.getNombreUsuarioLoggeado();
        if(user == null) return 0L;
        Long bestTime = ctrlEstadistiques.getMillorTempsPerNivell(user, nivell);
        return bestTime != null ? bestTime : 0;
    }

    /**
     * @brief Retorna la mitjana de temps per nivell
     * @param nivell Nivell dels kenkens
     * @pre cert
     * @post Retorna la mitjana de temps per nivell
     */
    public Long getMitjanaTempsPerNivell(int nivell) {
        String user = ctrlUsuari.getNombreUsuarioLoggeado();
        if(user == null) return 0L;
        Long avgTime = ctrlEstadistiques.getMitjanaTempsPerNivell(user, nivell);
        return avgTime != null ? avgTime : 0;
    }

    /**
     * @brief Retorna el nombre de kenkens jugats per l'usuari
     * @return Nombre d'ids de kenkens jugats per l'usuari
     */
    public List<Integer> getJocsGuardats() {
        Usuari user = ctrlUsuari.getUsuariLoguejat();
        return user.getJocsGuardats();
    }

    /**
     * @brief Retorna el nombre de kenken jugat per l'usuari amb identificador idKK
     * @return Nom del kenken amb identificador idKK
     */
    public String getKenkenName(Integer idKK){
        try {
            return ctrlKenken.getKenkenName(idKK);
        } catch (ExcepcioKenken e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @brief Retorna l'identificador del kenken a partir de l'identificador del joc
     * @param gameId Identificador del joc
     * @return Identificador del kenken
     */
    public int getKenkenIdFromGameId(int gameId) {
        String user = ctrlUsuari.getNombreUsuarioLoggeado();
        Joc joc = ctrlPersistencia.loadJoc(user, gameId);
        if (joc == null) {
            throw new IllegalStateException("El juego con id " + gameId + " no existe");
        }
        int kenkenId = joc.getKenkenId();
        return kenkenId;
    }

    /**
     * @brief Elimina el kenken amb identificador id
     */
    public void deleteKenken(int id) {
        String user = ctrlUsuari.getNombreUsuarioLoggeado();
        ctrlKenken.deleteKenken(id);
        ctrlPersistencia.deleteKenken(user, id);
        ctrlUsuari.deleteKenkenGuardat(id);
    }
}