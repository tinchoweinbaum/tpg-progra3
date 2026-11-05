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
                System.out.println(nombreHilo() + " intento poner una bateria pero no hay mas capacidad");
                wait();
            }
            baterias.addFirst(b);
            System.out.println(nombreHilo() + " puso la bateria " + b.getID() + " en la plataforma | Ahora hay " + baterias.size() + " baterias");
            notifyAll();
        } else {
            throw new NullPointerException();
        }
    }

    public synchronized Bateria sacarBateria() throws InterruptedException {
        Bateria b;
        while (baterias.isEmpty()){
            System.out.println(nombreHilo() + " intento sacar una bateria pero no hay");
            wait();
        }
        b = baterias.removeLast();
        System.out.println(nombreHilo() + " saco la " + b + " de la plataforma | Ahora hay " + baterias.size() + " baterias");
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

    public String nombreHilo(){
        return Thread.currentThread().getName();
    }
}
