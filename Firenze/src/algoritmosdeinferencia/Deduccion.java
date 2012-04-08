package algoritmosdeinferencia;

import java.util.List;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author Charlie Corner
 */
public class Deduccion extends AlgoritmoDeInferencia {

    private List<Regla> reglasDisparadas;

    public Deduccion() {
        this.reglasDisparadas = new Stack<Regla>();
    }

    public Deduccion(List<Regla> conjuntoDeReglas, List<String> hechosDeInicio) {
        this.conjuntoDeReglas = conjuntoDeReglas;
        this.hechosDeInicio = hechosDeInicio;
    }

    public String correrAlgoritmo() {
        
        for (Regla r : conjuntoDeReglas) {
            boolean seCompletaLaRegla = true;

            // La bandera de cumpliemento de regla cambiara si y sólo si el elemento
            // definitivamente no está en ninguna de las tres listas
            for (String s : r.getCausantes()) {
                
                if (false == isElementoEnListas(s)) {
                    
                    if (true == preguntarSiElementoEsta(s)) {
                        agregarAHechosPreguntados(s);
                        
                    } else {
                        seCompletaLaRegla = false;
                    }
                }
            }

            // Si se cumple la regla y el elemento producido no está ya en alguna de las listas
            if (seCompletaLaRegla) {
                reglasDisparadas.add(r);

                if (!isElementoEnListas(r.getProducidos())) {
                    agregarAHechosInferidos(r.getProducidos());
                }
            }
        }
        return crearTextoRespuesta();
    }

    @Override
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

    private boolean isElementoEnHechosInicio(String elemento) {
        if (null != hechosDeInicio) {

            for (String s : hechosDeInicio) {

                if (elemento.equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isElementoEnHechosInferidos(String elemento) {
        if (null != hechosInferidos) {

            for (String s : hechosInferidos) {

                if (elemento.equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isElementoEnHechosPreguntados(String elemento) {
        if (null != hechosPreguntados) {

            for (String s : hechosPreguntados) {

                if (elemento.equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean preguntarSiElementoEsta(String s) {
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que \"" + s + "\" no se tiene ya?", "Confirmación", JOptionPane.YES_NO_OPTION);
        System.err.println("DEBUG:::::: respuesta: " + respuesta);

        if (0 == respuesta) {
            return true;
        } else {
            return false;
        }
    }

    private String crearTextoRespuesta() {
        StringBuilder sb = new StringBuilder("***Resultados del algoritmo de deducción***\n\n");
        sb = sb.append("Explicación:\n");

        for(Regla r: reglasDisparadas){
            sb = sb.append("Se disparó la regla ").append(r.getIndiceDeRegla()).append(" y se agregó: ").append(r.getProducidos());
            sb = sb.append('\n');
        }
        sb = sb.append('\n');
        sb = sb.append("Hechos de inicio: \n");
        
        for(String s: hechosDeInicio){
            sb = sb.append(s).append('\n');
        }
        
        sb = sb.append('\n');
        sb = sb.append("Hechos preguntados: \n");
        
        for(String s: hechosPreguntados){
            sb = sb.append(s).append('\n');
        }
        
        sb = sb.append('\n');
        sb = sb.append("Hechos inferidos: \n");
        
        for(String s: hechosInferidos){
            sb = sb.append(s).append('\n');
        }
        
        return sb.toString();
    }
}
