package misiones;

public class Mision3 extends Mision{

    public Mision3(float combustibleRequerido,float desgasteRequerido){
        super("M-03", "Retorno seguro", combustibleRequerido, 0, desgasteRequerido);
    }

    public void ejecutar(){
        System.out.println("COMPLETANDO EL REGRESO SIMULADO");
    }

    public void evaluar(){
        System.out.println("LA NAVE FINALIZO Y SE ENCUENTRA EN ESTADO OPERATIVO");
    }
}
