public class Protector extends Jugador{
    private Jugador fueProtegido;
    
    public Protector(String name){
	super(name, 'P');
	this.fueProtegido = null;
    }
    
    
    public void protect(Jugador objetivo){
	if (objetivo == null)
	    throw new IllegalArgumentException("El jugador debe de existir");
	if (!objetivo.getVivo()){
	    System.out.println("Espabila, los muertos no necesitan proteccion");
	    return;
	}
	if (objetivo == fueProtegido){
	    System.out.println("No puedes proteger dos veces a la misma persona");
	    return;
	}
	objetivo.setProteccion(true);
        this.fueProtegido = objetivo;
    }

    // Falta hacer un reset para quitar la proteccion 
}
