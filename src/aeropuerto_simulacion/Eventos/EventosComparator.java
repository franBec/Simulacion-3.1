package aeropuerto_simulacion.Eventos;

import java.util.Comparator;

public class EventosComparator implements Comparator <Evento>{
    @Override public int compare(Evento e1, Evento e2){
        
        //Si los eventos tienen el mismo tiempo, la prioridad en la fel es: Salida - FIN - Arribo
        if(e1.getTiempo()==e2.getTiempo())
            return e1.getTipoDeEvento().ordinal() - e2.getTipoDeEvento().ordinal();
        else
            return (int) (e1.getTiempo() - e2.getTiempo());       
    }
}

/*
Que pasa si entran dos eventos que tienen el mismo tiempo y son del mismo tipo?

(Por ejemplo: la salida de un vuelo privado y la salida de un vuelo de cabotaje
ocurren al mismo tiempo)

Segun la siguiente pagina: https://www.geeksforgeeks.org/priority-queue-class-in-java-2/

"If multiple elements are tied for least value, the head is one of those
elements — ties are broken arbitrarily"

Ocurrira un comportamiento arbitrario al elegir a cual evento darle prioridad
Sin embargo como estos eventos serán atendidos por distintos servers, me animo a
afirmar que estas situaciones no afectaran al programa
*/