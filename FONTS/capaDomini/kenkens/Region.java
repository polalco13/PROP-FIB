package capaDomini.kenkens;
import java.util.*;
import capaDomini.operacions.*;
import capaDomini.excepcions.ExcepcioKenken;

public class Region {
    private final int size;
    private final int result;
    private int[] counter;
    private final Operacions op;
    private List<Cell> cells;
    private Set<Integer> possibleValues;

    ///////////////////////////////////////////////// METODES PRIVATS /////////////////////////////////////////////////

    /**
     * @brief Comparador de regions per nombre de valors possibles
     */
    public static final Comparator<Region> numOfPossibleValues = new Comparator<Region>() {
        public int compare(Region r1, Region r2) {
            return r1.possibleValues.size() - r2.possibleValues.size();
        }
    };

    ////////////////////////////////////////////////// CONSTRUCTORES //////////////////////////////////////////////////

    /**
     * @brief Constructora d'una regio
     * @param op Operació de la regio
     * @param result Resultat de la regio
     * @param size Mida de la regio
     * @throws ExcepcioKenken si la mida de la regio és menor o igual a 0 o el resultat de la regio és negatiu
     * @post Crea una regio amb l'operació, el resultat i la mida donats
     */
    public Region(int op, int result, int size) throws ExcepcioKenken {
        if (size <= 0) throw new ExcepcioKenken("La mida de la regio ha de ser major que 0");
        if (result < 0) throw new ExcepcioKenken("El resultat de la regio ha de ser major o igual a 0");

        this.size = size;
        this.result = result;
        this.op = Operacions.getOperation(op);
        if (!this.op.regioValida(size)) throw new ExcepcioKenken("La regio no es valida per aquesta operacio");

        this.cells = new ArrayList<>(size);
        this.possibleValues = new HashSet<Integer>();
    }

    /**
     * @brief Constructora d'una regio per copia
     * @param r Regió a copiar
     * @post Crea una regio amb la mateixa informació que la regio donada
     */
    public Region(Region r) {
        this.op = Operacions.getOperation(r.getOperation());
        this.size = r.getSize();
        this.result = r.getResult();
        this.counter = new int[r.counter.length];

        this.cells = new ArrayList<>(size);
        for (Cell c : r.cells) {
            this.cells.add(new Cell(c));
        }

        this.possibleValues = new HashSet<Integer>();
        for (Integer i : r.getPossibleValues()) this.possibleValues.add(i);
    }


    ///////////////////////////////////////////////////// GETTERS /////////////////////////////////////////////////////

    /**
     * @brief Consultora del nombre de possibles valors de la regio
     * @return Nombre de possibles valors de la regio
     */
    public Set<Integer> getPossibleValues() {
        return possibleValues;
    }

    /**
     * @brief Consultora de la cel·la de la regio amb índex idx
     * @param idx Index de la cel·la
     * @return Cel·la de la regio amb índex idx
     */
    public Cell getCell(int idx) {
        return cells.get(idx);
    }

    /**
     * @brief Consultora de l'operacio de la regio
     * @return Operacio de la regio
     */
    public int getOperation() {
        return op.getOperationID();
    }

    /**
     * @brief Consultora del resultat de la regio
     * @return Resultat de la regio
     */
    public int getResult() {
        return result;
    }

    /**
     * @brief Consultora de la mida de la regio
     * @return Mida de la regio
     */
    public int getSize() {
        return size;
    }


    ///////////////////////////////////////////////////// SETTERS //////////////////////////////////////////////////////

    /**
     * @brief Assigna el comptador de la regio
     * @param boardSize Mida del tauler
     */
    public void setCounter(int boardSize) {
        // Inicialitza el comptador de la regio, aquest marca qunates vegades es pot utilitzar cada valor com a màxim
        this.counter = new int[boardSize];
        for (int i = 0; i < boardSize; ++i) counter[i] = (size + 1) / 2;
    }

    /**
     * @brief Genera els valors possibles de la regio
     *
     */
    public void setPossibleValues() {
        // Genera el valors posibles de la regio
        if (op.getOperationID() == 1 || op.getOperationID() == 3) op.getPossibleValues(result, 1, size, possibleValues, new ArrayList<>(), counter);
        else op.getPossibleValues(result, possibleValues, counter.length);

        // Assigna els valors possibles a les cel·les buides
        for (Cell c : cells) {
            Set<Integer> copy = new HashSet<>(possibleValues);
            c.setPossibleValues(copy);
        }
    }

    /**
     * @brief Assigna les cel·les de la regio
     * @param cells Llista de cel·les de la regio
     */
    public void setCells (List<Cell> cells) {
        this.cells = cells;
    }

    ///////////////////////////////////////////////// MÈTODES PÚBLICS /////////////////////////////////////////////////

    /**
     * @brief Afegeix una cel·la a la regio
     * @param c Cel·la a afegir
     * @post La cel·la c és afegida a la regio
     */
    public void addCell(Cell c) {
        cells.add(c);
    }

    /**
     * @brief Comprova si la regio és vàlida
     * @return Cert si el calcul de la regio és igual al resultat, fals altrament
     */
    public boolean isValid() {
        return op.calcular(cells) == result;
    }
}