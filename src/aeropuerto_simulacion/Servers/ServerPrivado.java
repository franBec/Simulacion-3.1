package aeropuerto_simulacion.Servers;

public class ServerPrivado extends Server{
    
    private static final TipoDeServer TIPO = Server.TipoDeServer.PRIVADO;    
    private static int cantidadServersPrivado = 0; //Contador de cuantos servers privados existen
    
    public ServerPrivado(){
        super();
        cantidadServersPrivado++;
    }

    public static int getCantidadServersPrivado() {
        return cantidadServersPrivado;
    }

    public static void setCantidadServersPrivado(int cantidadServersPrivado) {
        ServerPrivado.cantidadServersPrivado = cantidadServersPrivado;
    }

    @Override public TipoDeServer getTipoDeServer() {
        return TIPO;
    }    
}
