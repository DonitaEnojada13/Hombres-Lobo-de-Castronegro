import estructuras.ListaJugadores;
public class Main{
    public static void main(String[] args){
        
        System.out.println("======= LOBOS NEGROS DE CATONEGRO =======\n");

        ListaJugadores nuevaLista = new ListaJugadores();

        Controlador nuevoJuego = new Controlador(nuevaLista);

        nuevoJuego.empiezaPartida();

        nuevoJuego.juego();

        System.out.println("===============================================");
    }
}