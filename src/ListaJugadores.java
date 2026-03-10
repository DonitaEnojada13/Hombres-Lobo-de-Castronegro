public class ListaJugadores<Jugador>{
    private Nodo cabeza;
    private Nodo rabo;
    private int jugadores;

    private class Nodo{
	private Jugador jugador;
	private Nodo siguiente;
	private Nodo anterior;

	private Nodo(Jugador nJugador){
	    jugador = nJugador;
	}
    }

}
