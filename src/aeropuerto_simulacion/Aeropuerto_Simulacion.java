package aeropuerto_simulacion;

import aeropuerto_simulacion.FuncionesAuxiliares.PrepararSimulacion;
import aeropuerto_simulacion.Eventos.Evento;
import aeropuerto_simulacion.FuncionesAuxiliares.Estadisticas;

public class Aeropuerto_Simulacion {
    public static void main(String[] args) {
        
	Evento inminente;
	final float simulationTime = 10080; //minutos en una semana
    final int iteraciones = 50; //cantidad de iteraciones del programa

        for(int i=0; i<iteraciones; i++){
            //prepara valores necesarios para arrancar
            PrepararSimulacion.start(simulationTime); 

            //parte iterativa
            while(FEL.getFel().peekFel().getTipoDeEvento() != Evento.TipoDeEvento.FIN){
                inminente = FEL.getFel().pollFel(); //inminente = cabeza de la FEL
                inminente.doEvento();               //realiza evento segun sea arribo o salida
            }

            //Ejecuto evento FIN (la razon por la que corto la parte iterativa)

            /*Por que no uso un do while entonces?
            Porque mi parte iterativa remueve y mi condicion solo compara, entonces
            cuando quiera fijarme si lo siguiente va a ser evento FIN, este ya habra
            sido ejecutado y no se encontrara, produciendo comportamiento inesperado
            */

            FEL.getFel().pollFel().doEvento();

            //Acumulo estadisticas
            Estadisticas.acumData();
        }
        
        //Estadisticas time!!!
        Estadisticas.print(simulationTime, iteraciones);
    }
}
