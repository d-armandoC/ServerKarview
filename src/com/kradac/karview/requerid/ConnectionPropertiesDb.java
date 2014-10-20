/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kradac.karview.requerid;

import com.kradac.karview.window.Utilities;
import com.kradac.karview.entities.controllers.ConfiguracionesJpaController;
import com.kradac.karview.window.FrameFileConnection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

/**
 *
 * @author KRADAC
 */
public class ConnectionPropertiesDb {

    private String hostname;
    private int port;
    private String username;
    private String password;
    private String database;
    private String databasehistoric;
    private int autostart;
    private String path;
    private String city;
    private int limitConDb;
    private int countSend;
    private EntityManagerFactory emfLogic;
    private EntityManagerFactory emfHistoric;
    private EntityManagerFactory emfLogic2;
    private EntityManagerFactory emfHistoric2;
    private ConfiguracionesJpaController cjc;
    private int persistenceLogic;
    private int persistenceHistoric;

    public ConnectionPropertiesDb() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(Utilities.get() + "/config/connection.properties");
            ResourceBundle rb = new PropertyResourceBundle(fis);

            this.hostname = rb.getString("hostname");
            this.port = Integer.parseInt(rb.getString("port"));
            this.username = rb.getString("username");
            this.password = rb.getString("password");
            this.database = rb.getString("database");
            this.databasehistoric = rb.getString("databasehistoric");
            this.autostart = Integer.parseInt(rb.getString("autostart"));
            this.path = rb.getString("pathfiles");
            this.city = rb.getString("city");
            this.limitConDb = Integer.parseInt(rb.getString("limitConDb"));
            this.countSend = 0;

            try {
                this.emfLogic = Persistence.createEntityManagerFactory("ServerKarviewPU1", getMappingDinamic(this.database));
                this.emfHistoric = Persistence.createEntityManagerFactory("ServerKarviewPU2", getMappingDinamic(this.databasehistoric));
                
                this.persistenceLogic = 1;
                this.persistenceHistoric = 1;
                this.cjc = new ConfiguracionesJpaController(this.choosePersistenceLogicOpen());
            } catch (PersistenceException e) {
                System.out.println(e.getMessage());
                System.out.println(e.getCause().getMessage());
                JOptionPane.showMessageDialog(null, "Datos de Conexión con el SGBD son Invalidos, o los Servicios necesarios no estan Levantados.");
                FrameFileConnection ffc = new FrameFileConnection();
                ffc.setVisible(true);
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "El Archivo de Conexión con el SGDB no se encuentra.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Lectura/Escritura del archivo de Conexión ha fallado.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Puerto de Conexión con el SGBD no es un numero.");
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lectura/Escritura del archivo de Conexión ha fallado.");
            }
        }
    }

    /**
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * @param hostname the hostname to set
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the database
     */
    public String getDatabase() {
        return database;
    }

    /**
     * @param database the database to set
     */
    public void setDatabase(String database) {
        this.database = database;
    }

    /**
     * @return the autostart
     */
    public int getAutostart() {
        return autostart;
    }

    /**
     * @param autostart the autostart to set
     */
    public void setAutostart(int autostart) {
        this.autostart = autostart;
    }

    /**
     * @return the databasehistoric
     */
    public String getDatabasehistoric() {
        return databasehistoric;
    }

    /**
     * @param databasehistoric the databasehistoric to set
     */
    public void setDatabasehistoric(String databasehistoric) {
        this.databasehistoric = databasehistoric;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the emfLogic
     */
    public EntityManagerFactory getEmfLogic() {
        return emfLogic;
    }

    /**
     * @param emfLogic the emfLogic to set
     */
    public void setEmfLogic(EntityManagerFactory emfLogic) {
        this.emfLogic = emfLogic;
    }

    /**
     * @return the emfHistoric
     */
    public EntityManagerFactory getEmfHistoric() {
        return emfHistoric;
    }

    /**
     * @param emfHistoric the emfHistoric to set
     */
    public void setEmfHistoric(EntityManagerFactory emfHistoric) {
        this.emfHistoric = emfHistoric;
    }

    private HashMap<String, String> getMappingDinamic(String database) {
        HashMap<String, String> con = new HashMap<>();
        con.put("javax.persistence.jdbc.url", "jdbc:mysql://" + this.getHostname() + ":" + this.getPort() + "/" + database);
        con.put("javax.persistence.jdbc.password", this.getPassword());
        con.put("javax.persistence.jdbc.user", this.getUsername());
        return con;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the limitConDb
     */
    public int getLimitConDb() {
        return limitConDb;
    }

    /**
     * @param limitConDb the limitConDb to set
     */
    public void setLimitConDb(int limitConDb) {
        this.limitConDb = limitConDb;
    }

    /**
     * @return the countSend
     */
    public int getCountSend() {
        return countSend;
    }

    /**
     * @param countSend the countSend to set
     */
    public void setCountSend(int countSend) {
        this.countSend = countSend;
    }

    /**
     * @return the cjc
     */
    public ConfiguracionesJpaController getCjc() {
        return cjc;
    }

    /**
     * @param cjc the cjc to set
     */
    public void setCjc(ConfiguracionesJpaController cjc) {
        this.cjc = cjc;
    }

    /**
     * @return the emfLogic2
     */
    public EntityManagerFactory getEmfLogic2() {
        return emfLogic2;
    }

    /**
     * @param emfLogic2 the emfLogic2 to set
     */
    public void setEmfLogic2(EntityManagerFactory emfLogic2) {
        this.emfLogic2 = emfLogic2;
    }

    /**
     * @return the emfHistoric2
     */
    public EntityManagerFactory getEmfHistoric2() {
        return emfHistoric2;
    }

    /**
     * @param emfHistoric2 the emfHistoric2 to set
     */
    public void setEmfHistoric2(EntityManagerFactory emfHistoric2) {
        this.emfHistoric2 = emfHistoric2;
    }

    public void openOtherPersistence() {
        if (!this.emfLogic.isOpen()) {
            this.setEmfLogic(Persistence.createEntityManagerFactory("ServerKarviewPU", getMappingDinamic(this.getDatabase())));
            this.setEmfHistoric(Persistence.createEntityManagerFactory("ServerKarviewPU2", getMappingDinamic(this.getDatabasehistoric())));
            this.setPersistenceLogic(1);
            this.setPersistenceHistoric(1);
        }

        try {
            if (!this.emfLogic2.isOpen()) {
                this.setEmfLogic2(Persistence.createEntityManagerFactory("ServerKarviewPU", getMappingDinamic(this.getDatabase())));
                this.setEmfHistoric2(Persistence.createEntityManagerFactory("ServerKarviewPU2", getMappingDinamic(this.getDatabasehistoric())));
                this.setPersistenceLogic(2);
                this.setPersistenceHistoric(2);
            }
        } catch (NullPointerException e) {
            this.setEmfLogic2(Persistence.createEntityManagerFactory("ServerKarviewPU", getMappingDinamic(this.getDatabase())));
            this.setEmfHistoric2(Persistence.createEntityManagerFactory("ServerKarviewPU2", getMappingDinamic(this.getDatabasehistoric())));
            this.setPersistenceLogic(2);
            this.setPersistenceHistoric(2);
        }
    }

    public void closePersistenceOpen() {
        if (this.getPersistenceLogic() == 1) {
            this.getEmfLogic2().close();
            this.getEmfHistoric2().close();
        } else {
            this.getEmfLogic().close();
            this.getEmfHistoric().close();
        }
    }

    public EntityManagerFactory choosePersistenceLogicOpen() {
        if (this.getPersistenceLogic() == 1) {
            return this.getEmfLogic();
        } else {
            return this.getEmfLogic2();
        }
    }

    public EntityManagerFactory choosePersistenceHistoricOpen() {
        if (this.getPersistenceHistoric() == 1) {
            return this.getEmfHistoric();
        } else {
            return this.getEmfHistoric2();
        }
    }


    /**
     * @return the persistenceLogic
     */
    public int getPersistenceLogic() {
        return persistenceLogic;
    }

    /**
     * @param persistenceLogic the persistenceLogic to set
     */
    public void setPersistenceLogic(int persistenceLogic) {
        this.persistenceLogic = persistenceLogic;
    }

    /**
     * @return the persistenceHistoric
     */
    public int getPersistenceHistoric() {
        return persistenceHistoric;
    }

    /**
     * @param persistenceHistoric the persistenceHistoric to set
     */
    public void setPersistenceHistoric(int persistenceHistoric) {
        this.persistenceHistoric = persistenceHistoric;
    }
}
