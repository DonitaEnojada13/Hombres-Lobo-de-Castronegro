package jugadores;
import acciones.*;
public class Bruja extends Jugador implements AccionDeNoche {

    private boolean pCura;
    private boolean pMata;
    private int pociones;
    

    public Bruja(String nombre){
		super(nombre, 'B');	
		this.pCura = true;
		this.pMata = true;
		this.pociones = 2;
    }

    public int potisRestantes(){
		return pociones;
    }
    public boolean hasCura(){
		return pCura;
    }
    public boolean hasMata(){
		return pMata;
    }

    public void setpCura(boolean nCura){
		this.pCura = nCura;
    }
    public void setpMata(boolean nMata){
		this.pMata = nMata;
    }

    public void accionNocturna(){
		return;
    }

    @Override
    public String obtenerMensajeDespertar(){
	return "La bruja despierta y puede curar o matar a alguién";
    }

    @Override
    public String accionNocturna(Jugador... objetivos){
	String resultado = "";
	if (objetivos.length > 0 && objetivos[0] != null) {
	    if (hasCura()) {
		cura(objetivos[0]);
		resultado += "Se ha usado la poción de vida en " + objetivos[0].getNombre() + ". ";
	    }
	}
	
	if (objetivos.length > 1 && objetivos[1] != null) {
	    if (hasMata()) {
		mata(objetivos[1]);
		resultado += "Se ha usado la poción de muerte en " + objetivos[1].getNombre() + ".";
	    }
	}
	
	if (resultado.equals("")) {
	    return "La bruja no ha realizado ninguna acción esta noche.";
	}
	
	return resultado;
    }

	
    private void cura(Jugador herido){
	if (herido == null)
	    throw new IllegalArgumentException("El jugador debe de existir");
	if(!hasCura() || pociones <= 0)
	    return;
	
	if (!herido.getVivo()){
	    herido.setVivo(true);
	    setpCura(false);
	    pociones--;
	}
    }

    private void mata(Jugador objetivo){
	
	if (objetivo == null)
	    throw new IllegalArgumentException("El jugador debe de existir");
	if(!hasMata() ||  pociones <= 0)
	    return;
	
	if(objetivo.getVivo() == true){
	    objetivo.setVivo(false);
	    setpMata(false);
	    pociones--;
	}
    }
    

}
