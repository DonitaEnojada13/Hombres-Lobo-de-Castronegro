package jugadores;
import acciones.*;
public class Flautista extends Jugador implements AccionDeNoche{
    public Flautista(String name){
		super(name, 'F');
    }

    public void accionNocturna(){
	return;
    }
    private void hechizar(Jugador objetivo){
	if (objetivo == null)
	    throw new IllegalArgumentException("El jugador debe de existir");
	if (this == objetivo){
	    System.out.println("No te puedes hechizar a ti mismo, espabila");
	    return;
	}
	
	if(!objetivo.getHechizado()){
	    objetivo.setHechizado(true);
	} else {
	    System.out.println("Pero la musica ya sonaba en su mente...");
	    return;
	}
	
    }
}
