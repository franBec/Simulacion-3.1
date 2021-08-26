package aeropuerto_simulacion.FuncionesAuxiliares;

import aeropuerto_simulacion.Items.ItemLiviano;
import aeropuerto_simulacion.Items.ItemMediano;
import aeropuerto_simulacion.Items.ItemPesado;
import aeropuerto_simulacion.Servers.Server;
import aeropuerto_simulacion.Servers.ServerCabotaje;
import aeropuerto_simulacion.Servers.ServerInternacional;
import aeropuerto_simulacion.Servers.ServerPrivado;
import aeropuerto_simulacion.Servers.ServersManagement;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Estadisticas {
    
    private static final float Z_ALFA_SOBRE_2 = (float) 1.645;   //90% de confanza

    //Tiempos en cola por cada tipo de vuelo
    private static ArrayList<Float> tiemposColaLivianos=new ArrayList<>();
    private static ArrayList<Float> tiemposColaMedianos=new ArrayList<>();
    private static ArrayList<Float> tiemposColaPesados=new ArrayList<>();
    
    //Tiempos en transito por cada tipo de vuelo
    private static ArrayList<Float> tiemposTransitoLivianos=new ArrayList<>();
    private static ArrayList<Float> tiemposTransitoMedianos=new ArrayList<>();
    private static ArrayList<Float> tiemposTransitoPesados=new ArrayList<>();
    
    //Tiempo ocioso por cada tipo de pista
    private static ArrayList<Float> tiemposOcioPrivado=new ArrayList<>();
    private static ArrayList<Float> tiemposOcioCabotaje=new ArrayList<>();
    private static ArrayList<Float> tiemposOcioInternacional=new ArrayList<>();
    
    public static void acumData(){
        tiemposColaLivianos.add(ItemLiviano.getTiempoAcumCola()/ItemLiviano.getCantidadItemsLiviano());
        tiemposColaMedianos.add(ItemMediano.getTiempoAcumCola()/ItemMediano.getCantidadItemsMediano());
        tiemposColaPesados.add(ItemPesado.getTiempoAcumCola()/ItemPesado.getCantidadItemsPesado());
        
        tiemposTransitoLivianos.add(ItemLiviano.getTiempoAcumTransito()/ItemLiviano.getCantidadItemsLiviano());
        tiemposTransitoMedianos.add(ItemMediano.getTiempoAcumTransito()/ItemMediano.getCantidadItemsMediano());
        tiemposTransitoPesados.add(ItemPesado.getTiempoAcumTransito()/ItemPesado.getCantidadItemsPesado());
        
        float acumTiempoPriv=0, acumTiempoCab=0, acumTiempoInt=0;
        
        Iterator<Server> it = ServersManagement.getServidoresArray().iterator();
        while(it.hasNext()){
            Server s = it.next();
            switch(s.getTipoDeServer()){
                case PRIVADO:
                    acumTiempoPriv += s.getAcumTiempoOcio();
                    break;                   
                case CABOTAJE:
                    acumTiempoCab += s.getAcumTiempoOcio();
                    break;                
                case INTERNACIONAL:
                    acumTiempoInt += s.getAcumTiempoOcio();
                    break;
            }
        }
        tiemposOcioPrivado.add(acumTiempoPriv/ServerPrivado.getCantidadServersPrivado());
        tiemposOcioCabotaje.add(acumTiempoCab/ServerCabotaje.getCantidadServersCabotaje());
        tiemposOcioInternacional.add(acumTiempoInt/ServerInternacional.getCantidadServersInternacional());
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
        
        TIEMPO MEDIO DE TRANSITO DE CADA TIPO DE VUELO
            .Vuelos livianos = (%f ; %f)
            .Vuelos medianos = (%f ; %f)
            .Vuelos pesados = (%f ; %f)
        
        TIEMPO MEDIO DE OCIOSIDAD DE CADA TIPO DE PISTA (En porcentaje al tiempo total de simulacion)
            .Pista/s privadas = (%f% ; %f%)
            .Pista/s de cabotaje = (%f% ; %f%)
            .Pista/s internacionales = (%f% ; %f%)
        */
        
        System.out.println(
                
                "SIMULACION TERMINADA - " +iteraciones +" ejecuciones de " +simulationTime +" minutos cada una\n"
                +"(Las siguientes afirmaciones son con un 90% de confianza)\n\n"

                +"TIEMPO MEDIO EN COLA DE CADA TIPO DE VUELO\n"
                    +"\t.Vuelos livianos = (" +getLInferior(tiemposColaLivianos) +" ; " +getLSuperior(tiemposColaLivianos) +")\n"
                    +"\t.Vuelos medianos = (" +getLInferior(tiemposColaMedianos) +" ; " +getLSuperior(tiemposColaMedianos) +")\n"
                    +"\t.Vuelos pesados = (" +getLInferior(tiemposColaPesados) +" ; " +getLSuperior(tiemposColaPesados) +")\n\n"
                
                +"TIEMPO MEDIO DE TRANSITO DE CADA TIPO DE VUELO\n"
                    +"\t.Vuelos livianos = (" +getLInferior(tiemposTransitoLivianos) +" ; " +getLSuperior(tiemposTransitoLivianos) +")\n"
                    +"\t.Vuelos medianos = (" +getLInferior(tiemposTransitoMedianos) +" ; " +getLSuperior(tiemposTransitoMedianos) +")\n"
                    +"\t.Vuelos pesados = (" +getLInferior(tiemposTransitoPesados) +" ; " +getLSuperior(tiemposTransitoPesados) +")\n\n"
                
                +"TIEMPO MEDIO DE OCIOSIDAD DE CADA TIPO DE PISTA (En porcentaje al tiempo total de simulacion)\n"
                    +"\t.Pista/s privadas = (" +getLInferior(tiemposOcioPrivado)/simulationTime*100 +" % ; " +getLSuperior(tiemposOcioPrivado)/simulationTime*100 +" %)\n"
                    +"\t.Pista/s de cabotaje = (" +getLInferior(tiemposOcioCabotaje)/simulationTime*100 +" % ; " +getLSuperior(tiemposOcioCabotaje)/simulationTime*100 +" %)\n"
                    +"\t.Pista/s internacionales = (" +getLInferior(tiemposOcioInternacional)/simulationTime*100 +" % ; " +getLSuperior(tiemposOcioInternacional)/simulationTime*100 +" %)" 
        );   
    }
}
