package tripulantes;

public class Vulcano extends Tripulante {
    
    public Vulcano(String nom,int ant){
        super(nom, ant);
        this.sueldo = 30;
    }


    public String getOrigen(){
        return "Vulcano";
    }
}
