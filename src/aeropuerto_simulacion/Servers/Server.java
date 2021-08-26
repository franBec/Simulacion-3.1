package aeropuerto_simulacion.Servers;

import aeropuerto_simulacion.Items.Item;
import java.util.LinkedList;
import java.util.Queue;

public abstract class Server {
    private int id;                 //identificador unico de un server
    private boolean ocupado;        //true = ocupado, false = no ocupado
    private float inicioOcio;       //guarda el cloak donde empezo el ocio
    private float acumTiempoOcio;   //acumulador de tiempo ocioso
    private Queue<Item> queue;      //cola de espera de items
    
    private static int cantidadDeServers = 0;   //contador de cuantas instancias de server existen
    
    public enum TipoDeServer{
        PRIVADO, CABOTAJE, INTERNACIONAL;
    }
    
    public Server() {
        cantidadDeServers++;
        this.id = cantidadDeServers;
        this.ocupado = false;
        this.inicioOcio = 0;
        this.acumTiempoOcio = 0;
        this.queue = new LinkedList<>();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public float getInicioOcio() {
        return inicioOcio;
    }

    public void setInicioOcio(float inicioOcio) {
        this.inicioOcio = inicioOcio;
    }

    public static int getCantidadDeServers() {
        return cantidadDeServers;
    }

    public static void setCantidadDeServers(int cantidadDeServers) {
        Server.cantidadDeServers = cantidadDeServers;
    }
    
    public float getAcumTiempoOcio() {
        return acumTiempoOcio;
    }

    public void addAcumTiempoOcio(float acumTiempoOcio) {
        this.acumTiempoOcio += acumTiempoOcio;
    }

    public Queue<Item> getQueue() {
        return queue;
    }

    public void setQueue(Queue<Item> queue) {
        this.queue = queue;
    }
    
    public abstract TipoDeServer getTipoDeServer();
}