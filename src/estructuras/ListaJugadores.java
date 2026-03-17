package estructuras;
import jugadores.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

//Por el momento, veamos como se comporta sin genericos
public class ListaJugadores implements Iterable<Jugador> {
    private Nodo cabeza;
    private Nodo rabo;
    private int jugadores;

	@Override
	public Iterator<Jugador> iterator(){
		return new IteradorLista();
	}

    public int getJugadores(){
		return jugadores;
    }

    public boolean esVacia(){
		return cabeza == null && rabo == null;
    }

    public void meteJugador(Jugador participante){
		if (participante == null) throw new IllegalArgumentException("El jugador debe de existir");
		if(buscaNombre(participante.getNombre())) throw new IllegalArgumentException("Ya existe un jugador con ese nombre");
		
		Nodo aux = new Nodo(participante);
		
		if (esVacia()){
			cabeza = aux;
			rabo = aux;
			jugadores++;
			return;
		}

		rabo.siguiente = aux;
		aux.anterior = rabo;
		rabo = aux;
		jugadores++;
    }

    private boolean buscaNombre(String porBuscar){

		if (porBuscar == null || porBuscar.trim().isEmpty()) return false;

		String s = porBuscar.trim().toUpperCase();
	
		if (s.isEmpty()) return false; // Aqui habria que hacer algo para evitar los nombres vacios, no?
		
		Nodo aux = cabeza;

		while(aux != null) {
			if(aux.jugador.getNombre().equalsIgnoreCase(s)) return true;

			aux = aux.siguiente;
		}
		return false;
    }

	public Jugador obtenerPorNombre(String porBuscar){
		if(porBuscar == null || porBuscar.trim().isEmpty()) throw new IllegalArgumentException("El nombre no puede ser vacio");
		
		String s = porBuscar.trim();
        Nodo aux = cabeza;

        while(aux != null) {
            if(aux.jugador.getNombre().equalsIgnoreCase(s)){
                return aux.jugador; 
            }
            aux = aux.siguiente;
        }
        return null; 
    }

	public Jugador obtenerPorIndice(int indice){
		if(indice < 0 || indice >= jugadores){
			throw new IndexOutOfBoundsException("Indice supera el rango permitido");
		}

		Nodo aux = cabeza;
		for(int i = 0; i < indice; i++){
			aux = aux.siguiente;
		}

		return aux.jugador;
	}
	

	//Clase del nodo
	private class Nodo{
		private Jugador jugador;
		private Nodo siguiente;
		private Nodo anterior;

		private Nodo(Jugador nJugador){
			jugador = nJugador;
			this.siguiente = null;
			this.anterior = null;
		}
    }

	//Clase del iterator
	private class IteradorLista implements Iterator<Jugador>{
		private Nodo actual;

		public IteradorLista(){
			this.actual = cabeza;
		}

		@Override
		public boolean hasNext(){
			return actual != null;
		}

		@Override
		public Jugador next(){
			if(!hasNext()){
				throw new NoSuchElementException("Ya no hay más jugadores en la lista");
			}

			Jugador jugadorActual = actual.jugador;
			return jugadorActual;
		}	
	}

}
