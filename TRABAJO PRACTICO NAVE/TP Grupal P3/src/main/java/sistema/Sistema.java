package sistema;

import asistentes.Asistente;
import exceptions.TipoNaveInvalidoException;
import nave.*;

public class Sistema {

    public static Sistema _instance=null;

    private Sistema(){
        super();
    }

    public static Sistema getInstance(){
        if(_instance==null){
            _instance=new Sistema();
        }
        return _instance;
    }

    public void inicio(String tipoNave){
        try{
            Nave nave = FactoryNaves.getTipo(tipoNave);
            Asistente asistente = new Asistente(nave);
        }catch(TipoNaveInvalidoException e){
            System.out.println(e.getMessage()); //no se me ocurre otra forma que no sea
                                                // con un cartelito ahora, tal vez más
                                                // adelante con una ventana para el usuario?
        }
    }

}
