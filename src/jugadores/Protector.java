package jugadores;
import acciones.*;
public class Protector extends Jugador implements AccionDeNoche{
    private Jugador fueProtegido;
    
    public Protector(String name){
		super(name, 'P');
		this.fueProtegido = null;
    }

    public Jugador getFueProtegido() {
	return fueProtegido;
    }
    
    @Override
    public String obtenerMensajeDespertar(){
	return "El protector despierta y tiene la posibilidad de proteger a un jugador o a sí mismo";
    }

    public String accionNocturna(Jugador... objetivo){
	if(objetivo == null || objetivo.length == 0)
	    return "No seleccionaste a nadie.";
	
	Jugador seleccionado = objetivo[0];
	
        if (seleccionado == fueProtegido)
	    return "No puedes proteger a la misma persona dos veces seguidas.";
	
	protect(seleccionado);
        return "Has protegido a " + seleccionado.getNombre() + " Estará a salvo esta noche.";
    }
    
    private void protect(Jugador objetivo) {
    
        objetivo.setProteccion(true); 
    
        this.fueProtegido = objetivo;
    }
    
}
