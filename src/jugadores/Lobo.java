package jugadores;
import acciones.*;
public class Lobo extends Jugador implements AccionDeNoche{
    
    public Lobo(String name){
	super(name, 'L');
    }

    @Override
    public String obtenerMensajeDespertar(){
        return "Los lobos despiertan y deben de escoger a un jugador para matar";
    }

    @Override
    public void accionNocturna(Jugador... objetivos){

        if(objetivos == null || objetivos.length == 0) return;
 
        for(Jugador asesinados : objetivos){
                mata(asesinados);
        }    
	    return;
    }




    private void mata(Jugador presa){
        if (presa == null)
            throw new IllegalArgumentException("El jugador debe de existir");
        if (presa instanceof Lobo)
            throw new IllegalArgumentException("Los lobos no pueden asesinar a otros lobos");
        if(!presa.getVivo())
            throw new IllegalArgumentException("El jugador debe de estár vivo");

        if(presa.getProteccion()){
            System.out.println("El jugador " + presa.getNombre() + " esta protegido");
            return;
        }

        presa.setVivo(false);
	}

    

}
