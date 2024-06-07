package capaDomini.kenkens;
import capaDomini.excepcions.ExcepcioKenken;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cell {
    private final int row;
    private final int column;
    private int region;
    private int valor;
    private Set<Integer> possibleValues;

    ////////////////////////////////////////////////// CONSTRUCTORES //////////////////////////////////////////////////

    /**
     * @brief Constructora d'una cel.la
     * @param row Fila de la cel.la
     * @param col Columna de la cel.la
     * @param valor Valor de la cel.la
     * @post Crea una cel.la amb la fila, columna i valor donats
     */
    public Cell(int row, int col, int valor) {
        this.row = row;
        this.column = col;
        this.valor = valor;
        this.region = -1;
        this.possibleValues = new HashSet<Integer>();
    }

    /**
     * @brief Constructora d'una cel.la
     * @param region Regió de la cel.la
     * @param row Fila de la cel.la
     * @param col Columna de la cel.la
     * @param valor Valor de la cel.la
     * @post Crea una cel.la amb la regió, fila, columna i valor donats
     */
    public Cell(int region, int row, int col, int valor) {
        this.row = row;
        this.column = col;
        this.valor = valor;
        this.region = region;
        this.possibleValues = new HashSet<Integer>();
    }

    /**
     * @brief Constructora d'una cel.la per copia
     * @param c Cel.la a copiar
     * @post Crea una cel.la amb els valors de la cel.la donada
     */
    public Cell(Cell c) {
        this.row = c.getRow();
        this.column = c.getColumn();
        this.valor = c.getValue();
        this.region = c.getRegion();
        this.possibleValues = new HashSet<Integer>(); //ALERT
        for (Integer i : c.getPossibleValues()) this.possibleValues.add(i);
    }

    ///////////////////////////////////////////////////// GETTERS /////////////////////////////////////////////////////

    /**
     * @brief Retorna la fila de la cel.la
     * @return Fila de la cel.la
     */
    public int getRow() {
        return row;
    }

    /**
     * @brief Retorna la columna de la cel.la
     * @return Columna de la cel.la
     */
    public int getColumn() {
        return column;
    }

    /**
     * @brief Retorna la regió de la cel.la
     * @return Regió de la cel.la
     */
    public int getRegion() {
        return region;
    }

    /**
     * @brief Retorna el valor de la cel.la
     * @return Valor de la cel.la
     */
    public int getValue() {
        return valor;
    }

    /**
     * @brief Retorna els valors possibles de la cel.la
     * @return Valors possibles de la cel.la
     */
    public Set<Integer> getPossibleValues() {
        return possibleValues;
    }

    ///////////////////////////////////////////////////// SETTERS /////////////////////////////////////////////////////

    /**
     * @brief Modifica la regió de la cel.la
     * @param newRegion Nova regió de la cel.la
     * @throws ExcepcioKenken si la regió no és vàlida
     * @post La regió de la cel.la és la nova regió
     */
    //TODO haria falta excepcio?
    public void setRegion(int newRegion) {
        region = newRegion;
    }

    /**
     * @brief Modifica el calor de la cel.la
     * @param newValor Nou valor de la cel.la
     * @post El valor de la cel.la és el nou valor
     */
    public void setValue(int newValor) {
        valor = newValor;
    }

    /**
     * @brief Modifica els valors possibles de la cel.la
     * @param possibleValues Nous valors possibles de la cel.la
     * @post Els valors possibles de la cel.la són els nous possibleValues
     */
    public void setPossibleValues(Set<Integer> possibleValues) {
        this.possibleValues = possibleValues;
    }

    ///////////////////////////////////////////////// MÈTODES PÚBLICS /////////////////////////////////////////////////

    /**
     * @brief Comprova si la cel.la està buida
     * @return Cert si la cel.la està buida, fals altrament
     */
    public boolean isEmpty() {
        return valor == 0;
    }

    /**
     * @brief Afegeix un valor possible a la cel.la
     * @param num Valor a afegir
     * @post El valor num és un valor possible de la cel.la
     */
    public void addPossibleValue(int num) {
        possibleValues.add(num);
    }

    /**
     * @brief Elimina un valor possible de la cel.la
     * @param num Valor a eliminar
     * @post El valor num ja no és un valor possible de la cel.la
     */
    public void removePossibleValue(int num) {
        possibleValues.remove(num);
    }

    /**
     * @brief Comprova si la cel.la és igual a una altra
     * @param obj Objecte a comparar
     * @return Cert si la cel.la és igual a l'objecte, fals altrament
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cell) {
            Cell c = (Cell) obj;
            return row == c.row && column == c.column;
        } else {
            return false;
        }
    }

    /**
     * @brief Retorna el hash de la cel.la
     * @return Hash de la cel.la
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
