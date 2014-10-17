/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.thread;

import com.kradac.karview.netty.EchoServer;
import com.kradac.karview.window.Gui;
import java.net.BindException;
import javax.swing.JOptionPane;

/**
 *
 * @author Dalton
 */
public class ThreadNettySkp extends Thread {
    
    private final int port;
    private final int timeout;
    private EchoServer es;

    public ThreadNettySkp(int port, int timeout) {
        this.port = port;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        try {
            es = new EchoServer(this.port, this.timeout);
            System.out.println(this.port);
            getEs().run();
        } catch (BindException ex) {
            JOptionPane.showMessageDialog(null, "El Puerto TCP [" + this.port + "] esta en uso por otra Aplicación.");
            Gui.stop();
        } catch (Exception ex) {
            System.out.println("Excepción en Hilo Skp [" + ex.getMessage() + "]");
            //Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the es
     */
    public EchoServer getEs() {
        return es;
    }
}
