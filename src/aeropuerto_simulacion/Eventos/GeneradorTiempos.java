package aeropuerto_simulacion.Eventos;

import aeropuerto_simulacion.Items.Item;
import java.util.Random;

public abstract class GeneradorTiempos{
    private static Random random = new Random(System.currentTimeMillis());
    
    //variables usadas en la generacion variables aleatorias
    private static final float MU_EXP = 30;       //mu de la Distribucion Exponencial
    private static final float LI_UNI = 10;       //Limite Inferior de la Distribucion Uniforme
    private static final float LS_UNI = 20;       //Limite Superior de la Distribucion Uniforme
    private static final float MU_NOR = 120;      //mu de la Distribucion Normal
    private static final float VRZA_NOR = 30;     //varianza de la Distribucion Normal

    public static boolean isHoraPico(float cloak){
        /*Si el modulo de cloak cae en estos intervalos, se considera hora pico
        
        from 7hs to 9hs = from 420min to 540min
        from 20hs to 22hs = from 1200min to 1320min
        */
        
        float i = cloak%1440;
        return (i >= 420 && i <= 540) || (i >= 1200 && i <= 1320);
    }
    
    public static float varExponencialGenerator(float mu){
        float rnd;
        
        do{
        rnd = random.nextFloat(); //genera random entre [0.0 y 1.0)
        }while(rnd==0); //si rnd llega a dar 0.0, return seria 0.0 y eso es un valor no deseado para quien usa esta funcion
        
        return (float) (-mu * Math.log(1-rnd)); 
    }
    
    public static float varUniformeGenerator(float li, float ls){
        float rnd = random.nextFloat(); //genera random entre [0.00 y 1.0)
        return li+(ls-li)*rnd;
    }
    
    public static float varNormalGenerator(float mu, float vrza){
        
        /*
        Normalizo 12 variables aleatorias independientes uniformes distribuidas entre 0 y 1
        
            mu_z = ((a+b)/2)*N = ((0+1)/2)*12 = 6
            vrza_z = (((b-a)^2)/12)*12 = (b-a)^2 = (1-0)^2 = 1
            des_z = raiz(vrza_z) = 1
        
            Z' = (Z-mu_z)/des_z = (Z-6)/1 = Z-6
        
            return Z' * raiz(VRZA_NOR) + MU_NOR
        */
        
        float z = 0;   
        for(int i = 0; i<12; i++)
            z += random.nextFloat();
        
        float z_prima = z-6;
        return (float) (z_prima * Math.sqrt(vrza) + mu);
    }
   
    public static float getTiempoEntreArribos(Item.TipoDeItem i, float cloak){
        int rnd = random.nextInt(99); //genera random entre 0 y 99 inclusive
        switch(i){
            case LIVIANO:
                if(isHoraPico(cloak)){
                    if(rnd<35) return 40;
                    else return 50;     
                }
                else{
                    if(rnd<25) return 60;
                    else return 70;   
                }
                
            case MEDIANO:
                if(isHoraPico(cloak)){
                    if(rnd<50) return 10;
                    else{
                        if(rnd<85) return 20;
                        else return 30;
                    }
                }
                else{
                    if(rnd<30)
                        return 20;
                    else{
                        if(rnd<70) return 30;
                        else return 40;
                    }
                }
               
            case PESADO:
                if(isHoraPico(cloak)){
                    if(rnd<40) return 60;
                    else return 90;                   
                }
                else{
                    if(rnd<50) return 120;
                    else return 180;
                }
                   
       }
        return 0; //nunca se deberia llegar aca
   }
    
    public static float getTiempoDeClasifcacion(Item.TipoDeItem i){
        switch(i){
            case LIVIANO:
                return varExponencialGenerator(MU_EXP);
            case MEDIANO:
                return varUniformeGenerator(LI_UNI, LS_UNI);
            case PESADO:
                return varNormalGenerator(MU_NOR, VRZA_NOR);                
        }
        return 0; //nunca deberia llegar aca
    }
}