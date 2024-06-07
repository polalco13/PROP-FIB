package capaDomini.excepcions;

public class ExcepcioKenken extends Exception{
    public ExcepcioKenken() {
    }

    public ExcepcioKenken(String message) {
        super(message);
    }

    public ExcepcioKenken(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcepcioKenken(Throwable cause) {
        super(cause);
    }

    public ExcepcioKenken(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
