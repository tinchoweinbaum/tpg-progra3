package misiones;

public class Mision2 extends Mision{

    public Mision2(float combustibleRequerido, float energiaAportada, float desgasteRequerido){
        super("M-02", "Recoleccion", combustibleRequerido, energiaAportada, desgasteRequerido);
    }

    public void ejecutar(){
        System.out.println("LLEGANDO AL PUNTO SIMULADO PARA OBTENER DATOS Y EXTRAER LA MUESTRA");
    }

    public void evaluar(){
        System.out.println("EL ELEMENTO QUEDO REGISTRADO COMO OBTENIDO EXITOSAMENTE");
    }
}
