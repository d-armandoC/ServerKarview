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
import com.kradac.karview.entities.controllers.EstandarVehiculosJpaController;
import com.kradac.karview.entities.controllers.GeocercaPuntosJpaController;
import com.kradac.karview.entities.controllers.GeocercaVehiculosJpaController;
import com.kradac.karview.entities.controllers.HistorialGeocercasJpaController;
import com.kradac.karview.entities.controllers.MantenimientovehiculoJpaController;
import com.kradac.karview.entities.controllers.PersonasJpaController;
import com.kradac.karview.entities.controllers.RegistrosMantenimientoJpaController;
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
import com.kradac.karview.entities.logic.EstandarVehiculos;
import com.kradac.karview.entities.logic.GeocercaPuntos;
import com.kradac.karview.entities.logic.GeocercaVehiculos;
import com.kradac.karview.entities.logic.Mantenimientovehiculo;
import com.kradac.karview.entities.logic.RegistrosMantenimiento;
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
import java.util.GregorianCalendar;
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
    private final MantenimientovehiculoJpaController vmc;
    private final EstandarVehiculosJpaController estv;
// Datos de la Historica
    private final DatoSpksJpaController dsjc;
    private final DatoInvalidosJpaController dijc;
    private final ComandosJpaController cjc;
    private final HistorialGeocercasJpaController hgc;
    private final RegistrosMantenimientoJpaController rgm;

    private Vehiculos v;
    private Equipos e;
    private UltimoDatoSkps uds;
    private SkyEventos se;
    private final Utilities u;
    private final Timer t;
    private int tipoEquipoGPRS;

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
        this.vmc = new MantenimientovehiculoJpaController(Gui.getCpdb().choosePersistenceLogicOpen());
        this.estv = new EstandarVehiculosJpaController(Gui.getCpdb().choosePersistenceLogicOpen());
        this.rgm = new RegistrosMantenimientoJpaController(Gui.getCpdb().choosePersistenceLogicOpen());

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
        System.out.println("Trama que llega: " + this.data);
        if (this.data.indexOf("0@80") == 0) {
            System.out.println("Trama SKP+ +param: [" + auxdata + "]");
            u.sendToFile(3, "skp+", this.data);
            tramaMinuto(this.data.substring(5));
        } else if (this.data.indexOf("0@8000001") == 0) {
            System.out.println("Trama Conexión SKP+: [" + auxdata + "]");
            u.sendToFile(2, "skp+", this.data);
            tramaConecxion(u.clearDataConnection(this.data));
             tipoEquipoGPRS=1;
        } else if (this.data.indexOf("0@80") == 0) {
            System.out.println("Trama Conexión SKP: [" + auxdata + "]");
            u.sendToFile(2, "skp", this.data);
            tramaConecxion(u.clearDataConnection(this.data));
             tipoEquipoGPRS=2;
        } else if (this.data.indexOf("0150") == 0) {
            System.out.println("Respuesta Cmd: [" + auxdata + "]");
            processResponseComand(this.data.substring(5));
        } else if (this.data.indexOf("0420") == 0) {
            System.out.println("Trama SKP: [" + data + "]");
            u.sendToFile(3, "skp", this.data);
            procesarSKP(this.data.substring(5));
        } else if (this.data.indexOf("0@8 488") == 0) { // 0@8 488 ￌ
            System.out.println("Trama SKP+ -param: [" + auxdata + "]");
            u.sendToFile(3, "skp", this.data);
            tramaMinuto(this.data.substring(9));
        } else {
            if (this.data.indexOf("0@80") == 0) {
                System.out.println("Aqui");
            } else if (this.data.indexOf("0@80") == 0) {

            } else if (this.data.indexOf("0 @80") == 0) {
                System.err.println("Trama sin Procesar: [" + auxdata + "]");
                if (registered) {
                    dijc.create(new DatoInvalidos(1, new Date(), e.getEquipo(), this.data));
                    System.out.println("entra aqui 1");
                } else {
                    System.out.println("entra aqui 2");
                    dijc.create(new DatoInvalidos(1, new Date(), "", this.data));
                }
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
        switch (cause.toString()) {
            case "io.netty.handler.timeout.ReadTimeoutException":
                dijc.create(new DatoInvalidos(7, new Date(), e.getEquipo(), this.data, cause.toString()));
                break;
            case "java.io.IOException: Se ha forzado la interrupción de una conexión existente por el host remoto":
            case "java.io.IOException: An existing connection was forcibly closed by the remote host":
                dijc.create(new DatoInvalidos(8, new Date(), e.getEquipo(), this.data, cause.toString()));
                break;
            case "java.io.IOException: Connection reset by peer":
                dijc.create(new DatoInvalidos(9, new Date(), e.getEquipo(), this.data, cause.toString()));
                break;
            default:
                dijc.create(new DatoInvalidos(2, new Date(), e.getEquipo(), this.data, cause.toString()));
                System.out.println("Excepcion TCP Conexión [" + this.data + "] [" + cause.toString() + "]");
                break;
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
                processSendComand();
            }
        }
    }

    private void tramaMinuto(String trama) throws Exception {
        String[] dataTrama = trama.split(",");
        Calendar objCalDevice = u.validateDate(dataTrama[9], dataTrama[1].substring(0, dataTrama[1].lastIndexOf('.')), true);
        if (objCalDevice != null) {
            boolean haveSpace = true;
            String cabezera = dataTrama[0].trim();
            while (haveSpace) {
                cabezera = cabezera.replace("  ", " ");
                haveSpace = cabezera.contains("  ");
            }
            String dataHeader[] = cabezera.split(" ");
            String gpio = "";
            if (dataHeader.length == 5) {
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
                gpio = u.convertNumberToHexadecimal(dataHeader[2], false);
                se = sejc.findSkyEventosByParametro(Short.parseShort(dataHeader[0]));
                if (se == null) {
                    System.out.println("No se encuentra el evento en la busqueda por Parametro");
                }
            } else {
                System.out.println("Trama inválida [" + this.data + "]");
                dijc.create(new DatoInvalidos(3, new Date(), e.getEquipo(), this.data));
            }
            if (registered) {
                double latitud = u.convertLatLonSkp(dataTrama[3], dataTrama[4]);
                double longitud = u.convertLatLonSkp(dataTrama[5], dataTrama[6]);
                double speed = Math.rint(Double.parseDouble(dataTrama[7]) * 1.85 * 100) / 100;
                double course = Double.parseDouble(dataTrama[8]);
                if (se.getIdSkyEvento() == 9 || se.getIdSkyEvento() == 10) {
                    if (speed > 90) {
                        se = sejc.findSkyEventos(20);
                    }
                    if (speed > 60) {
                        se = sejc.findSkyEventos(11);
                    }
                }
                try {
                    System.out.println(objCalDevice.getTime());
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
//                    notificarMantenimiento();
//                    registrosMantenimiento();
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
        //Determinamos si la fecha es valida
        if (objCalDevice != null) {
            //Formateamos la Trama
            boolean haveSpace = true;
            String cabezera = dataTrama[0].trim();
            String gpiodat = dataTrama[1].trim();
            String gpiodata[] = gpiodat.split(" ");
            while (haveSpace) {
                cabezera = cabezera.replace("  ", " ");
                haveSpace = cabezera.contains("  ");
            }
            String dataHeader[] = cabezera.split(" ");
            String gpio = "";
            // si es 2 obtenemos el evevento en length 2
            if (dataHeader.length == 2) {
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
                gpio = u.convertNumberToHexadecimal(gpiodata[0], true);
                se = sejc.findSkyEventosByEvento(Short.parseShort(gpiodata[1]));
                if (se == null) {
                    System.out.println("No se ha encontrado el evento en la búsqueda por evento");
                }
            } else if (dataHeader.length == 3) {
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
                gpio = u.convertNumberToHexadecimal(gpiodata[0], true);
                try {
                    se = sejc.findSkyEventosByParametroAndEvent(Short.parseShort(dataHeader[0]), Short.parseShort(gpiodata[2]));
                    if (se == null) {
                        se = sejc.findSkyEventosByEvento(Short.parseShort(gpiodata[2]));
                    }
                    if (se == null) {
                        System.out.println("No se ha encontrado el evento en la búsqueda por Parametro y Evento");
                    }
                } catch (Exception e) {
                    System.out.println("No se encontraron el Evento" + e.getLocalizedMessage());
                }

            } else {
                System.out.println("Trama invalida [" + this.data + "]");
                dijc.create(new DatoInvalidos(5, new Date(), e.getEquipo(), this.data));
            }
            // si esta registrado obtenemos su ubicacion
            if (registered) {
                double latitud = u.convertLatLonSkp(dataTrama[4], dataTrama[5]);
                double longitud = u.convertLatLonSkp(dataTrama[6], dataTrama[7]);
                double speed = Math.rint(Double.parseDouble(dataTrama[8]) * 1.85 * 100) / 100;
                double course = Double.parseDouble(dataTrama[9]);
                if (se.getIdSkyEvento() == 9 || se.getIdSkyEvento() == 10) {
                    if (speed > 90) {
                        se = sejc.findSkyEventos(20);
                    }
                    if (speed > 60) {
                        se = sejc.findSkyEventos(11);
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
//                    notificarMantenimiento();
//                    registrosMantenimiento();
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

//    public void notificarMantenimiento() {
//        try {
//            Calendar fechaProxima;
//            List<Mantenimientovehiculo> listaMant = vmc.obtenerRegistrosHoy(new Date());
//            for (Mantenimientovehiculo mantenimiento : listaMant) {
//                EstandarVehiculos estandarVehiculo = estv.findEstandarVehiculos(mantenimiento.getMantenimientovehiculoPK().getIdEstandarVehiculo());
//                AlertaMail am = new AlertaMail(e.getEquipo(), v.getIdPersona().getCorreo(), "Deberia Chequear  : " + estandarVehiculo.getEstandarVehiculo(), v.getIdPersona().getApellidos() + " " + v.getIdPersona().getNombres());
//                am.start();
//                fechaProxima = new GregorianCalendar(new Date().getYear() + 1900, new Date().getMonth(), new Date().getDate());
//                fechaProxima.add(Calendar.DATE, mantenimiento.getMdias());
//                mantenimiento.setFechaConfig(fechaProxima.getTime());
//                vmc.edit(mantenimiento);
//            }
//        } catch (Exception e) {
//            System.out.println("Exception" + e.getLocalizedMessage());
//        }
//    }
//
//    public void registrosMantenimiento() {
//        try {
//            final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
//            List<RegistrosMantenimiento> listaRegistro = rgm.obtenerRegistroMantenimiento(new Date());
//            for (RegistrosMantenimiento registro : listaRegistro) {
//                String tipoNotificacion = "";
//                switch (registro.getRegistrosMantenimientoPK().getIdRegistro()) {
//                    case 1:
//                        tipoNotificacion = "Revición de SOAT";
//                        break;
//                    case 2:
//                        tipoNotificacion = "Revición de MATRICULA";
//                        break;
//                    case 3:
//                        tipoNotificacion = "Revición de SEGURO";
//                        break;
//                }
//                AlertaMail mail = new AlertaMail(e.getEquipo(), v.getIdPersona().getCorreo(), "Deberia realizar una : " + tipoNotificacion, v.getIdPersona().getApellidos() + " " + v.getIdPersona().getNombres(), true);
//                mail.start();
//                Date fechaReg = registro.getFechaRegistro();// fecha registrada
//                Date fechaVenc = registro.getFechaVencimiento();// fecha de vencimiento
//                Calendar fechaRegistro = new GregorianCalendar(fechaReg.getYear() + 1900, fechaReg.getMonth(), fechaReg.getDate());
//                Calendar fechaProxima = new GregorianCalendar(fechaVenc.getYear() + 1900, fechaVenc.getMonth(), fechaVenc.getDate());
//                java.sql.Date fechaRegistrada = new java.sql.Date(fechaRegistro.getTimeInMillis());// Obtenemos los milisegundos de Fecha Registrada
//                long diferencia = (fechaVenc.getTime() - fechaRegistrada.getTime()) / MILLSECS_PER_DAY; //Diferencia entre fechaVencimiento-fechaRegistrada(cuantos dias hay...)
//                fechaProxima.add(Calendar.DATE, (int) diferencia);
//                registro.setFechaRegistro(fechaVenc);
//                registro.setFechaVencimiento(fechaProxima.getTime());
//                rgm.edit(registro);
//            }
//        } catch (Exception e) {
//            System.out.println("Exception" + e.getLocalizedMessage());
//
//        }
//    }

    private void processResponseComand(String trama) {
        System.out.println("Respuesta del Comando: "+trama);
        try {
            if (cmdSend.size() > 0) {
                Comandos cmd = cmdSend.get(0);
                cmd.setIdTipoEstadoCmd(3);
                System.out.println("coomando >>>>> "+trama.replace(" ", ""));
                cmd.setRespuesta(trama.replace(" ", ""));
                cmd.setFechaHoraRespuesta(new Date());
                System.out.println(cmd.getComando());
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
                if (cmd != null) {
                    this.c.write(cmd.getComando()+"%%"+tipoEquipoGPRS);
                    cmdSend.add(cmd);
                    cmd.setIdTipoEstadoCmd(2);
                    cmd.setFechaHoraEnvio(new Date());
                    String msj = "Enviando [" + cmd.getComando() + "] -> [" + e.getEquipo() + "]";
                    System.out.println(msj);
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

    public void verificarGeocercas(double lat, double lon) {
        try {
            EstadoGeocerca estG;
            int idV = v.getIdVehiculo();
            List<GeocercaVehiculos> lgv = gcvh.findGeocercaVehiculos(idV);
            if (lgv != null) {
                String correo = v.getIdPersona().getCorreo();
                String person = v.getIdPersona().getApellidos() + " " + v.getIdPersona().getNombres();
                for (GeocercaVehiculos lgv1 : lgv) {
                    List<GeocercaPuntos> listaPuntos = gcp.listaGeocercaPuntos(lgv1.getGeocercaVehiculosPK().getIdGeocerca());
                    if (listaPuntos != null) {
                        if (Utilities.pnpoly(listaPuntos.size(), dat_x_Latitud(listaPuntos), dat_y_Longitud(listaPuntos), lat, lon)) {
                            estG = egc.findEstadoGeocerca(idV);
                            if (estG == null) {
                                estG = new EstadoGeocerca();
                                estG.setIdVehiculo(idV);
                                estG.setEstado(1);
                                egc.create(estG);
                                hgc.create(new HistorialGeocercas(lgv1.getGeocercaVehiculosPK().getIdGeocerca(), idV, (short) 1, new Date()));
                                if (!correo.equals("")) {
                                    System.out.println("Enviando Geocerca");
                                    AlertaMail am1 = new AlertaMail(e.getEquipo(), correo, "Informe de Geo cerca", "Su Equipo acaba de Entrar a la Geo cerca", person, "Geocercas");
                                    am1.start();
                                } else {
                                    System.out.println("El Propietario de el equipo: " + e.getEquipo() + " no Cuenta con Correo para el Informe de Geo cerca");
                                }
                            } else {
                                if (estG.getEstado() == 1) {
                                    estG = new EstadoGeocerca();
                                    estG.setIdVehiculo(idV);
                                    estG.setEstado(0);
                                    egc.edit(estG);
                                    if (!correo.equals("")) {
                                        System.out.println("Enviando Geo cerca");
                                        AlertaMail am1 = new AlertaMail(e.getEquipo(), correo, "Informe de Geo cerca", "Su Equipo acaba de Entrar a la Geo cerca", person, "Geo cercas");
                                        am1.start();
                                        hgc.create(new HistorialGeocercas(lgv1.getGeocercaVehiculosPK().getIdGeocerca(), idV, (short) 1, new Date()));
                                    } else {
                                        System.out.println("El Propietario de el equipo: " + e.getEquipo() + " no Cuenta con Correo para el Informe de Estado de Geo cerca");
                                        hgc.create(new HistorialGeocercas(lgv1.getGeocercaVehiculosPK().getIdGeocerca(), idV, (short) 1, new Date()));
                                    }
                                }
                            }
                        } else {
                            estG = egc.findEstadoGeocerca(idV);
                            if (estG != null) {
                                if (estG.getEstado() == 0) {
                                    estG = new EstadoGeocerca();
                                    estG.setIdVehiculo(idV);
                                    estG.setEstado(1);
                                    egc.edit(estG);
                                    if (!correo.equals("")) {
                                        System.out.println("Enviando Geo cerca");
                                        AlertaMail am1 = new AlertaMail(e.getEquipo(), correo, "Informe de Geo cerca", "Su Equipo acaba de Salir de la Geo cerca", person, "Geo cercas");
                                        am1.start();
                                        hgc.create(new HistorialGeocercas(lgv1.getGeocercaVehiculosPK().getIdGeocerca(), idV, (short) 0, new Date()));
                                    } else {
                                        System.out.println("El Propietario de el equipo: " + e.getEquipo() + " no Cuenta con Correo para el Informe de Geo cerca");
                                        hgc.create(new HistorialGeocercas(lgv1.getGeocercaVehiculosPK().getIdGeocerca(), idV, (short) 0, new Date()));
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("NO hay puntos de esta Geocerca");
                    }
                }
            } else {
                System.out.println("Vehiculo no tiene Geocercas");
            }
        } catch (Exception e) {
            System.out.println("Error de Retorno de Objeto:" + e.getLocalizedMessage());
        }
    }

    public double[] dat_x_Latitud(List<GeocercaPuntos> list) {
        double x[] = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            x[i] = list.get(i).getLatitud();
        }
        return x;
    }

    public double[] dat_y_Longitud(List<GeocercaPuntos> list) {
        double y[] = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            y[i] = list.get(i).getLongitud();
        }
        return y;
    }

}
