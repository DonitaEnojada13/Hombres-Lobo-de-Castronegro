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
    public void accionNocturna(Jugador... objetivos){
		
		if(objetivos == null || objetivos.length == 0) return;

		for(Jugador hechizado : objetivos){
			hechizar(hechizado);
		}

		return;

    }
    private void hechizar(Jugador objetivo){
		if (objetivo == null)
			throw new IllegalArgumentException("El jugador debe de existir");
		if (objetivo instanceof Flautista){
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
