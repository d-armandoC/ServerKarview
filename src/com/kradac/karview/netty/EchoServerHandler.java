/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kradac.karview.netty;

import com.kradac.karview.entities.logic.EnvioCorreos;
import com.kradac.karview.entities.logic.Equipos;
import com.kradac.karview.entities.logic.SkyEventos;
import com.kradac.karview.entities.logic.UltimoDatoSkps;
import com.kradac.karview.entities.logic.Vehiculos;
import com.kradac.karview.entities.controllers.ComandosJpaController;
import com.kradac.karview.entities.controllers.DatoInvalidosJpaController;
import com.kradac.karview.entities.controllers.DatoSpksJpaController;
import com.kradac.karview.entities.controllers.EnvioCorreosJpaController;
import com.kradac.karview.entities.controllers.EquiposJpaController;
import com.kradac.karview.entities.controllers.EstadoGeocercaJpaController;
import com.kradac.karview.entities.controllers.GeocercaPuntosJpaController;
import com.kradac.karview.entities.controllers.GeocercaVehiculosJpaController;
import com.kradac.karview.entities.controllers.HistorialGeocercasJpaController;
import com.kradac.karview.entities.controllers.PersonasJpaController;
import com.kradac.karview.entities.controllers.SkyEventosJpaController;
import com.kradac.karview.entities.controllers.UltimoDatoSkpsJpaController;
import com.kradac.karview.entities.controllers.VehiculosJpaController;
import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import com.kradac.karview.entities.controllers.exceptions.PreexistingEntityException;
import com.kradac.karview.entities.historic.Comandos;
import com.kradac.karview.entities.historic.DatoInvalidos;
import com.kradac.karview.entities.historic.DatoSpks;
import com.kradac.karview.entities.historic.DatoSpksPK;
import com.kradac.karview.entities.historic.HistorialGeocercas;
import com.kradac.karview.entities.logic.EstadoGeocerca;
import com.kradac.karview.entities.logic.GeocercaPuntos;
import com.kradac.karview.entities.logic.GeocercaVehiculos;
import com.kradac.karview.entities.logic.Personas;
import com.kradac.karview.mail.AlertaMail;
import com.kradac.karview.window.Gui;
import com.kradac.karview.window.Utilities;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego C
 */
public class EchoServerHandler extends ChannelHandlerAdapter {

    private static final Logger logger = Logger.getLogger(
            EchoServerHandler.class.getName());
    ArrayList<Comandos> cmdSend;
//    private final ArrayList<Mensajes> smsSend;
    private String data;
    private boolean runTimerCmd;
    private boolean registered;
    private String auxDevice;
    private int timeCloseChannel;
    private Channel c;

/// datos de la Logica
    private final VehiculosJpaController vjc;
    private final EquiposJpaController ejc;
    private final UltimoDatoSkpsJpaController udsjc;
    private final SkyEventosJpaController sejc;
    private final EnvioCorreosJpaController ecjc;
    private final GeocercaVehiculosJpaController gcvh;
    private final GeocercaPuntosJpaController gcp;
    private final EstadoGeocercaJpaController egc;
    private final PersonasJpaController pc;

// Datos de la Historica
    private final DatoSpksJpaController dsjc;
    private final DatoInvalidosJpaController dijc;
    private final ComandosJpaController cjc;
    private final HistorialGeocercasJpaController hgc;

    private Vehiculos v;
    private Equipos e;
    private UltimoDatoSkps uds;
    private SkyEventos se;
    private final Utilities u;
    private final Timer t;

    public EchoServerHandler(Timer t) {
        this.u = new Utilities();
        this.t = t;
        this.cmdSend = new ArrayList<>();
        this.runTimerCmd = true;
        this.registered = false;
        this.timeCloseChannel = 3600;

        this.vjc = new VehiculosJpaController(Gui.getCpdb().choosePersistenceLogicOpen());
        this.ejc = new EquiposJpaController(Gui.getCpdb().choosePersistenceLogicOpen());
        this.udsjc = new UltimoDatoSkpsJpaController(Gui.getCpdb().choosePersistenceLogicOpen());
        this.sejc = new SkyEventosJpaController(Gui.getCpdb().choosePersistenceLogicOpen());
        this.ecjc = new EnvioCorreosJpaController(Gui.getCpdb().choosePersistenceLogicOpen());
        this.gcvh = new GeocercaVehiculosJpaController(Gui.getCpdb().choosePersistenceLogicOpen());
        this.gcp = new GeocercaPuntosJpaController(Gui.getCpdb().choosePersistenceLogicOpen());
        this.egc = new EstadoGeocercaJpaController(Gui.getCpdb().choosePersistenceLogicOpen());
        this.pc = new PersonasJpaController(Gui.getCpdb().choosePersistenceLogicOpen());

        this.dsjc = new DatoSpksJpaController(Gui.getCpdb().choosePersistenceHistoricOpen());
        this.dijc = new DatoInvalidosJpaController(Gui.getCpdb().choosePersistenceHistoricOpen());
        this.cjc = new ComandosJpaController(Gui.getCpdb().choosePersistenceHistoricOpen());
        this.hgc = new HistorialGeocercasJpaController(Gui.getCpdb().choosePersistenceHistoricOpen());

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        this.c = ctx.channel();
        ByteBuf buf = (ByteBuf) msg;
        String auxdata = "";
        while (buf.isReadable()) {
            byte aux = buf.readByte();
            char charVal = (char) aux;
            int valEnt = charVal;
            if (valEnt < 10) {
                auxdata += valEnt;
            } else if (valEnt == 10 || valEnt == 13) {
                auxdata += "@";
            } else {
                auxdata += charVal;
            }
        }
        this.data = auxdata;
        buf.clear();
        if (auxdata.indexOf("0@8000001") == 0) {
            System.out.println("Trama Conexion SKP+: [" + auxdata + "]");
            u.sendToFile(2, "skp+", this.data);
            tramaConecxion(u.clearDataConnection(this.data));
        } else if (auxdata.indexOf("0@80") == 0) {
            System.out.println("Trama Conexion SKP: [" + auxdata + "]");
            u.sendToFile(3, "skp", this.data);
            tramaConecxion(u.clearDataConnection(this.data));
        } else if (auxdata.indexOf("0150") == 0) {
            System.out.println("Respuesta Cmd: [" + auxdata + "]");
//                    processResponseComand(this.data.substring(5));
        } else if (auxdata.indexOf("0420") == 0) {
            System.out.println("Trama SKP: [" + data + "]");
            u.sendToFile(3, "skp", this.data);
            procesarSKP(this.data);
        } else if (auxdata.indexOf("0@8488") == 0) { // 0@8488ￌ
            System.out.println("Trama SKP+ -param: [" + auxdata + "]");
            u.sendToFile(3, "skp", this.data);
            tramaMinuto(this.data.substring(9));
        } else if (auxdata.indexOf("0@80") == 0) {
            System.out.println("Trama SKP+ +param: [" + auxdata + "]");
            u.sendToFile(3, "skp+", this.data);
            tramaMinuto(this.data.substring(9));
        } else {
            System.err.println("Trama sin Procesar: [" + auxdata + "]");
            if (registered) {
                dijc.create(new DatoInvalidos(1, new Date(), e.getEquipo(), this.data));
            } else {
                dijc.create(new DatoInvalidos(1, new Date(), "", this.data));
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        runTimerCmd = false;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//         Close the connection when an exception is raised.
        if (cause.toString().equals("io.netty.handler.timeout.ReadTimeoutException")) {
            dijc.create(new DatoInvalidos(7, new Date(), e.getEquipo(), this.data, cause.toString()));
        } else if (cause.toString().equals("java.io.IOException: Se ha forzado la interrupción de una conexión existente por el host remoto")
                || cause.toString().equals("java.io.IOException: An existing connection was forcibly closed by the remote host")) {
            dijc.create(new DatoInvalidos(8, new Date(), e.getEquipo(), this.data, cause.toString()));
        } else if (cause.toString().equals("java.io.IOException: Connection reset by peer")) {
            dijc.create(new DatoInvalidos(9, new Date(), e.getEquipo(), this.data, cause.toString()));
        } else {
            dijc.create(new DatoInvalidos(2, new Date(), e.getEquipo(), this.data, cause.toString()));
            System.out.println("Excepcion TCP Conexión [" + this.data + "] [" + cause.toString() + "]");
        }
        logger.log(Level.WARNING, "Unexpected exception from downstream.", cause.toString());
        runTimerCmd = false;
        ctx.close();
    }

    private void tramaConecxion(String device) {
        if (!registered) {
            e = ejc.findEquiposByEquipo(device);
            if (e == null) {
                auxDevice = device;
                System.out.println("Dato Enviado a Tabla de Invalidos por no estar registrado en el sistema [" + auxDevice + "].");
                dijc.create(new DatoInvalidos(3, new Date(), auxDevice, this.data, ""));
            } else {
                registered = true;
                uds = udsjc.findUltimoDatoSkpsByIdEquipo(e.getIdEquipo());
                v = vjc.findVehiculosByEquipo(e.getEquipo());
                if (v == null) {
                    System.out.println("No hay vehiculo asociado al Equipo [" + e.getEquipo() + "]");
                }
                if (uds == null) {
                    udsjc.create(new UltimoDatoSkps(new Date(), new Date(), 0.0, 0.0, 0.0, 0.0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, "", 0,
                            e, new SkyEventos(1)));
                } else {
                    try {
                        uds.setFechaHoraConex(new Date());
                        uds.setFechaHoraUltDato(new Date());
                        udsjc.edit(uds);
                    } catch (NonexistentEntityException ex) {
                        udsjc.create(new UltimoDatoSkps(new Date(), new Date(), 0.0, 0.0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, "", 0,
                                e, new SkyEventos(1)));
                    } catch (Exception ex) {
                        System.out.println("Excepcion al Editar Dato Conexion TCP [" + this.data + "] [" + ex.getMessage() + "]");
                        dijc.create(new DatoInvalidos(2, new Date(), e.getEquipo(), this.data, ex.getMessage()));
                    }
                }
//                processSendComand();
            }
        }
    }

    private void tramaMinuto(String trama) throws Exception {
        String[] dataTrama = trama.split(",");
        Calendar objCalDevice = u.validateDate(dataTrama[9], dataTrama[1].substring(0, dataTrama[1].lastIndexOf('.')), true);
        if (objCalDevice != null) {
            boolean haveSpace = true;
            String header = dataTrama[0].trim();
            while (haveSpace) {
                header = header.replace("  ", " ");
                haveSpace = header.contains("  ");
            }
            String dataHeader[] = header.split(" ");
            String gpio;
            if (dataHeader.length == 6 || dataHeader.length == 5) {
                se = sejc.findSkyEventosByParametro(Short.parseShort(dataHeader[0]));
                if (!registered) {
                    e = ejc.findEquiposByEquipo(dataHeader[1]);
                    if (e != null) {
                        registered = true;
                        v = vjc.findVehiculosByEquipo(e.getEquipo());
                        if (v == null) {
                            System.out.println("No hay vehiculo asociado al Equipo [" + e.getEquipo() + "]");
                        }
                    } else {
                        auxDevice = dataHeader[1];
                    }
                }
                gpio = u.convertNumberToHexadecimal(dataHeader[2]);
            } else {
                if (!registered) {
                    e = ejc.findEquiposByEquipo(dataHeader[0]);
                    if (e != null) {
                        registered = true;
                        v = vjc.findVehiculosByEquipo(e.getEquipo());
                        if (v == null) {
                            System.out.println("No hay vehiculo asociado al Equipo [" + e.getEquipo() + "]");
                        }
                    } else {
                        auxDevice = dataHeader[0];
                    }
                }
                gpio = u.convertNumberToHexadecimal(dataHeader[1]);
                se = sejc.findSkyEventosByEvento(Short.parseShort(dataHeader[2]));
                if (se == null) {
                    se = sejc.findSkyEventos(1);
                }
            }
            if (registered) {
                double latitud = u.convertLatLonSkp(dataTrama[3], dataTrama[4]);
                double longitud = u.convertLatLonSkp(dataTrama[5], dataTrama[6]);
                double speed = Math.rint(Double.parseDouble(dataTrama[7]) * 1.85 * 100) / 100;
                double course = Double.parseDouble(dataTrama[8]);
                if (se.getIdSkyEvento() == 10 || se.getIdSkyEvento() == 11) {
                    if (speed > 90) {
                        se = sejc.findSkyEventos(21);
                    }
                    if (speed > 60) {
                        se = sejc.findSkyEventos(12);
                    }
                }
                uds = udsjc.findUltimoDatoSkpsByIdEquipo(e.getIdEquipo());
                if (uds != null) {
                    try {
                        uds.setFechaHoraConex(new Date());
                        uds.setFechaHoraUltDato(new Date());
                        uds.setLatitud(latitud);
                        uds.setLongitud(longitud);
                        uds.setVelocidad(speed);
                        uds.setRumbo(course);
                        uds.setG1(Short.parseShort("" + gpio.charAt(8)));
                        uds.setG2(Short.parseShort("" + gpio.charAt(7)));
                        uds.setSal(Short.parseShort("" + gpio.charAt(6)));
                        uds.setBateria(Short.parseShort("" + gpio.charAt(5)));
                        uds.setV1(Short.parseShort("" + gpio.charAt(4)));
                        uds.setV2(Short.parseShort("" + gpio.charAt(3)));
                        uds.setGsm(Short.parseShort("" + gpio.charAt(2)));
                        uds.setGps(Short.parseShort("" + gpio.charAt(1)));
                        uds.setIgn(Short.parseShort("" + gpio.charAt(0)));
                        uds.setDireccion("");
                        uds.setIdEquipo(e);
                        uds.setIdSkyEvento(se);
                        udsjc.edit(uds);
                    } catch (Exception e) {
                        System.out.println("Problemas con el Objeto" + e.getMessage());
                    }
                }
                try {
                    dsjc.create(new DatoSpks(new DatoSpksPK(e.getIdEquipo(), objCalDevice.getTime(), objCalDevice.getTime(), se.getIdSkyEvento()), new Date(), latitud, longitud, speed, course,
                            Short.parseShort("" + gpio.charAt(8)),
                            Short.parseShort("" + gpio.charAt(7)),
                            Short.parseShort("" + gpio.charAt(6)),
                            Short.parseShort("" + gpio.charAt(5)),
                            Short.parseShort("" + gpio.charAt(4)),
                            Short.parseShort("" + gpio.charAt(3)),
                            Short.parseShort("" + gpio.charAt(2)),
                            Short.parseShort("" + gpio.charAt(1)),
                            Short.parseShort("" + gpio.charAt(0)),
                            0, ""));
                    if (se.getIdSkyEvento() == 12 || se.getIdSkyEvento() == 21) {
                        u.executeProcedureExcesoVelocidades(v.getIdVehiculo(), speed);
                    }
                    sendMails();
                    verificarGeocercas(latitud, longitud);
                } catch (PreexistingEntityException ex) {
                    System.out.println("Dato ya Existe [" + this.data + "]");
                    dijc.create(new DatoInvalidos(5, new Date(), e.getEquipo(), this.data));
                } catch (Exception ex) {
                    System.out.println("Excepcion TCP [" + this.data + "] [" + ex.getMessage() + "]");
                    dijc.create(new DatoInvalidos(2, new Date(), e.getEquipo(), this.data, ex.getMessage()));
                }
            } else {
                dijc.create(new DatoInvalidos(3, new Date(), auxDevice, this.data));
                System.out.println("No se encuentra registrado [" + this.data + "].");
            }
        } else {
            dijc.create(new DatoInvalidos(4, new Date(), e.getEquipo(), this.data));
            System.out.println("Poblemas de Fecha y Hora [" + this.data + "]");
        }
    }

    private void procesarSKP(String trama) {
        String[] dataTrama = trama.split(",");
        Calendar objCalDevice = u.validateDate(dataTrama[10], dataTrama[2].substring(0, dataTrama[2].lastIndexOf('.')), true);
        if (objCalDevice != null) {
            boolean haveSpace = true;
            String header = dataTrama[0].trim();
            String gpiodat = dataTrama[1].trim();
            String gpiodata[] = gpiodat.split(" ");

            while (haveSpace) {
                header = header.replace("  ", " ");
                haveSpace = header.contains("  ");
            }
            String dataHeader[] = header.split(" ");
            String gpio;
            if (dataHeader.length == 3 || dataHeader.length == 4) {
                se = sejc.findSkyEventosByParametro(Short.parseShort(gpiodata[1]));
                if (!registered) {
                    e = ejc.findEquiposByEquipo(dataHeader[1].substring(0, dataHeader[1].length()));
                    if (e != null) {
                        registered = true;
                        v = vjc.findVehiculosByEquipo(e.getEquipo());
                        if (v == null) {
                            System.out.println("No hay vehiculo asociado al Equipo [" + e.getEquipo() + "]");
                        }
                    } else {
                        auxDevice = dataHeader[1];
                    }
                }
                Utilities.isSKpat = true;
                gpio = u.convertNumberToHexadecimal(gpiodata[0]);
                Utilities.isSKpat = false;
            } else {

                if (!registered) {
                    e = ejc.findEquiposByEquipo(dataHeader[1].substring(0, dataHeader[1].length()));
                    if (e != null) {
                        registered = true;
                        v = vjc.findVehiculosByEquipo(e.getEquipo());
                        if (v == null) {
                            System.out.println("No hay vehiculo asociado al Equipo [" + e.getEquipo() + "]");
                        }
                    } else {
                        auxDevice = dataHeader[0];
                    }
                }
                gpio = u.convertNumberToHexadecimal(gpiodata[0]);
                System.out.println(Short.parseShort(dataHeader[1].substring(0, 1)));
                se = sejc.findSkyEventosByEvento(Short.parseShort(dataHeader[1].substring(0, 1)));
                if (se == null) {
                    se = sejc.findSkyEventos(1);
                }
            }
            if (registered) {
                double latitud = u.convertLatLonSkp(dataTrama[2], dataTrama[3]);
                double longitud = u.convertLatLonSkp(dataTrama[4], dataTrama[5]);
                double speed = Math.rint(Double.parseDouble(dataTrama[8]) * 1.85 * 100) / 100;
                double course = Double.parseDouble(dataTrama[9]);
                if (se.getIdSkyEvento() == 10 || se.getIdSkyEvento() == 11) {
                    if (speed > 90) {
                        se = sejc.findSkyEventos(21);
                    }
                    if (speed > 60) {
                        se = sejc.findSkyEventos(12);
                    }
                }
                uds = udsjc.findUltimoDatoSkpsByIdEquipo(e.getIdEquipo());
                if (uds != null) {
                    try {
                        uds.setFechaHoraConex(new Date());
                        uds.setFechaHoraUltDato(new Date());
                        uds.setLatitud(latitud);
                        uds.setLongitud(longitud);
                        uds.setVelocidad(speed);
                        uds.setRumbo(course);
                        uds.setG1(Short.parseShort("" + gpio.charAt(8)));
                        uds.setG2(Short.parseShort("" + gpio.charAt(7)));
                        uds.setSal(Short.parseShort("" + gpio.charAt(6)));
                        uds.setBateria(Short.parseShort("" + gpio.charAt(5)));
                        uds.setV1(Short.parseShort("" + gpio.charAt(4)));
                        uds.setV2(Short.parseShort("" + gpio.charAt(3)));
                        uds.setGsm(Short.parseShort("" + gpio.charAt(2)));
                        uds.setGps(Short.parseShort("" + gpio.charAt(1)));
                        uds.setIgn(Short.parseShort("" + gpio.charAt(0)));
                        uds.setDireccion("");
                        uds.setIdEquipo(e);
                        uds.setIdSkyEvento(se);
                        udsjc.edit(uds);
                    } catch (Exception e) {
                        System.out.println("Problemas en la asignación de datos al Pojo" + e.getMessage());
                    }
                }
                try {
                    dsjc.create(new DatoSpks(new DatoSpksPK(e.getIdEquipo(), objCalDevice.getTime(), objCalDevice.getTime(), se.getIdSkyEvento()), new Date(), latitud, longitud, speed, course,
                            Short.parseShort("" + gpio.charAt(8)),
                            Short.parseShort("" + gpio.charAt(7)),
                            Short.parseShort("" + gpio.charAt(6)),
                            Short.parseShort("" + gpio.charAt(5)),
                            Short.parseShort("" + gpio.charAt(4)),
                            Short.parseShort("" + gpio.charAt(3)),
                            Short.parseShort("" + gpio.charAt(2)),
                            Short.parseShort("" + gpio.charAt(1)),
                            Short.parseShort("" + gpio.charAt(0)),
                            0, ""));
                    if (se.getIdSkyEvento() == 12 || se.getIdSkyEvento() == 21) {
                        u.executeProcedureExcesoVelocidades(v.getIdVehiculo(), speed);
                    }
                    sendMails();
                    verificarGeocercas(latitud, longitud);
                } catch (PreexistingEntityException ex) {
                    System.out.println("Dato ya Existe [" + this.data + "]");
                    dijc.create(new DatoInvalidos(5, new Date(), e.getEquipo(), this.data));
                } catch (Exception ex) {
                    System.out.println("Excepcion TCP [" + this.data + "] [" + ex.getMessage() + "]");
                    dijc.create(new DatoInvalidos(2, new Date(), e.getEquipo(), this.data, ex.getMessage()));
                }
            } else {
                dijc.create(new DatoInvalidos(3, new Date(), auxDevice, this.data));
                System.out.println("No se encuentra registrado:  [" + this.data + "].");
            }
        } else {
            dijc.create(new DatoInvalidos(4, new Date(), e.getEquipo(), this.data));
            System.out.println("Poblemas de Fecha y Hora [" + this.data + "]");
        }
    }

    private void processResponseComand(String trama) {
        try {
            if (cmdSend.size() > 0) {
                Comandos cmd = cmdSend.get(0);
                cmd.setIdTipoEstadoCmd(3);
                cmd.setRespuesta(trama.replace(" ", ""));
                cmd.setFechaHoraRespuesta(new Date());
                cjc.edit(cmd);
                cmdSend.remove(0);
            }
        } catch (Exception ex) {
            System.out.println("Al Editar Respuesta del Comando: " + ex.getMessage());
        }
    }

    private void processSendComand() {
        if (runTimerCmd) {
            if (timeCloseChannel > 0) {
                timeCloseChannel -= 5;
                ///Verifico si tiene un comando de envio el equipo que llego en la trama 
                Comandos cmd = cjc.getComandosToSend(e.getIdEquipo());
                System.out.println(cmd.getComando());
                if (cmd != null) {
                    this.c.write(cmd.getComando());
                    cmdSend.add(cmd);
                    cmd.setIdTipoEstadoCmd(2);
                    cmd.setFechaHoraEnvio(new Date());
                    try {
                        cjc.edit(cmd);
                    } catch (Exception ex) {
                        System.out.println("Al Editar Envio de Comando: " + ex.getMessage());
                    }
                }
                this.t.newTimeout(new TimerTask() {
                    @Override
                    public void run(Timeout tmt) throws Exception {
                        processSendComand();
                    }
                }, 5, TimeUnit.SECONDS);
            } else {
                c.close();
            }
        }
    }

    private void sendMails() {
        List<EnvioCorreos> lem = ecjc.findEnvioCorreosEntities();
        for (EnvioCorreos envioMails : lem) {
            if (envioMails.getSkyEventos().getIdSkyEvento() == se.getIdSkyEvento()) {
                AlertaMail am = new AlertaMail(e.getEquipo(), envioMails.getPersonas().getCorreo(), se.getSkyEvento(), se.getMensaje(), envioMails.getPersonas().getApellidos() + " " + envioMails.getPersonas().getNombres());
                am.start();
            }
        }
    }

    public void verificarGeocercas(double lat, double lon) throws Exception {
        //Obtenemos el Id del Vehiculo
        Vehiculos vh = vjc.findVehiculosByEquipo(e.getEquipo());
        int idV = vh.getIdVehiculo();
        GeocercaVehiculos lgv = gcvh.findGeocercaVehiculos(idV);
        Personas personas = pc.findPersonas(vh.getIdPersona().getIdPersona());
        List<GeocercaPuntos> listaPuntos = gcp.listaGeocercaPuntos(lgv.getGeocercaVehiculosPK().getIdGeocerca());
        if (listaPuntos != null) {
            System.out.println("lista de puntos");
            EstadoGeocerca estG = egc.findEstadoGeocerca(idV);
            if (Utilities.pnpoly(listaPuntos.size(), datx(listaPuntos), daty(listaPuntos), lat, lon)) {
                if (estG == null) {
                    EstadoGeocerca esgc = new EstadoGeocerca();
                    esgc.setIdVehiculo(idV);
                    esgc.setEstado(1);
                    egc.create(esgc);
                    hgc.create(new HistorialGeocercas(lgv.getGeocercaVehiculosPK().getIdGeocerca(), idV, (short) 1, new Date()));
                } else {
                    if (estG.getEstado() == 1) {
                        EstadoGeocerca esgc = new EstadoGeocerca();
                        esgc.setIdVehiculo(idV);
                        esgc.setEstado(0);
                        egc.edit(esgc);
                        if (!personas.getCorreo().equals("")) {
                            AlertaMail am = new AlertaMail(e.getEquipo(), personas.getCorreo(), "Informe de Geocerca", "Su Equipo acaba de Salir de la Geocerca", personas.getApellidos() + " " + personas.getNombres());
                            am.start();
                            hgc.create(new HistorialGeocercas(lgv.getGeocercaVehiculosPK().getIdGeocerca(), idV, (short) 1, new Date()));
                        } else {
                            System.out.println("El Propietario de el equipo: " + e.getEquipo() + " no Cuenta con Correo para el Informe de Geocerca");
                            hgc.create(new HistorialGeocercas(lgv.getGeocercaVehiculosPK().getIdGeocerca(), idV, (short) 1, new Date()));
                        }

                    }
                }
            } else {
                if (estG.getEstado() == 0) {
//                    System.out.println("Fuera de la Geocerca");
                    EstadoGeocerca esgc = new EstadoGeocerca();
                    esgc.setIdVehiculo(idV);
                    esgc.setEstado(1);
                    egc.edit(esgc);
                    if (!personas.getCorreo().equals("")) {
                        AlertaMail am = new AlertaMail(e.getEquipo(), personas.getCorreo(), "Informe de Geocerca", "Su Equipo acaba de Salir de la Geocerca", personas.getApellidos() + " " + personas.getNombres());
                        am.start();
                        hgc.create(new HistorialGeocercas(lgv.getGeocercaVehiculosPK().getIdGeocerca(), idV, (short) 0, new Date()));
                    } else {
                        System.out.println("El Propietario de el equipo: " + e.getEquipo() + " no Cuenta con Correo para el Informe de Geocerca");
                        hgc.create(new HistorialGeocercas(lgv.getGeocercaVehiculosPK().getIdGeocerca(), idV, (short) 0, new Date()));
                    }

                }
            }
        }
    }

    public double[] datx(List<GeocercaPuntos> list) {
        double x[] = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            x[i] = list.get(i).getLatitud();
        }
        return x;
    }

    public double[] daty(List<GeocercaPuntos> list) {
        double y[] = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            y[i] = list.get(i).getLongitud();
        }
        return y;
    }

}
