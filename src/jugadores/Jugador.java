package jugadores;
public class Jugador{
    
    private String nombre;
    private char rol;
    private boolean vivo;
    private boolean hechizado;
    private boolean proteccion;
    //  private int votos;
    

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
}
