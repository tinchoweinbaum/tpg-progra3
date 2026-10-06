package misiones;

import bitacora.RegistroBitacora;

public class Mision1 extends Mision{

    public Mision1(String nombre, String descripcion, float combustibleRequerido, float energiaAportada, float desgasteRequerido){
        super(nombre, descripcion, combustibleRequerido, energiaAportada, desgasteRequerido);
    }

    public void preparar(){
        System.out.println("COMENZANDO INTERCEPCION Y ASISTENCIA");
    }

    public void ejecutar(){
        System.out.println("VIAJANDO AL OBJETIVO PARA REALIZAR ASISTENCIA");
    }

    public void evaluar(){
        System.out.println("LA ASISTENCIA SE COMPLETO CON RECURSOS SUFICIENTES");
    }

}
