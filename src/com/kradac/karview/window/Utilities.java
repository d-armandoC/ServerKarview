/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kradac.karview.window;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author
 */
public class Utilities {


    //Directorio Actual
    private static File WORKING_DIRECTORY;

    //Formatos de Fecha y Hora
    public SimpleDateFormat formatoFechaSave = new SimpleDateFormat("yyyy-MM-dd");
    public SimpleDateFormat formatoHoraSave = new SimpleDateFormat("HH:mm:ss");
    public SimpleDateFormat formatoFechaTexto = new SimpleDateFormat("ddMMyyHHmmss");

    /**
     * Devuelve el path actual del JAR
     *
     * @return
     */
    public static File get() {
        if (WORKING_DIRECTORY == null) {

            String Recurso = Utilities.class.getSimpleName() + ".class";
            try {
                URL url = Utilities.class.getResource(Recurso);
                switch (url.getProtocol()) {
                    case "file": {
                        File f = new File(url.toURI());
                        do {
                            f = f.getParentFile();
                        } while (!f.isDirectory());
                        WORKING_DIRECTORY = f;
                        break;
                    }
                    case "jar": {
                        String expected = "!/" + Recurso;
                        String s = url.toString();
                        s = s.substring(4);
                        s = s.substring(0, s.length() - expected.length());
                        File f = new File(new URL(s).toURI());
                        do {
                            f = f.getParentFile();
                        } while (!f.isDirectory());
                        WORKING_DIRECTORY = f;
                        break;
                    }
                }
            } catch (MalformedURLException | URISyntaxException e) {
                WORKING_DIRECTORY = new File(".");
            }
        }
        return WORKING_DIRECTORY;
    }

    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * Limpia la Trama de Conexion para obtener el nombre del Equipo
     *
     * @param in
     * @return
     */
    public String clearDataConnection(String in) {
        in = in.substring(9, in.length());
        Pattern patron = Pattern.compile("[ ]+");
        Matcher encaja = patron.matcher(in);
        return encaja.replaceAll("");
    }

    /**
     * Transforma un número a formato Hexadecimal
     *
     * @param numHex
     * @return
     */
     int conHex=0;
      public String convertNumberToHexadecimal(String numHex, boolean isHexadecimal) {
        if (isHexadecimal) {
            conHex = Integer.parseInt(numHex, 16);
        } else {
            conHex = Integer.parseInt(numHex);
        }
        String binario = Integer.toBinaryString(conHex);
        return completeDigits(binario);
    }
    
    /**
     * Completa el número de BITS faltantes
     *
     * @param binNum
     * @return
     */
    public String completeDigits(String binNum) {
        final int LONG_BITS = 9;
        for (int i = binNum.length(); i < LONG_BITS; i++) {
            binNum = "0" + binNum;
        }
        return binNum;
    }

    /**
     * Valida la Fecha y Hora, retorna un valor true: correcta o false:
     * incorrecta
     *
     * @param fecha
     * @param hora
     * @param isSkp
     * @return
     */
    public Calendar validateDate(String fecha, String hora, boolean isSkp) {
        try {
            Calendar objCalNow = new GregorianCalendar();
            objCalNow.setTime(formatoFechaTexto.parse(fecha.concat(hora)));
            if (isSkp) {
                objCalNow.add(Calendar.HOUR, -5);
            }

            //Limite Inferior de Fecha y Hora
            Calendar lowerLimit = new GregorianCalendar();
            lowerLimit.setTime(formatoFechaTexto.parse("010114000001"));

            //Limite Superior de Fecha y Hora -> Toma la Fecha del servidor y le suma + 2 horas
            Calendar upperLimit = new GregorianCalendar();
            upperLimit.add(Calendar.HOUR, 2);

            if (objCalNow.getTime().compareTo(upperLimit.getTime()) <= 0 && objCalNow.getTime().compareTo(lowerLimit.getTime()) >= 0) {
                return objCalNow;
            } else {
                return null;
            }
        } catch (ParseException | NumberFormatException e) {
            return null;
        }
    }

    public Calendar dateTimeCountPerson(String data) {
        //data = "2703162824"; //Contador de pasajeros
        //data = "170127031706"; //Alarma de Contador de Pasajeros, los 4 primeros caracters no son parte de la fecha
        String year = getDate().substring(2, 4);
        Calendar objCalNow = new GregorianCalendar();
        try {
            if (data.length() == 10) {
                objCalNow.setTime(formatoFechaTexto.parse(data.substring(0, 4) + year + data.substring(4, 10)));
            } else {
                objCalNow.setTime(formatoFechaTexto.parse(data.substring(4, 8) + year + data.substring(8, 12) + "00"));
            }
            return objCalNow;
        } catch (ParseException ex) {
            objCalNow.setTime(new Date());
            return objCalNow;
        }
    }

    public Calendar dateTimeDisplay(String data) {
        //data = "21082014132310";
        Calendar objCalNow = new GregorianCalendar();
        try {
            objCalNow.setTime(formatoFechaTexto.parse(data.substring(0, 4) + data.substring(6, 8) + data.substring(8, data.length())));
            return objCalNow;
        } catch (ParseException ex) {
            objCalNow.setTime(new Date());
            return objCalNow;
        }
    }

    /**
     * Convirte el valor de Latitud o Longitud enviado por el Equipo SKP+ al
     * formato de la BD.
     *
     * @param latLon
     * @param cuad
     * @return
     */
    public double convertLatLonSkp(String latLon, String cuad) {
        Double finalRta;
        String enteros = latLon.substring(0, latLon.lastIndexOf("."));
        double segundos = Double.parseDouble(latLon) - Integer.parseInt(enteros);
        int minutos = Integer.valueOf(enteros.substring(enteros.length() - 2, enteros.length()));
        int horas = Integer.valueOf(enteros.substring(0, enteros.length() - 2));

        finalRta = horas + ((double) minutos / 60) + ((double) segundos / 60);

        //N or S
        if (cuad.equals("S") || cuad.equals("W")) {
            return finalRta * -1;
        } else {
            return finalRta;
        }
    }

    /**
     * Convirte el valor de Latitud o Longitud enviado por el Equipo Fastrack al
     * formato de la BD.
     *
     * @param latLon
     * @param cuad
     * @return
     */
    public double convertLatLonFastrack(String latLon, String cuad) {
        double grados;
        //W or S
        if (cuad.equals("S") || cuad.equals("W")) {
            if (cuad.equals("S")) {
                grados = Double.parseDouble(latLon.substring(0, 2));
                latLon = latLon.substring(2);
            } else {
                grados = Double.parseDouble(latLon.substring(0, 3));
                latLon = latLon.substring(3);
            }

            Double minutos = Double.parseDouble(latLon.substring(0, 2) + "." + latLon.substring(2));
            minutos = grados + (minutos / 60);

            return minutos * -1;
        } else {
            return 0;
        }
    }

    public String[] repairIp(String ipLlega) {
        String ip[] = new String[2];
        ip[0] = "";
        ip[1] = "";
        String parametros[] = ipLlega.split("\\.");
        for (int i = 0; i < 4; i++) {
            if (i == 3) {
                int pos = 3;
                String aux = parametros[i];
                parametros[i] = parametros[i].substring(0, 3);
                if (aux.charAt(2) == '0' && aux.charAt(3) == '0') {
                    parametros[i] = aux.substring(0, 2);
                    pos = 2;
                }
                if (aux.charAt(1) == '0' && aux.charAt(2) == '0') {
                    parametros[i] = aux.substring(0, 1);
                    pos = 1;
                }
                if (Integer.parseInt(parametros[i]) > 255) {
                    parametros[i] = parametros[i].substring(0, 2);
                    pos = 2;
                }
                ip[1] = aux.substring(pos);
                ip[0] += parametros[i];

            } else {
                ip[0] += parametros[i] + ".";
            }
        }
        return ip;
    }

    public void sendToFile(int typeData, String type, String data) {
        FileWriter f = null;
        try {
            String dateNow = formatoFechaSave.format(new Date());
            String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            f = new FileWriter(Gui.getCpdb().getPath() + type + "_" + dateNow + ".txt", true);
            PrintWriter pw = new PrintWriter(f);
            pw.println("[" + typeData + "][" + now + "][" + data + "]");
        } catch (IOException ex) {
            System.out.println("Falla o Interrumpcion en Operationes I/O [" + ex.getMessage() + "].");
        } finally {
            try {
                if (f != null) {
                    f.close();
                }
            } catch (IOException ex) {
                System.out.println("Falla o Interrumpcion en Operationes I/O [" + ex.getMessage() + "].");
            }
        }
    }

    public void executeProcedureExcesoVelocidades(int idVehicle, double speed) {
//        EntityManager emAux = Gui.getCpdb().choosePersistenceHistoricOpen().createEntityManager();
//        emAux.getTransaction().begin();
//        Query q = emAux.createNativeQuery("call sp_exceso_velocidad(?, ?)");
//        q.setParameter(1, idVehicle);
//        q.setParameter(2, speed);
//        q.executeUpdate();
//        emAux.getTransaction().commit();
//        emAux.close();
    }

//    public void executeProcedurePapeletaDespachos(int idVehicle, int idPoint, Date dateTime, double speed) {
////        EntityManager emAux = Gui.getCpdb().choosePersistenceHistoricOpen().createEntityManager();
////        emAux.getTransaction().begin();
////        Query q = emAux.createNativeQuery("call sp_papeleta_despachos(?, -1, ?, ?, ?, ?, -1, 0)");
////        q.setParameter(1, idVehicle);
////        q.setParameter(2, idPoint);
////        q.setParameter(3, dateTime);
////        q.setParameter(4, dateTime);
////        q.setParameter(5, speed);
////        q.executeUpdate();
////        emAux.getTransaction().commit();
////        emAux.close();
//    }

    public void executeProcedureAsignarRutaSkp(int idVehicle, Date dateTime) {
//        EntityManager emAux = Gui.getCpdb().choosePersistenceHistoricOpen().createEntityManager();
//        emAux.getTransaction().begin();
//        Query q = emAux.createNativeQuery("call sp_asignar_ruta_skp(?, ?)");
//        q.setParameter(1, idVehicle);
//        q.setParameter(2, dateTime);
//        q.executeUpdate();
//        emAux.getTransaction().commit();
//        emAux.close();
    }
    
    
    
     public static boolean pnpoly(int nvert, double[] vert_x, double[] vert_y, double latitud, double longitud) {        
        int i, j;
        boolean c = false;
        for (i = 0, j = nvert - 1; i < nvert; j = i++) {
            if (((vert_y[i] > longitud) != (vert_y[j] > longitud)) && (latitud < (vert_x[j] - vert_x[i])
                    * (longitud - vert_y[i])
                    / (vert_y[j] - vert_y[i])
                    + vert_x[i])) {
                c = !c;
            }
        }
        return c;
    }
    
}
