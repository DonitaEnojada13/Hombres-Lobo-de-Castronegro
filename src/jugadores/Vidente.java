package jugadores;
import acciones.*;
public class Vidente extends Jugador implements AccionDeNoche{
    public Vidente(String nombre){
		  super(nombre, 'V');
    }

    public String obtenerMensajeDespertar(){
      return "El vidente despierta y tiene el chance de ver el rol de alguno de los jugadores";
    }

    @Override
    public String accionNocturna(Jugador... objetivos){
      if(objetivos == null || objetivos.length == 0)
	  return "No has elegido a nadie";
      
      return clarividencia(objetivos[0]); 
    }
    
    private String clarividencia(Jugador objetivo){
      if(objetivo == null) throw new IllegalArgumentException("El jugador debe de existir");
      if(!objetivo.getVivo()) return "Tiene el rol de muerto. Que esperabas, bobo?";
      if (objetivo  instanceof Vidente) return "Felicidades reafirmaste lo que ya sabias, bobo";
      
      return String.valueOf(objetivo.getRol());
    }
}
