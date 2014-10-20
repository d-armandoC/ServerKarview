/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kradac.karview.thread;

import com.kradac.karview.mail.AlertaMail;
import com.kradac.karview.window.Gui;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author 
 */
public class ThreadCountConnectionDb extends Thread {

    @Override
    public void run() {
        while (true) {
            try {
                Connection conexion = DriverManager.getConnection("jdbc:mysql://" + Gui.getCpdb().getHostname() + "/" + Gui.getCpdb().getDatabase(), Gui.getCpdb().getUsername(), Gui.getCpdb().getPassword());
                Statement s = conexion.createStatement();
                ResultSet rs = s.executeQuery("show processlist");
                int c = 0;
                while (rs.next()) {
                    c++;
                }
                Gui.lblCountConecctionBd.setText("" + c);
                conexion.close();
                if (c > Gui.getCpdb().getLimitConDb()) {
                    AlertaMail am = new AlertaMail(c);
                    am.start();
                }
                sleep(60000);
            } catch (InterruptedException ex) {
                System.out.println("Hilo Interrumpido de Cantida de Conexiones: " + ex.getMessage());
            } catch (SQLException ex) {
                try {
                    Gui.lblCountConecctionBd.setText("Sin Conexión con la BD");
                    AlertaMail am = new AlertaMail("diego.cale92@gmail.com", "Poblemas de Conexion con la BD", ex.getMessage());
                    am.start();
                    sleep(60000);
                } catch (InterruptedException ex1) {
                    System.out.println("Hilo Interrumpido de Cantidade Conexiones SQL:" + ex1.getMessage());
                }
            }
        }
    }

}
