package algoritmosdeinferencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
        this.reglasDisparadas = new ArrayList<Regla>();
        this.hechosInferidos = new ArrayList<String>();
        this.hechosPreguntados = new ArrayList<String>();
    }

    @Override
    public String correrAlgoritmo() {

        for (Regla r : conjuntoDeReglas) {
            boolean seCompletaLaRegla = true;

            // La bandera de cumpliemento de regla cambiara si y sólo si el elemento
            // definitivamente no está en ninguna de las tres listas
            for (String s : r.getCausantes()) {

                if (false == isElementoEnListas(s)) {

                    if (true == preguntarAlUsuarioSiEsta(s)) {

                        if (true != agregarAHechosPreguntados(s)) {
                            System.err.println("Hubo un problema al agregar "
                                    + r.getIndiceDeRegla() 
                                    + " a la lista de hechos preguntados");
                        }
                    } else {
                        seCompletaLaRegla = false;
                    }
                }
            }

            // Si se cumple la regla y el elemento producido no está ya en alguna de las listas
            if (seCompletaLaRegla) {
                reglasDisparadas.add(r);

                if (!isElementoEnListas(r.getProducidos())) {

                    if (true != agregarAHechosInferidos(r.getProducidos())) {
                        System.err.println("Hubo un problema al agregar "
                                + r.getIndiceDeRegla()
                                + " a la lista de hechos preguntados");
                    }
                }
            }
        }
        this.resultado = crearTextoRespuesta();
        return resultado;
    }

    @Override
    protected String crearTextoRespuesta() {
        StringBuilder sb = new StringBuilder("***Resultados del algoritmo de deducción***\n\n");
        sb = sb.append("Explicación:\n");

        for (Regla r : reglasDisparadas) {
            sb = sb.append("Se disparó la regla ")
                    .append(r.getIndiceDeRegla())
                    .append(" y se agregó: ")
                    .append(r.getProducidos());
            sb = sb.append('\n');
        }
        sb = sb.append('\n');
        sb = sb.append("Hechos de inicio: \n");

        for (String s : hechosDeInicio) {
            sb = sb.append(s).append('\n');
        }

        sb = sb.append('\n');
        sb = sb.append("Hechos preguntados: \n");

        for (String s : hechosPreguntados) {
            sb = sb.append(s).append('\n');
        }

        sb = sb.append('\n');
        sb = sb.append("Hechos inferidos: \n");

        for (String s : hechosInferidos) {
            sb = sb.append(s).append('\n');
        }

        return sb.toString();
    }
}
