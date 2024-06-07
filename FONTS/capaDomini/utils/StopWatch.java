package capaDomini.utils;

import java.time.Duration;

public class StopWatch {
    private long elapsedTime;
    private long startTime;
    private boolean running;

    //////////////////////////////////// CONSTRUCTORS ////////////////////////////////////

    /**
     * @brief Constructora de StopWatch
     * @post Crea un cronometre
     */
    public StopWatch() {
        this.startTime = System.currentTimeMillis();
        this.elapsedTime = 0;
        this.running = false;
    }

    /**
     * @brief Constructora de StopWatch
     * @param temps Temps inicial del cronometre
     * @post Crea un cronometre amb el temps inicial
     */
    public StopWatch(long temps) {
        this.startTime = System.currentTimeMillis();
        this.elapsedTime = temps;
        this.running = false;
    }

    //////////////////////////////////// METHODS ////////////////////////////////////

    /**
     * @brief Inicia el cronometre
     * @post Comença a contar el temps
     */
    public void start() {
        this.startTime = System.currentTimeMillis();
        this.running = true;
    }

    /**
     * @brief Pausa el cronometre
     * @post Para de contar el temps
     */
    public void stop() {
        if (running) {
            elapsedTime += System.currentTimeMillis() - startTime;
            running = false;
        }
    }

    /**
     * @brief Resetea el cronometre
     * @post Comença a contar el temps desde 0
     */
    public void reset() {
        this.startTime = System.currentTimeMillis();
        this.elapsedTime = 0;
    }

    private String formatDuration(long millis) {
        Duration duration = Duration.ofMillis(millis);
        long hours = duration.toHours();
        duration = duration.minusHours(hours);
        long minutes = duration.toMinutes();
        duration = duration.minusMinutes(minutes);
        long seconds = duration.getSeconds();
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public String getFormattedTime() {
        return formatDuration(getTime());
    }

    //////////////////////////////////// GETTERS ////////////////////////////////////

    /**
     * @brief Retorna el temps actual del cronometre
     * @return Temps actual del cronometre
     */
    public long getTime() {
        if (running) {
            return elapsedTime + (System.currentTimeMillis() - startTime);
        } else {
            return elapsedTime;
        }
    }

}