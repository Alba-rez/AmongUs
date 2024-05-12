package xogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// Clase que representa un estudante no xogo, que é un tipo de xogador
public class Estudante extends Xogador {
    Queue<Tarefa> tarefas;

    public Estudante(String alias) {
        super(alias);
    }

    // Método para realizar unha tarefa asignada ao estudante
    public void realizarTarefa() {

        // Obten as tarefas asignadas ao estudante
        tarefas = super.getTarefas();

        // Se hai tarefas na cola, realiza a primeira tarefa
        if (!tarefas.isEmpty()) {
            Tarefa tarefa = tarefas.poll();
            tarefa.realizar();
        } else {
            System.out.println("Non hai máis tarefas para realizar.");
        }
    }

    // Método para asignar unha cola de tarefas ao estudante
    public void asignarTarefas(Queue<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    // Método para obter os alias dos xogadores
    @Override
    public String getAlias() {
        return super.getAlias();
    }

    // Método para obter a cola de tarefas asignadas ao estudante
    public Queue<Tarefa> getTarefas() {
        return tarefas;
    }


}
