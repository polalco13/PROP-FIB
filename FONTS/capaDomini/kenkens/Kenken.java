package capaDomini.kenkens;

import capaDomini.excepcions.ExcepcioKenken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Kenken {
    //Informació del Kenken
    private int id;
    private int size;
    private String nom;

    //Estructura del Kenken
    private Cell[][] board;
    private Region[] regions;
    private boolean[][] inRow;
    private boolean[][] inCol;

    ///////////////////////////////////////////////// METODES PRIVATS /////////////////////////////////////////////////

    /**
     * @brief Inicialitza els valors possibles de les cel·les
     * @post Inicialitza els valors possibles de les cel·les
     */
    private void initPossibleValues() {
        for (Region r : regions) r.setPossibleValues();
        Arrays.sort(regions, Region.numOfPossibleValues);
    }

    /**
     * @brief Comprova si un nombre és vàlid per a una cel.la
     * @param row Fila de la cel.la
     * @param col Columna de la cel.la
     * @param num Nombre a comprovar
     * @return Cert si el nombre és vàlid per a la cel.la, fals altrament
     */
    private boolean isValidNumber(int row, int col, int num) {
        //num sera valid si nomes apareix una vegada a la fila i a la columna
        return !inRow[row][num-1] && !inCol[col][num-1];
    }

    /**
     * @brief Resol el Kenken a partir d'una regio
     * @param regionIdx Index de la regio
     * @param cellIdx Index de la cel.la
     * @return Cert si s'ha resolt el Kenken, fals altrament
     * @throws ExcepcioKenken si no es pot resoldre el Kenken
     */
    private boolean solveFromRegion(int regionIdx, int cellIdx) throws ExcepcioKenken {
        // Si hem revisat totes les regions, el Kenken esta resolt
        if (regionIdx == regions.length) return true;

        // Si hem revisat totes les celles de la regio actual, passem a la seguent regio
        // Pero nomes si la regio actual es valida
        if (cellIdx == regions[regionIdx].getSize()) {
            if (regions[regionIdx].isValid()) return solveFromRegion(regionIdx + 1, 0);
            else return false;
        }

        // Obtenim la cella actual que estem revisant
        Cell c = regions[regionIdx].getCell(cellIdx);
        int row = c.getRow();
        int col = c.getColumn();

        // Si la cella actual ja te un valor, passem a la seguent cella
        if (c.getValue() != 0) {
            return solveFromRegion(regionIdx, cellIdx + 1);
        }
        else {
            // Provem tots els valors possibles
            for (int value : c.getPossibleValues()) {
                // Nomes provem el valor si es valid (no esta en la mateixa fila o columna)
                if (isValidNumber(row, col, value)) {
                    setValue(row, col, value);
                    // Intentem resoldre el Kenken amb el nou valor
                    if (solveFromRegion(regionIdx, cellIdx + 1)) return true;
                    unsetValue(row, col, value);
                }
            }
        }
        // Si hem provat tots els valors possibles i no podem resoldre el Kenken, retornem false
        return false;
    }

    ////////////////////////////////////////////////// CONSTRUCTORES //////////////////////////////////////////////////

    /**
     * @brief Constructora d'un Kenken
     * @param size Mida del Kenken
     * @param numRegions Nombre de regions del Kenken
     * @throws ExcepcioKenken si la mida o el nombre de regions no son valids
     */
    public Kenken(int size, int numRegions) throws ExcepcioKenken {
        if (size < 3 || size > 9) throw new ExcepcioKenken("Mida no vàlida");
        if (numRegions < 1 || numRegions > size*size) throw new ExcepcioKenken("Nombre de regions no vàlid");
        // Informacio del Kenken
        this.id = -1;
        this.nom = null;
        this.size = size;

        // Estructura del Kenken
        this.board = new Cell[size][size];
        this.inRow = new boolean[size][size];
        this.inCol = new boolean[size][size];
        this.regions = new Region[numRegions];
    }

    /**
     * @brief Constructora d'un Kenken a partir d'una llista de llistes
     * @param kenkenList Llista de llistes amb la informacio del Kenken
     * @throws ExcepcioKenken si la llista no es valida
     */
    public Kenken(List<List<Integer>> kenkenList) throws ExcepcioKenken {
        // Informacio del Kenken
        this.id = -1;
        this.nom = null;

        int size = kenkenList.get(0).get(0);        // N
        int numRegions = kenkenList.get(0).get(1);  // R

        // Estableix la mida del tauler Kenken i inicialitza les estructures de dades
        this.size = size;
        this.board = new Cell[size][size];
        this.inRow = new boolean[size][size];
        this.inCol = new boolean[size][size];
        this.regions = new Region[numRegions];

        // Itera sobre cada regio en la llista d'entrada
        for (int i = 0; i < numRegions; ++i) {
            List<Integer> region = kenkenList.get(i+1);
            int idx = 0;
            int op = region.get(idx++);
            int result = region.get(idx++);
            int numCells = region.get(idx++);

            // Crea una nova regio amb la informacio proporcionada i l'afegeix
            regions[i] = new Region(op, result, numCells);
            regions[i].setCounter(size);

            // Itera sobre cada cella en la regio actual.
            while (idx < region.size()) {
                int row = region.get(idx++);
                int col = region.get(idx++);
                int num = region.get(idx++);
                this.addCell(i, row, col, num);
            }

        }
    }

    /**
     * @brief Constructora d'un Kenken a partir d'un fitxer
     * @param kenkenFile Fitxer amb la informacio del Kenken
     * @throws ExcepcioKenken si el fitxer no es valid
     */
    public Kenken(File kenkenFile) throws ExcepcioKenken {
        this.id = -1;
        this.nom = null;
        this.size = -1;
        this.board = null;
        this.inRow = null;
        this.inCol = null;
        this.regions = null;

        try {
            FileReader file = new FileReader(kenkenFile);
            BufferedReader reader = new BufferedReader(file);

            // Obtenim el nom del fitxer
            String nom = kenkenFile.getName();
            // Ajustem el format
            if (nom.contains("-")) nom = nom.split("-")[1];                         //Ens traiem el id
            if (nom.contains(".")) nom = nom.substring(0, nom.lastIndexOf('.'));    //Ens traiem el .txt;

            String line;
            if ((line = reader.readLine()) == null) throw new RuntimeException();
            else {
                int idx = 0;
                String[] tokens = line.split(" ");
                int size = Integer.parseInt(tokens[idx++]);
                int numRegions = Integer.parseInt(tokens[idx++]);
                // Inicialitzem les estructures de dades del Kenken
                this.size = size;
                this.board = new Cell[size][size];
                this.inRow = new boolean[size][size];
                this.inCol = new boolean[size][size];
                this.regions = new Region[numRegions];

                // Per a cada regió, llegim una línia del fitxer i la dividim en tokens
                for (int i = 0; i < numRegions; ++i) {
                    if ((line = reader.readLine()) == null) throw new RuntimeException();
                    tokens = line.split(" ");

                    idx = 0;
                    int op = Integer.parseInt(tokens[idx++]);
                    int result = Integer.parseInt(tokens[idx++]);
                    int numCells = Integer.parseInt(tokens[idx++]);
                    // Creem una nova regió amb aquesta informació i l'afegim al Kenken
                    regions[i] = new Region(op, result, numCells);
                    regions[i].setCounter(size);

                    while (idx < tokens.length) {
                        int num = 0;
                        int row = Integer.parseInt(tokens[idx++]) - 1;
                        int col = Integer.parseInt(tokens[idx++]) - 1;
                        if (idx < tokens.length && tokens[idx].charAt(0) == '[') {
                            num = (tokens[idx++]).charAt(1) - '0';
                        }
                        this.addCell(i, row, col, num);
                    }
                }
                reader.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @brief Constructora d'un Kenken per copia
     * @param k Kenken a copiar
     * @post Crea un Kenken amb la mateixa informació que el Kenken donat
     */
    public Kenken(Kenken k) {
        this.id = k.getId();
        this.size = k.getMida();
        this.nom = k.getNom();
        this.board = new Cell[size][size];
        this.inRow = new boolean[size][size];
        this.inCol = new boolean[size][size];
        this.regions = new Region[k.regions.length];

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                this.inRow[i][j] = k.inRow[i][j];
                this.inCol[i][j] = k.inCol[i][j];
            }
        }

        for (int i = 0; i < k.regions.length; ++i) {
            this.regions[i] = new Region(k.regions[i]);
            this.regions[i].setCounter(this.size);

            for (int j = 0; j < this.regions[i].getSize(); ++j) {
                Cell c = this.regions[i].getCell(j);
                this.board[c.getRow()][c.getColumn()] = c;
            }
        }
    }

    ///////////////////////////////////////////////////// GETTERS /////////////////////////////////////////////////////

    /**
     * @brief Consultora del nom del Kenken
     * @return Nom del Kenken
     */
    public String getNom() {
        return nom;
    }

    /**
     * @brief Consultora de l'identificador del Kenken
     * @return Identificador del Kenken
     */
    public int getId() {
        return id;
    }

    /**
     * @brief Consultora la mida del Kenken
     * @return Mida del Kenken
     */
    public int getMida() {
        return size;
    }

    /**
     * @brief Consultora del valor d'una cel.la
     * @param row Fila de la cel.la
     */
    public int getValue(int row, int col) throws ExcepcioKenken {
        if (row < 0 || row >= size || col < 0 || col >= size) throw new ExcepcioKenken("Posició no vàlida");
        return board[row][col].getValue();
    }

    /**
     * @brief Consultora de la solució del Kenken
     * @return Solució del Kenken
     * @throws ExcepcioKenken si no es pot resoldre el Kenken
     */
    public Kenken getSolution() throws ExcepcioKenken {
        Kenken solution = new Kenken(this);
        solution.initPossibleValues();
        if (solution.solveFromRegion(0, 0)) return solution;
        else return null;
    }

    ///////////////////////////////////////////////////// SETTERS //////////////////////////////////////////////////////

    /**
     * @brief Assigna l'identificador del Kenken
     * @param id Identificador del Kenken
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @brief Assigna el nom del Kenken
     * @param nom Nom del Kenken
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @brief Assigna el valor d'una cel.la
     * @param row Fila de la cel.la
     * @param column Columna de la cel.la
     * @param valor Nou valor de la cel.la
     * @throws ExcepcioKenken si la posicio o el valor no són vàlids
     * @post El valor de la cel.la és el nou valor
     */
    public void setValue(int row, int column, int valor) throws ExcepcioKenken {
        if (row < 0 || row >= size || column < 0 || column >= size) throw new ExcepcioKenken("Posició no vàlida");
        if (valor < 0 || valor > size) throw new ExcepcioKenken("Valor no vàlid");

        board[row][column].setValue(valor);
        inRow[row][valor-1] = true;
        inCol[column][valor-1] = true;
    }

    /**
     * @brief Desassigna el valor d'una cel.la
     * @param row Fila de la cel.la
     * @param column Columna de la cel.la
     * @param valor Valor de la cel.la
     * @throws ExcepcioKenken si la posicio o el valor no son valids
     * @post La cel.la no te cap valor assignat
     */
    public void unsetValue(int row, int column, int valor) throws ExcepcioKenken {
        if (row < 0 || row >= size || column < 0 || column >= size) throw new ExcepcioKenken("Posició no vàlida");
        if (valor < 0 || valor > size) throw new ExcepcioKenken("Valor no vàlid");

        board[row][column].setValue(0);
        inRow[row][valor-1] = false;
        inCol[column][valor-1] = false;
    }

    /**
     * @brief Assigna les regions del Kenken
     * @param regions Regions del Kenken
     * @post Les regions del Kenken son les regions donades
     */
    public void setRegions(Region[] regions) {
        this.regions = regions;
        for (Region r : regions) {
            r.setCounter(this.size);
            for (int i = 0; i < r.getSize(); ++i) {
                Cell c = r.getCell(i);
                c.setValue(0);
                board[c.getRow()][c.getColumn()] = c;
            }
        }
    }

    ///////////////////////////////////////////////// METODES PUBLICS //////////////////////////////////////////////////

    /**
     * @brief Afegeix una cel.la al Kenken
     * @param idx Index de la regio de la cel.la
     * @param row Fila de la cel.la
     * @param col Columna de la cel.la
     * @param num Valor de la cel.la
     * @post La cel.la es afegida al Kenken
     */
    public void addCell (int idx, int row, int col, int num) {
        Cell c = new Cell(row, col, num);
        c.setRegion(idx);
        regions[idx].addCell(c);
        board[row][col] = c;

        if (c.getValue() != 0) {
            inRow[row][c.getValue()-1] = true;
            inCol[col][c.getValue()-1] = true;
        }
    }

    /////////////////////////////////////////////////// CONVERSIONS ////////////////////////////////////////////////////

    /**
     * @brief Converteix el Kenken a una string
     * @return Kenken en format de string
     */
    public String kenkenToString() {
        List<List<Integer>> kenken = kenkenToList();
        String kenkenString = "";

        // Afegim la dimension i el nombre de regions
        kenkenString += kenken.get(0).get(0) + " " + kenken.get(0).get(1) + "\n";

        for (int i = 1; i < kenken.size(); ++i) {
            int idx = 0;
            // Guardem la informacio de la regio
            int op = kenken.get(i).get(idx++);
            int res = kenken.get(i).get(idx++);
            int size = kenken.get(i).get(idx++);
            kenkenString += op + " " + res + " " + size;

            //Afegim les cel.les de la regio
            while (idx < kenken.get(i).size()) {
                int row = kenken.get(i).get(idx++) + 1;
                int col = kenken.get(i).get(idx++) + 1;
                int val = kenken.get(i).get(idx++);
                // Si el valor de la cel.la es 0, no l'afegim
                if (val == 0) kenkenString += " " + row + " " + col;
                else kenkenString += " " + row + " " + col + " [" + val + "]";
            }
            kenkenString += "\n";
        }

        return kenkenString;
    }

    /**
     * @brief Consultora del kenken en format de llista de llistes
     * @return Kenken en format de llista de llistes
     * @post Retorna el Kenken en format de llista de llistes
     */
    public List<List<Integer>> kenkenToList() {
        List<List<Integer>> kenken = new ArrayList<>();
        // DIMENSION-NUMERO_DE_REGIONS
        kenken.add(Arrays.asList(size, regions.length));

        for (Region r : regions) {
            List<Integer> regionInfo = new ArrayList<>();
            // OPERATION-RESULT-NUM_ELEMENTS
            regionInfo.add(r.getOperation());
            regionInfo.add(r.getResult());
            regionInfo.add(r.getSize());

            for (int i = 0; i < r.getSize(); ++i) {
                Cell c = r.getCell(i);
                // FILA-COLUMNA-VALOR
                regionInfo.add(c.getRow());
                regionInfo.add(c.getColumn());
                regionInfo.add(c.getValue());
            }
            kenken.add(regionInfo);
        }
        return kenken;
    }
}
