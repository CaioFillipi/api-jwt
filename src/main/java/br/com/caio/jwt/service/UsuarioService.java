package br.com.caio.jwt.service;

import br.com.caio.jwt.model.Usuario;
import br.com.caio.jwt.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    public List<Usuario> getAllUsers(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Integer id){
        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isPresent()){
            return user.get();
        }
        return null;
    }

    public Usuario getUsuarioByLogin(String login){
        Optional<Usuario> usuario = usuarioRepository.findUsuarioByLogin(login);
        if(usuario.isPresent()){
            return  usuario.get();
        }
        return null;
    }

    public boolean loginUser(String login,String password){
        Usuario user = getUsuarioByLogin(login);
        if(user != null){
            boolean valid = encoder.matches(password,user.getPassword());
            return valid;
        }
        return false;
    }

    public Usuario createUser(Usuario usuario){
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public boolean deleteUser(Integer id){
        Usuario user = getUsuarioById(id);
        if(user != null) {
            usuarioRepository.delete(user);
            return true;
        }
        return false;
    }

    public Usuario updateUsuario(Integer id,Usuario user){
        Usuario updatedUser = getUsuarioById(id);

        if(updatedUser != null){
            updatedUser.setLogin(user.getLogin());
            updatedUser.setPassword(encoder.encode(user.getPassword()));
            updatedUser.setId(updatedUser.getId());

            updatedUser = createUser(updatedUser);

            return updatedUser;
        }
        return null;
    }

}
