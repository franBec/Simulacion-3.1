package aeropuerto_simulacion.Servers;

import aeropuerto_simulacion.Items.Item;
import java.util.ArrayList;
import java.util.Collections;

public abstract class ServersManagement {
    
    /*Esta clase trabaja como intermediario entre los Servers (que se encuentran
    en un ArrayList llamado "servidores") y el resto del programa
    */
    
    private static ArrayList<Server> servidores = new ArrayList<>();

    public static void addServer(Server s){
        
        /*Permite agregar un nuevo servidor a la lista de servidores
        Es necesario que un servidor se encuentre en la lista de servidores
        Caso contrario el resto del programa no podrá trabajar con el
        */
        
        servidores.add(s);
    }
    
    public static Server getServer(int id){
        for (Server s : servidores) 
            if(s.getId() == id)
                return s;
        
        return null;
    }
    
    public static Server getServerDisponible(){
        
        /*Retorna el primer server que se encuentre
        no ocupado, o server con la menor cola
        */
        
        /*
        Server.TipoDeServer t = null;
        switch(i){
            case LIVIANO:
                t = Server.TipoDeServer.PRIVADO;
                break;
            case MEDIANO:
                t = Server.TipoDeServer.CABOTAJE;
                break;
            case PESADO:
                t = Server.TipoDeServer.INTERNACIONAL;
                break;
        }
        */
                
        /*Ordeno el ArrayList segun ServerDisponibilidadComparator (leer
        comentario en la clase para entender que hace), y devuelvo el primer
        resultado valido
        
        Durante toda la ejecucion, el orden de los servers dentro del ArrayList
        servidores va a ir cambiando, sin embargo esto no resulta en un problema
        para la correcta ejecucion del programa
        */

        Collections.sort(servidores, new ServerDisponibilidadComparator());
        return servidores.get(0);
        
    }
    
    public static Item checkIfCola(int id){
        
        /*A partir de la id de un server, devuelve el item que haya en la cola
        Si no hay ningun item en cola, devolvera null
        */
        
        for (Server s : servidores)
            if(s.getId() == id)
                return s.getQueue().poll();
            
        return null;   
    }
    
    public static void setOcupado(int id, boolean status){
        //Setea true/false al ocupado del server con id id
        for (Server s : servidores){
            if(s.getId() == id){
                s.setOcupado(status);
                break;
            } 
        }       
    }
    
    public static void setInicioOcio(int id, float cloak){
        //Le setea al server id inicioOcio = cloak
        for (Server s : servidores){
            if(s.getId() == id){
                s.setInicioOcio(cloak);
                break;
            }
        }
    }
    
    public static ArrayList getServidoresArray(){
        return servidores;
    }  
}