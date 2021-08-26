package aeropuerto_simulacion.FuncionesAuxiliares;

import aeropuerto_simulacion.Items.ItemLiviano;
import aeropuerto_simulacion.Items.ItemMediano;
import aeropuerto_simulacion.Items.ItemPesado;
import aeropuerto_simulacion.Servers.Server;
import aeropuerto_simulacion.Servers.ServersManagement;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Estadisticas {
    
    private static final float Z_ALFA_SOBRE_2 = (float) 1.645;   //90% de confanza
    
    //Tiempos en cola por cada tipo de vuelo
    private static ArrayList<Float> tiemposColaLivianos=new ArrayList<>();
    private static ArrayList<Float> tiemposColaMedianos=new ArrayList<>();
    private static ArrayList<Float> tiemposColaPesados=new ArrayList<>();
    
    //Tiempo ocioso de cada pista
    private static ArrayList<Float> tiemposOcioServers=new ArrayList<>();
    
    public static void acumData(){
        tiemposColaLivianos.add(ItemLiviano.getTiempoAcumCola()/ItemLiviano.getCantidadItemsLiviano());
        tiemposColaMedianos.add(ItemMediano.getTiempoAcumCola()/ItemMediano.getCantidadItemsMediano());
        tiemposColaPesados.add(ItemPesado.getTiempoAcumCola()/ItemPesado.getCantidadItemsPesado());
    
        Iterator<Server> it = ServersManagement.getServidoresArray().iterator();
        while(it.hasNext())
            tiemposOcioServers.add(it.next().getAcumTiempoOcio());        
    }
    
    public static float getDOBLERAYA(ArrayList<Float> a){
        float acum = 0;
        
        for(Float f : a)
            acum += f;
        
        return acum/a.size();
    }
    
    public static float getS(ArrayList<Float> a){
        float xDobleRaya = getDOBLERAYA(a);
        int n = a.size();
        float s=0;
        
        for(Float f : a)
            s += Math.pow(f - xDobleRaya, 2)/(n-1);
        
        return (float) Math.sqrt(s);      
    }
    
    public static float getLInferior(ArrayList a){
        float xDobleRaya = getDOBLERAYA(a);
        float s = getS(a);
        int n = a.size();
        
        return (float) (xDobleRaya - Z_ALFA_SOBRE_2*(s/Math.sqrt(n)));
    }
    
    public static float getLSuperior(ArrayList a){
        float xDobleRaya = getDOBLERAYA(a);
        float s = getS(a);
        int n = a.size();
        
        return (float) (xDobleRaya + Z_ALFA_SOBRE_2*(s/Math.sqrt(n)));
    }
    
    public static void print(float simulationTime, int iteraciones){
        /*
        Va a mostrar por pantalla de la siguiente manera
        [Reemplazar %d %f %s segun corresponda]
        
        SIMULACION TERMINADA - 50 ejecuciones de %d minutos cada una
        (Las siguientes afirmaciones son con un 90% de confianza)
        
        TIEMPO MEDIO EN COLA DE CADA TIPO DE VUELO
            .Vuelos livianos = (%f ; %f)
            .Vuelos medianos = (%f ; %f)
            .Vuelos pesados = (%f ; %f)
        
        TIEMPO MEDIO DE OCIOSIDAD DE CADA PISTA (En porcentaje al tiempo total de simulacion)
            .Pista 1 = (%f% ; %f%)
            .Pista 2 = (%f% ; %f%)
                .
                .
                .
            .Pista n = (%f% ; %f%)
        */
        
        System.out.println("SIMULACION TERMINADA - " +iteraciones +" ejecuciones de " +simulationTime +" minutos cada una\n"
                +"(Las siguientes afirmaciones son con un 90% de confianza)\n\n"

                +"TIEMPO MEDIO EN COLA DE CADA TIPO DE VUELO\n"
                    +"\t.Vuelos livianos = (" +getLInferior(tiemposColaLivianos) +" ; " +getLSuperior(tiemposColaLivianos) +")\n"
                    +"\t.Vuelos medianos = (" +getLInferior(tiemposColaMedianos) +" ; " +getLSuperior(tiemposColaMedianos) +")\n"
                    +"\t.Vuelos pesados = (" +getLInferior(tiemposColaPesados) +" ; " +getLSuperior(tiemposColaPesados) +")\n\n"
                
                +"TIEMPO MEDIO DE OCIOSIDAD DE CADA PISTA (En porcentaje al tiempo total de simulacion)"
        );
        
        int cantServers = ServersManagement.getServidoresArray().size();
        ArrayList<Float> aux = new ArrayList<>();
        
        for(int i=0; i<cantServers; i++){
            aux.clear(); //limpia el arraylist para una nueva iteracion
            for(int j=i; j<tiemposOcioServers.size(); j+=cantServers){
                aux.add(tiemposOcioServers.get(j));
            }
            System.out.println(
                    "\t.Pista " +(i+1) +" = (" +getLInferior(aux)/simulationTime*100 +" % ; " +getLSuperior(aux)/simulationTime*100 +" %)"
            );
        }
    }
}
