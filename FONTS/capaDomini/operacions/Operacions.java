package capaDomini.operacions;

import capaDomini.kenkens.Cell;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public abstract class Operacions {
    private final int op;
    private static final Map<Integer, Operacions> operacionsMap = new HashMap<>();
    static {
        operacionsMap.put(0, new Igual(0));
        operacionsMap.put(1, new Suma(1));
        operacionsMap.put(2, new Resta(2));
        operacionsMap.put(3, new Multiplicacio(3));
        operacionsMap.put(4, new Divisio(4));
        operacionsMap.put(5, new Modul(5));
        operacionsMap.put(6, new Arrel(6));
    }

    ////////////////////////////////////////////////// CONSTRUCTORES ///////////////////////////////////////////////////

    /**
     * @brief Constructora per defecte
     * @param op identifica quina operacio es vol realitzar
     * @post Crea una operacio amb l'identificador op
     */
    public Operacions (int op) {
        this.op = op;
    }

    ///////////////////////////////////////////////////// GETTERS //////////////////////////////////////////////////////

    /**
     * @brief Get id 'op' de l'operacio
     * @return identificador de l'operacio
     */
    public int getOperationID() {
        return op;
    }

    /**
     * Retorna si la regio es valida.
     * @param mida
     * @return true si la regio es valida, false altrament
     */
    public abstract boolean regioValida(int mida);

    /**
     * @brief Get operacio concreta
     * @param op identificador de l'operacio
     * @return una operacio determinada en funcio de l'identificador op
     */
    public static Operacions getOperation (int op) { // FACTORY METHOD
        Operacions operacio = operacionsMap.get(op);
        if (operacio == null) throw new RuntimeException("Operacio no valida");
        return operacio;
    }

    /**
     * @brief Busca els possibles valors d'una cel.la de la regio
     * @param result valor cel.la que ja esta plena
     * @param values set d'enters amb tots els possibles valors
     * @post s'afegeixen al set 'values' els possibles valors
     */
    public void getPossibleValues (int result, Set<Integer> values, int maxNumber) {}

    /**
     * @brief Busca els possibles valors d'una cel.la de la regio
     * @param result valor cel.la que ja esta plena
     * @param idx posicio de la llista a partir de la qual es calculen els possibles valors
     * @param count maximes repeticions d'un nombre
     * @param values set d'enters amb tots els possibles valors
     * @param aux llista que s'utilitza per construir les possibles combinacions
     * @post s'afegeixen al set 'values' els possibles valors
     */
    public void getPossibleValues (int result, int idx, int count, Set<Integer> values, ArrayList<Integer> aux, int[] counter) {}

    ///////////////////////////////////////////////////// METODES //////////////////////////////////////////////////////

    /**
     * @brief Calcula el resultat de l'operacio d'una regio
     * @param regio llista de cel.les que formen la regio
     * @return resultat de l'operacio
     */
    public abstract float calcular (List<Cell> regio);
}