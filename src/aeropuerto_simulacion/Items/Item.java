package aeropuerto_simulacion.Items;

public abstract class Item{
    private int id; //identificador unico de un item
    private float tiempoArribo;
    private float tiempoDuracionServicio;
    private float tiempoEnCola;
    private float tiempoEnTransito;
    private int idHost; //id del server por el que es atendido
    
    public enum TipoDeItem{
        LIVIANO, MEDIANO, PESADO;
    }
    
    //Variables de clase con fines estadisticos
    private static int cantidadItems = 0; //Contador de cuantas instancias de item existen
	
    public Item(float tiempoArribo){
        cantidadItems++;    
        this.id = cantidadItems;
        this.tiempoArribo = tiempoArribo;
        this.tiempoDuracionServicio = 0;
        this.tiempoEnCola = 0;
        this.tiempoEnTransito = 0;
        this.idHost = -1; //valor invalido   
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTiempoArribo() {
        return tiempoArribo;
    }

    public void setTiempoArribo(float tiempoArribo) {
        this.tiempoArribo = tiempoArribo;
    }

    public float getTiempoDuracionServicio() {
        return tiempoDuracionServicio;
    }

    public void setTiempoDuracionServicio(float tiempoDuracionServicio) {
        this.tiempoDuracionServicio = tiempoDuracionServicio;
    }

    public float getTiempoEnCola() {
        return tiempoEnCola;
    }

    public void setTiempoEnCola(float tiempoEnCola) {
        this.tiempoEnCola = tiempoEnCola;
    }

    public float getTiempoEnTransito() {
        return tiempoEnTransito;
    }

    public void setTiempoEnTransito(float tiempoEnTransito) {
        this.tiempoEnTransito = tiempoEnTransito;
    }

    public int getIdHost() {
        return idHost;
    }

    public void setIdHost(int idHost) {
        this.idHost = idHost;
    }

    public static int getCantidadItems() {
        return cantidadItems;
    }

    public static void setCantidadItems(int cantidadItems) {
        Item.cantidadItems = cantidadItems;
    }

    public abstract TipoDeItem getTipoDeItem();
}
