/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kradac.karview.netty;

/**
 *
 * @author Diego C
 */
public class pruebas  {
    public static void main(String[] args) {
//        ///tamaño 2
////      String datos = "0420     PT46 D1,DE 30 $GPRMC,074121.00,A,0028.227156,S,07659.013842,W,0.0,0.0,081214,3.1,E,A*39@@   584932980";
//      //// tamaño 3
//     String datos = "0420         1      PT45 D1,DB  7 $GPRMC,092205.00,A,0054.152787,S,07838.208999,W,0.0,0.0,081214,1.9,E,A*38@@  4184270650";
//   //                 0420     PT45 D1,DF 12 $GPRMC,022752.00,A,0056.207234,S,07911.093398,W,38.0,62.5,161014,1.4,E,A*330r0420     PT45 D1,DF 12 $GPRMC,022752.00,A,0056.207234,S,07911.093398,W,38.0,62.5,161014,1.4,E,A*33
//        String[] dataTrama = datos.substring(9).split(",");
//            //Formateamos la Trama
//            boolean haveSpace = true;
//            String cabezera = dataTrama[0].trim();//0420         9      AS36 D1
//            String gpiodat = dataTrama[1].trim();//DF $GPRMC
//            String gpiodata[] = gpiodat.split(" ");
//            while (haveSpace) {
//                cabezera = cabezera.replace("  ", " ");
//                haveSpace = cabezera.contains("  ");
//            }
//          String dataHeader[] = cabezera.split(" ");
//          ///Tamaño 2
////          System.out.println("Tamaño: "+dataHeader.length);
////          System.out.println("Equipo: "+dataHeader[0]);
////         System.out.println("Gpio: "+gpiodata[0]);
////         System.out.println("Paramero: "+Short.parseShort(gpiodata[1]));
//         // Tamaño 3
//          System.out.println("Tamaño: "+dataHeader.length);
//          System.out.println("Equipo: "+dataHeader[1]);
//          System.out.println("Parametro: "+dataHeader[0]);
//         System.out.println("Gpio: "+gpiodata[0]);
//         System.out.println("Evemto: "+Short.parseShort(gpiodata[2]));
////////////////////////////////////////////////////////////////////////////////////////////////
        
       ///tamaño 2
      String datos1 = "0@80         19     CM11     203  14 GPRMC,070312.00,A,0359.47706,S,07912.13854,W,0.000,0.0,201114,,,A*5A    51742311";
      //// tamaño 3
     //String datos1 = "0420         1      PT45 D1,DB  7 $GPRMC,092205.00,A,0054.152787,S,07838.208999,W,0.0,0.0,081214,1.9,E,A*38@@  4184270650";
   //                 0420     PT45 D1,DF 12 $GPRMC,022752.00,A,0056.207234,S,07911.093398,W,38.0,62.5,161014,1.4,E,A*330r0420     PT45 D1,DF 12 $GPRMC,022752.00,A,0056.207234,S,07911.093398,W,38.0,62.5,161014,1.4,E,A*33
        String[] dataTrama1 = datos1.substring(9).split(",");
            boolean haveSpace1 = true;
            String cabezera1 = dataTrama1[0].trim();
            while (haveSpace1) {
                cabezera1 = cabezera1.replace("  ", " ");
                haveSpace1 = cabezera1.contains("  ");
            }
            String dataHeader1[] = cabezera1.split(" ");
            String gpio1 = null;
            System.out.println("Tamaño: "+dataHeader1.length);
            System.out.println("Gpio "+dataHeader1[2]);
            System.out.println("Parametro: "+Short.parseShort(dataHeader1[0]));
            System.out.println("Evento: "+Short.parseShort(dataHeader1[3]));
            
            
    }
}
