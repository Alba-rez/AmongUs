package xogo;

import menu.SingletonScanner;

import java.util.*;

public class Xogo {
    Scanner sc = SingletonScanner.getInstance();
    private List<Tarefa> tarefas;
    private List<Xogador> xogadores;
    private int tempoMaximo;
    private List<String> habitacions;
    private boolean xogoEnMarcha = false;


    public Xogo() {

        this.tarefas = new LinkedList<>();
        this.xogadores = new LinkedList<>();
        this.tempoMaximo = 0;
        this.habitacions = new LinkedList<>();



        this.habitacions.add("Aula 1");
        this.habitacions.add("Aula 21");
        this.habitacions.add("Aula 23");

        // Crear tarefas
        new Tarefa("Limpar o pó", "Aula 1");
        new Tarefa("Abrir ventás", "Aula 21");
        new Tarefa("Fregar o chan", "Aula 23");
    }


    public String obtenerHabitacionAleatoria() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(habitacions.size());
        return habitacions.get(randomIndex);
    }

    public void iniciarPartida(int numeroImpostores,int numeroEstudantes) {
        Random rand = new Random();

        // Garantizar mínimo un impostor
        if (numeroImpostores < 1) {
            numeroImpostores = 1;
        }

        //  impostores
        for (int i = 0; i < numeroImpostores; i++) {
            int randomIndex = rand.nextInt(xogadores.size());
            Xogador xogador = xogadores.get(randomIndex);
            Impostor impostor = new Impostor(xogador.getAlias());
            xogadores.set(randomIndex, impostor);
        }

        // estudiantes
        for (int i = numeroImpostores; i < numeroImpostores + numeroEstudantes; i++) {
            int randomIndex = rand.nextInt(xogadores.size());
            Xogador xogador = xogadores.get(randomIndex);
            Estudante estudante = new Estudante(xogador.getAlias());

            // Asignar tarefas ós estudantes
            for (Tarefa tarefa : Tarefa.getTarefas()) {
                estudante.getTarefas().add(tarefa);
            }

            xogadores.set(randomIndex, estudante);
            estudante.realizarTarefa();
        }

        xogoEnMarcha=true;
    }

    public void finalizarPartida() {

        int numImpostores = 0;
        int numEstudantes = 0;


        for (Xogador xogador : xogadores) {
            if (xogador instanceof Impostor) {
                numImpostores++;
            } else {
                numEstudantes++;
            }
        }
        if (numEstudantes <= numImpostores) {
            System.out.println("A partida terminou.");


            for (Xogador xogador : xogadores) {
                xogador.setVivo(true);
                if (xogador instanceof Impostor) {
                    ((Impostor) xogador).eliminados.clear();
                }
            }
            this.tarefas.clear();
            this.habitacions.clear();
            this.habitacions.add("Bodega");
            this.habitacions.add("Aula 21");
            this.habitacions.add("Aula 23");

        }
        xogoEnMarcha=false;



    }

    public void xogarRolda() {
        String resposta;
        boolean tempoEsgotado=false;
        Set<String> aliasSet = new HashSet<>();

        do {
            if (xogadores.isEmpty()) {
                for (int i = 0; i < 7; i++) {
                    String alias;
                    do {
                        alias = "@" + (char) ('a' + new Random().nextInt(26)) + (char) ('a' + new Random().nextInt(26)) + (char) ('a' + new Random().nextInt(26));

                    }while(!aliasSet.add(alias));

                        Xogador xogador = new Xogador(alias);
                        xogadores.add(xogador);
                }
            }

            // xeramos os impostores, que non poderán ser máis ca 3 e menos ca 1
            int numImpostores = Math.max(1,(int) (Math.random() * xogadores.size() / 2));
            int numEstudantes = xogadores.size() - numImpostores;
            iniciarPartida(numImpostores, numEstudantes);

            for (Xogador xogador : xogadores) {
                String novaHabitacion = obtenerHabitacionAleatoria();
                xogador.moverParaHabitacion(novaHabitacion);
                System.out.println("O xogador " + xogador.getAlias() + " moveuse á habitación " + novaHabitacion);
            }
                int asesinatos=0;
            for (Xogador xogador : xogadores) {
                if(xogador instanceof Impostor) {
                    Xogador victima=xogadores.get(new Random().nextInt(xogadores.size()));
                    if(victima instanceof Estudante && victima.isVivo()) {
                        ((Impostor) xogador).update(victima);
                        if(!victima.isVivo()) {
                            asesinatos++;
                        }
                    }

                }

            }
            System.out.println("Houbo " + asesinatos + " asesinato(s) nesta ronda.");



            System.out.println("¿Queres eliminar a un xogador? (s/n)");
            long tempoInicial = System.currentTimeMillis();
            resposta = sc.next();
            sc.nextLine();
            long tempoFinal = System.currentTimeMillis();

            if ((tempoFinal - tempoInicial) > getTempoMaximo()) {
                System.out.println("Tempo esgotado.");
                tempoEsgotado=true;
                xogarRolda();

            } else if (resposta.equalsIgnoreCase("s")) {
                borrarXogador();

            }else if(!resposta.equalsIgnoreCase("n")){
                System.out.println("Resposta non válida. Por favor, introduce 's' ou 'n'.");

            }

            if (!xogoEnMarcha || resposta.equalsIgnoreCase("n")) {
                System.out.println("¿Queres xogar outra rolda? (s/n)");
                resposta = sc.nextLine();
                if (resposta.equalsIgnoreCase("n")) {
                    System.exit(0);
                }

            }

        }while(resposta.equalsIgnoreCase("s") && xogoEnMarcha);

    }
    public void engadirTarefa() {
        String nome=sc.nextLine();
        String habitacion=sc.nextLine();
        engadirTarf(nome,habitacion);
    }

    private void engadirTarf(String nome, String habitacion) {
        Tarefa novaTarefa = new Tarefa(nome, habitacion);
        this.tarefas.add(novaTarefa);
        verTarefas();
    }

    public void borrarTarefa() {
        System.out.println("Indica a tarefa a borrar");
       String nomeT=sc.nextLine();
       System.out.println("Indica o nome da habitación asignado á tarefa a borrar");
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
        verTarefas();

    }


    public void verTarefas() {
        for(Tarefa tarf:tarefas){
            System.out.println("Nome tarefa: "+tarf.getNome()+", Nome habitación:  "+tarf.getHabitacion());
        }
    }

    public void engadirXogador() {
        System.out.println("Indica un alias para o xogador");
        String alias=sc.nextLine();
        Xogador xogador=new Xogador(alias);
        xogadores.add(xogador);
        verXogadores();
    }

    public void borrarXogador() {
        System.out.println("Indica o alias do xogador a eliminar");
        String aliasXogador=sc.nextLine();
        borraXog(aliasXogador);



    }

    private void borraXog(String alias){
        Xogador aliasXog=null;
        for(Xogador xogador:xogadores){
            if(xogador.getAlias().equals(alias)){
                aliasXog=xogador;
                break;

            }
        }
        xogadores.remove(aliasXog);
        System.out.println("Xogador eliminado.");
        if(aliasXog instanceof Impostor){
            System.out.println("");
        } else {
            System.out.println("Eliminaches a un Estudante.");
            if(xogoEnMarcha){
                finalizarPartida();
                System.out.println("Rematou a partida. \n¿Queres voltar a xogar? (s/n)");
                String respuesta = sc.nextLine();
                if(respuesta.equalsIgnoreCase("s")){
                    xogarRolda();
                }
            }
        }


    }

    public void verXogadores(){
        for(Xogador xogador:xogadores){
            System.out.println("Xogador con alias: "+xogador.getAlias());
        }
    }

    public void configurarTempo() {
        System.out.println("Indica o tempo máximo de resposta (en milisegundos)");
        int tempo = sc.nextInt();
        if (tempo > 0) {
            this.tempoMaximo = tempo;
            System.out.println("Tempo máximo de resposta configurado a " + tempo + " segundos.");
        } else {
            System.out.println("O tempo debe ser un número positivo.");
        }
    }

    public int getTempoMaximo() {
        return tempoMaximo;
    }
}


