package aeropuerto_simulacion;

import aeropuerto_simulacion.Eventos.Evento;
import aeropuerto_simulacion.Eventos.EventosComparator;
import java.util.PriorityQueue;
 
/*PriorityQueue no necesariamente se encuentra ordenada (imprimiendo la 
estructura con un iterator se puede comprobar). Sin embargo, siempre que se
use fel.poll() y fel.peek() va a devolver el elemento con mayor prioridad
de acuerdo a un comparator, y eso es lo que necesito

En principío el tamaño de PriorityQueue = 2 tipos de Eventos (Arribo y Salida) x 6 Servidores + Evento FIN = 13
Sin embargo este tamaño es dinamico, asi que resulta indiferente si se empiezan con mas o menos servidores

Implementado como singleton
*/
 
public class FEL{

    private static FEL fel = null;
    private PriorityQueue<Evento> pqueue;

    private FEL(){
        pqueue = new PriorityQueue<>(13, new EventosComparator());
    }

    public static FEL getFel(){
        if(fel==null) fel = new FEL();
        return fel;
    }

    public void addEvento(Evento e){
        pqueue.add(e);
    }

    public Evento peekFel(){
        return pqueue.peek();
    }
    
    public Evento pollFel(){
        return pqueue.poll();
    }
    
    public void clearFEL(){
        pqueue.clear();
    }
}