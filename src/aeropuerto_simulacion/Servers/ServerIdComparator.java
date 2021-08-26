package aeropuerto_simulacion.Servers;

import java.util.Comparator;

public class ServerIdComparator implements Comparator <Server>{

    @Override public int compare(Server s1, Server s2) {
        //odren id creciente
        return s1.getId() - s2.getId();
    }   
}
