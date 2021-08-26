package aeropuerto_simulacion.Eventos;

import aeropuerto_simulacion.Servers.Server;
import aeropuerto_simulacion.Servers.ServerIdComparator;
import aeropuerto_simulacion.Servers.ServersManagement;
import java.util.Collections;
import java.util.Iterator;

public class EventoFIN extends Evento{

    private static final TipoDeEvento TIPO = TipoDeEvento.FIN;
    private static int cantidadFIN = 0;
    
    public EventoFIN(float tiempo) {
        super(tiempo, null);
        EventoFIN.cantidadFIN++;
    }

    public static int getCantidadFIN() {
        return cantidadFIN;
    }

    public static void setCantidadFIN(int cantidadFIN) {
        EventoFIN.cantidadFIN = cantidadFIN;
    }
    
    @Override public TipoDeEvento getTipoDeEvento(){
        return TIPO;
    }
    
    @Override public void doEvento() {
        
        /*Al finalizar la simulacion, concluyo los ocios de los servers del programa
        Esto es util para evitar errores al coleccionar estadisticas de servers nuunca usados
        
        Tambien dejo ordenado los servers en orden id creciente, para que al mostar la info
        obtenida por pantalla, esta sea mas facilmente legible
        
        /*Por que uso un iterator en lugar de un for o for each?
        Por alguna razon estas opciones tiraban error "Object cannot be converted to Server"
        */
        
        Collections.sort(ServersManagement.getServidoresArray(), new ServerIdComparator());
        Iterator<Server> it = ServersManagement.getServidoresArray().iterator();
        while(it.hasNext()){
            Server s = it.next();
            s.addAcumTiempoOcio(this.getTiempo()-s.getInicioOcio());  
        }             
    }
}