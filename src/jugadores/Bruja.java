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

    @Override
    public String obtenerMensajeDespertar(){
        return "La bruja despierta. Tiene en su poder una poción para curar y otra para matar.";
    }

    @Override
    public String accionNocturna(Jugador... objetivos){
        String resultado = "";
        
        // objetivos[0] es la persona a curar (aCurar)
        if (objetivos.length > 0 && objetivos[0] != null) {
            if (hasCura()) {
                cura(); // Solo gastamos la poción
                resultado += "Has usado la poción de vida en " + objetivos[0].getNombre() + ". ";
            }
        }
        
        // objetivos[1] es la persona a matar (aMatar)
        if (objetivos.length > 1 && objetivos[1] != null) {
            if (hasMata()) {
                mata(); // Solo gastamos la poción
                resultado += "Has usado la poción de muerte en " + objetivos[1].getNombre() + ".";
            }
        }
        
        if (resultado.equals("")) {
            return "La bruja no ha gastado ninguna poción esta noche.";
        }
        
        return resultado;
    }

    // Ya no necesitamos pasarle al jugador porque el Controlador gestiona la vida/muerte
    private void cura(){
        if(hasCura() && pociones > 0){
            setpCura(false);
            pociones--;
        }
    }

    private void mata(){
        if(hasMata() && pociones > 0){
            setpMata(false);
            pociones--;
        }
    }
}