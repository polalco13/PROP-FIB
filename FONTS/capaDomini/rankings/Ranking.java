package capaDomini.rankings;

import capaDomini.estadistiques.*;

import java.util.*;

public class Ranking {

    private final Estadistiques estadistiques;

    /**
     * @brief Constructora de la classe Ranking
     * @param estadistiques Estadístiques dels usuaris
     */
    public Ranking(Estadistiques estadistiques) {
        this.estadistiques = estadistiques;
    }

    // Clase interna para encapsular la información del ranking
    public static class RankingEntry {
        private final String username;
        private final int level;
        private final long bestTime;

        public RankingEntry(String username, int level, long bestTime) {
            this.username = username;
            this.level = level;
            this.bestTime = bestTime;
        }

        public String getUsername() {
            return username;
        }

        public int getLevel() {
            return level;
        }

        public long getBestTime() {
            return bestTime;
        }
    }

    /**
     * @brief Genera el ranking de jugadors per cada nivell de dificultat
     * @return Un mapa amb el nivell de dificultat com a clau i una llista de jugadors i els seus millors temps com a valor
     */
    public Map<Integer, List<RankingEntry>> generarRanking() {
        Map<Integer, List<RankingEntry>> rankingPerDificultat = new HashMap<>();

        // Inicialitzar el mapa amb llistes buides per a cada nivell de dificultat
        for (int i = 3; i <= 9; i++) {
            rankingPerDificultat.put(i, new ArrayList<>());
        }

        // Verifica que 'estadistiques' no sea null
        if (estadistiques == null) {
            return rankingPerDificultat;
        }

        // Recórrer les estadístiques de tots els usuaris
        Map<String, UserStats> allStats = estadistiques.getAllStats();
        if (allStats == null) {
            return rankingPerDificultat;
        }

        for (Map.Entry<String, UserStats> entry : allStats.entrySet()) {
            String username = entry.getKey();
            UserStats stats = entry.getValue();

            // Verifica que 'stats' no sea null
            if (stats == null) {
                continue;
            }

            // Recórrer les dificultats
            for (int dificultat = 3; dificultat <= 9; dificultat++) {
                Long bestTime = stats.getBestTime(dificultat);
                if (bestTime != null) {
                    rankingPerDificultat.get(dificultat).add(new RankingEntry(username, dificultat, bestTime));
                }
            }
        }

        // Ordenar les llistes per temps
        for (int dificultat = 3; dificultat <= 9; dificultat++) {
            List<RankingEntry> rankingList = rankingPerDificultat.get(dificultat);
            if (rankingList != null) {
                rankingList.sort(Comparator.comparingLong(RankingEntry::getBestTime));
            }
        }
        return rankingPerDificultat;
    }
}