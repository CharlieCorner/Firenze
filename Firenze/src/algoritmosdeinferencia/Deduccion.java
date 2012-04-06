package algoritmosdeinferencia;

import java.util.Stack;

/**
 *
 * @author Charlie Corner
 */
public class Deduccion extends AlgoritmoDeInferencia {
    
    private Stack<Reglas> reglasDisparadas;

    @Override
    protected boolean isElementoEnListas(String elemento) {
        
        if (null != hechosDeInicio) {
            
            for(String s : hechosDeInicio){
                
                if (elemento.equals(s)) {
                    return true;
                }
            }
        }
        
        if (null != hechosInferidos) {
            
            for(String s: hechosInferidos){
                
                if (elemento.equals(s)) {
                    return true;
                }
            }
            
        }
        
        if (null != hechosPreguntados) {
            
            for(String s: hechosPreguntados){
                
                if (elemento.equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }
}
