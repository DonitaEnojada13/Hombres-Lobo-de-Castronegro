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
    public String accionNocturna(Jugador... objetivos){

        if(objetivos == null || objetivos.length == 0) return "No se seleccionó a nadie";
 
        for(Jugador asesinados : objetivos){
                mata(asesinados);
        }    
	    return "Se elimino al jugador";
    }




    private void mata(Jugador presa){
	if (presa == null)
            throw new IllegalArgumentException("El jugador debe de existir");

	if(presa.getProteccion()){
            System.out.println("La vida del aldeano " + presa.getNombre() + " estaba protegida, los dientes no perforaron la carne");
            return;
        }
	
	presa.setVivo(false);
	}

    

}
