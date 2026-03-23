/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import response.UsuarioResponse;

/**
 *
 * @author isaac
 */
public interface IServicioUsuarios {
     
    public UsuarioResponse login(String email, String password);
}
