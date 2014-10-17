/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kradac.karview.thread;

import com.kradac.karview.window.Gui;
import javax.swing.JOptionPane;

/**
 *
 * @author Dalton
 */
public class ThreadTimeStart extends Thread {

    private int c = 0;
    private boolean started = false;

    @Override
    public void run() {
        try {
            int tiempo = Gui.getCpdb().getAutostart();

            while (true) {
                try {
                    if (c == tiempo - 1 && !started) {
                        Gui.start();
                        started = true;
                    }

                    if (c == 10800) { // -> 3 horas
                        Gui.getCpdb().openOtherPersistence();
                    }

                    if (c == 21600) { // -> 3 horas
                        Gui.getCpdb().closePersistenceOpen();
                        c = 0;
                    }

                    c++;
                    sleep(1000);
                } catch (InterruptedException ex) {
                    //Logger.getLogger(ThreadTimeStart.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Hilo de Tiempo interrumpido [" + ex.getMessage() + "]");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El tiempo de Inicio Automatico no es un número.");
            System.exit(0);
        }
    }

}
