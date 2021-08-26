package aeropuerto_simulacion.Items;

public class ItemMediano extends Item{

    private static final TipoDeItem TIPO = Item.TipoDeItem.MEDIANO;
    private static final float TARIFA = 45000;
    
    private static float tiempoAcumCola = 0;        //Acumulador tiempo de espera de los items medianos
    private static float tiempoAcumTransito = 0;    //Acumulador tiempo de transito de los items medianos
    private static float dineroAcum = 0;            //Acumulador dinero generado por items medianos
    private static int cantidadItemsMediano = 0;    //Contador de cuantos items medianos existen
    
    public ItemMediano(float tiempoArribo) {
        super(tiempoArribo);
        cantidadItemsMediano++;
        dineroAcum += TARIFA;
    }

    public static float getTiempoAcumCola() {
        return tiempoAcumCola;
    }

    public static void addTiempoAcumCola(float tiempoAcumCola) {
        ItemMediano.tiempoAcumCola += tiempoAcumCola;
    }

    public static float getDineroAcum() {
        return dineroAcum;
    }

    public static int getCantidadItemsMediano() {
        return cantidadItemsMediano;
    }
    
    public static float getTiempoAcumTransito() {
        return tiempoAcumTransito;
    }

    public static void addTiempoAcumTransito(float tiempoAcumTransito) {
        ItemMediano.tiempoAcumTransito += tiempoAcumTransito;
    }

    public static void setTiempoAcumCola(float tiempoAcumCola) {
        ItemMediano.tiempoAcumCola = tiempoAcumCola;
    }

    public static void setTiempoAcumTransito(float tiempoAcumTransito) {
        ItemMediano.tiempoAcumTransito = tiempoAcumTransito;
    }

    public static void setDineroAcum(float dineroAcum) {
        ItemMediano.dineroAcum = dineroAcum;
    }

    public static void setCantidadItemsMediano(int cantidadItemsMediano) {
        ItemMediano.cantidadItemsMediano = cantidadItemsMediano;
    }

    @Override public TipoDeItem getTipoDeItem() {
        return TIPO;
    }
}