package capaDomini.usuaris;

import capaDomini.excepcions.ExcepcioUsuari;

import java.util.List;

/**
 * The `CtrlUsuari` class represents a controller for managing user operations.
 * It provides methods for creating users, deleting users, changing passwords,
 * logging in, logging out, and performing other user-related operations.
 */
public class CtrlUsuari {

    private Usuari usuariLogueado;

    /**
     * @brief Crea un controlador d'usuari
     * @post S'ha creat un controlador d'usuari
     */
    public CtrlUsuari() {
        usuariLogueado = null;
    }

    /**
     * @brief Crea un usuari amb el nom i contrasenya donats
     * @param nom Nom de l'usuari
     * @param contrasenya Contrasenya de l'usuari
     * @pre El nom d'usuari no pot ser buit
     * @post S'ha creat un usuari amb el nom i contrasenya donats
     */
    public Usuari creaUsuari(String nom, String contrasenya) throws ExcepcioUsuari {
        if (nom.isEmpty() || contrasenya.isEmpty()) {
            throw new ExcepcioUsuari("El nom d'usuari i/o la contrasenya no poden ser buits");
        }
        Usuari u = new Usuari(nom, contrasenya);
        usuariLogueado = u;
        return u;
    }

    /**
     * @brief Esborra l'usuari loguejat
     * @param contrasenya Contrasenya de l'usuari loguejat
     * @pre L'usuari ha d'estar loguejat
     * @pre La contrasenya ha de ser correcta
     * @post S'ha esborrat l'usuari loguejat
     */
    public String esborrarUsuari(String contrasenya) throws ExcepcioUsuari {
        if (usuariLogueado == null) {
            throw new ExcepcioUsuari("No hi ha cap usuari loguejat");
        }
        if (!usuariLogueado.getContrasenya().equals(contrasenya)) {
            throw new ExcepcioUsuari("La contrasenya es incorrecta");
        }
        String nom = usuariLogueado.getNom();
        usuariLogueado = null;
        return nom;
    }

    /**
     * @brief Canvia la contrasenya de l'usuari loguejat
     * @param contrasenyaActual Contrasenya actual de l'usuari loguejat
     * @param novaContrasenya Nova contrasenya de l'usuari loguejat
     * @pre L'usuari ha d'estar loguejat
     * @pre La contrasenya actual ha de ser correcta
     * @post S'ha canviat la contrasenya de l'usuari loguejat
     */
    public void canviarContrasenya(String contrasenyaActual, String novaContrasenya) throws ExcepcioUsuari {
        usuariLogueado.setContrasenya(novaContrasenya);
    }

    /**
     * @brief Logueja l'usuari amb el nom i contrasenya donats
     * @param nom Nom de l'usuari
     * @param contrasenya Contrasenya de l'usuari
     * @pre L'usuari ha d'existir
     * @pre La contrasenya ha de ser correcta
     * @post S'ha loguejat l'usuari
     * @return Cert si l'usuari s'ha loguejat correctament, fals altrament
     */
    public void login(String nom, String contrasenya, String[] userData) throws ExcepcioUsuari {
        if (userData[1] == null || !userData[1].equals(contrasenya)) {
            throw new ExcepcioUsuari("La contrasenya es incorrecta");
        }
        usuariLogueado = new Usuari(userData[0], userData[1]);
    }


    /**
     * @brief Indica si hi ha algun usuari loguejat
     * @pre Cert
     * @post Cert si hi ha algun usuari loguejat, fals altrament
     */
    public boolean isUsuariLoguejat() {
        return usuariLogueado != null;
    }

    /**
     * @brief Retorna l'usuari loguejat
     * @pre Cert
     * @post Retorna l'usuari loguejat
     */
    public Usuari getUsuariLoguejat() {
        return usuariLogueado;
    }

    /**
     * @brief Guarda el joc amb l'identificador donat
     * @param joc Identificador del joc
     * @pre L'usuari ha d'estar loguejat
     * @post S'ha guardat el joc amb l'identificador donat
     */
    public void guardarJoc(Integer joc) throws ExcepcioUsuari {
        if (usuariLogueado == null) {
            throw new ExcepcioUsuari("No hi ha cap usuari loguejat");
        }
        usuariLogueado.agregarJoc(joc);
    }

    /**
     * @brief Deslogueja l'usuari
     * @pre Cert
     * @post S'ha desloguejat l'usuari
     */
    public void logout() throws ExcepcioUsuari {
        if (usuariLogueado == null) {
            throw new ExcepcioUsuari("No hi ha cap usuari loguejat");
        }
        usuariLogueado = null;
    }

    /**
     * @brief Selecciona un kenken de l'usuari loguejat
     * @param kenkenId Identificador del kenken
     * @pre L'usuari ha d'estar loguejat
     * @pre L'usuari ha de tenir el kenken amb l'identificador donat
     * @post S'ha seleccionat el kenken amb l'identificador donat
     * @return Identificador del kenken seleccionat
     */
    public Integer seleccionarKenken(Integer kenkenId) throws ExcepcioUsuari {
        if (usuariLogueado == null) {
            throw new ExcepcioUsuari("No hi ha cap usuari loguejat");
        }
        return usuariLogueado.getKenken(kenkenId);
    }

    /**
     * @brief Obte el nom de l'usuari loguejat
     * @pre L'usuari ha d'estar loguejat
     * @post Retorna el nom de l'usuari loguejat
     */
    public String getNombreUsuarioLoggeado() {
        if (usuariLogueado == null) {
            return null;
        }
        return usuariLogueado.getNom();
    }

    /**
     * @brief Obte la contrasenya de l'usuari loguejat
     * @pre L'usuari ha d'estar loguejat
     * @post Retorna la contrasenya de l'usuari loguejat
     */
    public String getContrasenyaUsuariLoguejat() {
        return usuariLogueado.getContrasenya();
    }

    /**
     * @brief Afegeix un kenken a la llista de kenkens guardats de l'usuari
     * @param kenken Kenken a afegir
     * @pre El kenken no ha d'estar a la llista de kenkens guardats de l'usuari
     * @post S'ha afegit el kenken a la llista de kenkens guardats de l'usuari
     */
    public void deleteJocGuardat(int id) {
        usuariLogueado.deleteJoc(id);
    }

    /**
     * @brief Afegeix un kenken a la llista de kenkens guardats de l'usuari
     * @param kenken Kenken a afegir
     * @pre El kenken no ha d'estar a la llista de kenkens guardats de l'usuari
     * @post S'ha afegit el kenken a la llista de kenkens guardats de l'usuari
     */
    public void deleteKenkenGuardat(int id) {
        usuariLogueado.deleteKenken(id);
    }
}
