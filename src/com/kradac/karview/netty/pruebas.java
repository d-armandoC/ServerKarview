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
////        /tamaño 2
//      String datos = "0420         0      PT45 D1,DE  7 $GPRMC,180221.00,A,0056.658981,S,07914.933485,W,0.0,0.0,241214,1.5,E,A*35@@  4260103550";
////       tamaño 3
////     String datos = "0420     PT46 D1,DE 13 $GPRMC,211820.00,A,0028.217962,S,07659.018604,W,0.0,0.0,121214,3.1,E,A*30@@   587224270";
////     String datos = "0420     PT45 D1,DF 12 $GPRMC,022752.00,A,0056.207234,S,07911.093398,W,38.0,62.5,161014,1.4,E,A*330r0420     PT45 D1,DF 12 $GPRMC,022752.00,A,0056.207234,S,07911.093398,W,38.0,62.5,161014,1.4,E,A*33";
////                    0420     PT45 D1,DF 12 $GPRMC,022752.00,A,0056.207234,S,07911.093398,W,38.0,62.5,161014,1.4,E,A*330r0420     PT45 D1,DF 12 $GPRMC,022752.00,A,0056.207234,S,07911.093398,W,38.0,62.5,161014,1.4,E,A*33
//        String[] dataTrama = datos.substring(9).split(",");
////            Formateamos la Trama
//            boolean haveSpace = true;
//            String cabezera = dataTrama[0].trim();//0420         9      AS36 D1
//            String gpiodat = dataTrama[1].trim();//DF $GPRMC
//            String gpiodata[] = gpiodat.split(" ");
//            while (haveSpace) {
//                cabezera = cabezera.replace("  ", " ");
//                haveSpace = cabezera.contains("  ");
//            }
//          String dataHeader[] = cabezera.split(" ");
//////          /Tamaño 2
////          System.out.println("Tamaño: "+dataHeader.length);
////          System.out.println("Equipo: "+dataHeader[0]);
////         System.out.println("Gpio: "+gpiodata[0]);
////         System.out.println("Evento: "+Short.parseShort(gpiodata[1]));
////          Tamaño 3
//          System.out.println("Tamaño: "+dataHeader.length);
//          System.out.println("Equipo: "+dataHeader[1]);
//          System.out.println("Parametro: "+dataHeader[0]);
//         System.out.println("Gpio: "+gpiodata[0]);
//         System.out.println("Evemto: "+Short.parseShort(gpiodata[2]));
////////////////////////////////////////////////////////////////////////////////////////////////////////
//////        
////////       ///tamaño 2///0@80
//////        //
////      String datos1 = "0@80         10     CM11     203  14 GPRMC,043224.00,A,0359.47694,S,07912.13378,W,0.000,0.0,241214,,,D*53    58022426";
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
//////            
//        
////    

//                Calendar fechaProxima = new GregorianCalendar(new Date().getYear() + 1900, new Date().getMonth(), new Date().getDate());
//            fechaProxima.add(Calendar.DATE, 0);
//        System.out.println(fechaProxima.getTime());
        
        final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
        Calendar calendar = new GregorianCalendar(2014, 12 - 1, 22);
        java.sql.Date fecha = new java.sql.Date(calendar.getTimeInMillis());
        long diferencia = (new Date().getTime() - fecha.getTime()) / MILLSECS_PER_DAY;
        System.out.println(diferencia);

    }

}
