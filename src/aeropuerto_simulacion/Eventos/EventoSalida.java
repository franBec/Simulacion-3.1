package aeropuerto_simulacion.Eventos;

import aeropuerto_simulacion.FEL;
import aeropuerto_simulacion.Items.Item;
import aeropuerto_simulacion.Items.ItemLiviano;
import aeropuerto_simulacion.Items.ItemMediano;
import aeropuerto_simulacion.Items.ItemPesado;
import aeropuerto_simulacion.Servers.ServersManagement;

public class EventoSalida extends Evento{

    private static final TipoDeEvento TIPO = TipoDeEvento.SALIDA;
    private static int cantidadSalidas = 0;
    
    public EventoSalida(float duracionDeServicio, Item item) {
        super(duracionDeServicio, item);
        EventoSalida.cantidadSalidas++;
    }

    public static int getCantidadSalidas() {
        return cantidadSalidas;
    }

    public static void setCantidadSalidas(int cantidadSalidas) {
        EventoSalida.cantidadSalidas = cantidadSalidas;
    }
  
    @Override public TipoDeEvento getTipoDeEvento(){
        return TIPO;
    }
    
    @Override public void doEvento() {
        
        /*Al item auxiliar i se le va a asignar el elemento de la cabeza de la
        cola de espera correspondiente
        
        If i!=null: Habia cola
            Se procede a hacer el evento de salida del item i
        Else: No habia cola
            Se marca el Server como desocupado.
        */
        
        Item i = ServersManagement.checkIfCola(this.getItem().getIdHost());
        if(i != null){
            //calculo tiempo de servicio
            float duracionDeServicio = GeneradorTiempos.getTiempoDeClasifcacion(i.getTipoDeItem());
            //set duracion de servicio al item
            i.setTiempoDuracionServicio(duracionDeServicio);
            //set cuanto tiempo item estuvo en cola
            i.setTiempoEnCola(this.getTiempo() - i.getTiempoArribo());
            //creo evento de salida y lo agrego a la fel
            FEL.getFel().addEvento(new EventoSalida(this.getTiempo() + duracionDeServicio, i)); 
        }
        
        else
            ServersManagement.setOcupado(this.getItem().getIdHost(), false);
        
        //Set tiempo en transito al item
        this.getItem().setTiempoEnTransito(this.getItem().getTiempoDuracionServicio() + this.getItem().getTiempoEnCola());
        
        //ESTADISTICAS TIME!!!
        
        //actualizo acumuladores de tiempo en cola y tiempo en transito     
        switch(this.getItem().getTipoDeItem()){
            case LIVIANO:
                ItemLiviano.addTiempoAcumCola(this.getItem().getTiempoEnCola());
                ItemLiviano.addTiempoAcumTransito(this.getItem().getTiempoEnTransito());
                break;
            case MEDIANO:
                ItemMediano.addTiempoAcumCola(this.getItem().getTiempoEnCola());
                ItemMediano.addTiempoAcumTransito(this.getItem().getTiempoEnTransito());
                break;
            case PESADO:
                ItemPesado.addTiempoAcumCola(this.getItem().getTiempoEnCola());
                ItemPesado.addTiempoAcumTransito(this.getItem().getTiempoEnTransito());
                break;
        }
        
        /*Server se acaba de desocupar, empieza ocio ahora
        Que empiece el ocio no imnplica que ocio!=0, eso va a depender del proximo arribo
        */
        
        ServersManagement.setInicioOcio(this.getItem().getIdHost(),this.getTiempo());
    }
}