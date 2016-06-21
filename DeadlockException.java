package locks;

public class DeadlockException extends RuntimeException {

    public DeadlockException(Throwable t) {
        super(t);
    }

    public DeadlockException() {
        super();
    }

    public DeadlockException(String st) {
        super(st);
    }
    
    @Override
    public Throwable fillInStackTrace() {
        return null;
    } 
}