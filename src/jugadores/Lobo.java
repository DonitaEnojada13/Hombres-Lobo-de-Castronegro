package jugadores; 
import acciones.*
public class Lobo extends Jugador implements AccionDeNoche{
    public Lobo(String name){
	super(name, 'L');
    }

    public void accionNocturna(){
	return;
    }
    private void mata(Jugador presa){
	if (presa == null)
	    throw new IllegalArgumentException("El jugador debe de existir");
	if (presa == this)
	    return;
	
	
    }

    

}
