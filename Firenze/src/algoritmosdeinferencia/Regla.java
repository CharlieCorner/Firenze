package algoritmosdeinferencia;

import java.util.List;

/**
 *
 * @author Charlie Corner
 */
public class Regla {
    private int indiceDeRegla;
    private List<String> causantes;
    private List<String> producidos;

    public Regla() {
    }
    
    public static Regla fromLineaSinParsear(String lineaDeReglas){
        //TODO
        return null;
        
    }

    public void setCausantes(List<String> causantes) {
        this.causantes = causantes;
    }

    public void setProducidos(List<String> producidos) {
        this.producidos = producidos;
    }

    public List<String> getCausantes() {
        return causantes;
    }

    public List<String> getProducidos() {
        return producidos;
    }

    public int getIndiceDeRegla() {
        return indiceDeRegla;
    }

    public void setIndiceDeRegla(int indiceDeRegla) {
        this.indiceDeRegla = indiceDeRegla;
    }
}
