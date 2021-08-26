package aeropuerto_simulacion.Servers;

public class ServerInternacional extends Server{
    
    private static final TipoDeServer TIPO = Server.TipoDeServer.INTERNACIONAL;
    private static int cantidadServersInternacional = 0; //Contador de cuantos servers internacionles existen

    public ServerInternacional() {
        super();
        cantidadServersInternacional++;
    }

    public static int getCantidadServersInternacional() {
        return cantidadServersInternacional;
    }

    public static void setCantidadServersInternacional(int cantidadServersInternacional) {
        ServerInternacional.cantidadServersInternacional = cantidadServersInternacional;
    }

    @Override public TipoDeServer getTipoDeServer() {
        return TIPO;
    }
}
