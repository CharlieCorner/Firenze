package algoritmosdeinferencia;

import java.util.List;

/**
 *
 * @author Charlie Corner
 */
public abstract class AlgoritmoDeInferencia {

    protected List<String> hechosDeInicio;
    protected List<String> hechosInferidos;
    protected List<String> hechosPreguntados;

    public AlgoritmoDeInferencia() {
    }

    public List<String> getHechosDeInicio() {
        return hechosDeInicio;
    }

    public void setHechosDeInicio(List<String> hechosDeInicio) {
        this.hechosDeInicio = hechosDeInicio;
    }

    public List<String> getHechosInferidos() {
        return hechosInferidos;
    }

    public void setHechosInferidos(List<String> hechosInferidos) {
        this.hechosInferidos = hechosInferidos;
    }

    public List<String> getHechosPreguntados() {
        return hechosPreguntados;
    }

    public void setHechosPreguntados(List<String> hechosPreguntados) {
        this.hechosPreguntados = hechosPreguntados;
    }

    protected boolean agregarAHechosDeInicio(String elemento) {
        if (null != hechosDeInicio) {
            hechosDeInicio.add(elemento);
            return true;
        } else {
            return false;
        }
    }

    protected boolean agregarAHechosInferidos(String elemento) {
        if (null != hechosInferidos) {
            hechosInferidos.add(elemento);
            return true;
        } else {
            return false;
        }
    }

    protected boolean agregarAHechosPreguntados(String elemento) {
        if (null != hechosPreguntados) {
            hechosPreguntados.add(elemento);
            return true;
        } else {
            return false;
        }
    }
    
    protected abstract boolean isElementoEnListas(String elemento);
}
