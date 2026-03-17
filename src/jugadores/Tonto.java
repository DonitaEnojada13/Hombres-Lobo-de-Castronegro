package jugadores;
import acciones.AccionDeDia;
public class Tonto extends Jugador implements AccionDeDia{
    public Tonto(String name){
	    super(name, 'T');
    }

    //Las votaciones se delegan al mundo real, ahora el rol del tonto se quedara como un simple aldeano.
    public Jugador votaciones(){
        return null;
    }
}
