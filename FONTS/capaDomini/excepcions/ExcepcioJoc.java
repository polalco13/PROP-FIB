package capaDomini.excepcions;
import java.lang.Exception;

public class ExcepcioJoc extends Exception {

    public ExcepcioJoc() {
    }

    public ExcepcioJoc(String message) {
        super(message);
    }

    public ExcepcioJoc(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcepcioJoc(Throwable cause) {
        super(cause);
    }

    public ExcepcioJoc(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
