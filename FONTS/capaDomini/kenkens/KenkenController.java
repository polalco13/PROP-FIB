package capaDomini.kenkens;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import capaDomini.excepcions.ExcepcioKenken;
import capaPersistencia.CtrlPersistencia;

public class KenkenController {
    private Map<Integer,Kenken> kenkenSet;
    KenkenGenerator KG = new KenkenGenerator();

    ///////////////////////////////////////////////// METODES PRIVATS //////////////////////////////////////////////////


    ////////////////////////////////////////////////// CONSTRUCTORES ///////////////////////////////////////////////////

    /**
     * @brief Constructora per defecte
     * @pre Cert
     * @post S'ha creat un controlador de Kenkens buit
     */
    public KenkenController() {
        kenkenSet = new HashMap<Integer,Kenken>();
    }

    ///////////////////////////////////////////////////// GETTERS /////////////////////////////////////////////////////

/**
     * @brief Retorna el nombre de Kenkens
     * @return Nombre de Kenkens
     * @pre Cert
     * @post Retorna el nombre de Kenkens
     */
    public int getNumOfKenkens() {
        return kenkenSet.size();
    }

    /**
     * @brief Retorna una copia del Kenken amb identificador idKenken
     * @param idKenken Identificador del Kenken
     * @return Kenken amb identificador idKenken
     * @pre Existeix un Kenken amb identificador idKenken
     * @post Retorna una copia del Kenken amb identificador idKenken
     */
    public Kenken getKenken(int idKenken) throws ExcepcioKenken {
        if (!kenkenSet.containsKey(idKenken)) throw new ExcepcioKenken ("El Kenken amb identificador " + idKenken + " no existeix");
        Kenken kenken = kenkenSet.get(idKenken);
        Kenken copy = new Kenken(kenken);
        return copy;
    }

    /**
     * @brief Retorna la solució del Kenken amb identificador idKenken
     * @param idKenken Identificador del Kenken
     * @return Solució del Kenken amb identificador idKenken
     * @pre Existeix un Kenken amb identificador idKenken
     * @post Retorna la solució del Kenken amb identificador idKenken
     */
    public Kenken getSolution(int idKenken) throws ExcepcioKenken {
        if (!kenkenSet.containsKey(idKenken)) {
            throw new ExcepcioKenken("El Kenken amb identificador " + idKenken + " no existeix");
        }

        Kenken k = getKenken(idKenken);
        Kenken solution = k.getSolution();
        return solution;
    }

    public int[][] getSolution(List<List<Integer>> cages) throws ExcepcioKenken {
        Kenken k = new Kenken(cages);
        k = k.getSolution();
        if (k == null) return null;

        int mida = k.getMida();
        int[][] matAns = new int[mida][mida];
        for (int i = 0; i < mida; ++i) {
            for (int j = 0; j < mida; ++j) {
                matAns[i][j] = k.getValue(i,j);
            }
        }
        return matAns;
    }

    ///////////////////////////////////////////////// METODES PUBLICS /////////////////////////////////////////////////

    /**
     * @brief Resol el Kenken amb identificador idKenken
     * @param idKenken Identificador del Kenken
     * @pre Existeix un Kenken amb identificador idKenken
     * @post S'ha resolt el Kenken amb identificador idKenken
     */
    public void resolKenken(int idKenken) throws ExcepcioKenken {
        if (!kenkenSet.containsKey(idKenken)) {
            throw new ExcepcioKenken("El Kenken amb identificador " + idKenken + " no existeix");
        }

        Kenken kenken = this.getSolution(idKenken);
    }

    /**
     * @brief Genera un Kenken amb nom nom i mida mida
     * @param nom Nom del Kenken
     * @param mida Mida del Kenken
     * @return Kenken generat
     * @pre Cert
     * @post Retorna un Kenken generat amb nom nom i mida mida
     */
    public List<List<Integer>> generarKenken(String nom, int mida) throws ExcepcioKenken {
        Kenken kenken = KG.autoGenerateKenken(mida);
        return kenken.kenkenToList();
    }

    /**
     * @brief Guarda el Kenken amb nom nom i amb les regions i operacions de la llista kenken
     * @param nom Nom del Kenken
     * @param kenken Llista de regions i operacions del Kenken
     * @return Kenken guardat
     * @exception ExcepcioKenken Si no es pot guardar el Kenken
     */
    public Kenken saveKenkenGenerat(String nom, List<List<Integer>> kenken) throws ExcepcioKenken {
        Kenken k = new Kenken(kenken);
        int id = kenkenSet.size();
        k.setNom(nom);
        k.setId(id);
        kenkenSet.put(id,k);
        return k;
    }

    /**
     * @brief Carrega el kenkens de la base de dades al controlador
     * @param kenkenDatabase Map amb els Kenkens de la base de dades
     */
    public void initializeDatabase(Map<String,File> kenkenDatabase) throws ExcepcioKenken {
        if (kenkenDatabase == null) return;
        for (Map.Entry<String,File> entry : kenkenDatabase.entrySet()) {
            String[] infoKenken = entry.getKey().split("-");    //Separem: id-nom.txt
            int id = Integer.parseInt(infoKenken[0]);
            int dotIndex = infoKenken[1].indexOf('.');          // Treiem el .txt
            String nom = infoKenken[1].substring(0, dotIndex);

            Kenken k = new Kenken(entry.getValue());
            k.setId(id);
            k.setNom(nom);
            kenkenSet.put(id,k);
        }
    }

    /**
     * @brief Guarda el Kenken del fitxer kenkenFile a la base de dades
     * @param kenkenFile Fitxer amb el Kenken
     */
    public int importKenken(File kenkenFile) throws ExcepcioKenken {
        Kenken kenken = new Kenken(kenkenFile); //El kenken es crea con el nombre del fichero
        int id = kenkenSet.size();
        kenken.setId(id);
        String nomKenken = kenkenFile.getName();
        if (nomKenken.endsWith(".txt")) {
            nomKenken = nomKenken.substring(0, nomKenken.length() - 4);
        }
        kenken.setNom(nomKenken);
        kenkenSet.put(id,kenken);
        return id;
    }

    /**
     * @brief Consulta el nom del Kenken amb identificador idKK
     * @param idKK Identificador del Kenken
     */
    public String getKenkenName(Integer idKK) throws ExcepcioKenken {
        try {
            Kenken kenken = kenkenSet.get(idKK);
            if (kenken == null) {
                throw new ExcepcioKenken("No existeix cap Kenken amb identificador " + idKK);
            }
            return kenken.getNom();
        } catch (ExcepcioKenken e) {
            return null;
        }
    }

    /**
     * @brief Elimina el Kenken amb identificador id
     * @param id Identificador del Kenken
     */
    public void deleteKenken(int id) {
        kenkenSet.remove(id);
    }
}