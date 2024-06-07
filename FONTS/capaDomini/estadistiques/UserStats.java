package capaDomini.estadistiques;
import capaDomini.utils.*;
import capaDomini.usuaris.*;
import java.util.ArrayList;
import java.util.TreeSet;

public class UserStats {

    private ArrayList<TreeSet<Long>> tempsPerDificultat;

    /**
     * @brief Crea un nou objecte UserStats.
     * @post S'ha creat un nou objecte UserStats.
     */
    public UserStats() {
        tempsPerDificultat = new ArrayList<TreeSet<Long>>();
        for (int i = 0; i < 7; ++i) {
            tempsPerDificultat.add(new TreeSet<Long>());
        }
    }

    /**
     * @brief Afegeix un temps a les estadistiques.
     * @param dificultat Dificultat de la partida.
     * @param time Temps de la partida.
     * @post S'ha afegit el temps a les estadistiques.
     */
    public void addTime(int dificultat, long time) {
        if (dificultat >= 3 && dificultat <= 9) {
            tempsPerDificultat.get(dificultat - 3).add(time);
        } else {
            throw new IllegalArgumentException("Dificultat fora de rang: " + dificultat);
        }
    }

    /**
     * @brief Retorna els temps de les partides d'una determinada dificultat.
     * @param dificultat Dificultat de les partides.
     * @return Temps de les partides d'aquella dificultat.
     */
    public TreeSet<Long> getTimes(int dificultat) {
        if (dificultat >= 3 && dificultat <= 9) {
            return tempsPerDificultat.get(dificultat - 3);
        } else {
            throw new IllegalArgumentException("Dificultat fora de rang: " + dificultat);
        }
    }

    /**
     * @brief Retorna el millor temps d'una determinada dificultat.
     * @param dificultat Dificultat de les partides.
     * @return Millor temps d'aquella dificultat.
     */
    public Long getBestTime(int dificultat) {
        TreeSet<Long> times = getTimes(dificultat);
        return times.isEmpty() ? null : times.first();
    }

    /**
     * @brief Retorna la mitjana de temps d'una determinada dificultat.
     * @param dificultat Dificultat de les partides.
     * @return Mitjana de temps d'aquella dificultat.
     */
    public Long getAverageTime(int dificultat) {
        TreeSet<Long> times = getTimes(dificultat);
        if (times.isEmpty()) return null;
        long sum = 0;
        for (Long time : times) {
            sum += time;
        }
        return sum / times.size();
    }

    /**
     * @brief Retorna el total de partides jugades d'una determinada dificultat.
     * @param dificultat Dificultat de les partides.
     * @return Total de partides jugades d'aquella dificultat.
     */
    public int getTotalGames(int dificultat) {
        TreeSet<Long> times = getTimes(dificultat);
        return times.size();
    }

    /**
     * @brief Retorna el millor temps global.
     * @return Millor temps global.
     */
    public Long getGlobalBestTime() {
        Long bestTime = null;
        for (int i = 0; i < 7; ++i) {
            Long time = getBestTime(i + 3);
            if (time != null && (bestTime == null || time < bestTime)) {
                bestTime = time;
            }
        }
        return bestTime;
    }

    /**
     * @brief Retorna la mitjana de temps global.
     * @return Mitjana de temps global.
     */
    public Long getGlobalAverageTime() {
        long sum = 0;
        int count = 0;
        for (int i = 0; i < 7; ++i) {
            TreeSet<Long> times = getTimes(i + 3);
            for (Long time : times) {
                sum += time;
                ++count;
            }
        }
        return count == 0 ? null : sum / count;
    }

    /**
     * @brief Retorna el total de partides jugades global.
     * @return Total de partides jugades global.
     */
    public int getAllTotalGames() {
        int count = 0;
        for (int i = 0; i < 7; ++i) {
            count += getTotalGames(i + 3);
        }
        return count;
    }

    /**
     * @brief Retorna el total de partides jugades d'un determinat nivell.
     * @param nivell Nivell de les partides.
     * @return Total de partides jugades d'aquell nivell.
     */
    public int getGamesPlayedPerLevel(int nivell) {
        return getTotalGames(nivell);
    }
}
