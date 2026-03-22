package jugadores;
import acciones.*;
public class Flautista extends Jugador implements AccionDeNoche{
    public Flautista(String name){
		super(name, 'F');
    }

	@Override
	public String obtenerMensajeDespertar(){
		return "El flautista despierta y hechiza a los aldeanos ";
	}

    @Override
    public String accionNocturna(Jugador... objetivos){
	    if(objetivos == null || objetivos.length == 0)
		return "No seleccionaste a Nadie";
	    int hechi = 0;
	    
	    for(Jugador hechizado : objetivos){
		if (hechizado != null)
		    if(hechizar(hechizado))
			hechi++;
	    }
	    return "Tu musica cautivo a " + hechi + " aldaenos";
    }
    private boolean hechizar(Jugador objetivo) {
        if (objetivo == null)
	    throw new IllegalArgumentException("El objetivo debe de existir");
	
	if(!objetivo.getVivo() || objetivo instanceof Flautista) {
            return false; // No olvidar que puede pasar esto, el usuario es raro
        }

        if (!objetivo.getHechizado()) {
            objetivo.setHechizado(true);
            return true; 
        }
	return false;
    }
}
