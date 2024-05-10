package xogo;

import java.util.*;

class Tarefa {


    private static Set<Tarefa> tarefas = new LinkedHashSet<>();

    private String nome;


    static {
        new Tarefa("arranxar o proxector");
        new Tarefa("cambiar un cable Ethernet");
        new Tarefa("coller xices");
    }

    public Tarefa(String nome) {
        this.nome = nome;
        tarefas.add(this);


    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarefa tarefa = (Tarefa) o;
        return Objects.equals(nome, tarefa.nome) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public void realizar() {
    }

    public String getNome() {
        return nome;
    }

    public static Set<Tarefa> getTarefas() {
        return tarefas;
    }
    @Override
    public String toString() {
        return nome;
    }
}
