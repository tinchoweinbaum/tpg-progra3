package misiones;

public class Mision1 extends Mision{

    public Mision1(float combustibleRequerido, float energiaAportada, float desgasteRequerido){
        super("M-01", "Intercepcion y asistencia", combustibleRequerido, energiaAportada, desgasteRequerido);
    }

    public void ejecutar(){
        System.out.println("VIAJANDO AL OBJETIVO PARA REALIZAR ASISTENCIA");
    }

    public void evaluar(){
        System.out.println("LA ASISTENCIA SE COMPLETO CON RECURSOS SUFICIENTES");
    }

}
