package aeropuerto_simulacion.FuncionesAuxiliares;

import aeropuerto_simulacion.Eventos.Evento;
import aeropuerto_simulacion.Eventos.EventoArribo;
import aeropuerto_simulacion.Eventos.EventoFIN;
import aeropuerto_simulacion.Eventos.EventoSalida;
import aeropuerto_simulacion.FEL;
import aeropuerto_simulacion.Items.Item;
import aeropuerto_simulacion.Items.ItemLiviano;
import aeropuerto_simulacion.Items.ItemMediano;
import aeropuerto_simulacion.Items.ItemPesado;
import aeropuerto_simulacion.Servers.Server;
import aeropuerto_simulacion.Servers.ServerCabotaje;
import aeropuerto_simulacion.Servers.ServerInternacional;
import aeropuerto_simulacion.Servers.ServerPrivado;
import aeropuerto_simulacion.Servers.ServersManagement;

public abstract class PrepararSimulacion {
    public static void start(float simulationTime){
        
        //Limpio todo antes de arrancar
        
        //Estructuras
        ServersManagement.getServidoresArray().clear();
        FEL.getFel().clearFEL();
        
        //Eventos
        Evento.setCantidadEventos(0);
        
        EventoArribo.setCantidadArribos(0);
        EventoSalida.setCantidadSalidas(0);
        EventoFIN.setCantidadFIN(0);
        
        //Items
        Item.setCantidadItems(0);
        
        ItemLiviano.setCantidadItemsLiviano(0);
        ItemLiviano.setDineroAcum(0);
        ItemLiviano.setTiempoAcumCola(0);
        ItemLiviano.setTiempoAcumTransito(0);
        
        ItemMediano.setCantidadItemsMediano(0);
        ItemMediano.setDineroAcum(0);
        ItemMediano.setTiempoAcumCola(0);
        ItemMediano.setTiempoAcumTransito(0);
        
        ItemPesado.setCantidadItemsPesado(0);
        ItemPesado.setDineroAcum(0);
        ItemPesado.setTiempoAcumCola(0);
        ItemPesado.setTiempoAcumTransito(0);
        
        //Servers
        Server.setCantidadDeServers(0);
        
        ServerPrivado.setCantidadServersPrivado(0);
        ServerCabotaje.setCantidadServersCabotaje(0);
        ServerInternacional.setCantidadServersInternacional(0);
        
        //Inicializo 6 Pistas de aterrizaje: 1 privada, 4 cabotaje, 1 internacional
        ServersManagement.addServer(new ServerPrivado());
        ServersManagement.addServer(new ServerPrivado());
        
        ServersManagement.addServer(new ServerCabotaje());
        //ServersManagement.addServer(new ServerCabotaje());
        //ServersManagement.addServer(new ServerCabotaje());
        //ServersManagement.addServer(new ServerCabotaje());
        
        ServersManagement.addServer(new ServerInternacional());
        ServersManagement.addServer(new ServerInternacional());
        
        //Inicializo FEL con un arribo de cada tipo de avion
        FEL.getFel().addEvento(new EventoArribo(0, new ItemLiviano(0)));
        FEL.getFel().addEvento(new EventoArribo(0, new ItemMediano(0)));
        FEL.getFel().addEvento(new EventoArribo(0, new ItemPesado(0)));        
        FEL.getFel().addEvento(new EventoFIN(simulationTime));   
    }
}
