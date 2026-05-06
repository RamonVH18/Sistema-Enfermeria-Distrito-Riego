package repositorios;

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