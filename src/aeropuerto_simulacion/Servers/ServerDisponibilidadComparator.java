package aeropuerto_simulacion.Servers;

import java.util.Comparator;

public class ServerDisponibilidadComparator implements Comparator <Server>{

    @Override public int compare(Server s1, Server s2) {
        
        /*Ordena dos servidores s1 y s2 de acuerdo al siguiente criterio:
        
        Si la cantidad de items en cola de s1 es diferente a la cantidad de
        items en cola de s2, tiene prioridad el servidor con menor cola

        Si lo anterior no se cumple, tiene prioridad el servidor que este desocupado

        Si ninguno de los dos criterios anteriores se cumplen (las colas de s1 y
        s2 tienen el mismo tamaño, y s1 y s2 estan ambos ocupados o ambos desocupados),
        tiene prioridad el servidor con menor id

        */
        
        if(s1.getQueue().size() != s2.getQueue().size())
            return s1.getQueue().size() - s2.getQueue().size();
        else
            if(Boolean.compare(s1.isOcupado(), s2.isOcupado()) != 0)
                return Boolean.compare(s1.isOcupado(), s2.isOcupado());
            else
                return s1.getId() - s2.getId();
    }
}