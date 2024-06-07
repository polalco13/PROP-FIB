package capaDomini.operacions;
import capaDomini.kenkens.Cell;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class Multiplicacio extends Operacions {
    /**
     * @brief Constructora per defecte
     * @param op identifica quina operacio es vol realitzar
     * @post Crea una Multiplicacio amb l'identificador op
     */
    public Multiplicacio(int op) {
        super(op);
    }

    /**
     * @brief Busca els possibles valors d'una cel.la de la regio
     * @param result valor cel.la que ja esta plena
     * @param values set d'enters amb tots els possibles valors
     * @post s'afegeixen al set 'values' els possibles valors
     */
    @Override
    public void getPossibleValues (int result, int idx, int count, Set<Integer> values, ArrayList<Integer> aux, int[] counter) {
        if (result == 1 && count == 0) {
            values.addAll(aux);
            return;
        }
        if (result < 1 || count < 0) return;

        for (int i = idx; i <= counter.length; ++i) {
            if (result % i == 0 && counter[i-1] > 0) {
                aux.add(i);
                --counter[i-1];
                getPossibleValues(result / i, i, count - 1, values, aux, counter);
                ++counter[i-1];
                aux.remove(aux.size() - 1);
            }
        }
    }

    /**
     * @brief Calcula el resultat de l'operacio d'una regio
     * @param regio llista de cel.les que formen la regio
     * @return resultat de l'operacio
     */
    @Override
    public float calcular (List<Cell> regio) {
        int multi = 1;
        for (Cell c : regio) multi *= c.getValue();
        return multi;
    }

    /**
     * Retorna si la regio es valida.
     * @param mida
     * @return true si la regio es valida, false altrament
     */
    @Override
    public boolean regioValida (int mida) {
        return mida >= 2;
    }
}