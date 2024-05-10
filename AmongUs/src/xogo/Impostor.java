package xogo;

import java.util.List;
import java.util.Queue;

public class Impostor extends Xogador implements Observer{

    List<Estudante> eliminados;

    public Impostor(String alias) {
        super(alias);
    }

    @Override
    public void update(Xogador xogador) {
        if (Math.random() < 0.75) { // 75% probabilidades de matar
            if(xogador instanceof Estudante) {
                //System.out.println("Impostor " + getAlias() + " matou " + xogador.getAlias());
                xogador.setVivo(false);
            }
        }
    }
}
