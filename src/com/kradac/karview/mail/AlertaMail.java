/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kradac.karview.mail;

import com.kradac.karview.window.Gui;
import com.kradac.karview.window.Utilities;

/**
 *
 * @author kradac
 */
public class AlertaMail extends Thread {

    private final String mailPara;
    private final String titulo;
    private final StringBuilder mensaje;
    private final String firm = "Att.<br><br><b>KRADAC CIA. LTDA.</b><br><b>Direccion:</b><br><b>Matriz Loja:</b> Paris s/n y Santiago de las montañas<br><b>Sucursal Quito:</b> Quitus, entre Princesa de Toa y Eplicachima<br><b>Emails:</b>	info@kradac.com ; soporte@kradac.com<br><b>Teléf.:</b>	072570275 opc: 3; ext: 2980 <br><center>www.kradac.com</center>";

    /**
     * Envia un email con la informacion del sensor al contacto que se
     * especifica
     *
     * @param station
     * @param tipoSen
     * @param valor
     * @param parMin
     * @param parMax
     * @param mailPara
     * @param nombre
     */
    
    
    public AlertaMail(String station,String tipoSen,String valor,double parMin,double parMax,String mailPara,String nombre) {
        this.mailPara = mailPara;
        this.titulo = "Alerta!!! Sensor [" + tipoSen + ": " + valor + "] : " + station + " : " + Utilities.getDate() + " " + Utilities.getTime();
        mensaje = new StringBuilder();
        mensaje.append(nombre).append(",<br><br>");
        mensaje.append("Le informamos que hay un sensor fuera de los limites permitidos:<br><br>");
        mensaje.append("<b>Estación: </b>").append(station).append("<br>");

        mensaje.append("<b>Mensaje:</b><br>");
        mensaje.append("Este sensor de ").append(tipoSen).append(" ha registrado la siguiente lectura:").
                append("<h1>").append(valor).append("</h1>").
                append("Los limites para este sensor son: MIN[<b>").append(parMin).append("</b>] y MAX[<b>").append(parMax).append("</b>]<br><br>");
        mensaje.append("Fecha: ").append(Utilities.getDate()).append("<br>");
        mensaje.append("Hora: ").append(Utilities.getTime()).append("<br><br><br>");
        mensaje.append(firm);
    }

    ///Constructor para el envio de eventos
    public AlertaMail(String equipo, String mailPara, String event, String message, String person) {
        this.mailPara = mailPara;
        this.titulo = "Alerta!!! Evento [" + event + "] : " + Utilities.getDate() + " " + Utilities.getTime();
        mensaje = new StringBuilder();
        mensaje.append(person).append(",<br><br>");
        mensaje.append("Le informamos que se ha producido un evento.<br><br>");
        mensaje.append("<b>Vehiculo: </b>").append(equipo).append("<br>");

        mensaje.append("<b>Mensaje:<b><br>");
        mensaje.append(message);

        mensaje.append("<br><br>Fecha: ").append(Utilities.getDate());
        mensaje.append("<br>Hora: ").append(Utilities.getTime()).append("<br><br><br>");
        mensaje.append(firm);
    }
    
    ///Constructor para el envio de Geocercas
    public AlertaMail(String equipo, String mailPara, String estadoGEocerca, String message, String person, String tipoServicio) {
        this.mailPara = mailPara;
        this.titulo = "Alerta!!! Evento de Geocerca [" + estadoGEocerca + "] : " + Utilities.getDate() + " " + Utilities.getTime();
        mensaje = new StringBuilder();
        mensaje.append(person).append(",<br><br>");
        mensaje.append("Le informamos que su Vehículo a ejcutado la Siguiente Acción.<br><br>");
        mensaje.append("<b>Vehiculo: </b>").append(equipo).append("<br>");

        mensaje.append("<b>Mensaje:<b><br>");
        mensaje.append(message);

        mensaje.append("<br><br>Fecha: ").append(Utilities.getDate());
        mensaje.append("<br>Hora: ").append(Utilities.getTime()).append("<br><br><br>");
        mensaje.append(firm);
    }

    public AlertaMail(int count) {
        this.mailPara = "dalton.agila@kradac.com";
        this.titulo = "Alerta!!! " + Gui.getCpdb().getCity() + " [Limite de Conexiones con BD superada: " + count + "] : " + Utilities.getDate() + " " + Utilities.getTime();

        mensaje = new StringBuilder();
        mensaje.append("Dalton Adrian Agila Espinosa").append(",<br><br>");
        mensaje.append("Le informamos que el Servidor K-Bus ha superado el limite de Conexiones con la BD.<br><br>");

        mensaje.append("<br><br>Fecha: ").append(Utilities.getDate());
        mensaje.append("<br>Hora: ").append(Utilities.getTime()).append("<br><br><br>");
        mensaje.append(firm);
    }

    public AlertaMail(String mailPara, String asunto, String messenge) {
        this.mailPara = mailPara;
        this.titulo = "Alerta!!! " + Gui.getCpdb().getCity()+" [" + asunto + "] : " + Utilities.getDate() + " " + Utilities.getTime();

        mensaje = new StringBuilder();
        mensaje.append("Dalton Adrian Agila Espinosa").append(",<br><br>");
        mensaje.append(messenge);

        mensaje.append("<br><br>Fecha: ").append(Utilities.getDate());
        mensaje.append("<br>Hora: ").append(Utilities.getTime()).append("<br><br><br>");
        mensaje.append(firm);
    }

    @Override
    public void run() {
        SMTPConfig.sendMail(titulo, mensaje.toString(), mailPara);
    }
}
