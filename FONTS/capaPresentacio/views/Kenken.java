package capaPresentacio.views;
import java.awt.*;
import javax.swing.*;

import capaDomini.excepcions.ExcepcioKenken;
import capaPresentacio.CtrlPresentacio;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Kenken extends JPanel {
    private Cell[][] board;
    private Cell currentCell;
    private int emptyCells;
    private boolean validated;
    private List<Cell> selectedCells;
    private List<List<Integer>> cages;
    private List<List<Integer>> manualCages;
    private partidaPanel partidaPanelRef;
    private int dx = 0;

    ///////////////////////////////////////////////// METODES PRIVATS //////////////////////////////////////////////////

    private String getOperationSymbol(int op) {
        switch (op) {
            case 1: return "+";
            case 2: return "-";
            case 3: return "*";
            case 4: return "/";
            case 5: return "%";
            case 6: return "√";
            default: return "=";
        }
    }
    
    private int getAdjacentEdge(Cell ini, Cell adj) {
        //direction = {TOP, LEFT, BOTTOM, RIGHT}
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, -1, 0, 1};

        for (int i = 0; i < dx.length; ++i) {
            int newRow = ini.getRow() + dy[i];
            int newCol = ini.getCol() + dx[i];
            if (newRow == adj.getRow() && newCol == adj.getCol()) {
                // TOP: 1, LEFT: 2, BOTTOM: 3, RIGHT: 4
                return i + 1;
            }
        }
        // NO EDGE
        return -1;
    }

    private int[] getTopLeftCell(List<Integer> cage) {
        int topLeftRow = Integer.MAX_VALUE;
        int topLeftCol = Integer.MAX_VALUE;

        int idx = 3;
        while (idx < cage.size()) {
            int row = cage.get(idx++);
            int col = cage.get(idx++);
            if (row <= topLeftRow && col <= topLeftCol) {
                topLeftRow = row;
                topLeftCol = col;
            }
            ++idx;
        }
        return new int[]{topLeftRow, topLeftCol};
    }

    private void printRegions() {
        String clue;
        // Comença al 1 perquè el 0 es la fila amb mida i numero de regions
        for (int i = 1; i< cages.size(); ++i) {
            List<Integer> cage = cages.get(i);
            int idx = 0;
            clue = "" + getOperationSymbol(cage.get(idx++));
            clue = "  " + cage.get(idx++) + clue;
            idx++;

            while (idx < cage.size()) {
                int iniRow = cage.get(idx++);
                int iniCol = cage.get(idx++);
                int valor = cage.get(idx++);                //NO FA RES DE MOMENT
                Cell iniCell = board[iniRow][iniCol];
                List<Integer> edges = new ArrayList<>();

                int j = 3;
                while (j < cage.size()) {
                    int adjRow = cage.get(j++);
                    int adjCol = cage.get(j++);
                    if (iniRow != adjRow || iniCol != adjCol) {
                        int direction = getAdjacentEdge(iniCell, board[adjRow][adjCol]);
                        if (direction != -1) edges.add(direction);
                    }
                    j++;
                }
                iniCell.setBorder(edges);
            }

            int topLeftRow = getTopLeftCell(cage)[0];
            int topLeftCol = getTopLeftCell(cage)[1];
            board[topLeftRow][topLeftCol].setClue(clue);
        }
    }

    ////////////////////////////////////////////////// CONSTRUCTORES ///////////////////////////////////////////////////

    public Kenken(List<List<Integer>> cages) {
        super();
        int dimension = cages.get(0).get(0);

        this.cages = cages;
        this.manualCages = new ArrayList<>();
        this.currentCell = null;
        this.validated = false;
        this.emptyCells = dimension * dimension;
        this.selectedCells = new ArrayList<>();
        this.board = new Cell[dimension][dimension];
        setLayout(new GridLayout(dimension,dimension));

        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                Cell cell = new Cell(i,j);
                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        deselectAllCells();
                        selectCell(cell);
                    }
                });
                cell.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        Point point = new Point(e.getX() + cell.getX(), e.getY() + cell.getY());
                        if (!contains(point)) {
                            deselectAllCells();
                        } else {
                            Component component = getComponentAt(point);
                            if (component instanceof Cell) {
                                selectCell((Cell) component);
                            }
                        }
                    }
                });
                board[i][j] = cell;
                add(cell);
            }
        }
        printRegions();
    }

    private boolean isAdjacent(Cell cell1, Cell cell2) {
        int rowDiff = Math.abs(cell1.getRow() - cell2.getRow());
        int colDiff = Math.abs(cell1.getCol() - cell2.getCol());
        return (rowDiff + colDiff) == 1;
    }

    private boolean isAdjacentToAnySelectedCell(Cell cell) {
        for (Cell selectedCell : selectedCells) {
            if (isAdjacent(cell, selectedCell)) {
                return true;
            }
        }
        return false;
    }

    private void selectCell(Cell cell) {
        if (!cell.isSelected()) {
            if (selectedCells.isEmpty() || isAdjacentToAnySelectedCell(cell)) {
                if (!selectedCells.contains(cell)) {
                    selectedCells.add(cell);
                    cell.setSelected(true); // Assuming Cell has a setSelected method
                }
            }
        }
    }

    private void deselectAllCells() {
        for (Cell cell : selectedCells) {
            cell.setSelected(false);
        }
        selectedCells.clear();
    }

    public Kenken(List<List<Integer>> cages, partidaPanel partidaPanelRef) {
        super();
        int dimension = cages.get(0).get(0);
        this.cages = cages;
        this.currentCell = null;
        this.validated = false;
        this.emptyCells = dimension * dimension;
        this.selectedCells = new ArrayList<>();
        this.partidaPanelRef = partidaPanelRef;
        this.board = new Cell[dimension][dimension];
        setLayout(new GridLayout(dimension,dimension));

        int[][] matrix = new int[dimension][dimension];

        for (int i = 1; i < cages.size(); ++i) {
            List<Integer> cage = cages.get(i);
            int idx = 3;
            while (idx < cage.size()) {
                int row = cage.get(idx++);
                int col = cage.get(idx++);
                int valor = cage.get(idx++);
                matrix[row][col] = valor;
            }
        }

        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                board[i][j] = new Cell(i,j);
                add(board[i][j]);
                if (matrix[i][j] != 0) board[i][j].setValor(matrix[i][j]);
            }
        }
        printRegions();
    }

    ///////////////////////////////////////////////////// GETTERS //////////////////////////////////////////////////////

    public int getMida() {
        return board.length;
    }

    public boolean getPista() throws ExcepcioKenken {
        if (currentCell != null) {
            int[][] solution = CtrlPresentacio.getSolution(cages);
            int row = currentCell.getRow();
            int col = currentCell.getCol();
            currentCell.setValor(solution[row][col]);
            CtrlPresentacio.assignaValor(row, col, solution[row][col]);
            return true;
        }
        return false;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public List<List<Integer>> getCages() {
        return cages;
    }

    public List<Cell> getSelectedCells() {
        return selectedCells;
    }

    public int getCellValue(int row, int col) {
        return board[row][col].getValue();
    }

    public TreeSet<Integer> getCellNotes(int row, int col) {
        return board[row][col].getNotes();
    }

    ///////////////////////////////////////////////////// SETTERS //////////////////////////////////////////////////////

    public void setCurrentCell(Cell cell) {
        currentCell = cell;
        if (partidaPanelRef != null) partidaPanelRef.updateNumPad();
    }

    public void setCellValue(int row, int col, int num) {
        board[row][col].setValor(num);
    }

    public void unsetCellValue(int row, int col) {
        board[row][col].setValor(0);
    }

    ///////////////////////////////////////////////////// METODES //////////////////////////////////////////////////////
    public void addManulaCage(List<Integer> cage) {
        manualCages.add(cage);
    }

    public boolean isSolvable() throws ExcepcioKenken {
        List<Integer> infoKenken = new ArrayList<>();
        infoKenken.add(board.length);
        infoKenken.add(manualCages.size());
        manualCages.add(0, infoKenken);
        cages = manualCages;

        int[][] solucio = CtrlPresentacio.getSolution(cages);
        if (solucio == null) return false;
        else {
            validated = true;
            return true;
        }
    }

    public boolean isValidated() {
        return validated;
    }

    public void addSelectedCell(Cell sourceCell) {
        selectedCells.add(sourceCell);
    }

    public void addNota(int row, int col, int num) {
        board[row][col].addNota(num);
    }

    public void removeNota(int row, int col, int num) {
        board[row][col].removeNota(num);
    }

    public void showNotas() {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board.length; ++j) {
                if (board[i][j].getValue() == 0)
                    board[i][j].showNotas();
            }
        }
    }

    public void hideNotas() {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board.length; ++j) {
                board[i][j].hideNotas();
            }
        }
    }

    public void hideNota(int row, int col) {
        board[row][col].hideNotas();
    }

    public void showNota(int posX, int posY) {
        board[posX][posY].showNotas();
    }

    public void showSolution() throws ExcepcioKenken {
        int[][] solution = CtrlPresentacio.getSolution(cages);
        for (int i = 0; i < solution.length; ++i) {
            for (int j = 0; j < solution.length; ++j) {
                board[i][j].setValor(solution[i][j]);
                CtrlPresentacio.assignaValor(i, j, solution[i][j]);
            }
        }
    }

    public void updateEmptyCells(int filledCells) {
        emptyCells -= filledCells;
    }

    public boolean isFull() {
        return emptyCells == 0;
    }

    public void printManualCage(List<Integer> cage) {
        int[] topLeftCoords = getTopLeftCell(cage);
        String clue = "" + getOperationSymbol(cage.get(0));
        clue = "  " + cage.get(1) + clue;
        board[topLeftCoords[0]][topLeftCoords[1]].setClue(clue);

        int idx = 3;
        while (idx < cage.size()) {
            int iniRow = cage.get(idx++);
            int iniCol = cage.get(idx++);
            int valor = cage.get(idx++);                //NO FA RES DE MOMENT
            Cell iniCell = board[iniRow][iniCol];
            List<Integer> edges = new ArrayList<>();

            int j = 3;
            while (j < cage.size()) {
                int adjRow = cage.get(j++);
                int adjCol = cage.get(j++);
                if (iniRow != adjRow || iniCol != adjCol) {
                    int direction = getAdjacentEdge(iniCell, board[adjRow][adjCol]);
                    if (direction != -1) edges.add(direction);
                }
                j++;
            }
            iniCell.setBorder(edges);
        }
    }

    public void cleanClues() {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board.length; ++j) {
                board[i][j].setClue("");
            }
        }
    }
}
