package xogo;

import java.util.*;

// Clase que representa un jugador en el juego, implementando las interfaces Observable y Comparable<Xogador>
public class Xogador  implements Observable, Comparable<Xogador>{

    private String alias;
    private boolean vivo;
    private Queue<Tarefa> tarefas; // Cola de tareas asignadas al jugador
    private List<Observer> observers = new ArrayList<>();


    public Xogador(String alias) {
        this.alias = alias;
        this.vivo=true;

        // Obtener tareas aleatorias y asignarlas al jugador
        List<Tarefa> tarefasAleatorias = new LinkedList<>(Tarefa.getTarefas());
        Collections.shuffle(tarefasAleatorias);
        this.tarefas = new LinkedList<>();
        for (int i = 0; i <  tarefasAleatorias.size(); i++) {
            this.tarefas.add(tarefasAleatorias.get(i));
        }
    }

    // Método para registrar un observador al jugador
    @Override
    public void rexistrarObserver(Observer o) {
        observers.add(o);
    }

    // Método para eliminar un observador del jugador
    @Override
    public void borrarObserver(Observer o) {
        observers.remove(o);
    }

    // Método para notificar a los observadores del jugador
    @Override
    public void notificarObservers() {
        for (Observer o : observers) {
            o.update(this);
        }
    }

    // Método para mover al jugador a una habitación especificada
    public void moverParaHabitacion(String habitacion) {

        notificarObservers();
    }

    // Método para obtener el alias del jugador
    public String getAlias() {
        return alias;
    }

    // Método para establecer el estado de vida del jugador
    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    // Método para obtener las tareas asignadas al jugador
    public Queue<Tarefa> getTarefas() {
        return tarefas;
    }

    // Método para verificar si el jugador está vivo
    public boolean isVivo() {
        return vivo;
    }

    // Método de comparación para ordenar jugadores por alias
    @Override
    public int compareTo(Xogador o) {
        return this.alias.compareTo(o.alias);
    }
}