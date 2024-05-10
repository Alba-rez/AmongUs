package xogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Estudante extends Xogador {
    Queue<Tarefa> tarefas;

    public Estudante(String alias) {
        super(alias);
    }

    public void realizarTarefa() {
        tarefas = super.getTarefas();
        if (!tarefas.isEmpty()) {
            Tarefa tarefa = tarefas.poll();
            tarefa.realizar();
        } else {
            System.out.println("Non hai máis tarefas para realizar.");
        }
    }
    public void asignarTarefas(Queue<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    @Override
    public String getAlias() {
        return super.getAlias();
    }
    public Queue<Tarefa> getTarefas() {
        return tarefas;
    }


}
