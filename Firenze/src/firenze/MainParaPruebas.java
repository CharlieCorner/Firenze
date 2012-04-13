package firenze;

import algoritmosdeinferencia.Abduccion;
import algoritmosdeinferencia.AlgoritmoDeInferencia;
import algoritmosdeinferencia.Deduccion;
import algoritmosdeinferencia.Induccion;
import algoritmosdeinferencia.Regla;
import capasdecontrol.FirenzeUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Determina la clase para hacer pruebas de funcionalidad sin ninguna interfaz
 * gráfica.
 *
 * @author Charlie Corner
 */
public class MainParaPruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // correrAlgoritmoDeduccion(args);
        correrAlgoritmoInduccion(args);
        // correrAlgoritmoAbduccion(args);
    }

    private static void correrAlgoritmoDeduccion(String[] args) {
        String lineaDeReglas[] = null;
        String hechosDeInicio[] = null;

        if (0 == args.length) {
            lineaDeReglas = new String[]{"L=J",
                "F^J=M",
                "I^K=P",
                "L^M=N",
                "N=G",
                "G=H"};
            hechosDeInicio = new String[]{"L", "F", "I"};
        } else {
            String reglasHechos[][] = parsearArchivo(args[0]);
            lineaDeReglas = reglasHechos[0];
            hechosDeInicio = reglasHechos[1];
        }

        List<Regla> listaDeReglas = FirenzeUtil.listaFromLineasSinParsear(lineaDeReglas);
        List<String> hechos = Arrays.asList(hechosDeInicio);

        // imprimirListaReglas(listaDeReglas);
        // imprimirListaString(hechos);

        AlgoritmoDeInferencia nuevoAlgoritmo = new Deduccion(listaDeReglas, hechos);

        String resultado = nuevoAlgoritmo.correrAlgoritmo();
        System.out.println(resultado);
    }

    private static void correrAlgoritmoInduccion(String[] args) {
        String lineaDeReglas[] = null;
        String hechosDeInicio[] = null;
        String objetivo = "";

        if (0 == args.length) {
            lineaDeReglas = new String[]{"L=J",
                "F^J=M",
                "I^K=P",
                "L^M=N",
                "N=G",
                "G=H"};
            hechosDeInicio = new String[]{"L"};
            objetivo = "H";
        } else {
            String reglasHechos[][] = parsearArchivo(args[0]);
            lineaDeReglas = reglasHechos[0];
            hechosDeInicio = reglasHechos[1];
            objetivo = reglasHechos[2][0];
        }

        List<Regla> listaDeReglas = FirenzeUtil.listaFromLineasSinParsear(lineaDeReglas);
        List<String> hechos = Arrays.asList(hechosDeInicio);

        Induccion nuevoAlgoritmo = new Induccion(listaDeReglas, hechos, objetivo);
        String resultado = nuevoAlgoritmo.correrAlgoritmo();
        System.out.println(resultado);
        imprimirPilaObjetivos(nuevoAlgoritmo.getPilaDeObjetivos());
    }

    private static void correrAlgoritmoAbduccion(String[] args) {
        String lineaDeReglas[] = null;
        String hechosDeInicio[] = null;
        String objetivo = "";

        if (0 == args.length) {
            lineaDeReglas = new String[]{"L=J",
                "F^J=M",
                "I^K=P",
                "L^M=N",
                "N=G",
                "G=H"};
            hechosDeInicio = new String[]{"L", "F", "I"};
            objetivo = "H";
        } else {
            String reglasHechos[][] = parsearArchivo(args[0]);
            lineaDeReglas = reglasHechos[0];
            hechosDeInicio = reglasHechos[1];
            objetivo = reglasHechos[2][0];
        }

        AlgoritmoDeInferencia nuevoAlgoritmo = new Abduccion();
        String resultado = nuevoAlgoritmo.correrAlgoritmo();
        System.out.println(resultado);
    }

    private static void imprimirListaReglas(List<Regla> lista) {
        System.out.println("****Lista de reglas****");

        for (Regla r : lista) {
            System.out.println(r.getIndiceDeRegla());

            for (String s : r.getCausantes()) {
                System.out.print(s + " ");
            }
            System.out.print(" = " + r.getProducidos() + "\n\n");
        }
    }

    private static void imprimirListaString(List<String> lista) {
        System.out.println("****Lista de hechos****");

        for (String s : lista) {
            System.out.println(s);
        }
        System.out.println("");
    }

    private static void imprimirPilaObjetivos(Stack<List<String>> pila) {
        System.out.println("*****Pila de objetivos en orden inverso******");

        for (List<String> l : pila) {
            for(String s: l){
                System.out.print(s + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private static String[][] parsearArchivo(String string) {
        // TODO implementación de leer archivos de prueba desde argumento
        //  de terminal
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
