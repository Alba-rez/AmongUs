package xogo;

public class Tarefa {

    private String nome;
    private String habitacion;

    public Tarefa(String nome, String habitacion) {
        this.nome = nome;
        this.habitacion = habitacion;
    }

    public String getNome() {
        return nome;
    }

    public String getHabitacion() {
        return habitacion;
    }
}
