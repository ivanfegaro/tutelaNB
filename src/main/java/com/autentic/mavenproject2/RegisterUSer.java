/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autentic.mavenproject2;

import com.autentic.mavenproject2.Exceptions.confirmEmailException;
import com.autentic.mavenproject2.Exceptions.confirmUserException;
import datosmysql.Datosusuario;
import interfaces.DatosUsuarioRepository;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.transaction.Transactional;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ivanf
 */
@ManagedBean
@ViewScoped

public class RegisterUSer implements Serializable {
    
    private boolean term;
    private String user = null;
    private int document;
    private String email;
    private String confirmEmail = null;
    private String Password= null;
    private String confirmPassword = null;
    private int ramdonNumber = 0;
    
    @Autowired(required = true)
    DatosUsuarioRepository DUR;

    public int getRamdonNumber() {
        return ramdonNumber;
    }

    public void setRamdonNumber(int ramdonNumber) {
        this.ramdonNumber = ramdonNumber;
    }
    
    

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isTerm() {
        return term;
    }

    public void setTerm(boolean term) {
        this.term = term;
    }
    
    public String registerUSer() throws confirmUserException,confirmEmailException  {
        
        //
        
        
       boolean regexEmail = this.validateEmail(email);
       
        boolean ifError = false;
        String error = null;        
        if (!user.equals("")){
             if (email.equals(confirmEmail)){ 
                if (Password.equals(confirmPassword)){
                    if (email.equals(confirmEmail)){
                        if (Boolean.TRUE.equals(term)){
                            if (!regexEmail){

                                 //this.enviarCorreo();
                                 this.addUser();
                                 
                            } else{
                                return error = "ingrese un correo valido";
                            }
                    }else{
                       return error = "el correo debe no debe ir vacio";  
                     }  
                    }
                    else {
                        return error = "Debe Aceptar los terminos y condiciones";
                    } 
            }else{
                return error = "confirmar password";
            }  
        }else {
            return error = "Confirmar Email";
        }   
        }else {
            return error = "El usuario no puede ir vacio";
        }
        return error;
    }
    
    public void enviarCorreo(){
        
        try{
            Random rnd = new Random();
            ramdonNumber = rnd.nextInt(90000)+10000;

            String host ="smtp.gmail.com" ;
            String user = "ivanfegaro@gmail.com";
            String pass = "fnwrsnxgjxadllbj";
            String to = email;
            String from = "ivanfegaro@gmail.com";
            String subject = "Clave de confirmacion";
            String messageText = "Por favor inserte este codigo para activar su Cuenta:" + ramdonNumber;
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

            Transport transport=mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        System.out.println(matcher.find());
        return matcher.find();
        
}
    
    @Autowired
    public void addUser(){
        Datosusuario usuario;
        usuario = new Datosusuario( Integer.toString(document) , email , 11 , Password, user);
        try{
            Datosusuario save = DUR.save(usuario);
        
        }catch(Exception e){
            System.out.println(e);
        }
    }    
    
}
