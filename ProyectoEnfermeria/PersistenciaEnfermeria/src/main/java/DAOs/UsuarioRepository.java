/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOs;

import entidades.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author rodri
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    
    Optional<Usuario> findByEmail(String email);
}
