package misiones;

public class Mision3 extends Mision{

    public Mision3(String nombre, String descripcion, float combustibleRequerido, float energiaAportada, float desgasteRequerido){
        super(nombre, descripcion, combustibleRequerido, energiaAportada, desgasteRequerido);
    }

    public void ejecutar(){
        System.out.println("COMPLETANDO EL REGRESO SIMULADO");
    }

    public void evaluar(){
        System.out.println("LA NAVE FINALIZO Y SE ENCUENTRA EN ESTADO OPERATIVO");
    }
}
