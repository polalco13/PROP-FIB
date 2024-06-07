package capaPresentacio;

import capaDomini.controladors.ControladorDomini;
import capaDomini.excepcions.ExcepcioJoc;
import capaDomini.excepcions.ExcepcioKenken;
import capaDomini.excepcions.ExcepcioUsuari;
import capaDomini.rankings.Ranking;
import capaPresentacio.views.loginView;

import java.io.File;
import java.util.List;

public class CtrlPresentacio {
    private static final ControladorDomini CD = new ControladorDomini();

    public CtrlPresentacio() {}

    /**
     * @brief Inicialitza el controlador de presentació.
     * @post Es mostra la finestra de login.
     */
    public static void ini() {
        loginView lv = new loginView();
        lv.setVisible(true);
    }

    ////////////////////////////////////////////////////// USUARI //////////////////////////////////////////////////////

    /**
     * @brief Comprova si l'usuari existeix.
     * @param nom Nom de l'usuari a comprovar.
     * @return True si l'usuari existeix, false altrament.
     */
    public static boolean existeixUsuariConcret(String nom) {
        return CD.existeixUsuariConcret(nom);
    }

    /**
     * @brief Crea un usuari.
     * @param nom Nom de l'usuari a crear.
     * @param password Contrasenya de l'usuari a crear.
     * @throws ExcepcioUsuari Si ja existeix un usuari amb aquest nom.
     */
    public static void crearUsuari(String nom, String password) throws ExcepcioUsuari {
        try {
            CD.crearUsuari(nom, password);
        } catch (ExcepcioUsuari | ExcepcioKenken e) {
            System.out.println("CP.crearUsuari " + e.getMessage());
        }
    }

    /**
     * @brief Loggeja un usuari.
     * @param nom Nom de l'usuari a loggejar.
     * @param password Contrasenya de l'usuari a loggejar.
     * @throws ExcepcioUsuari Si l'usuari no existeix o la contrasenya és incorrecta.
     * @throws ExcepcioKenken Si hi ha hagut un error al carregar els kenkens de l'usuari.
     */
    public static void login(String nom, String password) throws ExcepcioUsuari, ExcepcioKenken {
        CD.login(nom, password);
    }

    /**
     * @brief Obtenir el nom de l'usuari loggejat.
     * @return Nom de l'usuari loggejat.
     */
    public static String getNombreUsuarioLoggeado() {
        return CD.getNombreUsuarioLoggeado();
    }

    /**
     * @brief Obtenir els kenkens guardats de l'usuari loggejat.
     * @return Llista amb els identificadors dels kenkens guardats de l'usuari loggejat.
     */
    public static List<Integer> getKenkensGuardats() {
        return CD.getUsuariLoguejat().getKenkensGuardats();
    }

    /**
     * @brief eliminar perfil d'usuari loguejat
     * @param contrasenya contrasenya de l'usuari
     * @throws ExcepcioUsuari si la contrasenya no és correcta
     * @post s'elimina el perfil de l'usuari loguejat
     */
    public static void eliminarPerfilUsuario(String contrasenya) throws ExcepcioUsuari {
        CD.esborrarUsuari(contrasenya);
    }

    /**
     * @brief Comprova si la contrasenya és correcta.
     * @param contrasenya Contrasenya a comprovar.
     * @return True si la contrasenya és correcta, false altrament.
     * @throws ExcepcioUsuari Si hi ha hagut un error al comprovar la contrasenya.
     */
    public static boolean comprovarContrasenya(String contrasenya) throws ExcepcioUsuari {
        return CD.comprovarContrasenya(contrasenya);
    }

    /**
     * @brief Canvia la contrasenya de l'usuari loggejat.
     * @param contrasenyaActual Contrasenya actual de l'usuari.
     * @param novaContrasenya Nova contrasenya de l'usuari.
     * @throws ExcepcioUsuari Si la contrasenya actual no és correcta.
     */
    public static void canviarContrasenya(String contrasenyaActual, String novaContrasenya) throws ExcepcioUsuari {
        CD.canviarContrasenya(contrasenyaActual, novaContrasenya);
    }

    /**
     * @brief Obtenir la contrasenya de l'usuari loggejat.
     * @return Contrasenya de l'usuari loggejat.
     */
    public static String getPassword() {
        return CD.getPasswordUserLoged();
    }

    /**
     * @brief Tancar la sessió de l'usuari loggejat.
     * @throws ExcepcioUsuari Si hi ha hagut un error al tancar la sessió.
     */
    public static void logout() throws ExcepcioUsuari {
        CD.logout();
    }

    ///////////////////////////////////////////////////// RANKING //////////////////////////////////////////////////////

    /**
     * @brief Obtenir el ranking per dificultat.
     * @param dificultat Dificultat del ranking a obtenir.
     * @return Llista amb les entrades del ranking per la dificultat especificada.
     */
    public static List<Ranking.RankingEntry> getRankingPerDificultat(int dificultat) {
        CD.calcularRanking(); // Asegurarse de que el ranking esté calculado
        return CD.getRanking().generarRanking().get(dificultat);
    }


    /////////////////////////////////////////////////////// JOC ////////////////////////////////////////////////////////

    /**
     * @brief Assigna un valor a una casella del taulell.
     * @param posX Posició X de la casella.
     * @param posY Posició Y de la casella.
     * @param valor Valor a assignar.
     * @return True si el taulell esta omplert, false altrament.
     */
    public static boolean assignaValor(int posX, int posY, int valor) {
        boolean acabat = false;
        try {
            acabat = CD.assignaValor(posX, posY, valor);
        } catch(ExcepcioKenken | ExcepcioJoc e) {
            System.out.println("CP.assignaValor " + e.getMessage());
        }
        return acabat;
    }

    /**
     * @brief Acaba la partida.
     * @post Pausa el temporitzador, envia les dades a les estadistiques de l'usuari i acaba la partida.
     */
    public static void acabaPartida() {
        try {
            CD.acabaPartida();
        } catch (ExcepcioJoc e) {
            System.out.println("CP.acabaPartida " + e.getMessage());
        }
    }

    /**
     * @brief Desassigna un valor a una casella del taulell.
     * @param posX Posició X de la casella.
     * @param posY Posició Y de la casella.
     * @param v Valor a desassignar.
     * @post Desassigna el valor de la casella especificada.
     */
    public static void desassignaValor(int posX, int posY, int v) {
        try {
            CD.desassignaValor(posX, posY, v);
        } catch(ExcepcioKenken | ExcepcioJoc e) {
            System.out.println("CP.desassignaValor " + e.getMessage());
        }
    }

    /**
     * @brief Pausa el temporitzador del joc actual.
     */
    public static void pausa() {
        try {
            CD.pausaTemps();
        } catch (ExcepcioJoc e) {
            System.out.println("CP.pausa "+ e.getMessage());
        }
    }

    /**
     * @brief Continua el temporitzador del joc actual.
     */
    public static void comencaTemps() {
        try {
            CD.comencaTemps();
        }
        catch (ExcepcioJoc e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @brief Crear un joc.
     * @param idKenken Identificador del kenken.
     * @post Crea un joc amb el kenken especificat.
     */
    public static void creaJoc(int idKenken) {
        try {
            CD.creaJoc(idKenken);
        } catch (ExcepcioKenken e) { //kenken no existe, no hauria de passar mai
            System.out.println(e.getMessage());
        }
        comencaTemps(); //comenca temps
    }

    /**
     * @brief Obtenir el temps transcorregut del joc actual.
     * @return Temps transcorregut del joc actual en format hh:mm:ss.
     */
    public static String getFormattedTime() {
        try {
            return CD.getFormattedTime();
        } catch (ExcepcioJoc e) {
            System.out.println("CP.getFormattedTime " + e.getMessage());
            return null;
        }
    }

    /**
     * @brief Guarda el joc actual.
     * @post Guarda el joc actual a la base de dades.
     */
    public static void guardarJoc() {
        try {
            CD.guardaJoc();
        } catch (ExcepcioUsuari | ExcepcioJoc e) {
            System.out.println("CP.guardarJoc " + e.getMessage());
        }
    }

    /**
     * @brief Obtenir els jocs guardats de l'usuari loggejat.
     * @return Llista amb els identificadors dels jocs guardats de l'usuari loggejat.
     */
    public static List<Integer> getJocsGuardats() {
        return CD.getJocsGuardats();
    }

    /**
     * @brief Carregar un joc guardat.
     * @param id Identificador del joc guardat.
     * @post Carrega el joc guardat amb l'identificador especificat.
     */
    public static void continuaJoc(int idJoc) {
        CD.continuaJoc(idJoc);
        comencaTemps();
    }

    /**
     * @brief Obtenir nombre de pistes restants.
     */
    public static int getPistesRestants() {
        try {
            return CD.getPistesRestants();
        } catch (ExcepcioJoc e) {
            System.out.println("CP.getPistesRestants " + e.getMessage());
            return -1;
        }
    }

    /**
     * @brief Utilitza una pista.
     * @return Nombre de pistes restants.
     */
    public static int usaPista() {
        try {
            return CD.usaPista();
        } catch (ExcepcioJoc e) {
            System.out.println("CP.usaPista " + e.getMessage());
            return -1;
        }
    }

    ////////////////////////////////////////////////////// KENKEN //////////////////////////////////////////////////////

    /**
     * @brief Genera un kenken.
     * @param nom Nom del kenken.
     * @param mida Mida del kenken.
     * @return Llista amb les cel·les del kenken generat.
     * @throws ExcepcioKenken Si hi ha hagut un error al generar el kenken.
     */
    public static List<List<Integer>> generarKenken(String nom, int mida) throws ExcepcioKenken {
        return CD.generarKenken(nom,mida);
    }

    /**
     * @brief Guarda un kenken generat.
     * @param nom Nom del kenken.
     * @param kenken Llista amb les cel·les del kenken.
     * @throws ExcepcioKenken Si hi ha hagut un error al guardar el kenken.
     */
    public static void saveKenkenGenerat(String nom, List<List<Integer>> kenken) throws ExcepcioKenken {
        CD.saveKenkenGenerat(nom, kenken);
    }

    /**
     * @brief Carrega un kenken.
     * @param id Identificador del kenken.
     * @param nom Nom del kenken.
     * @param kenken Llista amb les cel·les del kenken.
     * @throws ExcepcioKenken Si hi ha hagut un error al carregar el kenken.
     */
    public static List<List<Integer>> loadKenken() {
        try {
            return CD.loadKenken();
        } catch (ExcepcioJoc e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * @brief Importa un fitxer de kenken.
     * @param selectedFile Fitxer a importar.
     * @pre El fitxer ha de seguir el format de kenken de l'assignatura.
     */
    public static void importFile(File selectedFile) throws ExcepcioKenken {
        CD.importFile(selectedFile);
    }

    /**
     * @brief Obte la solució d'un kenken.
     * @param cages Llista amb les cel·les del kenken.
     * @return Matriu amb la solució del kenken. Es null si no troba soluciò.
     */
    public static int[][] getSolution(List<List<Integer>> cages) throws ExcepcioKenken {
        return CD.getSolution(cages);
    }

    /**
     * @brief Obte el nom d'un kenken.
     * @param i Identificador del kenken.
     * @return Nom del kenken.
     */
    public static String getKenkenName(Integer i) {
        return CD.getKenkenName(i);
    }

    /**
     * @brief Obte el nom d'un kenken a partir de l'identificador d'un joc.
     * @param gameId Identificador del joc.
     * @return Nom del kenken.
     */
    public static String getKenkenNameFromGameId(int gameId) {
        int kenkenId = CD.getKenkenIdFromGameId(gameId);
        //System.out.println("KenkenId: " + kenkenId);
        String kenkenName = CD.getKenkenName(kenkenId);
        return kenkenName;
    }

    /////////////////////////////////////////////////// ESTADISTIQUES //////////////////////////////////////////////////

    /**
     * @brief Obtenir el nombre de kenkens jugats.
     * @return Nombre de kenkens jugats.
     */
    public static int getKenkensJugats() {
        return CD.getKenkensJugats();
    }

    /**
     * @brief Obtenir el millor temps absolut.
     * @return Millor temps absolut.
     */
    public static Long getMillorTempsAbsolut() {
        return CD.getMillorTemps();
    }

    /**
     * @brief Obtenir la mitjana de temps absolut.
     * @return Mitjana de temps absolut.
     */
    public static Long getMitjanaTempsAbsolut() {
        return CD.getMitjanaTemps();
    }

    /**
     * @brief Obtenir el nombre de kenkens jugats per nivell.
     * @param nivell Nivell dels kenkens.
     * @return Nombre de kenkens jugats per nivell.
     */
    public static int getKenkensJugatsPerNivell(int nivell) {
        return CD.getKenkensJugatsPerNivell(nivell);
    }

    /**
     * @brief Obtenir el millor temps per nivell.
     * @param nivell Nivell dels kenkens.
     * @return Millor temps per nivell.
     */
    public static Long getMillorTempsPerNivell(int nivell) {
        return CD.getMillorTempsPerNivell(nivell);
    }

    /**
     * @brief Obtenir la mitjana de temps per nivell.
     * @param nivell Nivell dels kenkens.
     * @return Mitjana de temps per nivell.
     */
    public static Long getMitjanaTempsPerNivell(int nivell) {
        return CD.getMitjanaTempsPerNivell(nivell);
    }
}