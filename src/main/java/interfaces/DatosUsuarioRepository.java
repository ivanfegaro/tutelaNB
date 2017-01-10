/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import datosmysql.Datosusuario;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ivanf
 */
@Repository
public interface DatosUsuarioRepository extends CrudRepository <Datosusuario,Long> {
    
    List <Datosusuario> findByCedula (int cedula);
    List <Datosusuario> findByCorreo (String correo);
    
    
    
}
