package algoritmosdeinferencia;

import capasdecontrol.FirenzeUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Modela un algoritmo de inducción de tipo inductivo dada una lista de regla,
 * una lista de hechos de inicio y un objetivo. Extiende a
 * <code>AlgoritmoDeInferencia</code> quien proporciona un comportamiento
 * básico.
 *
 * @author Charlie Corner
 * @see AlgoritmoDeInferencia
 */
public class Induccion extends AlgoritmoDeInferencia {

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
     * La lista de reglas comprobadas por el algoritmo.
     *
     * @see Regla
     */
    private List<Regla> reglasDisparadas;
    /**
     * El objetivo a comprobar por el algoritmo.
     */
    private String objetivo;
    /**
     * La bandera que permite saber si el objetivo fue encontrado después de la
     * ejecución del algoritmo.
     */
    private boolean seEncontroObjetivo;

    /**
     * Constructor por defecto hecho privado para evitar hacer uso de él.
     */
    private Induccion() {
    }

    /**
     * Constructor que inicializa todos los campos necesarios para la correcta
     * ejecución del algoritmo. Además inicializa el campo de resultado para
     * mostrar un mensaje de error por si no se ha ejecutado el algoritmo.
     *
     * @param conjuntoDeReglas la lista con las reglas que regirán la ejecución
     * del algoritmo
     * @param hechosDeInicio la lista de hechos de inicio con el que se ejecutará
     *                          el algoritmo. Puede ser opcional y el constructor
     *                          se encargará de inicializar la lista.
     * @param objetivo  el objetivo a comprobar por el algoritmo
     * @see FirenzeUtil#listaFromLineasSinParsear(java.lang.String[]) 
     * @see FirenzeUtil#fromLineaSinParsear(java.lang.String)  
     * @see Regla
     */
    public Induccion(List<Regla> conjuntoDeReglas, List<String> hechosDeInicio, String objetivo) {
        this.conjuntoDeReglas = conjuntoDeReglas;
        this.hechosDeInicio = (null == hechosDeInicio) ? new ArrayList<String>() 
                : hechosDeInicio;
        this.reglasDisparadas = new ArrayList<Regla>();
        this.hechosInferidos = new ArrayList<String>();
        this.hechosPreguntados = new ArrayList<String>();
        this.reglasPorDisparar = new Stack<Regla>();
        this.pilaDeObjetivos = new Stack<List<String>>();
        this.objetivo = objetivo;
        this.seEncontroObjetivo = false;
        this.resultado = "No se ha corrido el algoritmo\n";
    }

    @Override
    public String correrAlgoritmo() {
        List<String> listaObjetivos = new ArrayList<String>();
        listaObjetivos.add(objetivo);
        pilaDeObjetivos.push(listaObjetivos);

        while (!pilaDeObjetivos.empty()) {
            listaObjetivos = pilaDeObjetivos.peek();
            boolean seCumpleRegla = true;

            for (int i = 0; i < listaObjetivos.size(); i++) {
                String s = listaObjetivos.get(i);

                // Si se encuentra el elemento en alguna de las listas
                if (isElementoEnListas(s)) {

                    if (objetivo.equals(s)) {
                        seEncontroObjetivo = true;
                        pilaDeObjetivos.pop();
                        break;

                    } else {

                        if ((i + 1) == listaObjetivos.size()) {
                            pilaDeObjetivos.pop();
                            Regla r = reglasPorDisparar.pop();
                            agregarAHechosInferidos(r.getProducidos());
                            reglasDisparadas.add(r);
                        } else {
                            continue;
                        }
                    }
                    // Si el elemento no se encuentra en las listas, buscamos en reglas o preguntamos
                } else {
                    Regla reglaAComprobar = buscarReglaQueProduzca(s);

                    if (null == reglaAComprobar) {

                        if (preguntarAlUsuarioSiEsta(s)) {
                            agregarAHechosPreguntados(s);
                            continue;

                        } else {
                            seCumpleRegla = false;
                            break;
                        }

                    } else {
                        pilaDeObjetivos.push(reglaAComprobar.getCausantes());
                        reglasPorDisparar.push(reglaAComprobar);
                        break;
                    }
                }
            }

            if (!seCumpleRegla) {
                break;
            }
        }

        resultado = crearTextoRespuesta();
        return resultado;
    }

    @Override
    protected String crearTextoRespuesta() {
        StringBuilder sb = new StringBuilder("***Resultados del algoritmo de inducción***\n\n");
        sb = sb.append("Explicación:\n");
        sb = sb.append("Objetivo: ").append(objetivo).append('\n');

        for (Regla r : reglasDisparadas) {
            sb = sb.append("Se disparó la regla ").append(r.getIndiceDeRegla()).append(" y se agregó: ").append(r.getProducidos());
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

        sb = sb.append(seEncontroObjetivo ? "Se encontró el objetivo!\n"
                : "No se encontró el objetivo :(\n");
        return sb.toString();
    }

    /**
     * Permite buscar una regla que produzca a <code>productoABuscar</code> en
     * el <code>conjuntoDeRegla</code> de este objeto.
     * @param productoABuscar la cadena con el producto a buscar en la lista de
     *                          reglas de este objeto
     * @return  un objeto <code>Regla</code> que produzca a <code>productoABuscar</code>,
     *          <code>null</code> si no encuentra ninguna <code>Regla</code> que
     *          lo produzca
     * @see Regla
     */
    private Regla buscarReglaQueProduzca(String productoABuscar) {
        Regla reglaARegresar = null;

        for (Regla r : conjuntoDeReglas) {
            String productoDeRegla = r.getProducidos();

            if (productoABuscar.equals(productoDeRegla)) {
                reglaARegresar = r;
                break;
            }
        }

        return reglaARegresar;
    }

    /**
     * Permite obtener el objetivo que se quiere comprobar con este objeto.
     * @return la cadena con el objetivo a comprobar por este objeto
     */
    public String getObjetivo() {
        return objetivo;
    }

    /**
     * Permite obtener la pila de objetivos a comprobar por este objeto.
     * @return la pila de objetivos por comprobar por este objeto
     */
    public Stack<List<String>> getPilaDeObjetivos() {
        return pilaDeObjetivos;
    }

    /**
     * Permite obtener las reglas disparadas que explican el funcionamiento de
     * la ejecución de este objeto
     * @return la lista con las <code>Regla</code> disparadas por la ejecución
     *         de este objeto
     * @see Regla
     */
    public List<Regla> getReglasDisparadas() {
        return reglasDisparadas;
    }

    /**
     * Permite obtener la pila de reglas por disparar que se espera sea comprobada
     * por la ejecución de este objeto.
     * @return la pila de <code>Regla</code> por comprobar por este objeto
     * @see Regla
     */
    public Stack<Regla> getReglasPorDisparar() {
        return reglasPorDisparar;
    }

    /**
     * Permite conocer si la ejecución del algoritmo modelado por este objeto
     * fue capaz de comprobar la existencia del objetivo.
     * @return <code>true</code> si la ejecución de este algoritmo pudo comprobar
     *          la existencia del objetivo, <code>false</code> si no lo pudo comprobar
     *          o si el algoritmo no ha sido ejecutado aún
     */
    public boolean isSeEncontroObjetivo() {
        return seEncontroObjetivo;
    }
}
