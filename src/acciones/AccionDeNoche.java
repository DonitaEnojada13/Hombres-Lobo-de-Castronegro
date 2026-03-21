package acciones;
import jugadores.Jugador;
public interface AccionDeNoche{

    public String obtenerMensajeDespertar();

    //Agregamos los vargas, ya que varía la cantidad de jugadores que puden ser afectados
    public String accionNocturna(Jugador... objetivos);
    
}
