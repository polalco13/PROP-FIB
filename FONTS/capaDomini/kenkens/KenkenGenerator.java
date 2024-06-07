package capaDomini.kenkens;

import java.util.*;

import capaDomini.excepcions.ExcepcioKenken;
import capaDomini.operacions.Operacions;
import capaDomini.utils.*;

public class KenkenGenerator {
    private final int[] dx;                 // Direccions possibles
    private final int[] dy;                 // Direccions possibles
    private final Random rng;               // Random number generator
    private int[] distribution;             // Distribucio de probabilitat

    ///////////////////////////////////////////////// METODES PRIVATS //////////////////////////////////////////////////
    
    /**
     * @brief Retorna les cel.les buides del tauler
     * @param mida Mida del kenken
     * @return Conjunt de cel.les buides
     */
    private Set<String> getIndexes(int mida) {
        Set<String> emptyCells = new HashSet<>();
        for (int i = 0; i < mida; ++i) {
            for (int j = 0; j < mida; ++j)
                emptyCells.add(i + "-" + j);
        }
        return emptyCells;
    }

    /**
     * @brief Retorna la cel.la buida amb menor index
     * @param board Tauler de joc
     * @return Index de la cel.la buida amb menor index
     */
    private String getEmptyCell(int[][] board) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board.length; ++j) {
                if (board[i][j] == 0) return (i + "-" + j);
            }
        }
        return null;
    }

    /**
     * @brief Inicialitza la distribució de probabilitat
     * @param mida Mida del kenken
     */
    private void initDistributions(int mida) {
        // L'eleccio d'aquestes distribucions es basa en kenkens que hem anant veient per internet
        if (mida <= 4) this.distribution = new int[] {1, 1, 2, 2, 2, 2, 2, 2, 3, 3};
        else if (4 < mida && mida <= 7) this.distribution = new int[] {1, 2, 2, 2, 2, 3, 3, 3, 3, 4};
        else if (7 < mida && mida <= 9) this.distribution = new int[] {1, 2, 2, 3, 3, 3, 3, 3, 4, 4};
        else this.distribution = new int[] {};
    }

    /**
     * @brief Genera un tauler llatí
     * @param mida Mida del kenken
     * @return Tauler llatí generat
     */
    private List<List<Integer>> generateLatinSquare(int mida) {
        // Escollim fer un tauler llatí amb lliestes per aprofitar la funcionalitat de shuffle
        List<List<Integer>> latinSquare = new ArrayList<>(mida);
        for (int i = 0; i < mida; ++i) {
            latinSquare.add(new ArrayList<>(mida));
            // Omplim la primera fila amb els valors de 1 a mida
            for (int j = 0; j < mida; ++j) latinSquare.get(i).add(j+1);
        }

        // Barregem la primera fila
        Collections.shuffle(latinSquare.get(0));
        // Files intermitges
        for (int i = 1; i < mida-1; ++i) {
            boolean shuffled = false;
            shuffling:
            while (!shuffled) {
                Collections.shuffle(latinSquare.get(i));
                for (int k = 0; k < i; ++k) {
                    for (int j = 0; j < mida; ++j) {
                        if (Objects.equals(latinSquare.get(k).get(j), latinSquare.get(i).get(j))) {
                            continue shuffling;
                        }
                    }
                }
                shuffled = true;
            }
        }

        // Ultima fila
        for (int j = 0; j < mida; ++j) {
            List<Boolean> used = new ArrayList<>(mida);
            for (int i = 0; i < mida; ++i) used.add(false);
            for (int i = 0; i < mida-1; ++i) used.set(latinSquare.get(i).get(j)-1, true);

            for (int k = 0; k < mida; ++k) {
                if (!used.get(k)) {
                    latinSquare.get(mida-1).set(j, k+1);
                    break;
                }
            }
        }

        // Barregem les files
        Collections.shuffle(latinSquare);
        return latinSquare;
    }

    /**
     * @brief Divideix el tauler en regions
     * @param mida Mida del kenken
     * @return Mapa de regions
     */
    public Map<Integer,List<String>> divideIntoRegions(int mida) {
        Map<Integer,List<String>> cages = new HashMap<>();  // Cages: on es guarden les regions
        Set<String> emptyCells = getIndexes(mida);          // emptyCells: on es guarden les cel.les buides
        int[][] regionMap = new int[mida][mida];            // regionMap: on es va marcant la regio a la que pertany cada cel.la
        int regionID = 1;

        while (emptyCells.size() > 0) {
            List<String> region = new ArrayList<>();
            String startCell = getEmptyCell(regionMap);     // Agafem la primera cel.la buida
            region.add(startCell);

            int row = startCell.charAt(0) - '0';
            int col = startCell.charAt(2) - '0';
            regionMap[row][col] = regionID;                 // Marquem la regio a la que pertany la cel.la

            emptyCells.remove(row + "-" + col);                 // Eliminem la cel.la de la llista de cel.les buides
            int regionIdx = rng.nextInt(distribution.length);   // Escollim una mida de regio aleatoria

            for (int i = 0; i < distribution[regionIdx]-1; ++i) {
                List<Integer> nextPossibleRows = new ArrayList<>();
                List<Integer> nextPossibleCols = new ArrayList<>();

                // Veiem que direccions son valides
                for (int j = 0; j < 4; ++j) {
                    // Nova direccio
                    int nextRow = row + dy[j];
                    int nextCol = col + dx[j];

                    // Mirem si la nova direccio es fora del tauler
                    boolean validRow = (0 <= nextRow && nextRow < mida);
                    boolean validCol = (0 <= nextCol && nextCol < mida);

                    // Mirem si la nova direccio es valida i si la cel.la esta buida
                    if (validRow && validCol && regionMap[nextRow][nextCol] == 0) {
                        // Afegim la nova direccio a les possibles direccions
                        nextPossibleRows.add(nextRow);
                        nextPossibleCols.add(nextCol);
                    }
                }

                if (nextPossibleRows.size() == 0) {
                    // Si no hi ha cap fila possible per a la següent cel·la, sortim del bucle
                    i = distribution[regionIdx];
                }
                else {
                    int randomIdx = rng.nextInt(nextPossibleRows.size());
                    // Si hi ha files possibles per a la següent cel.la, seleccionem una fila i una columna aleatories
                    row = nextPossibleRows.get(randomIdx);
                    col = nextPossibleCols.get(randomIdx);

                    // Marquem la cel·la com a part de la regió actual.
                    regionMap[row][col] = regionID;

                    // Eliminem la cel·la de la llista de cel·les buides.
                    emptyCells.remove(row + "-" + col);

                    // Afegim la cel·la a la llista de cel·les de la regió actual.
                    region.add(row + "-" + col);
                }
            }
            cages.put(regionID, region);
            ++regionID;
        }
        return cages;
    }
    
    /**
     * @brief Genera les pistes d'una regio
     * @param region Regio a la que generar les pistes
     * @return Llista de pistes possibles generades
     */
    public List<List<Float>> generateClues(List<Cell> region) {
        List<List<Float>> clues = new ArrayList<>();
        clues.add(new ArrayList<>()); // Operacions possibles
        clues.add(new ArrayList<>()); // Resultats possibles
        //Operacions mod = new Modul(5)

        if (region.size() == 1) {
            /// IGUAL ///
            Operacions op = Operacions.getOperation(0);
            clues.get(0).add(0f);
            clues.get(1).add(op.calcular(region));

            /// ARREL ///
            op = Operacions.getOperation(6);
            int value = region.get(0).getValue();
            if (value == 1 || value == 4 || value == 9) {
                clues.get(0).add(6f);
                clues.get(1).add(op.calcular(region));
            }
        }
        else if (region.size() == 2) {
            for (int i = 1; i <= 5; ++i) {
                Operacions op = Operacions.getOperation(i);
                //Si la divisio no es exacta saltem a la seguent operacio
                if (i == 4 && op.calcular(region) != (int) op.calcular(region)) continue;
                clues.get(0).add((float)i);
                clues.get(1).add(op.calcular(region));
            }
        }
        else {
            /// SUMA ///
            Operacions op = Operacions.getOperation(1);
            clues.get(0).add(1f);
            clues.get(1).add(op.calcular(region));

            /// RESTA ///
            op = Operacions.getOperation(3);
            clues.get(0).add(3f);
            clues.get(1).add(op.calcular(region));
        }
        return clues;
    }

    /////////////////////////////////////////////////// CONSTRUCTORES //////////////////////////////////////////////////

    /**
     * @brief Constructora d'un generador de kenkens
     * @post Crea un generador de kenkens
     */
    public KenkenGenerator() {
        this.rng = new Random();
        this.dx = new int[] {1, 0, -1,  0};
        this.dy = new int[] {0, 1,  0, -1};
    }

    ////////////////////////////////////////////////////// GETTERS /////////////////////////////////////////////////////

    /**
     * @brief Genera un kenken per parametres
     * @return Kenken generat
     */
    public Kenken autoGenerateKenken(int mida) throws ExcepcioKenken {
        initDistributions(mida);
        List<List<Integer>> latinSquare = generateLatinSquare(mida);
        Map<Integer,List<String>> cages = divideIntoRegions(mida);
        Region[] regions = new Region[cages.size()];

        for (Map.Entry<Integer,List<String>> cage : cages.entrySet()) {
            int regionID = cage.getKey() - 1;
            List<String> cells = cage.getValue();

            List<Cell> region = new ArrayList<>(cells.size());
            for (String cell : cells) {
                int row = cell.charAt(0) - '0';
                int col = cell.charAt(2) - '0';
                int value = latinSquare.get(row).get(col);
                region.add(new Cell(regionID, row, col, value));
            }

            // Generem les pistes per a la regio
            List<List<Float>> clues = generateClues(region);
            int clueIdx = rng.nextInt(clues.get(0).size());

            // Obtenim l'operacio i el resultat de la pista
            int op = clues.get(0).get(clueIdx).intValue();
            int result = clues.get(1).get(clueIdx).intValue();

            // Creem una nova regio amb l'operacio, el resultat i les cel·les
            regions[regionID] = new Region(op, result, cells.size());
            regions[regionID].setCells(region);
        }

        Kenken kenken = new Kenken(mida, cages.size());
        kenken.setRegions(regions);
        return kenken;
    }
}
