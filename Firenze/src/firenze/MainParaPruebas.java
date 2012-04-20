package firenze;

import algoritmosdeinferencia.*;
import capasdecontrol.FirenzeUtil;
import java.util.Arrays;
import java.util.List;

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
        correrAlgoritmoDeduccion(args);
        System.out.println("\n\n");
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
                "G=H",
                "H^P=R",
                "R=E"};
            hechosDeInicio = new String[]{"L", "F", "P"};
        } else {
            String reglasHechos[][] = parsearArchivo(args[0]);
            lineaDeReglas = reglasHechos[0];
            hechosDeInicio = reglasHechos[1];
        }

        List<Regla> listaDeReglas = FirenzeUtil.listaFromLineasSinParsear(lineaDeReglas);
        List<String> hechos = Arrays.asList(hechosDeInicio);

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
                "G=H",
                "H^P=R",
                "R=E"};
            
            hechosDeInicio = new String[]{"M","F","K"};
            objetivo = "F";
        } else {
            String reglasHechos[][] = parsearArchivo(args[0]);
            lineaDeReglas = reglasHechos[0];
            hechosDeInicio = reglasHechos[1];
            objetivo = reglasHechos[2][0];
        }

        List<Regla> listaDeReglas = FirenzeUtil.listaFromLineasSinParsear(lineaDeReglas);
        List<String> hechos = Arrays.asList(hechosDeInicio);

        AlgoritmoDeInferencia nuevoAlgoritmo = new Induccion(listaDeReglas, hechos, objetivo);
        String resultado = nuevoAlgoritmo.correrAlgoritmo();
        System.out.println(resultado);
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

    private static String[][] parsearArchivo(String string) {
        // TODO implementación de leer archivos de prueba desde argumento
        //  de terminal
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
