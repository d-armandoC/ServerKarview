/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kradac.karview.mail;

import java.security.Security;
import java.util.Properties;


/**
 *
 * @author Dalton
 */
public class SMTPConfig {

    /**
     * @param titulo : titulo del mensaje
     * @param mensaje : Cuerpo del Mensaje
     * @param paraEmail : Email receptor del mensaje
     * @return true si el envío es conforme y false si no es así.
     */
    public static synchronized boolean sendMail(String titulo, String mensaje, String paraEmail) {

        boolean envio = false;

//        try {
//        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        //Propiedades de la conexion
//        Properties propiedades = new Properties();

//            propiedades.setProperty("mail.transport.protocol", Gui.getCpdb().getCjc().findConfiguracionesByNombre("mail.transport.protocol").getValor());
//            propiedades.setProperty("mail.host", Gui.getCpdb().getCjc().findConfiguracionesByNombre("mail.host").getValor());
//            propiedades.put("mail.smtp.auth", Gui.getCpdb().getCjc().findConfiguracionesByNombre("mail.smtp.auth").getValor());
//            propiedades.put("mail.smtp.port", Gui.getCpdb().getCjc().findConfiguracionesByNombre("mail.smtp.port").getValor());
//            propiedades.put("mail.smtp.socketFactory.fallback", Gui.getCpdb().getCjc().findConfiguracionesByNombre("mail.smtp.socketFactory.fallback").getValor());
//            propiedades.put("mail.smtp.mail.sender", Gui.getCpdb().getCjc().findConfiguracionesByNombre("mail.smtp.mail.sender").getValor());
//            propiedades.put("mail.smtp.ssl.enable", Gui.getCpdb().getCjc().findConfiguracionesByNombre("mail.smtp.ssl.enable").getValor());
//            propiedades.setProperty("mail.smtp.quitwait", Gui.getCpdb().getCjc().findConfiguracionesByNombre("mail.smtp.quitwait").getValor());
        //Preparamos la Sesion autenticando al usuario
//        Session session = Session.getDefaultInstance(propiedades, new javax.mail.Authenticator() {

//                @Override
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(Gui.getCpdb().getCjc().findConfiguracionesByNombre("mail.user").getValor(), Gui.getCpdb().getCjc().findConfiguracionesByNombre("mail.password").getValor());
//                }
//            });
            //Preparamos el Mensaje
//            MimeMessage message = new MimeMessage(session);
//            message.setSender(new InternetAddress(Gui.getCpdb().getCjc().findConfiguracionesByNombre("mail.email").getValor()));
//            message.setSubject(titulo);
//            message.setContent(mensaje, "text/html; charset=utf-8");
//            message.setFrom(new InternetAddress(Gui.getCpdb().getCjc().findConfiguracionesByNombre("mail.smtp.mail.sender").getValor()));
//            message.setReplyTo(InternetAddress.parse(Gui.getCpdb().getCjc().findConfiguracionesByNombre("mail.smtp.mail.sender").getValor()));
//
//            if (paraEmail.indexOf(',') > 0) {
//                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(paraEmail));
//            } else {
//                message.setRecipient(Message.RecipientType.TO, new InternetAddress(paraEmail));
//            }
//
//            //envío del mensaje
//            Transport.send(message);
//            envio = true;
//
//        } catch (Throwable e) {
//            envio = false;
//            System.out.println(e.getMessage());
//            //e.printStackTrace();
//        } finally {
//            return envio;
//        }
//        }
//                return true;
//    }
        return false;
}
}
