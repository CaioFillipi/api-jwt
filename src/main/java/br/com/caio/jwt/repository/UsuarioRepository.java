package br.com.caio.jwt.repository;

import br.com.caio.jwt.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    public Optional<Usuario> findUsuarioByLogin(String login);

}
