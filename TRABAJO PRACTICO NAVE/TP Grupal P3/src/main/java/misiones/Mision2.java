package misiones;

public class Mision2 extends Mision{

    public Mision2(String nombre, String descripcion, float combustibleRequerido, float energiaAportada, float desgasteRequerido){
        super(nombre, descripcion, combustibleRequerido, energiaAportada, desgasteRequerido);
    }

    public void ejecutar(){
        System.out.println("LLEGANDO AL PUNTO SIMULADO PARA OBTENER DATOS Y EXTRAER LA MUESTRA");
    }

    public void evaluar(){
        System.out.println("EL ELEMENTO QUEDO REGISTRADO COMO OBTENIDO EXITOSAMENTE");
    }
}
