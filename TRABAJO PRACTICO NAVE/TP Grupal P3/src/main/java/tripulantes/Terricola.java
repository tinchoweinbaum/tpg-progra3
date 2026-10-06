package tripulantes;

public class Terricola extends Tripulante{
    
    public Terricola(String nom,int ant){
        super(nom, ant);
        this.setSueldo(20);
    }


    public String getOrigen(){
        return "Terricola";
    }


}
