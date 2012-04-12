package algoritmosdeinferencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Charlie Corner
 */
public class Induccion extends AlgoritmoDeInferencia{
    
    private Stack<Regla> reglasPorDisparar;
    private List<Regla> reglasDisparadas;
    private String objetivo;

    public Induccion(List<Regla> conjuntoDeReglas, List<String> hechosDeInicio, String objetivo) {
        this.conjuntoDeReglas = conjuntoDeReglas;
        this.hechosDeInicio = hechosDeInicio;
        this.reglasDisparadas = new ArrayList<Regla>();
        this.hechosInferidos = new ArrayList<String>();
        this.hechosPreguntados = new ArrayList<String>();
        this.objetivo = objetivo;
    }  

    @Override
    protected String crearTextoRespuesta() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
