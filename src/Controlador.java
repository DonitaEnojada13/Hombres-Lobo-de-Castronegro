import jugadores.*;
import acciones.*;
import estructuras.*;
import java.util.Scanner;
import java.util.Random;
public class Controlador{

    // En teoria, el mvc trabaja con el modelo, en este caso, este esta compuesto por
    // Los jugadores y la lista doblemente ligada
        // Scanner para las entradas
    
    private ListaJugadores lista;
    private Scanner sc;
    private Jugador victimaLobo;
    private boolean fMuerto;

    public Controlador(ListaJugadores nLista) {
	lista = nLista;
	sc = new Scanner(System.in);
	victimaLobo = null;
    }

   
    public void empiezaPartida() {
	int numero = pideNum();
	armarJuego(numero);

	
    }

    private void cicloNoche(){
	System.out.println("La noche se cierne sobre la aldea");
    }

    private int estadoDeJuego(Informacion c) {
	if (c.getHechi() == c.getVivos()-1 && c.getFlautistaEstado())
	    return 2;
	if (c.getLobos() >= c.getHumanos())
	    return 1;
	if (c.getLobos() == 0 && !c.getFlautistaEstado())
	    return 3;
	return 0;
	
    }
    
    private void armarJuego(int numJugadores) {
	char[] rolesAleatorios = arregloRol(numJugadores);
	desArreglo(rolesAleatorios);

	for(int i = 0; i < rolesAleatorios.length; i++) {
	    String s = pideNom();
	    char r = rolesAleatorios[i];
	    lista.meteJugador(creaJugador(r,s));
	}
    }
    
    private Jugador creaJugador(char r, String s) {
	 switch(r) {
	    case 'L':
		return new Lobo(s);
	    case 'B':
		return new Bruja(s);
	    case 'P':
	        return new Protector(s);
	    case 'F':
		return new Flautista(s);
	    case 'T':
		return new Tonto(s);
	    case 'A':
		return new Aldeano(s);
	    case 'C':
		return new Cazador(s);
	    case 'V':
		return new Vidente(s);
	 default:
	    System.out.println("Ah canijote, como llego eso aqui? (Toma, eres un aldeano)");
	    return new Aldeano(s);
	    }
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
