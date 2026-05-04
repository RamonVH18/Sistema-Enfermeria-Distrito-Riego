/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package utilerias;

import controladores.MenuPrincipalController;

/**
 *
 * @author isaac
 */

// esta interfaz sirve para que todos los demas controllers sean hijos de MenuPrincipal
public interface ParentAware {
    
    void setMenuPrincipal(MenuPrincipalController main);
    
}
