public class Jugador{
    
    private String nombre;
    private char rol;
    private boolean vivo;
    private boolean hechizado;
    private boolean proteccion;
    private int votos;
    

    public Jugador(String nombre, char rol){
	this.nombre = nombre;
	this.rol = rol;
	this.vivo = true;
    }


    //Getters
    
    public String getNombre(){
	return nombre;
    }
    public char getRol(){
	return rol;
    }
    public boolean getVivo(){
	return vivo;
    }
    public boolean getHechizado(){
	return hechizado;
    }
    public boolean getProteccion(){
	return proteccion;
    }
    public int getVotos(){
	return votos;
    }

    //setters 

    public void setNombre(String nNombre){
	nombre = nNombre;
    }
    public void setRol(char nRol){
	rol = nRol;
    }
    public void setVivo(boolean nVivo){
	vivo = nVivo;
    }
    public void setHechizado(boolean nHechi){
	hechizado = nHechi;
    }
    public void setProteccion(boolean nProtec){
	proteccion = nProtec;
    }
    public void setVotos(int nVotos){
	votos = nVotos;
    }


    public void votaPor(Jugador votado){
	if (votado == null)
	    throw new IllegalArgumentException("El jugador debe de existir");
	if (!this.getVivo()){
	    System.out.println("Los muertos no votan, espabila");
	    return;
	}
	if (!votado.getVivo()){
	    System.out.println("No puedes votar a un muerto, espabila");
	    return;
	}
	
	int cantidad = votado.getVotos() + 1;
	votado.setVotos(cantidad);
	return;
    }

    
}
