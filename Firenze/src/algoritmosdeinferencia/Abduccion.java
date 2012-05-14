package algoritmosdeinferencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Charlie Corner
 */
public class Abduccion extends AlgoritmoDeInferencia {

    private Deduccion deduccion;
    private Induccion induccion;
    /**
     * La lista de reglas que han sido disparadas y que se usarán para la
     * explicación de la ejecución del algoritmo.
     *
     * @see Regla
     */
    private List<Regla> reglasDisparadas;
    /**
     * La pila de reglas por comprobar por el algoritmo.
     *
     * @see Regla
     */
    private Stack<Regla> reglasPorDisparar;
    /**
     * La pila de objetivos por comprobar por el algoritmo.
     */
    private Stack<List<String>> pilaDeObjetivos;
    /**
     * El objetivo a comprobar por el algoritmo.
     */
    private String objetivoFinal;
    private byte contadorDeCiclos;
    /**
     * La bandera que permite saber si el objetivo fue encontrado después de la
     * ejecución del algoritmo.
     */
    private boolean seEncontroObjetivoFinal;

    public Abduccion(List<Regla> listaReglas,
            List<String> listaHechosDeInicioSeleccionados,
            String objetivo, byte contadorDeCiclos) {
        this.conjuntoDeReglas = listaReglas;
        this.hechosDeInicio = listaHechosDeInicioSeleccionados;
        this.hechosInferidos = new ArrayList<String>();
        this.hechosPreguntados = new ArrayList<String>();
        this.reglasDisparadas = new ArrayList<Regla>();
        this.reglasPorDisparar = new Stack<Regla>();
        this.pilaDeObjetivos = new Stack<List<String>>();
        this.resultado = "";
        this.objetivoFinal = objetivo;
        this.contadorDeCiclos = contadorDeCiclos;
        this.deduccion = new Deduccion(conjuntoDeReglas,
                hechosDeInicio,
                hechosInferidos,
                hechosPreguntados,
                reglasDisparadas);
        this.induccion = new Induccion(reglasPorDisparar,
                pilaDeObjetivos,
                reglasDisparadas,
                hechosDeInicio,
                hechosInferidos,
                hechosPreguntados,
                conjuntoDeReglas,
                objetivo);
        this.seEncontroObjetivoFinal = false;
    }

    @Override
    public String correrAlgoritmo() {
        boolean romperCiclo = false;
        byte contadorAbduccion = 0;

        if (isElementoEnListas(objetivoFinal)) {
            seEncontroObjetivoFinal = true;
        }

        while (!seEncontroObjetivoFinal) {
            deduccion.correrAlgoritmo();

            if (!isElementoEnListas(objetivoFinal)) {
                boolean seguirHaciendoInduccion = true;
                Regla reglaAPuntoDeComprobarse = buscarReglaAPuntoDeComprobarse();

                do {

                    if (null == reglaAPuntoDeComprobarse) {
                        romperCiclo = true;
                        break;
                    } else {
                        String objetivo = traerPrimerObjetivoAComprobar(reglaAPuntoDeComprobarse);
                        induccion.setObjetivo(objetivo);
                        induccion.correrAlgoritmo();

                        if (!induccion.isSeEncontroObjetivo()) {
                            seguirHaciendoInduccion = false;
                            romperCiclo = true;
                        } else {

                            if (isElementoEnListas(objetivoFinal)) {
                                seEncontroObjetivoFinal = true;
                                seguirHaciendoInduccion = false;
                            } else {
                                reglaAPuntoDeComprobarse = buscarReglaAPuntoDeComprobarse();

                                if (null == reglaAPuntoDeComprobarse) {
                                    seguirHaciendoInduccion = false;
                                    romperCiclo = false;
                                }
                            }
                        }
                    }
                } while (seguirHaciendoInduccion);

            } else {
                seEncontroObjetivoFinal = true;
            }

            if (romperCiclo || contadorAbduccion > contadorDeCiclos) {
                break;
            }
            contadorAbduccion++;
        }

        resultado = crearTextoRespuesta();
        return resultado;
    }

    @Override
    protected String crearTextoRespuesta() {
        StringBuilder sb = new StringBuilder();
        sb = sb.append("Explicación:\n");
        sb = sb.append("Objetivo Final: ").append(objetivoFinal).append('\n');

        for (Regla r : reglasDisparadas) {
            sb = sb.append("Se disparó la regla ").append(r.getIndiceDeRegla()).append(" y se agregó: ").append(r.getProducto());
            sb = sb.append('\n');
            sb = sb.append("Porque: \n");
            sb = sb.append(r.toString()).append('\n');
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

        sb = sb.append(seEncontroObjetivoFinal ? "Se encontró el objetivo!\n"
                : "No se encontró el objetivo :(\n");
        return sb.toString();
    }

    private Regla buscarReglaAPuntoDeComprobarse() {
        Regla reglaAPuntoDeComprobarse = null;
        int contador = 0;

        for (Regla candidata : conjuntoDeReglas) {
            int causantesYaObtenidos = 0;

            if (isElementoEnListas(candidata.getProducto())) {
                continue;
            }

            for (String causantes : candidata.getCausantes()) {

                if (isElementoEnListas(causantes)) {
                    causantesYaObtenidos++;
                }
            }

            if (causantesYaObtenidos >= candidata.getCausantes().size() / 2) {

                if (((causantesYaObtenidos > contador && causantesYaObtenidos != candidata.getCausantes().size())
                        && !isElementoEnListas(candidata.getProducto()))
                        || (1 == candidata.getCausantes().size()
                        && 0 == contador
                        && (!isElementoEnListas(candidata.getProducto()) && !isElementoEnListas(candidata.getCausantes().get(0))))) {
                    contador = causantesYaObtenidos;
                    reglaAPuntoDeComprobarse = candidata;
                }
            }
        }
        return reglaAPuntoDeComprobarse;
    }

    private String traerPrimerObjetivoAComprobar(Regla reglaAPuntoDeComprobarse) {

        for (String obj : reglaAPuntoDeComprobarse.getCausantes()) {

            if (!isElementoEnListas(obj)) {
                return obj;
            }
        }
        return null;
    }
}
