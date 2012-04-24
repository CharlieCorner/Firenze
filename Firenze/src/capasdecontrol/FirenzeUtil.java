package capasdecontrol;

import algoritmosdeinferencia.Regla;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

        return new Regla(listaCausantes, producto);
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

            listasDeReglas.add(new Regla(listaCausantes, producto));
        }
        return listasDeReglas;
    }
    
    public static List<Regla> listaFromLineasSinParsear(List<String> listaSinParsear) {
        List<Regla> listasDeReglas = new ArrayList<Regla>();

        for (String linea : listaSinParsear) {
            List<String> listaCausantes = new ArrayList<String>();
            String producto;
            String reglas[] = linea.split("=");
            String causas[] = reglas[0].replaceAll("[()]", "").split("\\^");

            listaCausantes.addAll(Arrays.asList(causas));
            producto = reglas[1];

            listasDeReglas.add(new Regla(listaCausantes, producto));
        }
        return listasDeReglas;
    }
    
    @SuppressWarnings("CallToThreadDumpStack")
    public static List<String> getListaDeStringDeArchivo(File archivo){
        List<String> listaDeCadenas = new ArrayList<String>();
        FileReader fr = null;
        BufferedReader br;
        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                listaDeCadenas.add(linea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return listaDeCadenas;
    }
}
