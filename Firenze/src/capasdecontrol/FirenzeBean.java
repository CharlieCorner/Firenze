package capasdecontrol;

import algoritmosdeinferencia.Abduccion;
import algoritmosdeinferencia.AlgoritmoDeInferencia;
import algoritmosdeinferencia.Deduccion;
import algoritmosdeinferencia.Induccion;
import algoritmosdeinferencia.Regla;
import firenze.Main;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Charlie Corner
 */
public class FirenzeBean {

    private AlgoritmoDeInferencia algoritmo;
    private List<Regla> listaReglas;
    private List<String> comboDeObjetivosASeleccionar;
    private List<String> listaHechosDeInicioASeleccionar;
    private String objetivo;
    private List<String> listaHechosDeInicioSeleccionados;
    private File archivoDeRegla;
    private String resultado;
    private byte numeroDeCiclos;
    private boolean activarBoton;

    public FirenzeBean() {
        algoritmo = null;
        activarBoton = false;
        numeroDeCiclos = 15;
    }

    public void correrAlgoritmoDeduccion() {
        this.algoritmo = new Deduccion(listaReglas, listaHechosDeInicioSeleccionados);
        this.algoritmo.correrAlgoritmo();
        this.resultado = algoritmo.getResultado();
    }

    public void correrAlgoritmoInduccion() {
        this.algoritmo = new Induccion(listaReglas, listaHechosDeInicioSeleccionados, objetivo);
        this.algoritmo.correrAlgoritmo();
        this.resultado = algoritmo.getResultado();
    }

    public void correrAlgoritmoAbduccion() {
        this.algoritmo = new Abduccion(listaReglas, listaHechosDeInicioSeleccionados, objetivo, numeroDeCiclos);
        this.algoritmo.correrAlgoritmo();
        this.resultado = algoritmo.getResultado();
    }

    public boolean isActivarBoton() {
        return activarBoton;
    }

    public void setActivarBoton(boolean activarBoton) {
        this.activarBoton = activarBoton;
    }

    public File getArchivoDeRegla() {
        return archivoDeRegla;
    }

    public void setArchivoDeRegla(File archivoDeRegla) {
        this.archivoDeRegla = archivoDeRegla;
        if (null != this.archivoDeRegla) {
            this.listaReglas = FirenzeUtil.listaFromLineasSinParsear(leerArchivo());
            this.listaHechosDeInicioASeleccionar = parsearListaDeHechosDeInicio(this.listaReglas);
            this.comboDeObjetivosASeleccionar = parsearListaDeObjetivos(this.listaReglas);
            activarBoton = true;
        }
    }

    public AlgoritmoDeInferencia getAlgoritmo() {
        return algoritmo;
    }

    public String getResultado() {
        return resultado;
    }

    @SuppressWarnings("CallToThreadDumpStack")
    private List<String> leerArchivo() {
        return FirenzeUtil.getListaDeStringDeArchivo(archivoDeRegla);
    }

    public List<String> getComboDeObjetivosASeleccionar() {
        return comboDeObjetivosASeleccionar;
    }

    public void setComboDeObjetivosASeleccionar(List<String> comboDeObjetivosASeleccionar) {
        this.comboDeObjetivosASeleccionar = comboDeObjetivosASeleccionar;
    }

    public List<String> getListaHechosDeInicioSeleccionados() {
        return listaHechosDeInicioSeleccionados;
    }

    public void setListaHechosDeInicioSeleccionados(List<String> listaHechosDeInicioSeleccionados) {
        this.listaHechosDeInicioSeleccionados = listaHechosDeInicioSeleccionados;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public List<String> getListaHechosDeInicioASeleccionar() {
        return listaHechosDeInicioASeleccionar;
    }

    public void setListaHechosDeInicioASeleccionar(List<String> listaHechosDeInicioASeleccionar) {
        this.listaHechosDeInicioASeleccionar = listaHechosDeInicioASeleccionar;
    }

    public void setNumeroDeCiclos(byte numeroDeCiclos) {
        this.numeroDeCiclos = numeroDeCiclos;
    }

    public void preguntarNumeroDeCiclos(Main framePrincipal) {
        boolean isRespuestaValida;
        do {
            String respuesta = JOptionPane.showInputDialog(framePrincipal, "Ingrese el número de ciclos máximos con el\n"
                    + "que desea limitar el algoritmo", "15");
            try {
                isRespuestaValida = true;
                this.numeroDeCiclos = Byte.parseByte(respuesta);
            } catch (NumberFormatException e) {
                isRespuestaValida = false;
                JOptionPane.showMessageDialog(framePrincipal,
                        "El valor que ha ingresado no es válido, por favor, intente de nuevo.",
                        "Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
            
            if (numeroDeCiclos <= 0) {
                isRespuestaValida = false;
                JOptionPane.showMessageDialog(framePrincipal,
                        "El valor que ha ingresado no es válido, por favor, intente de nuevo ingresando\n"
                        + " un valor entre 1 y 127",
                        "Error!",
                        JOptionPane.ERROR_MESSAGE);
            }

        } while (!isRespuestaValida);
    }

    private List<String> parsearListaDeHechosDeInicio(List<Regla> listaReglas) {
        List<String> hechosDeInicio = new ArrayList<String>();

        for (Regla r : listaReglas) {
            List<String> causantes = r.getCausantes();

            for (String s : causantes) {

                if (!isCadenaEnLista(s, hechosDeInicio)) {
                    hechosDeInicio.add(s);
                }
            }
        }
        return hechosDeInicio;
    }

    private List<String> parsearListaDeObjetivos(List<Regla> listaReglas) {
        List<String> objetivos = new ArrayList<String>();

        for (Regla r : listaReglas) {
            String producto = r.getProducto();

            if (!isCadenaEnLista(producto, objetivos)) {
                objetivos.add(producto);
            }
        }
        return objetivos;
    }

    private boolean isCadenaEnLista(String cadena, List<String> lista) {

        for (String s : lista) {
            if (cadena.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
