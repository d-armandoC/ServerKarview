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
//         [0420     PT46 D1,DE 13 $GPRMC,145205.00,A,0028.232446,S,07659.018071,W,0.0,0.0,181114,3.1,E,A*3E@@   58
//3721760]
//
// [0420         9      AS28 D1,DF $GPRMC,145450.00,A,0358.206787,S,07912.636719,W,0.0,0.0,181114,1.0,E,A*3
        String datos = "0@80          9     CM10     459  14 GPRMC,215634.00,A,0326.65002,S,07957.58693,W,0.000,0.0,291014,,,A*52    55446007";
        String[] dataTrama = datos.split(",");
        String gpiodat = dataTrama[1].trim();
        String gpiodata[] = gpiodat.split(" ");
        System.out.println(gpiodata[1]);
//        System.out.println(Short.parseShort(dataHeader[1].substring(0, 1)));
    }
}
