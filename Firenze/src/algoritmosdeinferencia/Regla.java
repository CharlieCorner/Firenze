package algoritmosdeinferencia;

import java.util.List;

/**
 * Modela las reglas que regirán la ejecución de los algoritmos de inferencia.
 * @author Charlie Corner
 */
public class Regla {

    /**
     * Campo estático que permite asignar un número identificador único a cada
     * una de las instancias de esta clase
     */
    private static int cuentaIndiceReglas = 1;
    /**
     * Identificador único que fue asignado a este objeto al momento de su creación
     */
    private int indiceDeRegla;
    /**
     * La lista de hechos causantes de este objeto
     */
    private List<String> causantes;
    /**
     * El elemento producito si todos los hechos causantes se cumplen en este objeto
     */
    private String producidos;

    /**
     * Constructor por defecto hecho privado y explícito para evitar su uso
     */
    private Regla() {
    }

    /**
     * Constructor que inicializa los campos de lista de hechos causantes y hecho
     * producido de este objeto. Adicionalmente le asigna un identificador único
     * a este objeto de manera anónima usando el campo estático para tal fin.
     * @param causantes la lista de hechos causantes de este objeto
     * @param producidos el elemento producido si todos los hechos causantes de
     *                      este objeto se cumplen
     * @see Regla#cuentaIndiceReglas
     */
    public Regla(List<String> causantes, String producidos) {
        this.indiceDeRegla = cuentaIndiceReglas++;
        this.causantes = causantes;
        this.producidos = producidos;
    }

    /**
     * Permite modificar el campo estáticos que asigna identificadores únicos
     * a las instancias de esta clase.
     * @param cuentaIndiceReglas el nuevo valor entero del que se partirá para
     *                           asignar identificadores únicos a cada una de
     *                           las nuevas instancias de esta clase
     */
    public static void setCuentaIndiceReglas(int cuentaIndiceReglas) {
        Regla.cuentaIndiceReglas = cuentaIndiceReglas;
    }

    /**
     * Permite obtener el valor actual del contador de identificadores únicos para
     * todas las instancias de esta clase.
     * @return el número entero con la cuenta actual de identificadores de esta
     *          clase
     */
    public static int getCuentaIndiceReglas() {
        return cuentaIndiceReglas;
    }

    /**
     * Permite establecer la lista de hechos causantes de este algoritmo.
     * @param causantes la lista de hechos causantes de este objeto
     */
    public void setCausantes(List<String> causantes) {
        this.causantes = causantes;
    }

    /**
     * Permite establecer el hecho producido por este objeto si sus <code>causantes</code>
     * se cumplen en su totalidad.
     * @param producidos la cadena con el nuevo elemento producido por este objeto
     */
    public void setProducidos(String producidos) {
        this.producidos = producidos;
    }

    /**
     * Permite obtener la lista de hechos causantes de este objeto.
     * @return la lista con los hechos causantes que modelan a este objeto
     */
    public List<String> getCausantes() {
        return causantes;
    }

    /**
     * Permite obtener el elemento producido por este objeto.
     * @return el elemento producido por este objeto si todas los hechos causantes
     *          se cumplen
     */
    public String getProducidos() {
        return producidos;
    }

    /**
     * Permite obtener el identificador único que se le asignó a este objeto al
     * momento de su creación.
     * @return el número entero con el identificador único asignado a este objeto
     */
    public int getIndiceDeRegla() {
        return indiceDeRegla;
    }

    /**
     * Permite modificar el identificador que se le fue asignado a este objeto.
     * @param indiceDeRegla el valor entero con el nuevo identificador asignado
     *                      a este objeto
     */
    public void setIndiceDeRegla(int indiceDeRegla) {
        this.indiceDeRegla = indiceDeRegla;
    }
}
