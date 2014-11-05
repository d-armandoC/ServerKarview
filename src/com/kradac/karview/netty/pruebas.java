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
public class pruebas {

    public static void main(String[] args) {
        String datos = "0@80          9     CM10     459  14 GPRMC,215634.00,A,0326.65002,S,07957.58693,W,0.000,0.0,291014,,,A*52    55446007";
        String[] dataTrama = datos.split(",");
        String gpiodat = dataTrama[1].trim();
        String gpiodata[] = gpiodat.split(" ");
//        System.out.println(gpiodata[1]);
////        System.out.println(Short.parseShort(dataHeader[1].substring(0, 1)));
    }
}
