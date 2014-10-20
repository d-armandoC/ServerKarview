/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kradac.karview.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/**
 *
 * @author
 */
public class MyDecoder extends ByteToMessageDecoder {

    private ByteBuf in = Unpooled.buffer();

    @Override
    protected void decode(ChannelHandlerContext chc, ByteBuf bb, List<Object> list) throws Exception {

        if (bb.readableBytes() < 2) {
            return;
        }
        bb.markReaderIndex();
        int length = bb.readChar();
        if (length > 150) {
            String data = "";
            while (bb.isReadable()) {
                data += (char) bb.readByte();
            }
            bb.clear();

            if (data.contains("OK") || data.contains("handshake")) {
                if (data.contains("handshake")) {
                    chc.channel().write("0%%at");
                }
                if (data.contains("OK")) {
                    System.out.println("Respuesta de Comando AT [" + data + "]");
                }
            } else {
                System.err.println("Datos incorrectos enviados al Servidor [" + data + "]");
                chc.channel().disconnect();
            }
        }

        if (bb.readableBytes() < length - 2) {
            bb.resetReaderIndex();
            return;
        }
//            bb.readBytes(length - 2).array();
        in.writeBytes(bb);
        in.discardReadBytes();
        in.retain();
        list.add(in);
//         list.add(bb.readBytes(length - 2));

    }

    protected void bypass(ByteBuf bb, List<Object> out) {
        in.writeBytes(bb);
        in.discardReadBytes();
        in.retain();
        out.add(in);
    }
//    

//    @Override
//    protected void decode(ChannelHandlerContext chc, ByteBuf bb, List<Object> list) throws Exception {
//        if (bb.readableBytes() < 2) {
//            return;
//        }
//        bb.markReaderIndex();
//        int length = bb.readChar();
//        if (length > 150) {
//            String data = "";
//            while (bb.isReadable()) {
//                data += (char) bb.readByte();
//            }
//            bb.clear();
//
//            if (data.contains("OK") || data.contains("handshake")) {
//                if (data.contains("handshake")) {
//                    chc.channel().write("0%%at");
//                }
//                if (data.contains("OK")) {
//                    System.out.println("Respuesta de Comando AT [" + data + "]");
//                }
//            } else {
//                System.err.println("Datos incorrectos enviados al Servidor [" + data + "]");
//                chc.channel().disconnect();
//            }
//        }
//
//        if (bb.readableBytes() < length - 2) {
//            bb.resetReaderIndex();
//            return;
//        }
//
//        list.add(bb.readBytes(length - 2));
//    }
}
