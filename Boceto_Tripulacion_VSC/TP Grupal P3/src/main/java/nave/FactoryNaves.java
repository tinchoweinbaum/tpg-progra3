package nave;

public class FactoryNaves {
    public static Nave _instance = null;

    public Nave getInstance(String tipoNave){
        if (_instance==null){
            _instance = getTipo(tipoNave);
        }
        return  _instance;
    }

    private Nave getTipo(String tipoNave){
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