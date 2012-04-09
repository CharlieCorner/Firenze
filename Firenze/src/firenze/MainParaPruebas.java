package firenze;

import algoritmosdeinferencia.Deduccion;
import algoritmosdeinferencia.Regla;
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
        // TODO code application logic here
        String lineaDeReglas[] = {"L=J",
            "F^J=M",
            "I^K=P",
            "L^M=N",
            "N=G",
            "G=H"};
        String hechosDeInicio[] = {"L", "F", "I"};
        List<Regla> listaDeReglas = Regla.listaFromLineasSinParsear(lineaDeReglas);
        List<String> hechos = Arrays.asList(hechosDeInicio);

        imprimirListaReglas(listaDeReglas);
        imprimirListaString(hechos);
        
        Deduccion nuevoAlgoritmo = new Deduccion(listaDeReglas, hechos);

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
            System.out.print(" = " + r.getProducidos()+"\n\n");
        }
    }
    
    private static void imprimirListaString(List<String> lista) {
        System.out.println("****Lista de hechos****");
        
        for (String s : lista) {
            System.out.println(s);
        }
        System.out.println("");
    }
}
