package xogo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Definición da clase Impostor, que é un tipo especial de xogador e tamén observador
public class Impostor extends Xogador implements Observer{

    // Lista de estudantes eliminados polo impostor
    List<Estudante> eliminados;
    int asesinatos=0;

    public Impostor(String alias) {

        super(alias);
        eliminados=new  ArrayList<>();

    }

    // Método update() implementado pola interface Observer, que decide se o impostor mata a outro xogador
    @Override
    public void update(Xogador xogador) {
            if (Math.random() < 0.50) { // 50% probabilidades de matar
                if (xogador instanceof Estudante) {
                    //System.out.println("Impostor " + getAlias() + " matou " + xogador.getAlias());
                    xogador.setVivo(false);
                    eliminados.add((Estudante) xogador);
                    asesinatos++;
                }
                // Visualiza o resultado dos asasinatos nesta rolda


            }

    }
    // Método para obter a lista de estudantes eliminados polo impostor
    public int getEliminados() {
        int numEliminados=0;
        for(int i=0;i<eliminados.size();i++){
            numEliminados=i;
        }
        return numEliminados;
    }
}
