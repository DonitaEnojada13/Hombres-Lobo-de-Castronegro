package jugadores;
import acciones.AccionDeDia;
public class Tonto extends Jugador implements AccionDeDia{
    boolean primerVotacion = true;

    public Tonto(String name){
	    super(name, 'T');
    }

    //Las votaciones se delegan al mundo real, ahora el rol del tonto se quedara como un simple aldeano.
    public void votaciones(){
        if(primerVotacion){
            System.out.println("Este jugador se refiere al tonto, por lo que esta ronda no le pasa nada");
            primerVotacion = false;
            return;
        }else{
            this.setVivo(false);
        }
    }
}
