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

    // Planeo convertir estos metodos a privados, por el encapsulamiento, asi nadie podra actiarlos
    // fuera del turno de la bruja
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
