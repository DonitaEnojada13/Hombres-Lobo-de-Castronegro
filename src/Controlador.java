import java.util.Random;
import java.util.Scanner;

public class Controlador{

    // En teoria, el mvc trabaja con el modelo, en este caso, este esta compuesto por
    // Los jugadores y la lista doblemente ligada
        // Scanner para las entradas
    
    private ListaJugadores lista;
    private Scanner sc;

    public Controlador(ListaJugadores nLista) {
	lista = nLista;
	sc = new Scanner(System.in);
    }

    public void empiezaPartida() {
	return;
    }

    private void armarJuego(int numJugadores) {
	return;
    }

    // Al parecer, desordenar un arreglo es algo  poquitin mas complicado que ordenarlo
    // me puse a investigar y encontre un algoritmo que permite pseudo-desorden. Intentare
    // explicarlo de la manera mas campechana posible porque esto es un poco raro

    
    // Fisher-Yates Shuffle
    private void desArreglo(char[] entrada){
	Random rd = new Random();

	//empezamos desde el ultimo elemento en el arreglo
	for (int i = entrada.length-1; i > 0; i--) {

	    // Aqui se guarda un numero pseudo aleatorio desde 0 hatsa el aleatorio
	    int j = rd.nextInt(i + 1);

	    //El clasico swap de toda la vida
	    char temp = entrada[i];
	    entrada[i] = entrada[j];
	    entrada[j] = temp;
	}   
    }
    private char[] arregloRol(int num) {
	char[] roles = new char[num];
	int numLobos = num/5;
	int i = 0;

	while(i < numLobos) {
	    roles[i] = 'L';
	    i++;
	}
	roles[i++] = 'P';
	roles[i++] = 'F';
	roles[i++] = 'B';
	roles[i++] = 'V';
	roles[i++] = 'C';
	roles[i++] = 'T';

	while(i < roles.length) {
	    roles[i] = 'A';
	    i++;
	}
	return roles;
    }

    private String pideNom(){
	// Un while para pedir nombres
	String nombre = "";

	// Mientras el nombre sea vacio que se haga esta accion
	while(nombre.isEmpty()){    
	    System.out.println("Ingresa el nombre del jugador");
	    nombre = sc.nextLine().trim();
	    
	    if(nombre.isEmpty())
		System.out.println("Aqui no aceptamos nombres vacios, bobo");
	}
	return nombre;
    }

    private int pideNum(){
    	int numJugadores = 0;

	// Me apegare a las reglas que el profesor dejo, podria hacer malabares mentales para aumentar los
	// jugadores, pero no me gusta comer mas de lo que hay en mi plato (tal vez a futuro le mueva)
	
	while(numJugadores < 8 || numJugadores > 20){
	    System.out.println("Ingresa la cantidad de jugadores (Minimo 8 jugadores, maximo 20 jugadores)");

	    if(sc.hasNextInt()) {
		numJugadores = sc.nextInt();
		sc.nextLine();
		
		if(numJugadores < 8 || numJugadores > 20){
		    
		    System.out.println("Espabila, ese numero no esta contemplado");
		}
	    } else {
		System.out.println("Eso no es un numero, espabila");
		sc.next();
	    }
	}

	return numJugadores;
    }

}
