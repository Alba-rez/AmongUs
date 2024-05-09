package xogo;

import java.util.*;

public class Xogador {

    private String alias;
    private Queue<Tarefa> tarefas;

    public Xogador(String alias) {
        this.alias = alias;
        List<Tarefa> tarefasAleatorias = new ArrayList<>(Tarefa.getTarefas());
        Collections.shuffle(tarefasAleatorias);
        this.tarefas = new LinkedList<>();
        for (int i = 0; i <  tarefasAleatorias.size(); i++) {
            this.tarefas.add(tarefasAleatorias.get(i));
        }
    }

    public void realizarTarefa() {
        if (!tarefas.isEmpty()) {
            Tarefa tarefa = tarefas.poll();
            tarefa.realizar();
        } else {
            System.out.println("Non hai máis tarefas para realizar.");
        }
    }

    public String getAlias() {
        return alias;
    }
}
