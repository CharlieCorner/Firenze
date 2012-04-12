package algoritmosdeinferencia;

import java.util.List;

/**
 *
 * @author Charlie Corner
 */
public class Regla {

    private static int cuentaIndiceReglas = 1;
    private int indiceDeRegla;
    private List<String> causantes;
    private String producidos;

    private Regla() {
    }

    public Regla(int indiceDeRegla, List<String> causantes, String producidos) {
        this.indiceDeRegla = indiceDeRegla;
        this.causantes = causantes;
        this.producidos = producidos;
    }

    public static void setCuentaIndiceReglas(int cuentaIndiceReglas) {
        Regla.cuentaIndiceReglas = cuentaIndiceReglas;
    }

    public static int getCuentaIndiceReglas() {
        return cuentaIndiceReglas;
    }

    public void setCausantes(List<String> causantes) {
        this.causantes = causantes;
    }

    public void setProducidos(String producidos) {
        this.producidos = producidos;
    }

    public List<String> getCausantes() {
        return causantes;
    }

    public String getProducidos() {
        return producidos;
    }

    public int getIndiceDeRegla() {
        return indiceDeRegla;
    }

    public void setIndiceDeRegla(int indiceDeRegla) {
        this.indiceDeRegla = indiceDeRegla;
    }
}
