public class Lobo extends Jugador{
    public Lobo(String name){
	super(name, 'L');
    }

    public void loboVota(Jugador presa){
	if (presa == null)
	    throw new IllegalArgumentException("El jugador debe de existir");
	if (presa == this)
	    return;
	
	
    }

    

}
