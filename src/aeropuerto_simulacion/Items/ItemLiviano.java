package aeropuerto_simulacion.Items;

public class ItemLiviano extends Item{

    private static final TipoDeItem TIPO = Item.TipoDeItem.LIVIANO;
    private static final float TARIFA = 25000;
    
    private static float tiempoAcumCola = 0;        //Acumulador tiempo de espera de los items livianos
    private static float tiempoAcumTransito = 0;    //Acumulador tiempo de transito de los items livianos
    private static float dineroAcum = 0;            //Acumulador dinero generado por items livianos
    private static int cantidadItemsLiviano = 0;    //Contador de cuantos items livianos existen
    
    public ItemLiviano(float tiempoArribo) {
        super(tiempoArribo);
        cantidadItemsLiviano++;
        dineroAcum += TARIFA;
    }

    public static float getTiempoAcumCola() {
        return tiempoAcumCola;
    }

    public static void addTiempoAcumCola(float tiempoAcumCola) {
        ItemLiviano.tiempoAcumCola += tiempoAcumCola;
    }

    public static float getDineroAcum() {
        return dineroAcum;
    }

     public static int getCantidadItemsLiviano() {
        return cantidadItemsLiviano;
    }
     
    public static float getTiempoAcumTransito() {
        return tiempoAcumTransito;
    }

    public static void addTiempoAcumTransito(float tiempoAcumTransito) {
        ItemLiviano.tiempoAcumTransito += tiempoAcumTransito;
    }

    public static void setTiempoAcumCola(float tiempoAcumCola) {
        ItemLiviano.tiempoAcumCola = tiempoAcumCola;
    }

    public static void setTiempoAcumTransito(float tiempoAcumTransito) {
        ItemLiviano.tiempoAcumTransito = tiempoAcumTransito;
    }

    public static void setDineroAcum(float dineroAcum) {
        ItemLiviano.dineroAcum = dineroAcum;
    }

    public static void setCantidadItemsLiviano(int cantidadItemsLiviano) {
        ItemLiviano.cantidadItemsLiviano = cantidadItemsLiviano;
    }
    
    @Override public TipoDeItem getTipoDeItem() {
        return TIPO;
    }
}