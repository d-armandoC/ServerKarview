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
    
     public String convertNumberToHexadecimal(String numHex) {
         
        int conHex = Integer.parseInt(numHex);
        String binario = Integer.toBinaryString(conHex);
        
        return completeDigits(binario);
    }
     
      public String completeDigits(String binNum) {
        final int LONG_BITS = 9;
        for (int i = binNum.length(); i < LONG_BITS; i++) {
            binNum = "0" + binNum;
        }
        return binNum;
    }
      
//          private String convertirHEX(String numHex) {
//        if (isSKP) {
//            //String a Hex
//            int conHex = Integer.parseInt(numHex, 16);
//            //Hex a Binario
//            String binary = Integer.toBinaryString(conHex);
//            //Presentacion
//            return completeDigits(binary);
//        } else {
//            int dec = Integer.parseInt(numHex);
//            String binary = Integer.toBinaryString(dec);
//            return completeDigits(binary);
//        }
//    }
      
    public static void main(String[] args) {
//     pruebas obj= new pruebas();
//        System.out.println(obj.convertNumberToHexadecimal("DF"));
        System.out.println(Integer.parseInt("DF", 16));
    }
    
    
    
    
}
