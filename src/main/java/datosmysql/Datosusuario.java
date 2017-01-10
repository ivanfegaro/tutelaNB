/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datosmysql;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ivanf
 */
@Entity
@Table(name = "datosusuario")
@NamedQueries({
    @NamedQuery(name = "Datosusuario.findAll", query = "SELECT d FROM Datosusuario d")
    , @NamedQuery(name = "Datosusuario.findByIdDatosUsuario", query = "SELECT d FROM Datosusuario d WHERE d.idDatosUsuario = :idDatosUsuario")
    , @NamedQuery(name = "Datosusuario.findByNombreUsuario", query = "SELECT d FROM Datosusuario d WHERE d.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "Datosusuario.findByCedula", query = "SELECT d FROM Datosusuario d WHERE d.cedula = :cedula")
    , @NamedQuery(name = "Datosusuario.findByDireccion", query = "SELECT d FROM Datosusuario d WHERE d.direccion = :direccion")
    , @NamedQuery(name = "Datosusuario.findByCelular", query = "SELECT d FROM Datosusuario d WHERE d.celular = :celular")
    , @NamedQuery(name = "Datosusuario.findByCorreo", query = "SELECT d FROM Datosusuario d WHERE d.correo = :correo")
    , @NamedQuery(name = "Datosusuario.findByCodigoConfirmacion", query = "SELECT d FROM Datosusuario d WHERE d.codigoConfirmacion = :codigoConfirmacion")
    , @NamedQuery(name = "Datosusuario.findByUserPass", query = "SELECT d FROM Datosusuario d WHERE d.userPass = :userPass")
    , @NamedQuery(name = "Datosusuario.findByUserLogin", query = "SELECT d FROM Datosusuario d WHERE d.userLogin = :userLogin")})
public class Datosusuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_datos_usuario", unique=true, nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDatosUsuario;
    @Size(max = 20)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Size(max = 20)
    @Column(name = "cedula")
    private String cedula;
    @Size(max = 30)
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "celular")
    private Integer celular;
    @Size(max = 20)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CodigoConfirmacion")
    private int codigoConfirmacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "user_pass")
    private String userPass;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "user_login")
    private String userLogin;

    public Datosusuario() {
    }

    public Datosusuario(Long idDatosUsuario) {
        this.idDatosUsuario = idDatosUsuario;
    }

    public Datosusuario( String cedula,String correo,  int codigoConfirmacion, String userPass, String userLogin) {
        
        this.cedula = cedula;
        this.correo = correo;
        this.codigoConfirmacion = codigoConfirmacion;
        this.userPass = userPass;
        this.userLogin = userLogin;
    }

   

    public Long getIdDatosUsuario() {
        return idDatosUsuario;
    }

    public void setIdDatosUsuario(Long idDatosUsuario) {
        this.idDatosUsuario = idDatosUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCelular() {
        return celular;
    }

    public void setCelular(Integer celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getCodigoConfirmacion() {
        return codigoConfirmacion;
    }

    public void setCodigoConfirmacion(int codigoConfirmacion) {
        this.codigoConfirmacion = codigoConfirmacion;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDatosUsuario != null ? idDatosUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datosusuario)) {
            return false;
        }
        Datosusuario other = (Datosusuario) object;
        if ((this.idDatosUsuario == null && other.idDatosUsuario != null) || (this.idDatosUsuario != null && !this.idDatosUsuario.equals(other.idDatosUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datosmysql.Datosusuario[ idDatosUsuario=" + idDatosUsuario + " ]";
    }
    
}
