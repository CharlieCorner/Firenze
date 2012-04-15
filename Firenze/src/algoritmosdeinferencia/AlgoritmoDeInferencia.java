package algoritmosdeinferencia;

import java.util.List;
import javax.swing.JOptionPane;

/**
 * Modela un algoritmo de inferencia con todas las características básicas que
 * debe tener.
 * 
 * @author Charlie Corner
 */
public abstract class AlgoritmoDeInferencia {

    /**
     * La lista de hechos de inicio que debe tener el algortimo.
     */
    protected List<String> hechosDeInicio;
    /**
     * La lista de los hechos que han sido inferidos por el algoritmo.
     */
    protected List<String> hechosInferidos;
    /**
     * La lista de hechos que se le ha preguntado al usuario.
     */
    protected List<String> hechosPreguntados;
    /**
     * La lista de reglas que regirá el comportamiento del algoritmo.
     */
    protected List<Regla> conjuntoDeReglas;
    /**
     * La cadena con la explicación y resultado de la ejecución del algoritmo.
     */
    protected String resultado;

    /**
     * Constructor único por defecto hecho explícito y que no inicializa ningún
     * campo.
     */
    public AlgoritmoDeInferencia() {
    }

    /**
     * Permite correr el algoritmo de inferencia deseado. Todas las clases que
     * extiendan a esta clase deben hacer su propia implementación del algoritmo
     * que están modelando.
     * 
     * @return la cadena con el resultado del algoritmo
     */
    public abstract String correrAlgoritmo();
    
    /**
     * Permite obtener la lista de hechos de inicio.
     * @return la lista de cadenas con los hechos de inicio del algoritmo
     */
    public List<String> getHechosDeInicio() {
        return hechosDeInicio;
    }

    /**
     * Permite establecer la lista de hechos de inicio del algoritmo.
     * @param hechosDeInicio la lista de hechos de inicio con la que trabajará
     *                          el algoritmo
     */
    public void setHechosDeInicio(List<String> hechosDeInicio) {
        this.hechosDeInicio = hechosDeInicio;
    }

    /**
     * Permite obtener la lista de hechos que han sido inferidos por el algoritmo.
     * @return la lista de hechos que han sido inferidos usando las reglas del
     *         algoritmo
     * @see AlgoritmoDeInferencia#conjuntoDeReglas
     */
    public List<String> getHechosInferidos() {
        return hechosInferidos;
    }

    /**
     * Permite establecer una lista de hechos que han sido inferidos por el algoritmo.
     * @param hechosInferidos la lista de cadenas con los hechos inferidos por el
     *                          algoritmo
     */
    public void setHechosInferidos(List<String> hechosInferidos) {
        this.hechosInferidos = hechosInferidos;
    }

    /**
     * Permite obtener la lista de hechos que se le han preguntado al usuario en
     * la ejecución del algoritmo.
     * @return la lista de hechos que han sido preguntados al usuario
     */
    public List<String> getHechosPreguntados() {
        return hechosPreguntados;
    }

    /**
     * Permite establecer la lista de hechos preguntados al usuario.
     * @param hechosPreguntados la lista de hechos preguntados al usuario
     */
    public void setHechosPreguntados(List<String> hechosPreguntados) {
        this.hechosPreguntados = hechosPreguntados;
    }

    /**
     * Permite obtener la lista de reglas que rigen la ejecución del algoritmo.
     * @return la lista con las reglas que regirán al algoritmo
     * @see Regla
     */
    public List<Regla> getConjuntoDeReglas() {
        return conjuntoDeReglas;
    }

    /**
     * Permite establecer la lista de reglas que regirán al algoritmo.
     * @param conjuntoDeReglas la lista de objetos Regla que modelan las reglas
     *                          de ejecución del algoritmo
     * @see Regla
     */
    public void setConjuntoDeReglas(List<Regla> conjuntoDeReglas) {
        this.conjuntoDeReglas = conjuntoDeReglas;
    }

    /**
     * Permite obtener la cadena con el resultado de la ejecución del algoritmo.
     * @return la cadena con el resultado y la explicación de la ejecución del
     *          algoritmo
     */
    public String getResultado() {
        return resultado;
    }
    
    /**
     * Permite agregar de manera segura un elemento a la lista de hechos de inicio
     * del algoritmo. Nunca agregará el elemento si la lista no ha sido inicializada.
     * @param elemento  la cadena que se agregará a la lista de hechos de inicio
     * @return  <code>true</code> si el elemento se agregó a la lista de manera
     *          exitosa, <code>false</code> en cualquier otro caso.
     * @see AlgoritmoDeInferencia#hechosDeInicio
     */
    protected boolean agregarAHechosDeInicio(String elemento) {
        if (null != hechosDeInicio) {
            hechosDeInicio.add(elemento);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Permite agregar de manera segura un elemento a la lista de hechos inferidos
     * por el algoritmo. Nunca agregará el elemento si la lista no ha sido inicializada.
     * @param elemento  la cadena que se agregará a la lista de hechos inferidos
     * @return  <code>true</code> si el elemento se agregó a la lista de manera
     *          exitosa, <code>false</code> en cualquier otro caso.
     * @see AlgoritmoDeInferencia#hechosInferidos
     */
    protected boolean agregarAHechosInferidos(String elemento) {
        if (null != hechosInferidos) {
            hechosInferidos.add(elemento);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Permite agregar de manera segura un elemento a la lista de hechos preguntados
     * por el algoritmo. Nunca agregará el elemento si la lista no ha sido inicializada.
     * @param elemento  la cadena que se agregará a la lista de hechos dpreguntados
     * @return  <code>true</code> si el elemento se agregó a la lista de manera
     *          exitosa, <code>false</code> en cualquier otro caso.
     * @see AlgoritmoDeInferencia#hechosPreguntados
     */
    protected boolean agregarAHechosPreguntados(String elemento) {
        if (null != hechosPreguntados) {
            hechosPreguntados.add(elemento);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Permite verificar de una sola vez si <code>elemento</code> se encuentra
     * en las listas <code>hechosDeInicio</code>, <code>hechosInferidos</code> o 
     * <code>hechosPreguntados</code> del algoritmo. Hace una llamada implícita
     * a los métodos <code>isElementoEnHechosInicio</code>,
     * <code>isElementoEnHechosInferidos</code> y <code>isElementoEnHechosPreguntados</code>.
     * @param elemento la cadena de la que se quiere comprobar su existencia en
     *                  las listas del algoritmo
     * @return  <code>true</code> si <code>elemento</code> se encuentra en alguna
     *          de las listas, <code>false</code> en cualquier otro caso
     * @see AlgoritmoDeInferencia#agregarAHechosDeInicio(java.lang.String) 
     * @see AlgoritmoDeInferencia#agregarAHechosInferidos(java.lang.String) 
     * @see AlgoritmoDeInferencia#agregarAHechosPreguntados(java.lang.String) 
     */
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

    /**
     * Permite verificar si <code>elemento</code> se encuentra en la lista
     * <code>hechosDeInicio</code> del algoritmo. Nunca hará la comprobación
     * si la lista no ha sido inicializada.
     * @param elemento la cadena de la que se quiere comprobar su existencia en
     *                  las listas del algoritmo
     * @return  <code>true</code> si <code>elemento</code> se encuentra en 
     * <code>hechosDeInicio</code>, <code>false</code> en cualquier otro caso
     * @see AlgoritmoDeInferencia#hechosDeInicio
     */
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

    /**
     * Permite verificar si <code>elemento</code> se encuentra en la lista
     * <code>hechosInferidos</code> del algoritmo. Nunca hará la comprobación
     * si la lista no ha sido inicializada.
     * @param elemento la cadena de la que se quiere comprobar su existencia en
     *                  las listas del algoritmo
     * @return  <code>true</code> si <code>elemento</code> se encuentra en 
     * <code>hechosInferidos</code>, <code>false</code> en cualquier otro caso
     * @see AlgoritmoDeInferencia#hechosInferidos
     */
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

    /**
     * Permite verificar si <code>elemento</code> se encuentra en la lista
     * <code>hechosPreguntados</code> del algoritmo. Nunca hará la comprobación
     * si la lista no ha sido inicializada.
     * @param elemento la cadena de la que se quiere comprobar su existencia en
     *                  las listas del algoritmo
     * @return  <code>true</code> si <code>elemento</code> se encuentra en 
     * <code>hechosPreguntados</code>, <code>false</code> en cualquier otro caso
     * @see AlgoritmoDeInferencia#hechosPreguntados
     */
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

    /**
     * Permite confirmar con el usuario si <code>elemento</code> se encuentra
     * realmente en su diagnóstico. Hace uso de una ventana de confirmación que
     * carece de una ventana padre.
     * @param elemento el elemento del que se quiere confirmar su existencia con
     *                  el usuario
     * @return <code>true</code> si el usuario contestó afirmativamente, 
     *         <code>false</code> en cualquier otro caso
     * @see JOptionPane
     */
    protected boolean preguntarAlUsuarioSiEsta(String elemento) {
        int respuesta = JOptionPane.showConfirmDialog(null,
                "¿Está seguro que \"" + elemento + "\" no se tiene ya?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION);

        if (0 == respuesta) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Permite armar el texto de resultado de ejecución del algoritmo. La implementación
     * de este método se delega a cada una de las clases que extiendan a esta.
     * @return la cadena con el resultado de la ejecución del algoritmo
     */
    protected abstract String crearTextoRespuesta();
}
