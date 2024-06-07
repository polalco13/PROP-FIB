package capaDomini.excepcions;
import java.lang.Exception;

public class ExcepcioUsuari extends Exception {

    public ExcepcioUsuari() {
    }

    public ExcepcioUsuari(String message) {
        super(message);
    }

    public ExcepcioUsuari(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcepcioUsuari(Throwable cause) {
        super(cause);
    }

    public ExcepcioUsuari(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
