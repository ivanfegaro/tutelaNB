/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autentic.mavenproject2;

import com.sun.faces.taglib.html_basic.InputFileTag;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ivanf
 */
@ManagedBean
@ViewScoped
public class FileUploadBean implements Serializable{

   
    private final String root_folder="C:\\Users\\ivanf\\Documents\\NetBeansProjects\\mavenproject2\\src\\main\\webapp\\images\\";
    private UploadedFile file;
    
    public void fileUploadListener(FileUploadEvent e){
        this.file = e.getFile();
        try{
            copyFile(file.getFileName(),file.getInputstream());    
        }catch(IOException er){
            System.out.println(er);
        }
        
    }
    
    public void copyFile(String name, InputStream in){
        try{
            OutputStream out = new FileOutputStream(new File (root_folder+ name));
            int read = 0;
            byte [] bytes = new byte [1024];
            while((read = in.read(bytes)) !=-1){
                out.write (bytes,0,read);
            }
            in.close();
            out.flush();
            out.close();
            
            addMessage("Info", "File uploaded !");
            
        }catch(IOException er){
            System.out.println(er);
        }
        
    
}
    
    private void addMessage(String summary,String detail){
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
        
    }
    
    
     public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    
}
