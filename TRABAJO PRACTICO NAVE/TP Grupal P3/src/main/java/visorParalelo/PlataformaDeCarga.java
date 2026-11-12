package visorParalelo;

import java.util.ArrayDeque;

public class PlataformaDeCarga {
    private ArrayDeque<Bateria> baterias;
    private int tope;
    private Visor visor;

    public PlataformaDeCarga(int t,Visor visor){
        this.baterias = new ArrayDeque<>();
        this.tope = t;
        this.visor = visor;
    }

    public PlataformaDeCarga(Visor visor){
        this(5,visor);
    }

    public synchronized void ponerBateria(Bateria b) throws InterruptedException, NullPointerException {
        if (b != null){
            while (baterias.size() >= tope) {
                visor.actualizaBitacoraParalela(nombreHilo() + " intento poner una bateria pero no hay mas capacidad");
                wait();
            }
            baterias.addFirst(b);
            visor.actualizaBitacoraParalela(nombreHilo() + " puso la bateria " + b.getID() + " en la plataforma | Ahora hay " + baterias.size() + " baterias");
            notifyAll();
        } else {
            throw new NullPointerException();
        }
    }

    public synchronized Bateria sacarBateria() throws InterruptedException {
        Bateria b;
        while (baterias.isEmpty()){
            visor.actualizaBitacoraParalela(nombreHilo() + " intento sacar una bateria pero no hay");
            wait();
        }
        b = baterias.removeLast();
        visor.actualizaBitacoraParalela(nombreHilo() + " saco la " + b + " de la plataforma | Ahora hay " + baterias.size() + " baterias");
        notifyAll();
        return b;
    }

    public void recibeMensajeNaves(String text){
        visor.actualizaBitacoraParalela(text);
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
