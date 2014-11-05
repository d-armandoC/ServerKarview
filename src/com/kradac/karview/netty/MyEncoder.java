/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kradac.karview.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 *
 * @author 
 */
public class MyEncoder extends MessageToByteEncoder<Object> {
       @Override
       protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf buf) throws Exception {
        if (msg instanceof String) {
            String msj = String.valueOf(msg);
            String [] data = msj.split("%%");
            msj = data[1];
            if (Integer.parseInt(data[0]) == 2) {
                System.out.println("pas1");
                buf.writeByte(0x00);
                buf.writeByte(msj.length() + 4 + 2 + 1); //Aumenta un 1 por el 0 que se aumenta
                buf.writeByte(0x00);
                buf.writeByte(0x01);
                buf.writeByte(0x04);
                buf.writeByte(0x00);
                buf.writeByte(0x00);
                buf.writeBytes(msj.getBytes());
            } else {
                System.out.println("pas2");
                buf.writeByte(0x00);
                buf.writeByte(msj.length() + 4 + 2);
                buf.writeByte(0x00);
                buf.writeByte(0x01);
                buf.writeByte(0x04);
                buf.writeByte(0x00);
                buf.writeBytes(msj.getBytes());
            }            
        } 
        }

    }
    
//    @Override
//    protected void encode(ChannelHandlerContext chc, Object i, ByteBuf bb) throws Exception {
//        System.out.println("dentro del MyEncoder");
//        String msg = String.valueOf(i);
//        bb.writeByte(0x00);
//        bb.writeByte(msg.length() + 4 + 2 + 1);
//        bb.writeByte(0x00);
//        bb.writeByte(0x01);
//        bb.writeByte(0x04);
//        bb.writeByte(0x00);
//        bb.writeByte(0x00);
//        bb.writeBytes(msg.getBytes());
//    }
//}
