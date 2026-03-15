package acciones;
//Por el momento, veamos como se comporta sin genericos
public class ListaJugadores {
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

    
    public int getJugadores(){
	return jugadores;
    }

    public boolean esVacia(){
	return cabeza == null;
    }

    public void meteJugador(Jugador participante){
	if (participante == null)
	    throw new IllegalArgumentException("El jugador debe de existir");
	
	Nodo mete = new Nodo(participante);
	
	if (esVacia()){
	    cabeza = mete;
	    rabo = mete;
	    jugadores++;
	    return;
	}

	rabo.siguiente = mete;
	mete.anterior = rabo;
	rabo = mete;
	jugadores++;
    }

    //Que pasa si hay dos jugadores llamados de la misma manera?
    private boolean buscaNombre(String porBuscar){

	if (porBuscar == null)
	    throw new IllegalArgumentException("No puedes introducir Strings nulos");

	if (esVacia())
	    return false;

	String s = porBuscar.trim().toUpperCase();
	
	if (s.isEmpty())
	    return false; // Aqui habria que hacer algo para evitar los nombres vacios, no?
	Nodo aux = cabeza;

	while(aux != null) {
	    if(aux.jugador.getNombre().equalsIgnoreCase(s))
		return true;
	    aux = aux.siguiente;
	}
	return false;
    }

    public void quitaPorNombre(String nombre) {
	if(nombre == null)
	    throw new IllegalArgumentException("El nombre no debe de ser null");
	if(esVacia())
	    return;

	String s = nombre.trim().toUpperCase();
	Nodo aux = cabeza;

	while (aux != null) {
	    if (aux.jugador.getNombre().equalsIgnoreCase(s)){

		if (jugadores == 1) {
		    cabeza = null;
		    rabo = null;
		} else if (aux == cabeza) {
		    cabeza = cabeza.siguiente;
		    cabeza.anterior = null;
		} else if (aux == rabo) {
		    rabo = rabo.anterior;
		    rabo.siguiente = null;
		} else {
		    aux.anterior.siguiente = aux.siguiente;
		    aux.siguiente.anterior = aux.anterior;
		}
		jugadores--;
		return;
	    }
	    aux = aux.siguiente;
	}
    }
}
