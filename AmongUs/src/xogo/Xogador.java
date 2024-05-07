package xogo;

import java.util.Queue;

public class Xogador {

    private String alias;
    private Queue<Tarefa>tarefas;

    public Xogador(String alias) {
        this.alias = alias;
    }

    public void realizarTarefa() {
        // Implementa la lógica para realizar una tarea
        // Por ejemplo, podrías desencolar una tarea de la cola de tareas
        Tarefa tarefa = tarefas.poll();
        // Y luego realizar la tarea
        // tarefa.realizar();
    }

    public String getAlias() {
        return alias;
    }
}
