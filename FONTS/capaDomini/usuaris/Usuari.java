package capaDomini.usuaris;
import java.util.ArrayList;
import java.util.List;

/**
 * The `Usuari` class represents a user in the system. It contains information
 * about the user, such as their username, password, and the Kenkens and games
 * they have saved.
 */
public class Usuari {

    private String nom;
    private String contrasenya;
    protected List<Integer> kenkensGuardats;
    protected List<Integer> jocsGuardats;

    /**
     * @brief Crea un usuari
     * @post S'ha creat un usuari
     */
    public Usuari() {
        this.kenkensGuardats = new ArrayList<Integer>();
        this.jocsGuardats = new ArrayList<Integer>();
    }

    /**
     * @brief Crea un usuari amb el nom i contrasenya donats
     * @param nom Nom de l'usuari
     * @param contrasenya Contrasenya de l'usuari
     * @pre El nom d'usuari no pot ser buit
     * @post S'ha creat un usuari amb el nom i contrasenya donats
     */
    public Usuari(String nom, String contrasenya) {
        this.nom = nom;
        this.contrasenya = contrasenya;
        this.kenkensGuardats = new ArrayList<Integer>();
        this.jocsGuardats = new ArrayList<Integer>();
    }

    /**
     * @brief Crea un usuari amb el nom donat
     * @param nom Nom de l'usuari
     * @pre El nom d'usuari no pot ser buit
     * @post S'ha creat un usuari amb el nom donat
     */
    public Usuari(String nom) { //stub para main, cambiamos luego
        this.nom = nom;
        this.kenkensGuardats = new ArrayList<Integer>();
        this.jocsGuardats = new ArrayList<Integer>();
    }

    /**
     * @brief Retorna el nom de l'usuari
     * @return Nom de l'usuari
     * @post Retorna el nom de l'usuari
     */
    public String getNom() {
        return nom;
    }

    /**
     * @brief Canvia el nom de l'usuari
     * @param nom Nou nom de l'usuari
     * @pre El nou nom d'usuari no pot ser buit
     * @post S'ha canviat el nom de l'usuari
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @brief Canvia la contrasenya de l'usuari
     * @param contrasenya Nova contrasenya de l'usuari
     * @pre La nova contrasenya no pot ser buida
     * @post S'ha canviat la contrasenya de l'usuari
     */
    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    /**
     * @brief Retorna la contrasenya de l'usuari
     * @post Retorna la contrasenya de l'usuari
     */
    public String getContrasenya() {
        return contrasenya;
    }

    /**
        * @brief Afegeix un kenken a la llista de kenkens guardats de l'usuari
     * @param kenken Kenken a afegir
     * @pre El kenken no ha d'estar a la llista de kenkens guardats de l'usuari
     * @post S'ha afegit el kenken a la llista de kenkens guardats de l'usuari
     */
    public void agregarKenken(Integer kenken) {
        kenkensGuardats.add(kenken);
    }

    /**
     * @brief Afegeix un joc a la llista de jocs guardats de l'usuari
     * @param joc Joc a afegir
     * @pre El joc no ha d'estar a la llista de jocs guardats de l'usuari
     * @post S'ha afegit el joc a la llista de jocs guardats de l'usuari
     */
    public void agregarJoc(Integer joc) {
        jocsGuardats.remove(joc);
        jocsGuardats.add(joc);
    }

    /**
     * @brief Retorna la llista de kenkens guardats de l'usuari
     * @return Llista de kenkens guardats de l'usuari
     * @post Retorna la llista de kenkens guardats de l'usuari
     */
    public List<Integer> getKenkensGuardats() {
        return kenkensGuardats;
    }

    /**
     * @brief Retorna la llista de jocs guardats de l'usuari
     * @return Llista de jocs guardats de l'usuari
     * @post Retorna la llista de jocs guardats de l'usuari
     */
    public List<Integer> getJocsGuardats() {
        return jocsGuardats;
    }

    /**
     * @brief Troba un kenken guardat de l'usuari
     * @param id Identificador del kenken
     * @return Kenken guardat
     * @pre El kenken ha d'estar a la llista de kenkens guardats de l'usuari
     * @post Retorna el kenken guardat
        */
    public Integer getKenken(Integer id) {
        if (!this.kenkensGuardats.contains(id)) {
            throw new IllegalArgumentException("El kenken no existeix");
        }
        return id;
    }

    /**
     * @brief Troba un joc guardat de l'usuari
     * @param id Identificador del joc
     * @return Joc guardat
     * @pre El joc ha d'estar a la llista de jocs guardats de l'usuari
     * @post Retorna el joc guardat
     */
    public void initializeDatabaseKenkens(List<Integer> kenkens) {
        if (kenkens == null) {
            return;
        }
        this.kenkensGuardats = kenkens;
    }

    /**
     * @brief Inicialitza la llista de jocs guardats de l'usuari
     * @param jocs Llista de jocs guardats
     * @pre Cert
     * @post S'ha inicialitzat la llista de jocs guardats de l'usuari
     */
    public void initializeDatabaseJocs(List<Integer> jocs) {
        if (jocs == null) {
            return;
        }
        this.jocsGuardats = jocs;
    }

    /**
     * @brief Troba un joc guardat de l'usuari
     * @param id Identificador del joc
     * @return Joc guardat
     * @pre El joc ha d'estar a la llista de jocs guardats de l'usuari
     * @post Retorna el joc guardat
     */
    public void deleteJoc(Integer id) {
        this.jocsGuardats.remove(id);
    }

    /**
     * @brief Troba un kenken guardat de l'usuari
     * @param id Identificador del kenken
     * @return Kenken guardat
     * @pre El kenken ha d'estar a la llista de kenkens guardats de l'usuari
     * @post Retorna el kenken guardat
     */
    public void deleteKenken(int id) {
        this.kenkensGuardats.remove(id);
    }
}