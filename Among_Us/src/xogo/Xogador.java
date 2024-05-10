package xogo;

import java.util.*;

public class Xogador  implements Observable, Comparable<Xogador>{

    private String alias;
    private boolean vivo;
    private Queue<Tarefa> tarefas;
    private List<Observer> observers = new ArrayList<>();


    public Xogador(String alias) {
        this.alias = alias;
        this.vivo=true;
        List<Tarefa> tarefasAleatorias = new LinkedList<>(Tarefa.getTarefas());
        Collections.shuffle(tarefasAleatorias);
        this.tarefas = new LinkedList<>();
        for (int i = 0; i <  tarefasAleatorias.size(); i++) {
            this.tarefas.add(tarefasAleatorias.get(i));
        }
    }

    @Override
    public void rexistrarObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void borrarObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notificarObservers() {
        for (Observer o : observers) {
            o.update(this);
        }
    }
    public void moverParaHabitacion(String habitacion) {

        notificarObservers();
    }

    public String getAlias() {
        return alias;
    }
    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public Queue<Tarefa> getTarefas() {
        return tarefas;
    }

    public boolean isVivo() {
        return vivo;
    }

    @Override
    public int compareTo(Xogador o) {
        return this.alias.compareTo(o.alias);
    }
}