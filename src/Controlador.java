import jugadores.*;
import acciones.*;
import estructuras.*;
import java.util.Scanner;
import java.util.Random;


@SuppressWarnings("unused")
public class Controlador{

    // En teoria, el mvc trabaja con el modelo, en este caso, este esta compuesto por
    // Los jugadores y la lista doblemente ligada
        // Scanner para las entradas
    
    private ListaJugadores lista;
    private Scanner sc;
    private Jugador victimaLobo;
    private Jugador victimaBruja;
    private boolean fMuerto;
    private boolean tontoDesc;
    

    public Controlador(ListaJugadores nLista) {
		lista = nLista;
		sc = new Scanner(System.in);
		victimaLobo = null;
		victimaBruja = null;
		tontoDesc = false;
    }

    public void empiezaPartida() {
		int numero = pideNum();
		armarJuego(numero);	
		mostrarRoles();
    }

	public void juego(){
		boolean juegoTerminado = false;
		
		while(!juegoTerminado){
			System.out.println("\n ---- Cae la noche ----");
			cicloNoche();
			Informacion infoNoche = lista.contador();
			int estadoNoche = estadoDeJuego(infoNoche);

			if(estadoNoche != 0){
				anuncioGanadores(estadoNoche);
				break;
			}

			mostrarEstadoJugadores();

			System.out.println("\n ---- Inicia el Día ----");
			cicloDia();
			Informacion cicloDia = lista.contador();
			int estadoDia = estadoDeJuego(cicloDia);
			if(estadoDia != 0){
				anuncioGanadores(estadoDia);
				break;
			}
		}

	}

	private void anuncioGanadores(int estado){
		System.out.println("===== FIN DE LA PARTIDA =====");
		if(estado == 1){
			System.out.println("LOS THERIAN HAN GANADO\n Han superado en numero a los humanos");
		}else if(estado == 2){
			System.out.println("EL FLAUTISTA HA GANADO\n Logró hechizar a todo el pueblo, todo un fockboy");
		}else if(estado == 3){
			System.out.println("LOS ALDEANOS HAN GANADO\n Lograron ponerse de acuerdo y por ende ganaron");
		}
	}

    public void cicloDia() {
		System.out.println("La ira del pueblo esta latente");
		
		Jugador elegido = null;
		while (elegido == null) {
			System.out.println("Quien sufrira la ira del pueblo? (Escribe el nombre)");
			String nombre = pideNom();
			
			elegido = lista.obtenerPorNombre(nombre);
			
			if (elegido == null || !elegido.getVivo()) {
			System.out.println("Ese nombre no existe o ya esta muerto");
			elegido = null;
			} else if (elegido.getRol() == 'T' && tontoDesc) {
			System.out.println("Ya revelaron al tonto, no sean bobos");
			elegido = null;
			}
		}
		
		if (elegido.getRol() == 'T') {
			System.out.println("Tristemente es el mas tonto del pueblo, su vida es perdonada");
			System.out.println("Su voto ya no cuenta, es demasiado bobo");
			this.tontoDesc = true;
		} else {
			elegido.setVivo(false);
			System.out.println(elegido.getNombre() + " fue linchado");
			
			if (elegido.getRol() == 'C') {
			muerteCazador();
			}
			if (elegido.getRol() == 'F') {
			fMuerto = true;
			}
		}
    }

    private void cicloNoche() {
		for (Jugador j : lista) {
			j.setProteccion(false);
		}
		turnoProtector();
		turnoVidente();
		turnoLobos();
		turnoBruja();

		if (!fMuerto) {
			turnoFlautista();
		}

		resolverNoche();

		//El metodo de arriba es para hacer todas las impresiones finales
    }

    private void resolverNoche() {
		System.out.println("La noche se levanta y las consecuencias aparecen");
		boolean huboMuertes = false;
		
		if (this.victimaLobo != null) {
			
			if (this.victimaLobo.getProteccion()) {
			System.out.println("La manada ataco, pero el protector fue mas rapido");
			} else {
			this.victimaLobo.setVivo(false);
			System.out.println("El aldeano " + victimaLobo.getNombre() + " fue devorado");
			huboMuertes = true;

			if (victimaLobo.getRol() == 'C') {
				muerteCazador();
			}
			
			if (victimaLobo.getRol() == 'F')
				fMuerto = true;;
			}
		}

		if (this.victimaBruja != null) {
			if(this.victimaBruja.getVivo()) {
			this.victimaBruja.setVivo(false);
			System.out.println(victimaBruja.getNombre() + " murio por mano de la bruja");
			huboMuertes = true;

			if (victimaBruja.getRol() == 'C') {
			muerteCazador();
			}

			if (victimaBruja.getRol() == 'F'){
			fMuerto = true;
			}
		} else {
			System.out.println("Quedo doblemente muerto  " + victimaBruja.getNombre());
			}
		}

		if (!huboMuertes) {
			System.out.println("No hubo victimas, que raro");
		}
		
		this.victimaLobo = null;
		this.victimaBruja = null;
    }

    private void muerteCazador() {
		System.out.println("El cazador fue atacado, pero no esperara la muerte con los brazos cruzados");
		System.out.println("Levanta su arma y apunta a: (Escribe el nombre)");
		
		Jugador victimaCazador = null;
		while (victimaCazador == null) {
			String nombre = pideNom();
			victimaCazador = lista.obtenerPorNombre(nombre);
			
			if (victimaCazador == null || !victimaCazador.getVivo()) {
			System.out.println("Debes elegir a alguien que siga vivo. Intenta de nuevo:");
			victimaCazador = null;
			}
		}

		victimaCazador.setVivo(false);
		System.out.println("Un sonido arrollador suena en el pueblo, " + victimaCazador.getNombre() + " ha muerto por el disparo del cazador.");
		
		if (victimaCazador.getRol() == 'F') fMuerto = true;
    }

    private void turnoFlautista() {
		Jugador f = lista.rolVivo('F');
		if (f != null){
			Flautista flautista = (Flautista) f;
			System.out.println(flautista.obtenerMensajeDespertar());

			int cantidadHechizos = lista.getJugadores() / 5; 
			if (cantidadHechizos < 1) cantidadHechizos = 1; //En teoria esto nunca pasa pero hay que tener cuidado, no?

			System.out.println("Puedes hechizar a " + cantidadHechizos + " jugadores esta noche.");

			Jugador[] aHechizar = new Jugador[cantidadHechizos];

			for (int i = 0; i < cantidadHechizos; i++) {
				Jugador elegido = null;
				while (elegido == null) {
				System.out.println("El aldeano numero " + (i + 1) + " sucumbira ante tu hechizo, di su nombre");
				String nombre = pideNom();
				elegido = lista.obtenerPorNombre(nombre);

				if (elegido == null) {
					System.out.println("Ese nombre no existe.");
				} else if (elegido instanceof Flautista) {
					System.out.println("No puedes hechizarte a ti mismo, espabila.");
					elegido = null;
				} else if (!elegido.getVivo()) {
					System.out.println("No puedes hechizar a los muertos, bobolon");
					elegido = null;
				}
				}
				aHechizar[i] = elegido;
			}
			System.out.println(flautista.accionNocturna(aHechizar));
		}
    }
    
    private void turnoBruja() {
	
		Jugador j = lista.rolVivo('B');
		if (j != null) {
			Bruja b = (Bruja) j;
			System.out.println(b.obtenerMensajeDespertar());
			
			Jugador aCurar = null;
			Jugador aMatar = null;

			if (b.hasCura()) {
			if (victimaLobo != null) {
				System.out.println("La victima de los lobos fue " + victimaLobo.getNombre());
				System.out.println("¿Curaras al aldeano moribundo? (s/n)");

				if (sc.nextLine().trim().equalsIgnoreCase("s")) {
					aCurar = victimaLobo;
					this.victimaLobo = null; 
					
				}
				
			} else {
				System.out.println("No hubo victimas esta noche");
			}
			}
			
			if (b.hasMata()) {
				System.out.println("Usaras tu pocion de muerte? (s/n)");
				if (sc.nextLine().trim().equalsIgnoreCase("s")) {
					while (aMatar == null) {
						System.out.println("A quien mataras?");
						String nombre = pideNom();
						aMatar = lista.obtenerPorNombre(nombre);
					
						if (aMatar == null) {
							System.out.println("Ese nombre no existe.");
						} else if (!aMatar.getVivo()) {
							System.out.println("No puedes matar lo que ya esta muerto");
							aMatar = null;
						}

						this.victimaBruja = aMatar;
					}
				}
			}

			String resultadoAccion = b.accionNocturna(aCurar, aMatar);
			System.out.println(resultadoAccion);
		}
    }
    
    private void turnoLobos() {
		Jugador l = lista.rolVivo('L');

		if (l != null){
			Lobo lobo = (Lobo) l;
			System.out.println(lobo.obtenerMensajeDespertar());

			Jugador elegido = null;

			while (elegido == null) {
				System.out.println("La manada de lobos ha elegido una victima");

				String nombre = pideNom();
				elegido = lista.obtenerPorNombre(nombre);

				if (elegido == null) {
					System.out.println("Ese nombre no existe en la aldea.");
				} else if (elegido instanceof Lobo) {
					System.out.println("Por que atacar a un lobo cuando hay aldeanos sabrosos?");
					elegido = null;
				} else if (!elegido.getVivo()) {
					System.out.println("Los muertos no alimentaran a la manada, prueba otra vez");
					elegido = null;
				}
			}
			this.victimaLobo = elegido;
			System.out.println("La presa ha sido seleccionada");
		}
    }
    
    private void turnoProtector() {
		Jugador j = lista.rolVivo('P');
		if (j != null){
			Protector p = (Protector) j;
			System.out.println(p.obtenerMensajeDespertar());

			Jugador protegido = null;
			
			while(protegido == null){
				String pJugador = pideNom();
				protegido = lista.obtenerPorNombre(pJugador);
				
				if(protegido == null) {
					System.out.println("Ese nombre no existe en la aldea. Inténtalo de nuevo.");
				} else if (protegido == p.getFueProtegido()) {
					System.out.println("No puedes proteger a la misma persona dos noches seguidas.");
					protegido = null;
				}
			}

			String resultado = p.accionNocturna(protegido);
			System.out.println(resultado);
			
		}
    }

    private void turnoVidente() {
		Jugador j = lista.rolVivo('V');

		if(j != null){
			Vidente v = (Vidente) j;
			System.out.println(v.obtenerMensajeDespertar());
			
			Jugador objetivo = null;

			while(objetivo == null){
				String nJugador = pideNom();
				objetivo = lista.obtenerPorNombre(nJugador);
				
				if(objetivo == null){
					System.out.println("Ese nombre no existe en la aldea. Inténtalo de nuevo.");
				}
			}
			String rolRevel = v.accionNocturna(objetivo);
			System.out.println(rolRevel);
		}
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
			String s = pideNomRegistro();
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
			
			if(nombre.isEmpty()){
				System.out.println("Aqui no aceptamos nombres vacios, bobo");
			}
		}
		return nombre;
    }

	private String pideNomRegistro(){
	// Un while para pedir nombres
		String nombre = "";

		// Mientras el nombre sea vacio que se haga esta accion
		while(nombre.isEmpty()){    
			System.out.println("Ingresa el nombre del jugador");
			nombre = sc.nextLine().trim();
			
			if(nombre.isEmpty()){
				System.out.println("Aqui no aceptamos nombres vacios, bobo");
			}else if(lista.obtenerPorNombre(nombre) != null){
				System.out.println("Ese nombre ya esta en la Aldea, ingresa otro plox");
				nombre = "";
			}
		}
		return nombre;
    }

	private void mostrarRoles(){
		System.out.println("\n ==== ROLES ASIGNADOS ====");
		for(Jugador j : lista){
				String nombreRol = j.getClass().getSimpleName();
				System.out.println("- " + j.getNombre() + " tiene el rol de: " + nombreRol );
		}
		System.out.println("==========================");
	}

	public void mostrarEstadoJugadores() {
        System.out.println("\n=== ESTADO ACTUAL DE LA ALDEA ===");
        
        for (Jugador j : lista) {
            String estado = j.getVivo() ? "[VIVO]" : "[MUERTO]";
            
            String rol = j.getClass().getSimpleName();
            
            System.out.println(estado + " - " + j.getNombre() + " (" + rol + ")");
        }
        
        System.out.println("=================================\n");
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
