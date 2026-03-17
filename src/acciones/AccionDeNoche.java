package acciones;
import jugadores.Jugador;
public interface AccionDeNoche{

    public String obtenerMensajeDespertar();

    //Agregamos los vargas, ya que varía la cantidad de jugadores que puden ser afectados
    public void  accionNocturna(Jugador... objetivos);
    
}
