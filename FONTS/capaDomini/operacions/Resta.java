package capaDomini.operacions;
import capaDomini.kenkens.Cell;

import java.util.Set;
import java.util.List;

public class Resta extends Operacions {
    /**
     * @brief Constructora per defecte
     * @param op identifica quina operacio es vol realitzar
     * @post Crea una Resta amb l'identificador op
     */
    public Resta(int op) {
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
        for (int i = 1; i <= maxNumber; ++i) {
            if (result + i <= maxNumber) {
                values.add(i);
                values.add(result + i);
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
        int operador1 = regio.get(0).getValue();
        int operador2 = regio.get(1).getValue();
        return Math.abs(operador1 - operador2);
    }

    /**
     * Retorna si la regio es valida.
     * @param mida
     * @return true si la regio es valida, false altrament
     */
    @Override
    public boolean regioValida(int mida) {
        return mida == 2;
    }
}