package visorParalelo;

import java.util.ArrayDeque;

public class PlataformaDeCarga {
    private ArrayDeque<Bateria> baterias;
    private int tope;

    public PlataformaDeCarga(int t){
        this.baterias = new ArrayDeque<>();
        this.tope = t;
    }

    public PlataformaDeCarga(){
        this(5);
    }

    public synchronized void ponerBateria(Bateria b) throws InterruptedException, NullPointerException {
        if (b != null){
            while (baterias.size() >= tope) {
                wait();
            }
            baterias.addFirst(b);
            notifyAll();
        } else {
            throw new NullPointerException();
        }
    }

    public synchronized Bateria sacarBateria() throws InterruptedException {
        Bateria b;
        while (baterias.isEmpty()){
            wait();
        }
        b = baterias.removeLast();
        notifyAll();
        return b;
    }

    public int getTope() {
        return tope;
    }

    public synchronized void setTope(int tope) {
        this.tope = tope;
        notifyAll();
    }
}
