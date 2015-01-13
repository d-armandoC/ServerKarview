/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kradac.karview.netty;

import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Diego C
 */
public class pruebas {

    public static void main(String[] args) {
//        /tamaño 2
      String datos = "0420 PT46 D1,DF 12 $GPRMC,150453.00,A,0009.691460,S,07828.984606,W,0.0,0.0,030115,2.2,E,A*3F@@ 5965249800";
//       tamaño 3
//     String datos = "0420 PT46 D1,DF 12 $GPRMC,150453.00,A,0009.691460,S,07828.984606,W,0.0,0.0,030115,2.2,E,A*3F@@ 5965249800p0420 PT46 D1,DF 12 $GPRMC,150653.00,A,0009.894";
////     String datos = "0420     PT45 D1,DF 12 $GPRMC,022752.00,A,0056.207234,S,07911.093398,W,38.0,62.5,161014,1.4,E,A*330r0420     PT45 D1,DF 12 $GPRMC,022752.00,A,0056.207234,S,07911.093398,W,38.0,62.5,161014,1.4,E,A*33";
//                    0420     PT45 D1,DF 12 $GPRMC,022752.00,A,0056.207234,S,07911.093398,W,38.0,62.5,161014,1.4,E,A*330r0420     PT45 D1,DF 12 $GPRMC,022752.00,A,0056.207234,S,07911.093398,W,38.0,62.5,161014,1.4,E,A*33
        String[] dataTrama = datos.substring(5).split(",");
//            Formateamos la Trama
            boolean haveSpace = true;
            String cabezera = dataTrama[0].trim();//0420         9      AS36 D1
            String gpiodat = dataTrama[1].trim();//DF $GPRMC
            String gpiodata[] = gpiodat.split(" ");
            while (haveSpace) {
                cabezera = cabezera.replace("  ", " ");
                haveSpace = cabezera.contains("  ");
            }
          String dataHeader[] = cabezera.split(" ");
//          /Tamaño 2
          System.out.println("Tamaño: "+dataHeader.length);
          System.out.println("Equipo: "+dataHeader[0]);
         System.out.println("Gpio: "+gpiodata[0]);
         System.out.println("Evento: "+Short.parseShort(gpiodata[1]));
////          Tamaño 3
          System.out.println("Tamaño: "+dataHeader.length);
          System.out.println("Equipo: "+dataHeader[1]);
          System.out.println("Parametro: "+dataHeader[0]);
         System.out.println("Gpio: "+gpiodata[0]);
         System.out.println("Evemto: "+Short.parseShort(gpiodata[2]));
////////////////////////////////////////////////////////////////////////////////////////////////////////
//////        
////////       ///tamaño 2///0@80
////////        //
//      String datos1 = "0@80 10 CM11 203 14 GPRMC,160122.00,A,0359.47823,S,07912.13462,W,0.000,0.0,030115,,,D*5E 58194366";
//////      String datos1 = "0@80          9     CM12     459  14 GPRMC,202248.00,A,0400.94504,S,08001.25514,W,0.000,0.0,141014,,,D*5D    37594029";
//////            0420     PT46 D1,DE 13 $GPRMC,212821.00,A,0028.217573,S,07659.017946,W,0.0,0.0,121214,3.1,E,A*38@@   587224270   
////        String datos1="0@80 20 CM13 459 15 GPRMC,231406.00,A,0402.39108,S,07900.44480,W,34.243,238.91,221214,,,D*54 16702310";
//        String[] dataTrama1 = datos1.substring(5).split(",");
//            boolean haveSpace1 = true;
//            String cabezera1 = dataTrama1[0].trim();
//            while (haveSpace1) {
//                cabezera1 = cabezera1.replace("  ", " ");
//                haveSpace1 = cabezera1.contains("  ");
//            }
//            String dataHeader1[] = cabezera1.split(" ");
//            System.out.println("Tamaño: "+dataHeader1.length);
//            System.out.println("Gpio "+dataHeader1[2]);
//            System.out.println("Equipo "+dataHeader1[1]);
//            System.out.println("Parametro: "+Short.parseShort(dataHeader1[0]));
//            System.out.println("Evento: "+Short.parseShort(dataHeader1[3]));


    }

}
