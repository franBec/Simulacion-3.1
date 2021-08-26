package aeropuerto_simulacion.Eventos;

import aeropuerto_simulacion.FEL;
import aeropuerto_simulacion.Items.Item;
import aeropuerto_simulacion.Servers.Server;
import aeropuerto_simulacion.Items.ItemLiviano;
import aeropuerto_simulacion.Items.ItemMediano;
import aeropuerto_simulacion.Items.ItemPesado;
import aeropuerto_simulacion.Servers.ServersManagement;

public class EventoArribo extends Evento{

    private static final TipoDeEvento TIPO = TipoDeEvento.ARRIBO;
    private static int cantidadArribos = 0;
    
    public EventoArribo(float tiempoArribo, Item item) {
        super(tiempoArribo, item);
        EventoArribo.cantidadArribos++;
    }

    public static int getCantidadArribos() {
        return cantidadArribos;
    }

    public static void setCantidadArribos(int cantidadArribos) {
        EventoArribo.cantidadArribos = cantidadArribos;
    }

    @Override public TipoDeEvento getTipoDeEvento() {
        return TIPO;
    }
    
    @Override public void doEvento() {
    
        /*
        Consigo un server desocupado o en su defecto el que tenga menor cola
        
        If server is ocupado
            item va a la cola del server
        Else
            ahora el server estara ocupado con este item
        */
        
        //devuelve un servidor disponible segun tipo de item
        Server s = ServersManagement.getServerDisponible(this.getItem().getTipoDeItem());
        
        if(s.isOcupado()){
            this.getItem().setIdHost(s.getId());
            s.getQueue().add(this.getItem());
        }
        
        else{
            s.setOcupado(true);
            
            //acumulo tiempo de ocio que tuvo el server
            s.addAcumTiempoOcio(this.getTiempo()-s.getInicioOcio());
            //set tiempo de duracion de servicio al item del evento inminente
            float duracionDeServicio = GeneradorTiempos.getTiempoDeClasifcacion(this.getItem().getTipoDeItem());
            this.getItem().setTiempoDuracionServicio(duracionDeServicio);
            //set id del server que atiende el arribo del item
            this.getItem().setIdHost(s.getId());
            //creo evento de salida de item y lo agrego a la fel
            FEL.getFel().addEvento(new EventoSalida(this.getTiempo() + duracionDeServicio, this.getItem()));
        }
        
        //creo tiempo de arribo de un nuevo item del mismo tipo
        float tiempoArribo = GeneradorTiempos.getTiempoEntreArribos(this.getItem().getTipoDeItem(),this.getTiempo()) + this.getItem().getTiempoArribo();
        
        //switch case para agregar a la fel un evento de arribo con item de tipo correspondiente
        switch(this.getItem().getTipoDeItem()){
            case LIVIANO:
                FEL.getFel().addEvento(new EventoArribo(tiempoArribo,new ItemLiviano(tiempoArribo)));
                break;
            case MEDIANO:
                FEL.getFel().addEvento(new EventoArribo(tiempoArribo,new ItemMediano(tiempoArribo)));
                break;
            case PESADO:
                FEL.getFel().addEvento(new EventoArribo(tiempoArribo,new ItemPesado(tiempoArribo)));
                break;
        }
    }    
}