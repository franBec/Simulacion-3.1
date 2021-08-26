package aeropuerto_simulacion.Items;

public class ItemPesado extends Item{

    private static final TipoDeItem TIPO = Item.TipoDeItem.PESADO;
    private static final float TARIFA = 70000;
    
    private static float tiempoAcumCola = 0;        //Acumulador tiempo de espera de los items pesados
    private static float tiempoAcumTransito = 0;    //Acumulador tiempo de transito de los items pesados
    private static float dineroAcum = 0;            //Acumulador dinero generado por items pesados
    private static int cantidadItemsPesado = 0;     //Contador de cuantos item pesados existen
    
    public ItemPesado(float tiempoArribo) {
        super(tiempoArribo);
        cantidadItemsPesado++;
        dineroAcum += TARIFA;
    }

    public static float getTiempoAcumCola() {
        return tiempoAcumCola;
    }

    public static void addTiempoAcumCola(float tiempoAcumCola) {
        ItemPesado.tiempoAcumCola += tiempoAcumCola;
    }

    public static float getDineroAcum() {
        return dineroAcum;
    }

    public static int getCantidadItemsPesado() {
        return cantidadItemsPesado;
    }

    public static float getTiempoAcumTransito() {
        return tiempoAcumTransito;
    }

    public static void addTiempoAcumTransito(float tiempoAcumTransito) {
        ItemPesado.tiempoAcumTransito += tiempoAcumTransito;
    }

    public static void setTiempoAcumCola(float tiempoAcumCola) {
        ItemPesado.tiempoAcumCola = tiempoAcumCola;
    }

    public static void setTiempoAcumTransito(float tiempoAcumTransito) {
        ItemPesado.tiempoAcumTransito = tiempoAcumTransito;
    }

    public static void setDineroAcum(float dineroAcum) {
        ItemPesado.dineroAcum = dineroAcum;
    }

    public static void setCantidadItemsPesado(int cantidadItemsPesado) {
        ItemPesado.cantidadItemsPesado = cantidadItemsPesado;
    }
        
    @Override public TipoDeItem getTipoDeItem() {
        return TIPO;
    }
}