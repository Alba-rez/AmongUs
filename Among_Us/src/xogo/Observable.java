package xogo;

public interface Observable {
    void rexistrarObserver(Observer o);
    void borrarObserver(Observer o);
    void notificarObservers();
}
