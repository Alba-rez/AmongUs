package xogo;

import menu.SingletonScanner;

import java.util.*;

public class Xogo {
    Scanner sc = SingletonScanner.getInstance();
    private List<Tarefa> tarefas;
    private List<Xogador> xogadores;
    private int tempoMaximo;

    public Xogo() {

        this.tarefas = new ArrayList<>();
        this.xogadores = new ArrayList<>();
        this.tempoMaximo = 0;
    }

    public void iniciarPartida(int numeroImpostores) {
        Random rand = new Random();
        List<Xogador> impostores = new ArrayList<>();
        for (int i = 0; i < numeroImpostores; i++) {
            int randomIndex = rand.nextInt(xogadores.size());
            Xogador xogador = xogadores.get(randomIndex);
            Impostor impostor = new Impostor(xogador.getAlias());
            impostores.add(impostor);
            xogadores.remove(randomIndex);
        }
        xogadores.addAll(impostores);
    }

    public void finalizarPartida() {
        // Implementa la lógica para finalizar una partida
    }

    public void xogarRolda() {
        // Implementa la lógica para jugar una ronda
    }
    public void engadirTarefa() {
        String nome=sc.nextLine();
        String habitacion=sc.nextLine();
        engadirTarf(nome,habitacion);
    }

    private void engadirTarf(String nome, String habitacion) {
        Tarefa novaTarefa = new Tarefa(nome, habitacion);
        this.tarefas.add(novaTarefa);
    }

    public void borrarTarefa() {
       String nomeT=sc.nextLine();
       String nomeH=sc.nextLine();
       borrarTarf(nomeT,nomeH);
    }

    private void borrarTarf(String nomeTarefa,String habitacion){
        Tarefa borraTarefa=null;
        for(Tarefa trf:tarefas){
            if(trf.getNome().equals(nomeTarefa)){
                borraTarefa=trf;
                break;
            }
        }
        tarefas.remove(borraTarefa);
        System.out.println("Tarefa eliminada.");
    }


    public void verTarefas() {
        for(Tarefa tarf:tarefas){
            System.out.println("Nome tarefa: "+tarf.getNome()+", Nome habitación:  "+tarf.getHabitacion());
        }
    }

    public void engadirXogador() {

    }

    public void borrarXogador() {

    }

    public void verXogadores() {

    }

    public void configurarTempo() {

    }


}
