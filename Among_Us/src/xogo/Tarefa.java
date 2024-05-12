package xogo;

import java.util.*;

class Tarefa implements Comparable<Tarefa>{

    // Colección de tarefas, almacenadas nun Set para evitar duplicados
    private static Set<Tarefa> tarefas = new TreeSet<>();

    private String nome;


    // Bloque estático para inicializar algúns exemplos de tarefas ao cargar a clase
    static {
        new Tarefa("arranxar o proxector");
        new Tarefa("cambiar un cable Ethernet");
        new Tarefa("coller xices");
    }

    public Tarefa(String nome) {
        this.nome = nome;
        tarefas.add(this);

    }
    // Método equals para comparar tarefas por nome


    @Override
    public int compareTo(Tarefa other) {
        return this.nome.compareTo(other.nome);
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

    // Método estático para obter todas as tarefas almacenadas
    public static Set<Tarefa> getTarefas() {
        return tarefas;
    }

    @Override
    public String toString() {
        return nome;
    }


}
