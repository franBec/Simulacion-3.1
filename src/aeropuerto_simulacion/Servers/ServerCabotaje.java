package aeropuerto_simulacion.Servers;

public class ServerCabotaje extends Server{
    
    private static final TipoDeServer TIPO = Server.TipoDeServer.CABOTAJE;    
    private static int cantidadServersCabotaje = 0; //Contador de cuantos servers de cabotaje existen
    
    public ServerCabotaje(){
        super();
        cantidadServersCabotaje++;
    }

    public static int getCantidadServersCabotaje() {
        return cantidadServersCabotaje;
    }

    public static void setCantidadServersCabotaje(int cantidadServersCabotaje) {
        ServerCabotaje.cantidadServersCabotaje = cantidadServersCabotaje;
    }

    @Override public TipoDeServer getTipoDeServer() {
        return TIPO;
    }
}
