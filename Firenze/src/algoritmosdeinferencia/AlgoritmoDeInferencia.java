package algoritmosdeinferencia;

import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Charlie Corner
 */
public abstract class AlgoritmoDeInferencia {

    protected List<String> hechosDeInicio;
    protected List<String> hechosInferidos;
    protected List<String> hechosPreguntados;
    protected List<Regla> conjuntoDeReglas;
    protected String resultado;

    public AlgoritmoDeInferencia() {
    }

    public abstract String correrAlgoritmo();
    
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

    public List<Regla> getConjuntoDeReglas() {
        return conjuntoDeReglas;
    }

    public void setConjuntoDeReglas(List<Regla> conjuntoDeReglas) {
        this.conjuntoDeReglas = conjuntoDeReglas;
    }

    public String getResultado() {
        return resultado;
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
    
    protected boolean isElementoEnListas(String elemento) {

        if (isElementoEnHechosInicio(elemento)) {
            return true;
        }

        if (isElementoEnHechosInferidos(elemento)) {
            return true;
        }

        if (isElementoEnHechosPreguntados(elemento)) {
            return true;
        }
        return false;
    }

    protected boolean isElementoEnHechosInicio(String elemento) {
        if (null != hechosDeInicio) {

            for (String s : hechosDeInicio) {

                if (elemento.equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean isElementoEnHechosInferidos(String elemento) {
        if (null != hechosInferidos) {

            for (String s : hechosInferidos) {

                if (elemento.equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean isElementoEnHechosPreguntados(String elemento) {
        if (null != hechosPreguntados) {

            for (String s : hechosPreguntados) {

                if (elemento.equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean preguntarAlUsuarioSiEsta(String s) {
        int respuesta = JOptionPane.showConfirmDialog(null,
                "¿Está seguro que \"" + s + "\" no se tiene ya?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION);

        if (0 == respuesta) {
            return true;
        } else {
            return false;
        }
    }
    
    protected abstract String crearTextoRespuesta();
}
