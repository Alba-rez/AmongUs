package xogo;

import menu.SingletonScanner;

import java.util.*;

public class Xogo {
    Scanner sc = SingletonScanner.getInstance();

    //private List<Tarefa> tarefasIniciales;
    private Set <Xogador> xogadores;
    private int tempoMaximo;
    private List<String> habitacions;
    private boolean xogoEnMarcha = false;
    private Impostor impostor;
    private List<Observable> observables;


    public Xogo() {
        this.xogadores = new TreeSet<>();
        this.habitacions = new LinkedList<>();
        this.habitacions.add("Aula Bodega");
        this.habitacions.add("Sala Profesorado");
        this.habitacions.add("Conserxería");
        this.observables = new ArrayList<>();
    }


    public String obtenerHabitacionAleatoria() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(habitacions.size());
        return habitacions.get(randomIndex);
    }

    public void iniciarPartida(int numeroImpostores, int numeroEstudantes) {
        Random rand = new Random();
        Set<Integer> impostoresIndices = new HashSet<>();

        // Garantizar mínimo un impostor
        if (numeroImpostores < 1) {
            numeroImpostores = 1;
        }

        // Garantizar mínimo un estudiante
        if (numeroEstudantes <=3) {
            numeroEstudantes = 4;
        }

        if (numeroImpostores + numeroEstudantes > xogadores.size()) {
            numeroImpostores = xogadores.size() -numeroEstudantes;

        }

        //  impostores
        impostor = null;
        if (numeroImpostores <= xogadores.size()) {
            List<Xogador> xogadoresList = new ArrayList<>(xogadores);
            while (impostoresIndices.size() < numeroImpostores) {
                int randomIndex = rand.nextInt(xogadoresList.size());
                if (!impostoresIndices.contains(randomIndex) && xogadoresList.get(randomIndex).isVivo()) {
                    impostoresIndices.add(randomIndex);
                    Xogador xogador = xogadoresList.get(randomIndex);
                    impostor = new Impostor(xogador.getAlias());
                    xogadores.remove(xogador); // Elimina o xogador original
                    xogadores.add(impostor);

                }
            }
        } else {
            numeroImpostores = xogadores.size()-numeroEstudantes;
        }

        // Agregar impostores a la lista de observables
        if (impostor != null) {
            observables.add(impostor);
        }

        // Estudiantes
        List<Xogador> xogadoresList = new ArrayList<>(xogadores);
        for (int i = 0; i < xogadoresList.size(); i++) {
            if (!impostoresIndices.contains(i)) {
                Xogador xogador = xogadoresList.get(i);
                Queue<Tarefa> tarefas = obtenerTarefasAleatorias();
                Estudante estudante = new Estudante(xogador.getAlias());
                estudante.asignarTarefas(tarefas);
                xogadores.remove(xogador); // Elimina o xogador original
                xogadores.add(estudante);
                observables.add(estudante);
                estudante.realizarTarefa();
            }
        }

        xogoEnMarcha = true;

        // Agregar impostores como observadores de estudiantes
        for (Observable observable : observables) {
            if (observable instanceof Estudante) {
                for (Xogador xogador : xogadores) {
                    if (xogador instanceof Impostor) {
                        observable.rexistrarObserver((Observer) xogador);
                    }
                }
            }
        }
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




        }

        xogoEnMarcha=false;
        System.out.println("Rematou a partida. \n¿Queres voltar a xogar? (s/n)");
        String respuesta = sc.nextLine();
        if(respuesta.equalsIgnoreCase("s")){
            xogarRolda();
        }else {
            System.exit(0);
        }



    }
    public boolean finPartida(){
        int numImp=0;
        int numEstudantesVivos=0;

        for(Xogador xogador:xogadores){
            if(xogador instanceof Impostor){
                numImp++;
            } else if(xogador instanceof Estudante && xogador.isVivo()){
                numEstudantesVivos++;
            }
        }
        System.out.println("Número de impostores: " + numImp);
        System.out.println("Número de estudiantes vivos: " + numEstudantesVivos);
        return numEstudantesVivos <= numImp;
    }

    public void xogarRolda() {
        String resposta;
        boolean tempoEsgotado=false;
        Set<String> aliasSet = new TreeSet<>();

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
        do {

            // xeramos os impostores, que non poderán ser máis ca 3 e menos ca 1
            int numImpostores = Math.max(1,(int) (Math.random() * xogadores.size() / 2));
            int numEstudantes = 5;
            iniciarPartida(numImpostores, numEstudantes);

           System.out.println();
            for (Xogador xogador : xogadores) {
                String novaHabitacion = obtenerHabitacionAleatoria();
                xogador.moverParaHabitacion(novaHabitacion);
                if (xogador instanceof Estudante) {
                    Queue<Tarefa> tarefas = obtenerTarefasAleatorias();
                    if (!tarefas.isEmpty()) {
                        Tarefa tarefa = tarefas.poll();
                        System.out.println(xogador.getAlias() + " fixo " + tarefa + " en " + novaHabitacion);
                    }
                }
            }
            int asesinatos=0;
            List<Xogador> xogadoresList = new ArrayList<>(xogadores);

            Iterator<Xogador> iterator = xogadoresList.iterator();
            while (iterator.hasNext()) {
                Xogador xogador = iterator.next();
                if(xogador instanceof Impostor) {
                    int indexVictima = new Random().nextInt(xogadoresList.size());
                    Xogador victima = xogadoresList.get(indexVictima);
                    if (victima instanceof Estudante && victima.isVivo()) {
                        ((Impostor) xogador).update(victima);
                        if (!victima.isVivo()) {
                            asesinatos++;
                            xogadores.remove(victima);
                            xogadores.add(victima); // Actualizar a víctima na lista
                        }
                    }
                }
            }
            System.out.println("*************************************************");
            System.out.println("Houbo " + asesinatos + " asesinato(s) nesta ronda.");
            System.out.println("*************************************************");
            if(finPartida()){
                finalizarPartida();
                return;
            }


            System.out.println("¿Queres eliminar a un xogador? (s/n)");
            long tempoInicial = System.currentTimeMillis();
            resposta = sc.next();
            sc.nextLine();
            long tempoFinal = System.currentTimeMillis();

            if ((tempoFinal - tempoInicial) > getTempoMaximo()) {
                System.out.println("Tempo esgotado.");
                tempoEsgotado=true;
                xogoEnMarcha=true;
                xogarRolda();


            } else if (resposta.equalsIgnoreCase("s")) {
                borrarXogador();

            }else if(!resposta.equalsIgnoreCase("n")){
                System.out.println("Resposta non válida. Por favor, introduce 's' ou 'n'.");

            }

            if (!xogoEnMarcha || resposta.equalsIgnoreCase("n")) {
                System.out.println("¿Queres xogar outra rolda? (s/n)");
                resposta = sc.nextLine();
                xogoEnMarcha=true;
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
        Tarefa novaTarefa = new Tarefa(nome);
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
        for(Tarefa trf:Tarefa.getTarefas() ){
            if(trf.getNome().equals(nomeTarefa) ){
                borraTarefa=trf;
                break;
            }
        }
        if (borraTarefa != null) {
            Tarefa.getTarefas().remove(borraTarefa);
            System.out.println("Tarefa eliminada.");
        } else {
            System.out.println("No se encontró la tarea con el nombre " + nomeTarefa + " en la habitación " + habitacion);
        }
        verTarefas();

    }


    public void verTarefas() {
        for(Tarefa tarf:Tarefa.getTarefas()){
            System.out.println("Nome tarefa: "+tarf.getNome());
        }
    }

    public Queue<Tarefa> obtenerTarefasAleatorias() {
        List<Tarefa> tarefasAleatorias = new LinkedList<>(Tarefa.getTarefas());
        Collections.shuffle(tarefasAleatorias);
        return new LinkedList<>(tarefasAleatorias);
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
            System.out.println("Eliminaches a un Impostor!!");
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


