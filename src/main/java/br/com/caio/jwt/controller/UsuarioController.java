package br.com.caio.jwt.controller;

import br.com.caio.jwt.model.Usuario;
import br.com.caio.jwt.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAllUsers(){
        return ResponseEntity.ok(usuarioService.getAllUsers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> findUsuarioById(@PathVariable Integer id){
        Usuario user = usuarioService.getUsuarioById(id);
        if (user != null){
            return ResponseEntity.ok(user);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario, UriComponentsBuilder builder){
        Usuario newUser = usuarioService.createUser(usuario);
        URI uri = builder.path("/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uri).body(newUser);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id){
        if (usuarioService.deleteUser(id)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id,@RequestBody Usuario usuario,UriComponentsBuilder builder){
        Usuario updatedUser = usuarioService.updateUsuario(id,usuario);

        if (updatedUser != null){
            URI uri = builder.path("/{id}").buildAndExpand(updatedUser.getId()).toUri();
            return ResponseEntity.created(uri).body(updatedUser);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
