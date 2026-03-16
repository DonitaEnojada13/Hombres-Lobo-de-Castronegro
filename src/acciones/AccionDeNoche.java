package acciones;
import jugadores.Jugador;
public interface AccionDeNoche{

    public String obtenerMensajeDespertar();

    public void  ejecutarAcciónDeNoche(Jugador... objetivos);
    
}
