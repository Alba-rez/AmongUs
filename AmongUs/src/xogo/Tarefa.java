package xogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tarefa {

    private static List<Tarefa> tarefas = new ArrayList<>();

    private String nome;
    private String habitacion;

    public Tarefa(String nome, String habitacion) {
        this.nome = nome;
        this.habitacion = habitacion;
        tarefas.add(this);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarefa tarefa = (Tarefa) o;
        return Objects.equals(nome, tarefa.nome) && Objects.equals(habitacion, tarefa.habitacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, habitacion);
    }

    public void realizar() {
        System.out.println("Realizando a tarefa: " + nome + " na habitación : " + habitacion);
    }

    public String getNome() {
        return nome;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public static List<Tarefa> getTarefas() {
        return tarefas;
    }
}
