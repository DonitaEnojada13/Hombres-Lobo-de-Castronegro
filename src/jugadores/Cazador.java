package jugadores; 
public class Cazador extends Jugador{
    private boolean bala;
    public Cazador(String nombre){
	super(nombre, 'C');
	this.bala = true;
    }
    public void oneShot(Jugador objetivo){
	if (objetivo == null)
	    throw new IllegalArgumentException("El jugador debe de existir");
	if(bala == false)
	    return;
	if (objetivo == this)
	    return;
	if (!objetivo.getVivo())
	    return;

	objetivo.setVivo(false);
	bala = false;
	}
}
