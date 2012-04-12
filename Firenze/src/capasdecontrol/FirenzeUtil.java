package capasdecontrol;

import algoritmosdeinferencia.Regla;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Charlie Corner
 */
public class FirenzeUtil {

    private FirenzeUtil() {
    }

    public static Regla fromLineaSinParsear(String lineaDeReglas) {
        List<String> listaCausantes = new ArrayList<String>();
        String producto;
        String reglas[] = lineaDeReglas.split("=");
        String causas[] = reglas[0].replaceAll("[()]", "").split("\\^");

        listaCausantes.addAll(Arrays.asList(causas));
        producto = reglas[1];

        int indice = Regla.getCuentaIndiceReglas();
        Regla.setCuentaIndiceReglas(Regla.getCuentaIndiceReglas() + 1);
        return new Regla(indice, listaCausantes, producto);
    }

    public static List<Regla> listaFromLineasSinParsear(String listaSinParsear[]) {
        List<Regla> listasDeReglas = new ArrayList<Regla>();

        for (String linea : listaSinParsear) {
            List<String> listaCausantes = new ArrayList<String>();
            String producto;
            String reglas[] = linea.split("=");
            String causas[] = reglas[0].replaceAll("[()]", "").split("\\^");

            listaCausantes.addAll(Arrays.asList(causas));
            producto = reglas[1];

            int indice = Regla.getCuentaIndiceReglas();
            Regla.setCuentaIndiceReglas(Regla.getCuentaIndiceReglas() + 1);
            listasDeReglas.add(new Regla(indice, listaCausantes, producto));
        }
        return listasDeReglas;
    }
}
