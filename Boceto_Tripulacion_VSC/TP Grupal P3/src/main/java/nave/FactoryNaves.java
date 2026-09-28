package nave;

public class FactoryNaves {

    public Nave getTipo(String tipoNave){
        switch (tipoNave.toUpperCase()){
            case "CARGUERO" :
                return new NaveCarguero();
            case "COMBATE" :
                return new NaveCombate();
            case "EXPLORADOR" :
                return new NaveExploradora();
            default: return null;
        }
    }

}