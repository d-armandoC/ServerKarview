/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kradac.karview.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timer;

/**
 * @author 
 * @description Echoes back any received data from a client.
 */
public class EchoServer {

    private final int port;
    private final int timeout;
    private ChannelFuture f;
    private final Timer t;

    public EchoServer(int port, int timeout) {
        this.port = port;
        this.timeout = timeout;
        this.t = new HashedWheelTimer();
    }

    public void run() throws Exception {
        // Configure the server.
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ReadTimeoutHandler(timeout * 60));
                            ch.pipeline().addLast(new MyDecoder());
                            ch.pipeline().addLast(new MyEncoder());
                            ch.pipeline().addLast(
//                                    new LoggingHandler(LogLevel.INFO),
                                    new EchoServerHandler(t));
                        }
                    });

            // Start the server.
            f = b.bind(port).sync();
            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public void stop() {
        f.channel().close();
    }
}
