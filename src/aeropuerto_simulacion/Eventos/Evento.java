package aeropuerto_simulacion.Eventos;

import aeropuerto_simulacion.Items.Item;

public abstract class Evento{
    private float tiempo;
    private Item item;
    private static int cantidadEventos = 0;
    
    public enum TipoDeEvento{
        SALIDA, FIN, ARRIBO;
    }

    public Evento(float tiempo, Item item) {
        Evento.cantidadEventos++;
        this.tiempo = tiempo;
        this.item = item;
    }
    
    public float getTiempo() {
        return tiempo;
    }

    public void setTiempo(float tiempo) {
        this.tiempo = tiempo;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }

    public static void setCantidadEventos(int cantidadEventos) {
        Evento.cantidadEventos = cantidadEventos;
    }
    
    public abstract TipoDeEvento getTipoDeEvento();

    //EventoArribo, EventoSalida y EventoFIN hacen sus respectivas cosas aca
    public abstract void doEvento(); 
}