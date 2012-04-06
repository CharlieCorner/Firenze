package algoritmosdeinferencia;

import java.util.List;

/**
 *
 * @author Charlie Corner
 */
public class Reglas {
    private int indiceDeRegla;
    private List<String> causantes;
    private List<String> producidos;

    public Reglas() {
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