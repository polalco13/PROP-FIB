package capaDomini.operacions;
import capaDomini.kenkens.Cell;

import java.util.Set;
import java.util.List;

public class Arrel extends Operacions {

    /**
     * @brief Constructora per defecte
     * @param op identifica quina operacio es vol realitzar
     * @post Crea una Arrel amb l'identificador op
     */
    public Arrel(int op) {
        super(op);
    }

    /**
     * @brief Busca els possibles valors d'una cel.la de la regio
     * @param result valor cel.la que ja esta plena
     * @param values set d'enters amb tots els possibles valors
     * @post s'afegeixen al set 'values' els possibles valors
     */
    @Override
    public void getPossibleValues (int result, Set<Integer> values, int maxNumber) {
        values.add(result * result);
    }

    /**
     * @brief Calcula el resultat de l'operacio d'una regio
     * @param regio llista de cel.les que formen la regio
     * @return resultat de l'operacio
     */
    @Override
    public float calcular (List<Cell> regio) {
        int num = regio.get(0).getValue();
        return ((float) Math.sqrt(num));
    }

    /**
     * Retorna si la regio es valida.
     * @param mida
     * @return true si la regio es valida, false altrament
     */
    @Override
    public boolean regioValida(int mida) {
        return mida == 1;
    }
}
