package jugadores;
import acciones.*;
public class Protector extends Jugador implements AccionDeNoche{
    private Jugador fueProtegido;
    
    public Protector(String name){
		super(name, 'P');
		this.fueProtegido = null;
    }

	public String obtenerMensajeDespertar(){
		return "El protector despierta y tiene la posibilidad de proteger a un jugador o a sí mismo";
	}

    public void accionNocturna(Jugador... objetivo){

		if(objetivo == null || objetivo.length == 0) return;

		for(Jugador protegido : objetivo){	
				protect(protegido);
		}
		

		return;
    }
    
    private void protect(Jugador objetivo){
		if (objetivo == null)
			throw new IllegalArgumentException("El jugador debe de existir");
		if (!objetivo.getVivo()){
			System.out.println("Espabila, los muertos no necesitan proteccion");
			return;
		}
		if (objetivo == fueProtegido){
			System.out.println("No puedes proteger dos veces a la misma persona");
			return;
		}
		objetivo.setProteccion(true);
			this.fueProtegido = objetivo;
		}

}
