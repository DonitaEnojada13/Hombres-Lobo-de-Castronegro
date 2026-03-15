public class Vidente extends Jugador implements AccionDeNoche{
    public Vidente(String nombre){
	super(nombre, 'V');
    }

    public void accionNocturna(){
	return;
    }
    private String clarividencia(Jugador objetivo){
	if(objetivo == null)
	    throw new IllegalArgumentException("El jugador debe de existir");
	if(!objetivo.getVivo())
	    return "Tiene el rol de muerto. Que esperabas, bobo?";
	if (objetivo == this)
	    return "Felicidades reafirmaste lo que ya sabias, bobo";
	
	return String.valueOf(objetivo.getRol());
    }
}
